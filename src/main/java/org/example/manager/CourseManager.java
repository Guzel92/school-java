package org.example.manager;

import lombok.RequiredArgsConstructor;
import org.example.dto.CourseGetAllResponseDTO;
import org.example.dto.CourseGetByIdResponseDTO;
import org.example.exception.CourseNotFoundException;
import org.example.model.CourseModel;
import org.example.rowmapper.CourseRowMapper;
import org.springframework.dao.EmptyResultDataAccessException;
import org.springframework.jdbc.core.ResultSetExtractor;
import org.springframework.jdbc.core.namedparam.NamedParameterJdbcTemplate;
import org.springframework.stereotype.Component;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

@Component
@RequiredArgsConstructor
public class CourseManager {
private final NamedParameterJdbcTemplate template;
    private final CourseRowMapper courseRowMapper;

    public CourseGetAllResponseDTO getAll() {
        final List<CourseModel> items = template.query(
//language=PostgreSQL
                """
                        SELECT id,name,price,numberseats,image FROM courses
                        WHERE removed=FALSE
                        ORDER BY id
                        LIMIT 500
                        """,
                courseRowMapper
        );
        final CourseGetAllResponseDTO responseDTO = new CourseGetAllResponseDTO(new ArrayList<>(items.size()));
        for (CourseModel item : items) {
            responseDTO.getCourses().add(new CourseGetAllResponseDTO.Course(
                    item.getId(),
                    item.getName(),
                    item.getPrice(),
                    item.getImage(),
                    item.getNumberseats()
            ));
        }
        return responseDTO;
    }

    public CourseGetByIdResponseDTO getById(long id) {
        try {
            final CourseModel item = template.queryForObject(
                    //language=PostgreSQL
                    """
                        SELECT id,name,price,numberseats,image FROM courses
                        WHERE id = :id AND removed=FALSE
                        """,
                Map.of("id", id),
                courseRowMapper
        );
        final CourseGetByIdResponseDTO responseDTO =
                new CourseGetByIdResponseDTO(new CourseGetByIdResponseDTO.Course(
                item.getId(),
                item.getName(),
                item.getPrice(),
                item.getImage(),
                item.getNumberseats()
        ));
        return responseDTO;
        }catch (EmptyResultDataAccessException e){
            throw new CourseNotFoundException(e);
        }
    }
}

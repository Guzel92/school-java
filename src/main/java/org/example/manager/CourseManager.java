package org.example.manager;

import lombok.RequiredArgsConstructor;
import org.example.dto.CourseGetAllResponseDTO;
import org.example.dto.CourseGetByIdResponseDTO;
import org.example.dto.CourseSaveRequestDTO;
import org.example.dto.CourseSaveResponseDTO;
import org.example.exception.CourseNotFoundException;
import org.example.model.CourseModel;
import org.example.rowmapper.CourseRowMapper;
import org.springframework.dao.EmptyResultDataAccessException;
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
    private final String defaultImage = "noimage.jpg";

    public CourseGetAllResponseDTO getAll() {
        final List<CourseModel> items = template.query(
                //language=PostgreSQL
                """
                        SELECT id, name, price,image,numberseats FROM courses
                        WHERE removed=FALSE
                        ORDER BY id
                        LIMIT 500
                        """,
                courseRowMapper
        );
        final CourseGetAllResponseDTO responseDTO =
                new CourseGetAllResponseDTO(new ArrayList<>(items.size()));
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
                            SELECT id, name, price,image,numberseats FROM courses
                            WHERE id=:id AND removed=FALSE
                            """,
                    Map.of("id",id),
                    courseRowMapper
            );
            final CourseGetByIdResponseDTO responseDTO = new CourseGetByIdResponseDTO(
                    new CourseGetByIdResponseDTO.Course(
                            item.getId(),

                            item.getName(),
                            item.getPrice(),
                            item.getImage(),
                            item.getNumberseats()
                    ));
            return responseDTO;
        } catch (EmptyResultDataAccessException e) {
            throw new CourseNotFoundException(e);
        }
    }

    public CourseSaveResponseDTO save(CourseSaveRequestDTO requestDTO) {
        return requestDTO.getId() == 0 ? create(requestDTO) : update(requestDTO);
    }


    private CourseSaveResponseDTO create(CourseSaveRequestDTO requestDTO) {
        final CourseModel item = template.queryForObject(
                //language=PostgreSQL
                """
                        INSERT INTO courses(  name, price, image,numberseats) VALUES (:name, :price, :image,:numberseats)
                        RETURNING id,name,price, image,numberseats       
                                               """,
                Map.of (
                        "name", requestDTO.getName(),
                        "price", requestDTO.getPrice(),
                        "image", getImage(requestDTO.getImage()),
                        "numberseats", requestDTO.getNumberseats()
                ),
                courseRowMapper
        );
        final CourseSaveResponseDTO responseDTO = new CourseSaveResponseDTO(new CourseSaveResponseDTO.Course(
                item.getId(),
                item.getName(),
                item.getPrice(),
                item.getImage(),
                item.getNumberseats()
        ));
        return responseDTO;
    }
    public void removeById(long id) {
        final int affected = template.update(
                //language=PostgreSQL
                """
                        UPDATE courses SET removed=TRUE WHERE id =:id
                        """,
                Map.of("id", id)
        );
        if (affected==0){
            throw new CourseNotFoundException("course with id" + id + "not found");
        }
    }

    private CourseSaveResponseDTO update(CourseSaveRequestDTO requestDTO) {
        try {
            final CourseModel item = template.queryForObject(
                    //language=PostgreSQL
                    """
                            UPDATE courses SET name= :name,price= :price, image=:image, numberseats=:numberseats
                            WHERE id=:id AND removed=FALSE
                            RETURNING id,name,price, image, numberseats       
                                                   """,
                    Map.of(
                            "id", requestDTO.getId(),
                            "name", requestDTO.getName(),
                            "price", requestDTO.getPrice(),
                            "image", getImage(requestDTO.getImage()),
                            "numberseats", requestDTO.getNumberseats()
                    ),
                    courseRowMapper
            );
            final CourseSaveResponseDTO responseDTO = new CourseSaveResponseDTO(new CourseSaveResponseDTO.Course(
                    item.getId(),
                    item.getName(),
                    item.getPrice(),
                    item.getImage(),
                    item.getNumberseats()
            ));
            return responseDTO;
        } catch (EmptyResultDataAccessException e) {
            throw new CourseNotFoundException(e);
        }
    }

    private String getImage(String image) {
        return image == null ? defaultImage : image;
    }


}
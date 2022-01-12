package org.example.manager;

import lombok.RequiredArgsConstructor;
import org.example.dto.StatGetSumResponseDTO;
import org.example.dto.StatResponseDTO;
import org.example.model.StatModel;
import org.example.rowmapper.StatRowMapper;
import org.springframework.jdbc.core.namedparam.NamedParameterJdbcTemplate;
import org.springframework.stereotype.Component;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

@Component
@RequiredArgsConstructor
public class StatManager {
    private final NamedParameterJdbcTemplate template;
    private final StatRowMapper statRowMapper;

    public StatResponseDTO getAll() {
        final List<StatModel> items = template.query(
                //language=PostgreSQL
                """
                        SELECT id,course_id,register_id, name, price,qty FROM register_positions
                        WHERE name='JavaScript'
                        ORDER BY id
                        LIMIT 500;
                       
                      
                        """,
                statRowMapper
        );
        final StatResponseDTO statResponseDTO = new StatResponseDTO(new ArrayList<>(items.size()));
        for (StatModel item : items) {
            statResponseDTO.getPositions().add(new StatResponseDTO.Register(
                    item.getId(),
                    item.getCourseId(),
                    item.getRegisterId(),
                    item.getName(),
                    item.getPrice(),
                    item.getQty()
            ));
        }
        return statResponseDTO;
    }

    public StatGetSumResponseDTO getSum(long courseId) {
        int sum = template.queryForObject(
                //language=PostgreSQL
                """
SELECT sum(qty) FROM register_positions
""",

                Map.of("id",courseId),
                Integer.class
        );

        List<StatModel> models =template.query(
                //language=PostgreSQL
                """
SELECT id,course_id,register_id,name,price,qty FROM register_positions
WHERE id= :id

""",
               Map.of("id",courseId),
                statRowMapper);
       final StatGetSumResponseDTO statGetSumResponseDTO = new StatGetSumResponseDTO(new ArrayList<>(models.size()));
       for (StatModel item : models) {
           statGetSumResponseDTO.getPositions().add(new StatGetSumResponseDTO.Register(
                   item.getId(),
                   item.getCourseId(),
                  item.getRegisterId(),
                   item.getName(),
                   item.getPrice(),
                   item.getQty()
           ));
        }
         return statGetSumResponseDTO;
    }
}


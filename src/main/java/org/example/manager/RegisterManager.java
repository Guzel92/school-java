package org.example.manager;

import lombok.RequiredArgsConstructor;
import org.example.dto.*;
import org.example.exception.RegisterNotFoundException;
import org.example.model.RegisterModel;
import org.example.rowmapper.RegisterRowMapper;
import org.springframework.dao.EmptyResultDataAccessException;
import org.springframework.jdbc.core.namedparam.NamedParameterJdbcTemplate;
import org.springframework.stereotype.Component;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

@Component
@RequiredArgsConstructor
public class RegisterManager {
    private final NamedParameterJdbcTemplate template;
    private final RegisterRowMapper registerRowMapper;

    public RegisterResponseDTO getAll() {
        final List<RegisterModel> items = template.query(
                //language=PostgreSQL
                """
                        SELECT id,name,price,customer_name FROM registrations
                        WHERE confirmed=TRUE
                        ORDER BY id
                        LIMIT 500
                        """,
                registerRowMapper
        );
        final RegisterResponseDTO responseDTO = new RegisterResponseDTO(new ArrayList<>(items.size()));
        for (RegisterModel item : items) {
            responseDTO.getRegistrations().add(new RegisterResponseDTO.Registration(
                    item.getId(),
                    item.getName(),
                    item.getPrice(),
                    item.getCustomerName()
            ));
        }
        return responseDTO;
    }

    public RegisterGetByIdResponseDTO getById(long id) {
        try {
            final RegisterModel item = template.queryForObject(
                    //language=PostgreSQL
                    """
                        SELECT id,name, price,customer_name FROM registrations
                        WHERE id= :id AND confirmed=TRUE
                        """,
                Map.of("id", id),
                registerRowMapper
        );
        final RegisterGetByIdResponseDTO responseDTO = new RegisterGetByIdResponseDTO(new RegisterGetByIdResponseDTO.Registration(
                item.getId(),
                item.getName(),
                item.getPrice(),
                item.getCustomerName()
        ));
        return responseDTO;
        }catch (EmptyResultDataAccessException e){
throw new RegisterNotFoundException(e);
        }

    }

    public RegisterSaveResponseDTO save(RegisterSaveRequestDTO requestDTO) {
        return requestDTO.getId() == 0 ? create(requestDTO) : update(requestDTO);
    }

    private RegisterSaveResponseDTO create(RegisterSaveRequestDTO requestDTO) {
        final RegisterModel item = template.queryForObject(
                //language=PostgreSQL
                """
INSERT INTO registrations ( name, price, customer_name) VALUES (:name,:price,:customer_name)
RETURNING id,name,price,customer_name
""",
                Map.of(
                        "name", requestDTO.getName(),
                        "price",requestDTO.getPrice(),
                        "customer_name",requestDTO.getCustomerName()
                ),
                registerRowMapper
        );
        final RegisterSaveResponseDTO responseDTO = new RegisterSaveResponseDTO(new RegisterSaveResponseDTO.Registration(
                item.getId(),
                item.getName(),
                item.getPrice(),
                item.getCustomerName()
        ));
        return responseDTO;
    }
    private RegisterSaveResponseDTO update(RegisterSaveRequestDTO requestDTO) {
        try {
            final RegisterModel item = template.queryForObject(
                    //language=PostgreSQL
                    """
UPDATE registrations SET id= :id, name= :name, price= :price, customer_name= :customer_name
WHERE id= :id AND confirmed=TRUE
RETURNING id,name,price,customer_name
""",
                    Map.of(
                            "id",requestDTO.getId(),
                            "name", requestDTO.getName(),
                            "price", requestDTO.getPrice(),
                            "customer_name", requestDTO.getCustomerName()
                    ),
                    registerRowMapper
            );
            final RegisterSaveResponseDTO responseDTO = new RegisterSaveResponseDTO(new RegisterSaveResponseDTO.Registration(
                    item.getId(),
                    item.getName(),
                    item.getPrice(),
                    item.getCustomerName()
            ));
            return responseDTO;
        }catch(EmptyResultDataAccessException e) {
            throw new RegisterNotFoundException(e);
        }
}

    public ParticipantsDTO getParticipants(long id) {
        int numberseats = template.queryForObject(
                //language=PostgreSQL
                """
                    SELECT numberseats FROM courses
                    WHERE id=:id
                    """,
                Map.of("id",id),
                Integer.class
        );

        List<RegisterModel> models = template.query(
                //language=PostgreSQL
                """
                    SELECT id,name,price,customer_name FROM registrations
                    WHERE confirmed=TRUE AND id= :id
                    ORDER BY created
                    LIMIT :limit;
                    """,
                Map.of(
                        "id",id,
                        "limit",numberseats),
                registerRowMapper);

        final ParticipantsDTO participantsDTO = new ParticipantsDTO(new ArrayList<>(models.size()));
        for (RegisterModel item : models) {
            participantsDTO.getParticipants().add(new ParticipantsDTO.Participants(
                    item.getId(),
                    item.getName(),
                    item.getPrice(),
                    item.getCustomerName()
            ));

        }
        return participantsDTO;
    }
}

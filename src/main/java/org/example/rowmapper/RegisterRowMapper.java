package org.example.rowmapper;

import org.example.model.RegisterModel;
import org.springframework.jdbc.core.RowMapper;
import org.springframework.stereotype.Component;

import java.sql.ResultSet;
import java.sql.SQLException;

@Component
public class RegisterRowMapper implements RowMapper<RegisterModel> {

    @Override
    public RegisterModel mapRow(ResultSet rs, int rowNum) throws SQLException {
        return new RegisterModel(

                rs.getLong("id"),
                rs.getString("name"),
                rs.getInt("price"),
                rs.getString("customer_name")
        );
    }
}


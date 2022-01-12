package org.example.rowmapper;

import org.example.model.StatModel;
import org.springframework.jdbc.core.RowMapper;
import org.springframework.stereotype.Component;

import java.sql.ResultSet;
import java.sql.SQLException;

@Component
public class StatRowMapper implements RowMapper<StatModel> {


    @Override
    public StatModel mapRow(ResultSet rs, int rowNum) throws SQLException {
            return new StatModel(
                    rs.getLong("id"),
                    rs.getLong("course_id"),
                    rs.getLong("register_id"),
                    rs.getString("name"),
                    rs.getInt("price"),
                    rs.getInt("qty")
            );
    }
}

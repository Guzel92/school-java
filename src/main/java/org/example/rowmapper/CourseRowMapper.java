package org.example.rowmapper;

import org.example.model.CourseModel;
import org.springframework.jdbc.core.RowMapper;
import org.springframework.stereotype.Component;

import java.sql.ResultSet;
import java.sql.SQLException;
@Component
public class CourseRowMapper implements RowMapper<CourseModel> {

    @Override
    public CourseModel mapRow(ResultSet rs, int rowNum) throws SQLException {
        return new CourseModel(
                rs.getLong("id"),
                rs.getString("name"),
                rs.getInt("price"),
                rs.getString("image"),
                rs.getInt("numberseats")
        );
    }
}

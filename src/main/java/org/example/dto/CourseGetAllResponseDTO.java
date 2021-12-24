package org.example.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;
@AllArgsConstructor
@NoArgsConstructor
@Data
public class CourseGetAllResponseDTO {
    private List<Course> courses;
@AllArgsConstructor
@NoArgsConstructor
@Data
    public static class Course{
        private long id;
        private String name;
        private int price;
        private String image;
        private int numberseats;
    }

}

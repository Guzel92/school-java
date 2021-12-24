package org.example.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@NoArgsConstructor
@AllArgsConstructor
@Data
public class CourseGetByIdResponseDTO {
    private Course course;
    @NoArgsConstructor
    @AllArgsConstructor
    @Data
    public static class Course {
        private long id;
        private String name;
        private int price;
        private String image;
        private int numberseats;
    }
}


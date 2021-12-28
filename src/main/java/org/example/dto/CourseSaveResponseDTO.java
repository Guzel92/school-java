package org.example.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@AllArgsConstructor
@NoArgsConstructor
@Data
public class CourseSaveResponseDTO {
private Course course;
    @NoArgsConstructor
    @AllArgsConstructor
    @Data
    public static class Course{
        private long id;
        private long courseId;
        private String name;
        private int price;
        private String image;
        private int numberseats;
    }
}

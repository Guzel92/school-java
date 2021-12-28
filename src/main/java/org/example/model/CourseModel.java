package org.example.model;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@NoArgsConstructor
@AllArgsConstructor
@Data
public class CourseModel {
    private long id;
    private long courseId;
    private String name;
    private int price;
    private String image;
    private int numberseats;
}

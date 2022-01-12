package org.example.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@NoArgsConstructor
@AllArgsConstructor
@Data
public class CourseSaveRequestDTO {
    private long id;
    private String name;
    private int price;
    private String image;
    private int numberseats;

}

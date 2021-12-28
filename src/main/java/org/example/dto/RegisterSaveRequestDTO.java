package org.example.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@NoArgsConstructor
@AllArgsConstructor
@Data
public class RegisterSaveRequestDTO {
    private long id;
    private long courseId;
    private String name;
    private int price;
    private String customerName;
}

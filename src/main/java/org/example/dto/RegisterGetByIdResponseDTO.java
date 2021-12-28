package org.example.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@NoArgsConstructor
@AllArgsConstructor
@Data
public class RegisterGetByIdResponseDTO {
    private Registration registration;
@NoArgsConstructor
    @AllArgsConstructor
    @Data
    public static class Registration {
    private long id;
    private long courseId;
    private String name;
    private int price;
    private String customerName;
}
}

package org.example.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;

@NoArgsConstructor
@AllArgsConstructor
@Data
public class RegisterResponseDTO {
private List<Registration> registrations;

@NoArgsConstructor
    @AllArgsConstructor
    @Data
    public static class Registration{
    private long id;
    private String name;
    private int price;
    private String customerName;
}
}

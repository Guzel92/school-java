package org.example.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;

@NoArgsConstructor
@AllArgsConstructor
@Data
public class StatGetSumResponseDTO {
    private List<Register> positions;

    @NoArgsConstructor
    @AllArgsConstructor
    @Data
    public static class Register{
        private long id;
        private long courseId;
        private long registerId;
        private String name;
        private int price;
        private int qty;

    }
}

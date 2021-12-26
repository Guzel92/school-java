package org.example.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;

@NoArgsConstructor
@AllArgsConstructor
@Data
public class ParticipantsDTO {
private List<Participants> participants;
@NoArgsConstructor
    @AllArgsConstructor
    @Data
public static class Participants{
    private long id;
    private String name;
    private int price;
    private String customerName;
}
}

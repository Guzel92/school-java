package org.example.model;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@NoArgsConstructor
@AllArgsConstructor
@Data
public class StatModel {
 private long id;
 private long courseId;
 private long registerId;
 private String name;
 private int price;
 private int qty;
}

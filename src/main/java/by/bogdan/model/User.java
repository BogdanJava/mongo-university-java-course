package by.bogdan.model;

import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.ToString;

import java.util.UUID;

@Data
@NoArgsConstructor
@ToString
public class User {
    private String id = UUID.randomUUID().toString();
    private String name;
    private double weight;
    private double height;
    private String university;

}

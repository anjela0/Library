package model;

import lombok.*;

import java.util.List;

@Data
@EqualsAndHashCode(exclude = "authors")
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class Book {

    private int id;
    private String title;
    private String description;
    private double price;
    private List<Author> authors;
    private String profilePic;

}

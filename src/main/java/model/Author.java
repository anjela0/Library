package model;

import lombok.*;

import java.util.List;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class Author {

    private int id;
    private String name;
    private String surname;
    private String email;
    private int age;
    private String profilePic;
    private List<Book> books;

}

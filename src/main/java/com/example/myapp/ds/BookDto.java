package com.example.myapp.ds;

import lombok.*;
import org.springframework.format.annotation.DateTimeFormat;

import javax.persistence.CollectionTable;
import javax.persistence.ElementCollection;
import javax.persistence.ManyToOne;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;
@Getter
@Setter
@ToString
@EqualsAndHashCode
@AllArgsConstructor
public class BookDto {
    private  Integer id;
    private  String title;
    private  double price;
    private LocalDate yearPublished;
    private  String description;
    private String imageUrl;
    private int categoryId;
    private int authorId;
}

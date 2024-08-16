package com.SoyHenry.entities;


import jakarta.persistence.*;
import lombok.Data;

import java.time.LocalDate;

@Entity
@Data
public class Book {

    //id, title, author, publishYear y pageQuantity

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String title;
    private String author;
    @Column(name = "publish_year")
    private Integer publishYear;
    @Column(name = "page_quantity")
    private Integer pageQuantity;
}

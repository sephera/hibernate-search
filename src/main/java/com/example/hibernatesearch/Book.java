package com.example.hibernatesearch;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.hibernate.search.mapper.pojo.mapping.definition.annotation.*;

import javax.persistence.*;
import java.util.HashSet;
import java.util.Set;

@Indexed
@NoArgsConstructor
@AllArgsConstructor
@Getter
@Setter
@Entity
public class Book {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;

    @FullTextField
    private String title;

    @KeywordField
    private String isbn;

    @GenericField
    private int pageCount;

    @IndexedEmbedded
    @ManyToMany
    private Set<Author> authors = new HashSet<>();


}

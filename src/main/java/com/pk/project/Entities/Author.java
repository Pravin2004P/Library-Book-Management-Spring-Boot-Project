package com.pk.project.Entities;

import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import jakarta.persistence.Table;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Entity
@Table(name = "authors")
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor

public class Author {
    
    @Id
    //@GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;
    private String authorName;
    private String authorEmail;
}

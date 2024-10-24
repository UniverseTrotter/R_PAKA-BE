package com.ohgiraffers.r_pakabe.domains.genres.command.domain.model;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;

@NoArgsConstructor
@AllArgsConstructor
@Getter
@Entity
@Table
public class Genre {
    @Id
    @Column
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer genreCode;

    //장르 이름
    @Column(length = 20, unique = true)
    private String genreName;

    @Override
    public String toString() {
        return "Genre{" +
                "genreCode=" + genreCode +
                ", genreName='" + genreName + '\'' +
                '}';
    }
}

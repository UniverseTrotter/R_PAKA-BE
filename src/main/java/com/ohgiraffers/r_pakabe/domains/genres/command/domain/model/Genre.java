package com.ohgiraffers.r_pakabe.domains.genres.command.domain.model;

import jakarta.persistence.*;
import lombok.*;

@NoArgsConstructor (access = AccessLevel.PROTECTED)
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

    @Builder
    public Genre(String genreName) {
        this.genreName = genreName;
    }

    @Override
    public String toString() {
        return "Genre{" +
                "genreCode=" + genreCode +
                ", genreName='" + genreName + '\'' +
                '}';
    }
}

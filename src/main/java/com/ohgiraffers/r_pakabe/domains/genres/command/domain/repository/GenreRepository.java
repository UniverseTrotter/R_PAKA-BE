package com.ohgiraffers.r_pakabe.domains.genres.command.domain.repository;

import com.ohgiraffers.r_pakabe.domains.genres.command.domain.model.Genre;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface GenreRepository extends JpaRepository <Genre, Integer> {
    Optional<Genre> findByGenreName(String genreName);
}

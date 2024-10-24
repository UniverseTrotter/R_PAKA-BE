package com.ohgiraffers.r_pakabe.domains.genres.command.application.dto;

import com.ohgiraffers.r_pakabe.domains.genres.command.domain.model.Genre;

public record GenreDTO(
        Integer genreCode,
        String genreName
) {
    public static GenreDTO formEntity (Genre genre) {
        return new GenreDTO(genre.getGenreCode(), genre.getGenreName());
    }

    public static GenreDTO getEmpty(){
        return new GenreDTO(-1, "Empty");
    }
}

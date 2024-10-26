package com.ohgiraffers.r_pakabe.domains.genres.command.application.dto;

public class GenreRequestDTO {
    public record GenreNameDTO(
            String genreName
    ) {}

    public record GenreIdDTO(
            Integer genreId
    ){}
}

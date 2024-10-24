package com.ohgiraffers.r_pakabe.domains.genres.command.application.service;

import com.ohgiraffers.r_pakabe.domains.genres.command.application.dto.GenreDTO;
import com.ohgiraffers.r_pakabe.domains.genres.command.domain.model.Genre;
import com.ohgiraffers.r_pakabe.domains.genres.command.domain.service.GenreDomainService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@RequiredArgsConstructor
@Service
public class GenreAppService {
    private final GenreDomainService genreDomainService;

    public GenreDTO loadGenre(Integer genreId) {
        Genre genre = this.genreDomainService.findGenreById(genreId);
        if (genre == null) {
            return GenreDTO.getEmpty();
        }else {
            return GenreDTO.formEntity(genre);
        }
    }

    public Genre uploadGenre(GenreDTO genreDTO) {
        Genre genre = this.genreDomainService.findGenreById(genreDTO.genreCode());
        if (genre == null) {
            genre = genreDomainService.createGenre(
                    Genre.builder()
                            .genreName(genreDTO.genreName())
                            .build()
            );
        }else {
            genre = this.genreDomainService.updateGenre(
                    new Genre(genreDTO.genreName())
            );
        }
        return genre;
    }
}

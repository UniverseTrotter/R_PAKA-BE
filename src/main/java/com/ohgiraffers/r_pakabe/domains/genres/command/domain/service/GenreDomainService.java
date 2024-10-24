package com.ohgiraffers.r_pakabe.domains.genres.command.domain.service;

import com.ohgiraffers.r_pakabe.domains.genres.command.domain.model.Genre;
import com.ohgiraffers.r_pakabe.domains.genres.command.domain.repository.GenreRepository;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class GenreDomainService {
    private final GenreRepository genreRepository;

    public GenreDomainService(GenreRepository genreRepository) {
        this.genreRepository = genreRepository;
    }

    public List<Genre> getAllGenres() {
        return genreRepository.findAll();
    }

    public Genre findGenreById(Integer genreCode) {
        return genreRepository.findById(genreCode).orElse(null);
    }

    public Genre findGenreByName(String genreName) {
        return genreRepository.findByGenreName(genreName).orElse(null);
    }

    public Genre updateGenre(Genre genre) {
        return genreRepository.save(genre);
    }

    public Genre createGenre(Genre genre) {
        return genreRepository.save(genre);
    }

    public Genre saveGenre(Genre genre) {
        return genreRepository.save(genre);
    }

    public void deleteGenre(Integer genreCode) {
        genreRepository.deleteById(genreCode);
    }

}

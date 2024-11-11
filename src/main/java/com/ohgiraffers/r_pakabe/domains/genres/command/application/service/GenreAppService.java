package com.ohgiraffers.r_pakabe.domains.genres.command.application.service;

import com.ohgiraffers.r_pakabe.common.error.ApplicationException;
import com.ohgiraffers.r_pakabe.common.error.ErrorCode;
import com.ohgiraffers.r_pakabe.domains.genres.command.application.dto.GenreDTO;
import com.ohgiraffers.r_pakabe.domains.genres.command.domain.model.Genre;
import com.ohgiraffers.r_pakabe.domains.genres.command.domain.service.GenreDomainService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.ArrayList;
import java.util.List;

@Slf4j
@RequiredArgsConstructor
@Service
public class GenreAppService {
    private final GenreDomainService genreDomainService;

//    public GenreDTO loadGenre(Integer genreId) {
//        Genre genre = this.genreDomainService.findGenreById(genreId);
//        if (genre == null) {
//            return GenreDTO.getEmpty();
//        }else {
//            return GenreDTO.formEntity(genre);
//        }
//    }

    public Genre uploadGenre(GenreDTO genreDTO) {
        log.info("Upload genre : {}", genreDTO);
        Genre genre = this.genreDomainService.findGenreByName(genreDTO.genreName());
        if (genre == null) {
            genre = genreDomainService.createGenre(
                    Genre.builder()
                            .genreName(genreDTO.genreName())
                            .build()
            );
        }
        return genre;
    }

    @Transactional(readOnly = true)
    public List<GenreDTO> findAllGenre() {
        List<Genre> genreList = genreDomainService.getAllGenres();
        List<GenreDTO> genreDTOList = new ArrayList<>();
        for (Genre genre : genreList) {
            genreDTOList.add(GenreDTO.formEntity(genre));
        }
        return genreDTOList;
    }

    @Transactional(readOnly = true)
    public GenreDTO findGenreById(Integer genreId) {
        Genre genre = this.genreDomainService.findGenreById(genreId);
        if (genre == null) {
            throw new ApplicationException(ErrorCode.NO_SUCH_GENRE);
        }
        return GenreDTO.formEntity(genre);
    }

    @Transactional(readOnly = true)
    public GenreDTO findGenreByName(String genreName) {
        Genre genre = this.genreDomainService.findGenreByName(genreName);
        if (genre == null) {
            throw new ApplicationException(ErrorCode.NO_SUCH_GENRE);
        }
        return GenreDTO.formEntity(genre);
    }

    @Transactional(readOnly = true)
    public List<String> findGenreByList(List<Integer> genreIdList) {
        List<String> genreList = new ArrayList<>();
        for (Integer genreId : genreIdList) {
            Genre genre = this.genreDomainService.findGenreById(genreId);
            genreList.add(genre.getGenreName());
        }
        return genreList;
    }

    @Transactional
    public void createGenre(String genreName) {
        Genre genre = this.genreDomainService.findGenreByName(genreName);
        if (genre != null) {        //이미 있으면 안됨
            throw new ApplicationException(ErrorCode.GENRE_ALREADY_EXIT);
        }
        genre =  this.genreDomainService.createGenre(new Genre(genreName));
        log.info("Create Tag : {}", genre);
    }

    @Transactional
    public void deleteGenre(Integer genreId) {
        Genre genre = this.genreDomainService.findGenreById(genreId);
        if (genre == null) {
            throw new ApplicationException(ErrorCode.NO_SUCH_GENRE);
        }
        genreDomainService.deleteGenre(genreId);
        log.info("Delete Tag : {}", genre);
    }
}

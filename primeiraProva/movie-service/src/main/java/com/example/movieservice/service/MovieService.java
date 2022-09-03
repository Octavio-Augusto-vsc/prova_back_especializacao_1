package com.example.movieservice.service;

import com.example.movieservice.dto.MovieDTO;
import com.example.movieservice.entity.Movie;
import com.example.movieservice.repository.MovieRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;

import javax.persistence.EntityNotFoundException;
import java.util.List;
import java.util.stream.Collectors;

@Service
public class MovieService {

    @Value("${server.port}")
    private String port;

    public String getPort() {
        return port;
    }

    @Autowired
    public MovieRepository repository;


    public MovieDTO salvar( MovieDTO dto ){
        var movie = new Movie();
        movie.setId(dto.getId());
        movie.setNome(dto.getNome());
        movie.setIdGenero(dto.getIdGenero());
        movie.setUrlStream(dto.getUrlStream());
        movie.setPorta(this.port);
        movie = repository.save(movie);
        return getDto(movie);
    }

    public MovieDTO pesquisaPorId(Long id){
        var movie = repository.findById(id).orElseThrow(EntityNotFoundException::new);
        return getDto(movie);
    }

    /*
    public List<Movie> buscarTodos() {
        return repository.findAll();
    }
     */

    public List<MovieDTO> buscarTodos(){
        return repository.findAll()
                .stream()
                .map(this::getDto)
                .collect(Collectors.toList());
    }

    public List<MovieDTO> pesquisarPorIdGenero(Long idGenero) {
        return repository.findByIdGenero(idGenero)
                .stream().map(this::getDto).collect(Collectors.toList());
    }


    private MovieDTO getDto(Movie movie){
        return MovieDTO.builder()
                .id(movie.getId())
                .nome(movie.getNome())
                .idGenero(movie.getIdGenero())
                .urlStream(movie.getUrlStream())
                .porta(movie.getPorta())
                .build();
    }

}

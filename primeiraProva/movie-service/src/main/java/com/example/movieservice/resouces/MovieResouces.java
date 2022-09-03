package com.example.movieservice.resouces;


import com.example.movieservice.dto.MovieDTO;
import com.example.movieservice.service.MovieService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/movie")
public class MovieResouces {

    @Autowired
    private MovieService service;

    @GetMapping("/todos")
    public List<MovieDTO> buscarTodos() {
        return service.buscarTodos();
    }

    @PostMapping
    private ResponseEntity<MovieDTO> salvar(@RequestBody MovieDTO dto){
        return ResponseEntity.status(HttpStatus.CREATED).body(service.salvar(dto));
    }

    @GetMapping("/{id}")
    private ResponseEntity<MovieDTO> pesquisarPorId(@PathVariable Long id) {
        return ResponseEntity.ok(service.pesquisaPorId(id));
    }

    @GetMapping("/genero/{idGenero}")
    private ResponseEntity<List<MovieDTO>> pesquisarGeneroPorId(@PathVariable Long idGenero) {
        return ResponseEntity.ok(service.pesquisarPorIdGenero(idGenero));
    }

}

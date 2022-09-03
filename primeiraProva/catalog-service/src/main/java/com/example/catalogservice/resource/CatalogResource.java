package com.example.catalogservice.resource;

import com.example.catalogservice.dto.GeneroDTO;
import com.example.catalogservice.dto.MovieDTO;
import com.example.catalogservice.service.CatalogService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;


@RestController
@RequestMapping("/catalog")
public class CatalogResource {

    @Autowired
    private CatalogService service;

    @GetMapping("/mensagem/{id}")
    private ResponseEntity<String> getCatalogMovie(@PathVariable Long id){
        return ResponseEntity.ok(service.getCatalogMovie(id));
    }

    @PostMapping
    private ResponseEntity<GeneroDTO> salvar(@RequestBody GeneroDTO dto) {
        return ResponseEntity.status(HttpStatus.CREATED).body(service.salvar(dto));
    }

    @GetMapping("/genero/{nomeDoGenero}")
    private ResponseEntity<GeneroDTO> pesquisarPorGenero(@PathVariable String nomeDoGenero) {
        return ResponseEntity.ok(service.pesquisarPorGenero(nomeDoGenero));
    }

    @GetMapping("/{id}")
    private ResponseEntity<GeneroDTO> pesquisarPorId(@PathVariable Long id) {
        return ResponseEntity.ok(service.pesquisarPorId(id));
    }

    @GetMapping("/movie/{nomeGenero}")
    private ResponseEntity<List<MovieDTO>> pesquisarMoviesPorGenero(@PathVariable String nomeGenero) {
        return ResponseEntity.ok(service.pesquisarMovie(nomeGenero));
    }
}

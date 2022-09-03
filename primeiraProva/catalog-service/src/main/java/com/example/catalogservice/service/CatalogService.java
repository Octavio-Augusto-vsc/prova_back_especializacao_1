package com.example.catalogservice.service;

import com.example.catalogservice.dto.GeneroDTO;
import com.example.catalogservice.dto.MovieDTO;
import com.example.catalogservice.entity.Genero;
import com.example.catalogservice.repository.CatalogRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;

import javax.persistence.EntityNotFoundException;
import java.util.List;

@Service
public class CatalogService {

    @Autowired
    private MovieFeign movieFeign;

    @Autowired
    public CatalogRepository repository;


    @Value("${server.port}")
    private String port;

    public String getCatalogMovie(Long id) {
        String s = "Porta Servidor Catalog: " + port + ". Nome do Filme: " + movieFeign.pesquisarPorId(id).getBody().getNome() +
                ". O ID do Genero do Filme: " + movieFeign.pesquisarPorId(id).getBody().getIdGenero() +
                ". O Genero do Filme: " + repository.findById(id).get().getNome()+
                ". Porta do Microsserviço Movie: " + movieFeign.pesquisarPorId(id).getBody().getPorta() + ".";
        System.out.println(s);
        return s;
    }


    public GeneroDTO salvar(GeneroDTO dto) {
        Genero genero = new Genero();
        genero.setId(dto.getId());
        genero.setNome(dto.getNome());
        repository.save(genero);
        return getDto(genero);
    }

    public GeneroDTO pesquisarPorId(Long id) {
        Genero genero = repository.findById(id).orElseThrow(EntityNotFoundException::new);
        return getDto(genero);
    }

    public GeneroDTO pesquisarPorGenero(String nomeDoGenero) {
        Genero genero = repository.findByNome(nomeDoGenero).orElseThrow(EntityNotFoundException::new);
        return getDto(genero);
    }

    public List<MovieDTO> pesquisarMovie(String nomeGenero) {
        GeneroDTO generoDTO = pesquisarPorGenero(nomeGenero);
        return movieFeign.pesquisarGeneroPorId(generoDTO.getId()).getBody();
    }


    private GeneroDTO getDto(Genero genero){
        return GeneroDTO.builder()
                .id(genero.getId())
                .nome(genero.getNome())
                .build();
    }


}

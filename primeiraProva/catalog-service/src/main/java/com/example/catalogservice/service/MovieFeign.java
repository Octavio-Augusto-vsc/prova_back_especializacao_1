package com.example.catalogservice.service;

import com.example.catalogservice.config.LoadBalancerConfiguration;
import com.example.catalogservice.dto.MovieDTO;
import org.springframework.cloud.loadbalancer.annotation.LoadBalancerClient;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;

import java.util.List;

//porque o movie que Aparece no Eureka e que porcausa disto colocamos aqui e maiusculo
// sendo que o nome do Microsserviço e minusculo
@FeignClient(name="MOVIE-SERVICE")
@LoadBalancerClient(name = "MOVIE-SERVICE", configuration = LoadBalancerConfiguration.class)
public interface MovieFeign {



    //Poracausa do Gateway qua recebel dentro do Arquivo no GitHub um MOVIE como Rot aqui se deve coloca mais um MOVIE na rota
    @GetMapping("/api-movie/movie/{id}")
    public ResponseEntity<MovieDTO> pesquisarPorId(@PathVariable Long id);



    @GetMapping("/api-movie/movie/genero/{idGenero}")
    ResponseEntity<List<MovieDTO>> pesquisarGeneroPorId(@PathVariable Long idGenero);
}


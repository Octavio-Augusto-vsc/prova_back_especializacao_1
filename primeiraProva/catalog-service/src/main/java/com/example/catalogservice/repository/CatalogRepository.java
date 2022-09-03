package com.example.catalogservice.repository;

import com.example.catalogservice.entity.Genero;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;


@Repository
public interface CatalogRepository extends JpaRepository<Genero, Long> {

    Optional<Genero> findByNome(String nomeGenero);
}



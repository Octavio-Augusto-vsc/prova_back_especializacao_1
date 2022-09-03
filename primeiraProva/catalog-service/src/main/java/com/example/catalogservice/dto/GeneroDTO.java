package com.example.catalogservice.dto;

import lombok.Builder;
import lombok.Data;

import java.io.Serializable;

@Data
@Builder
public class GeneroDTO implements Serializable {

    private Long id;

    private String nome;
}

package com.example.catalogservice.dto;

import lombok.Builder;
import lombok.Data;

import java.io.Serializable;

@Data
@Builder
public class MovieDTO implements Serializable {

    private Long id;
    private String nome;
    private  Long idGenero;
    private String urlStream;
    private String porta;
}

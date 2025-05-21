package com.example.demo.model;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;

@Entity
@Getter
@Setter
@Table(name = "usuario")
public class Usuario {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
 
    private String usuario;

    private String email;

    private Double salario;

    private Integer idade;
 
    private String senha;

    private String cep;
 
    private String estado;
    
    private String cidade;
   
    private String bairro;
   
    private String rua;
   
    private Integer numero;

}
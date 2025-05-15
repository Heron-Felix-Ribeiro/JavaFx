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
    
   // private CPF cpf;

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

    public Usuario() {}

//    public Usuario(UsuarioRequest entrada) {
//
//        this.id = entrada.id();
//        this.usuario = entrada.usuario();
//        this.cpf = new CPF(entrada.cpf());
//        this.salario = entrada.salario();
//        this.idade = entrada.idade();
//        this.senha = entrada.senha();
//        this.cep = entrada.cep();
//        this.estado = entrada.estado();
//        this.cidade = entrada.cidade();
//        this.bairro = entrada.bairro();
//        this.rua = entrada.rua();
//        this.numero = entrada.numero();
//
//    }


}
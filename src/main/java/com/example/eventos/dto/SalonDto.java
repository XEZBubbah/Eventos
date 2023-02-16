package com.example.eventos.dto;

import lombok.*;

@Getter @Setter
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class SalonDto {

    private Long id;
    private String nombre;
    private int capacidad;
    private String direccion;
}

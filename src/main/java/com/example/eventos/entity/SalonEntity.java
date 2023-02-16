package com.example.eventos.entity;

import jakarta.persistence.*;
import lombok.*;

@Entity
@Table(name = "SALONES")
@Getter @Setter
@AllArgsConstructor @NoArgsConstructor @Builder
public class SalonEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private String nombre;
    private int capacidad;
    private String direccion;

}

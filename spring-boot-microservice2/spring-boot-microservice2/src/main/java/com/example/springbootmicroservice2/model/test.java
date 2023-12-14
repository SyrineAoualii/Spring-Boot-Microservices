package com.example.springbootmicroservice2.model;

import jakarta.persistence.*;
import lombok.Data;

@Data
@Entity
@Table(name = "test")
public class test {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    @Column(name = "test name", nullable = false)
    private Long test;
}

package com.sgf.model;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Entity
@Table(name = "prodotti")
@Data
@NoArgsConstructor
@AllArgsConstructor
public class Prodotto {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(nullable = false, unique = true, length = 50)
    private String codice;

    @Column(nullable = false, length = 150)
    private String nome;

    @Column(length = 500)
    private String descrizione;

    @Column(name = "prezzo_unitario", nullable = false)
    private Double prezzoUnitario;

    @Column(nullable = false)
    private Integer scorta;

    @Column(name = "soglia_minima_riordino", nullable = false)
    private Integer sogliaMinimaRiordino;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "categoria_id", nullable = false)
    private Categoria categoria;
}
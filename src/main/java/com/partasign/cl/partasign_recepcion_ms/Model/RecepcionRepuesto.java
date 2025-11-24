package com.partasign.cl.partasign_recepcion_ms.Model;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.Table;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDate;

@Entity
@Data
@AllArgsConstructor
@NoArgsConstructor
@Table(name = "recepciones_repuestos")
public class RecepcionRepuesto {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;

    @Column(nullable = false)
    private String repuestoId;

    @Column(nullable = false)
    private Integer cantidadRecibida;

    @Column(nullable = false)
    private String recibidoPor;

    @Column(nullable = false)
    private LocalDate fechaRecepcion;

    @Column(nullable = true)
    private String observacion;
}

package com.partasign.cl.partasign_recepcion_ms.Controller;

import com.partasign.cl.partasign_recepcion_ms.Model.RecepcionRepuesto;
import com.partasign.cl.partasign_recepcion_ms.Service.RecepcionService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@RestController
@Tag(name = "Recepciones", description = "CRUD para recepción de repuestos")
@RequestMapping("/api/recepciones")
public class RecepcionController {

    @Autowired
    private RecepcionService service;

    @GetMapping
    @Operation(summary = "Listar recepciones")
    public ResponseEntity<List<RecepcionRepuesto>> listar() {
        List<RecepcionRepuesto> lista = service.findAll();
        if (lista.isEmpty()) {
            return ResponseEntity.noContent().build();
        }
        return ResponseEntity.ok(lista);
    }

    @PostMapping("/crear")
    @Operation(summary = "Registrar recepción de repuesto")
    public ResponseEntity<RecepcionRepuesto> crear(@RequestBody RecepcionRepuesto recepcion) {
        RecepcionRepuesto creada = service.save(recepcion);
        return ResponseEntity.status(HttpStatus.CREATED).body(creada);
    }

    @GetMapping("/{id}")
    @Operation(summary = "Buscar recepción por id")
    public ResponseEntity<RecepcionRepuesto> buscar(@PathVariable Integer id) {
        Optional<RecepcionRepuesto> recepcion = service.findById(id);
        return recepcion.map(ResponseEntity::ok).orElseGet(() -> ResponseEntity.notFound().build());
    }

    @PutMapping("/{id}")
    @Operation(summary = "Actualizar recepción")
    public ResponseEntity<RecepcionRepuesto> actualizar(@PathVariable Integer id, @RequestBody RecepcionRepuesto recepcion) {
        Optional<RecepcionRepuesto> existenteOpt = service.findById(id);
        if (existenteOpt.isEmpty()) {
            return ResponseEntity.notFound().build();
        }

        RecepcionRepuesto existente = existenteOpt.get();
        existente.setRepuestoId(recepcion.getRepuestoId());
        existente.setCantidadRecibida(recepcion.getCantidadRecibida());
        existente.setRecibidoPor(recepcion.getRecibidoPor());
        existente.setFechaRecepcion(recepcion.getFechaRecepcion());
        existente.setObservacion(recepcion.getObservacion());

        RecepcionRepuesto actualizada = service.save(existente);
        return ResponseEntity.ok(actualizada);
    }

    @DeleteMapping("/{id}")
    @Operation(summary = "Eliminar recepción")
    public ResponseEntity<Void> eliminar(@PathVariable Integer id) {
        try {
            service.deleteById(id);
            return ResponseEntity.noContent().build();
        } catch (Exception e) {
            return ResponseEntity.notFound().build();
        }
    }
}

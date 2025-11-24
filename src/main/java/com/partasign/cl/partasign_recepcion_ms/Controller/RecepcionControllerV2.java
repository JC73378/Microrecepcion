package com.partasign.cl.partasign_recepcion_ms.Controller;

import com.partasign.cl.partasign_recepcion_ms.Assemblers.RecepcionModelAssembler;
import com.partasign.cl.partasign_recepcion_ms.Model.RecepcionRepuesto;
import com.partasign.cl.partasign_recepcion_ms.Service.RecepcionService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.hateoas.CollectionModel;
import org.springframework.hateoas.EntityModel;
import org.springframework.hateoas.MediaTypes;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

import static org.springframework.hateoas.server.mvc.WebMvcLinkBuilder.linkTo;
import static org.springframework.hateoas.server.mvc.WebMvcLinkBuilder.methodOn;

@RestController
@RequestMapping("/api/v2/recepciones")
public class RecepcionControllerV2 {

    @Autowired
    private RecepcionService service;

    @Autowired
    private RecepcionModelAssembler assembler;

    @GetMapping(produces = MediaTypes.HAL_JSON_VALUE)
    public CollectionModel<EntityModel<RecepcionRepuesto>> getAll() {
        List<EntityModel<RecepcionRepuesto>> lista = service.findAll().stream()
                .map(assembler::toModel)
                .collect(Collectors.toList());

        return CollectionModel.of(
                lista,
                linkTo(methodOn(RecepcionControllerV2.class).getAll()).withSelfRel()
        );
    }

    @GetMapping(value = "/{id}", produces = MediaTypes.HAL_JSON_VALUE)
    public ResponseEntity<EntityModel<RecepcionRepuesto>> getById(@PathVariable Integer id) {
        Optional<RecepcionRepuesto> recepcion = service.findById(id);
        return recepcion.map(value -> ResponseEntity.ok(assembler.toModel(value)))
                .orElseGet(() -> ResponseEntity.notFound().build());
    }

    @PostMapping(produces = MediaTypes.HAL_JSON_VALUE)
    public ResponseEntity<EntityModel<RecepcionRepuesto>> create(@RequestBody RecepcionRepuesto recepcion) {
        RecepcionRepuesto creada = service.save(recepcion);
        return ResponseEntity
                .created(linkTo(methodOn(RecepcionControllerV2.class).getById(creada.getId())).toUri())
                .body(assembler.toModel(creada));
    }

    @PutMapping(value = "/{id}", produces = MediaTypes.HAL_JSON_VALUE)
    public ResponseEntity<EntityModel<RecepcionRepuesto>> update(@PathVariable Integer id, @RequestBody RecepcionRepuesto recepcion) {
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
        return ResponseEntity.ok(assembler.toModel(actualizada));
    }

    @DeleteMapping(value = "/{id}", produces = MediaTypes.HAL_JSON_VALUE)
    public ResponseEntity<Void> delete(@PathVariable Integer id) {
        try {
            service.deleteById(id);
            return ResponseEntity.noContent().build();
        } catch (Exception e) {
            return ResponseEntity.notFound().build();
        }
    }
}

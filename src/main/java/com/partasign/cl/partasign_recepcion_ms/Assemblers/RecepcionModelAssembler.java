package com.partasign.cl.partasign_recepcion_ms.Assemblers;

import com.partasign.cl.partasign_recepcion_ms.Controller.RecepcionControllerV2;
import com.partasign.cl.partasign_recepcion_ms.Model.RecepcionRepuesto;
import org.springframework.hateoas.EntityModel;
import org.springframework.hateoas.server.RepresentationModelAssembler;
import org.springframework.stereotype.Component;

import static org.springframework.hateoas.server.mvc.WebMvcLinkBuilder.linkTo;
import static org.springframework.hateoas.server.mvc.WebMvcLinkBuilder.methodOn;

@Component
public class RecepcionModelAssembler implements RepresentationModelAssembler<RecepcionRepuesto, EntityModel<RecepcionRepuesto>> {
    @Override
    public EntityModel<RecepcionRepuesto> toModel(RecepcionRepuesto recepcion) {
        return EntityModel.of(
                recepcion,
                linkTo(methodOn(RecepcionControllerV2.class).getById(recepcion.getId())).withSelfRel(),
                linkTo(methodOn(RecepcionControllerV2.class).getAll()).withRel("recepciones")
        );
    }
}

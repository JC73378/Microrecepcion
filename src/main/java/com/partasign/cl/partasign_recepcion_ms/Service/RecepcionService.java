package com.partasign.cl.partasign_recepcion_ms.Service;

import com.partasign.cl.partasign_recepcion_ms.Model.RecepcionRepuesto;
import com.partasign.cl.partasign_recepcion_ms.Repository.RecepcionRepository;
import jakarta.transaction.Transactional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
@Transactional
public class RecepcionService {

    @Autowired
    private RecepcionRepository repository;

    public List<RecepcionRepuesto> findAll() {
        return repository.findAll();
    }

    public Optional<RecepcionRepuesto> findById(Integer id) {
        return repository.findById(id);
    }

    public RecepcionRepuesto save(RecepcionRepuesto recepcion) {
        return repository.save(recepcion);
    }

    public void deleteById(Integer id) {
        repository.deleteById(id);
    }
}

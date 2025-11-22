package com.partasign.cl.partasign_recepcion_ms.Repository;

import com.partasign.cl.partasign_recepcion_ms.Model.RecepcionRepuesto;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface RecepcionRepository extends JpaRepository<RecepcionRepuesto, Integer> {
}

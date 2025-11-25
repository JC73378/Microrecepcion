package com.partasign.cl.partasign_recepcion_ms;

import com.github.javafaker.Faker;
import com.partasign.cl.partasign_recepcion_ms.Model.RecepcionRepuesto;
import com.partasign.cl.partasign_recepcion_ms.Repository.RecepcionRepository;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.CommandLineRunner;
import org.springframework.stereotype.Component;

import java.time.LocalDate;
import java.util.Locale;
import java.util.concurrent.ThreadLocalRandom;

@Component
public class DataSeeder implements CommandLineRunner {

    private final RecepcionRepository repository;
    private final Faker faker = new Faker(new Locale("es"));
    private final boolean seedEnabled;

    public DataSeeder(RecepcionRepository repository,
                      @Value("${app.seed.enabled:true}") boolean seedEnabled) {
        this.repository = repository;
        this.seedEnabled = seedEnabled;
    }

    @Override
    public void run(String... args) {
        if (!seedEnabled || repository.count() > 0) {
            return;
        }

        for (int i = 0; i < 8; i++) {
            RecepcionRepuesto r = new RecepcionRepuesto();
            r.setRepuestoId("REP-" + faker.number().digits(5));
            r.setCantidadRecibida(ThreadLocalRandom.current().nextInt(1, 10));
            r.setRecibidoPor(faker.name().fullName());
            r.setFechaRecepcion(LocalDate.now().minusDays(faker.number().numberBetween(1, 15)));
            r.setObservacion(faker.lorem().sentence());
            repository.save(r);
        }
    }
}

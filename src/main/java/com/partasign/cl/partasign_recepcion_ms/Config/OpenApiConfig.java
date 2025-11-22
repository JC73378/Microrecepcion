package com.partasign.cl.partasign_recepcion_ms.Config;

import io.swagger.v3.oas.models.OpenAPI;
import io.swagger.v3.oas.models.info.Info;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class OpenApiConfig {
    @Bean
    public OpenAPI recepcionApi() {
        return new OpenAPI()
                .info(new Info()
                        .title("PartAsign Recepción API")
                        .version("1.0")
                        .description("API para registrar y consultar recepciones de repuestos"));
    }
}

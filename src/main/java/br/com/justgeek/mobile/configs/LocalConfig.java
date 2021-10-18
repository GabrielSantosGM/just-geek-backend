package br.com.justgeek.mobile.configs;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.boot.CommandLineRunner;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Profile;

@Configuration
@Profile("local")
public class LocalConfig {

    private static final Logger LOG = LoggerFactory.getLogger(LocalConfig.class);

    @Bean
    public CommandLineRunner executar() {
        return args -> LOG.info("[AMBIENTE] Subindo ambiente de desenvolvimento!");
    }
}

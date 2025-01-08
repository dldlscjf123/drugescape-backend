package com.lee.drugescape.drugescape.config;


import lombok.extern.slf4j.Slf4j;
import org.springframework.cloud.openfeign.FeignFormatterRegistrar;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.format.datetime.standard.DateTimeFormatterRegistrar;
@Configuration
@Slf4j
public class FeignConfiguration {
    @Bean
    public FeignFormatterRegistrar localDateFormatter() {
        return registry -> {
            DateTimeFormatterRegistrar registrar = new DateTimeFormatterRegistrar();
            registrar.setUseIsoFormat(true);
            registrar.registerFormatters(registry);
            log.info("Feign DateFormatter registered!");
        };
    }
}

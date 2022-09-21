package com.nhs.skill;

import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.cache.annotation.EnableCaching;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.scheduling.annotation.EnableAsync;

@Slf4j
@Configuration
@EnableCaching
@EnableAsync

public class SkillManagementCore {

    @Bean
    public String getRunEnvironment(@Value("${app.env}") final String _value) {
        return _value;
    }

}

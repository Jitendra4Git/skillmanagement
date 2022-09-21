package com.nhs.skill;

import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.builder.SpringApplicationBuilder;

@SpringBootApplication
public class SkillManagementApi {

    public static void main(String[] args) {

        new SpringApplicationBuilder()
                .profiles("api")
                .sources(SkillManagementApi.class)
                .run(args);
    }

}
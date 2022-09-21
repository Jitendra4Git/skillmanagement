package com.nhs.skill.configuration;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.boot.jdbc.DataSourceBuilder;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Primary;
import org.springframework.core.env.Environment;
import org.springframework.data.jpa.repository.config.EnableJpaRepositories;
import org.springframework.orm.jpa.JpaTransactionManager;
import org.springframework.orm.jpa.LocalContainerEntityManagerFactoryBean;
import org.springframework.orm.jpa.vendor.HibernateJpaVendorAdapter;
import org.springframework.transaction.PlatformTransactionManager;

import javax.sql.DataSource;
import java.util.HashMap;

/**
 * @author Jitendra Gupta
 */

@Configuration
@EnableJpaRepositories(
        basePackages = "com.nhs.skill.data.dao",
        entityManagerFactoryRef = "nhsEntityManager",
        transactionManagerRef = "nhsTransactionManager")
public class SkillManagementDBConfig {
    @Autowired
    private Environment env;

    public SkillManagementDBConfig() {
        super();
    }

    @Primary
    @Bean
    public LocalContainerEntityManagerFactoryBean nhsEntityManager() {
        final LocalContainerEntityManagerFactoryBean em = new LocalContainerEntityManagerFactoryBean();
        em.setDataSource(nhsDataSource());
        em.setPackagesToScan("com.nhs.skill.data.dao");
        em.setPackagesToScan("com.nhs.skill.data.domain");

        final HibernateJpaVendorAdapter vendorAdapter = new HibernateJpaVendorAdapter();
        em.setJpaVendorAdapter(vendorAdapter);
        final HashMap<String, Object> properties = new HashMap<String, Object>();
        properties.put("hibernate.hbm2ddl.auto", env.getProperty("skillmanagement.hibernate.hbm2ddl.auto"));
        properties.put("hibernate.dialect", env.getProperty("skillmanagement.hibernate.dialect"));
        em.setJpaPropertyMap(properties);

        return em;
    }

    @Bean
    @Primary
    @ConfigurationProperties(prefix = "spring.skillmanagement.datasource")
    public DataSource nhsDataSource() {
        return DataSourceBuilder.create().build();
    }

    @Primary
    @Bean
    public PlatformTransactionManager nhsTransactionManager() {
        final JpaTransactionManager transactionManager = new JpaTransactionManager();
        transactionManager.setEntityManagerFactory(nhsEntityManager().getObject());
        return transactionManager;
    }

}
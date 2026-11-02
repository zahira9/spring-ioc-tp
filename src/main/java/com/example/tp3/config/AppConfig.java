package com.example.tp3.config;

import com.example.tp3.dao.DaoApi;
import com.example.tp3.dao.DaoFile;
import com.example.tp3.dao.DaoImpl;
import com.example.tp3.dao.DaoImpl2;
import com.example.tp3.dao.IDao;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Primary;
import org.springframework.context.annotation.PropertySource;
import org.springframework.core.env.Environment;

@Configuration
@ComponentScan(basePackages = {"com.example.tp3.dao", "com.example.tp3.metier"})
@PropertySource("classpath:tp3-app.properties")
public class AppConfig {
    @Bean
    @Primary
    public IDao dynamicDao(Environment environment) {
        String target = environment.getProperty("dao.target", "dao");
        switch (target) {
            case "dao":
                return new DaoImpl();
            case "dao2":
                return new DaoImpl2();
            case "daoFile":
                return new DaoFile();
            case "daoApi":
                return new DaoApi();
            default:
                throw new IllegalArgumentException("Cible DAO inconnue : " + target);
        }
    }
}

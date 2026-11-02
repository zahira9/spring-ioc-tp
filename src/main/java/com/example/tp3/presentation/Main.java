package com.example.tp3.presentation;

import com.example.tp3.config.AppConfig;
import com.example.tp3.metier.IMetier;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;

public class Main {
    public static void main(String[] args) {
        try (AnnotationConfigApplicationContext context =
                     new AnnotationConfigApplicationContext(AppConfig.class)) {
            IMetier metier = context.getBean(IMetier.class);
            System.out.println("Résultat final : " + metier.calcul());
        }
    }
}

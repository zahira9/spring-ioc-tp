package com.example.presentation;

import com.example.metier.IMetier;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;

@Configuration
@ComponentScan(basePackages = {"com.example.dao", "com.example.metier"})
public class Presentation2 {
    public static void main(String[] args) {
        try (AnnotationConfigApplicationContext context =
                     new AnnotationConfigApplicationContext(Presentation2.class)) {
            IMetier metier = context.getBean(IMetier.class);
            System.out.println("Résultat = " + metier.calcul());
        }
    }
}

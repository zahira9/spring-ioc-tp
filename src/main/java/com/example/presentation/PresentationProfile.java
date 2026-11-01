package com.example.presentation;

import com.example.metier.IMetier;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;

@Configuration
@ComponentScan(basePackages = {"com.example.dao.profile", "com.example.metier"})
public class PresentationProfile {
    public static void main(String[] args) {
        String profile = args.length == 0 ? "dev" : args[0];
        if (!profile.equals("dev") && !profile.equals("prod")) {
            throw new IllegalArgumentException("Profil attendu : dev ou prod");
        }

        try (AnnotationConfigApplicationContext context =
                     new AnnotationConfigApplicationContext()) {
            context.getEnvironment().setActiveProfiles(profile);
            context.register(PresentationProfile.class);
            context.refresh();

            IMetier metier = context.getBean(IMetier.class);
            System.out.println("Résultat (" + profile + ") = " + metier.calcul());
        }
    }
}

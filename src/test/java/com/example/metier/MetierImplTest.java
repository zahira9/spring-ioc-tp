package com.example.metier;

import com.example.dao.IDao;
import com.example.presentation.Presentation2;
import com.example.presentation.PresentationProfile;
import org.junit.Test;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;

import static org.junit.Assert.assertEquals;

public class MetierImplTest {
    @Test
    public void calculMultipliesDaoValueByTwo() {
        IDao dao = () -> 10.0;
        MetierImpl metier = new MetierImpl();
        metier.setDao(dao);

        assertEquals(20.0, metier.calcul(), 0.001);
    }

    @Test
    public void annotationConfigurationInjectsDao2() {
        try (AnnotationConfigApplicationContext context =
                     new AnnotationConfigApplicationContext(Presentation2.class)) {
            IMetier metier = context.getBean(IMetier.class);
            assertEquals(300.0, metier.calcul(), 0.001);
        }
    }

    @Test
    public void xmlConfigurationInjectsDao2() {
        try (ClassPathXmlApplicationContext context =
                     new ClassPathXmlApplicationContext("applicationContext.xml")) {
            IMetier metier = context.getBean("metier", IMetier.class);
            assertEquals(300.0, metier.calcul(), 0.001);
        }
    }

    @Test
    public void profilesSelectDifferentDaoImplementations() {
        assertEquals(300.0, calculateWithProfile("dev"), 0.001);
        assertEquals(200.0, calculateWithProfile("prod"), 0.001);
    }

    private double calculateWithProfile(String profile) {
        try (AnnotationConfigApplicationContext context =
                     new AnnotationConfigApplicationContext()) {
            context.getEnvironment().setActiveProfiles(profile);
            context.register(PresentationProfile.class);
            context.refresh();
            return context.getBean(IMetier.class).calcul();
        }
    }
}

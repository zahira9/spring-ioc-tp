package com.example.tp3.metier;

import com.example.tp3.config.AppConfig;
import org.junit.Test;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;
import org.springframework.core.env.MapPropertySource;

import java.util.Collections;

import static org.junit.Assert.assertEquals;

public class OcpSelectionTest {
    @Test
    public void selectsImplementationFromExternalConfiguration() {
        assertCalculation("dao", 200.0);
        assertCalculation("dao2", 300.0);
        assertCalculation("daoFile", 360.0);
        assertCalculation("daoApi", 440.0);
    }

    private void assertCalculation(String target, double expected) {
        AnnotationConfigApplicationContext context =
                new AnnotationConfigApplicationContext();
        context.getEnvironment().getPropertySources().addFirst(
                new MapPropertySource(
                        "testProperties",
                        Collections.singletonMap("dao.target", target)));
        context.register(AppConfig.class);
        context.refresh();

        try {
            IMetier metier = context.getBean(IMetier.class);
            assertEquals(expected, metier.calcul(), 0.001);
        } finally {
            context.close();
        }
    }
}

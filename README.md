# TP 2 : Spring IoC

Projet Maven complet pour le laboratoire Spring IoC et injection de dépendances.

## Prérequis

- JDK 17 ou supérieur
- Maven 3.6 ou supérieur

Le TP mentionne JDK 11+, mais Spring Framework 6.1 nécessite Java 17 minimum.

## Exécution

Depuis la racine du projet :

```shell
mvn clean test
mvn -q exec:java -Dexec.mainClass=com.example.presentation.Presentation2
```

Le premier test de configuration affiche `Résultat = 300.0`, car `MetierImpl`
utilise `@Qualifier("dao2")` (150 × 2).

Pour la configuration XML, la classe `PresentationXML` lit
`src/main/resources/applicationContext.xml` et calcule également 300.0.

## Profils Spring

La variante `PresentationProfile` active `dev` par défaut. On peut sélectionner
un profil explicitement :

```shell
mvn -q exec:java -Dexec.mainClass=com.example.presentation.PresentationProfile -Dexec.args=dev
mvn -q exec:java -Dexec.mainClass=com.example.presentation.PresentationProfile -Dexec.args=prod
```

Le profil `dev` utilise la valeur 150 (résultat 300) et `prod` la valeur 100
(résultat 200). Les implémentations de profils réutilisent le nom de bean
`dao2`, ce qui permet de garder le même service et son `@Qualifier`.

## Structure

- `com.example.dao` : interface DAO et ses deux implémentations
- `com.example.metier` : interface métier et service injecté
- `com.example.presentation` : lancement par annotations, XML et profils
- `src/test/java` : tests unitaires et tests d'intégration Spring

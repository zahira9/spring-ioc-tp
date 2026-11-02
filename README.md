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

## TP 3 : Spring IoC et principe OCP

Le TP3 est ajouté dans le paquet `com.example.tp3`, sans modifier les classes
du TP2. Il présente quatre implémentations de `IDao` (base de production,
base de développement, fichier et API simulée), injectées dans `MetierImpl`
par constructeur.

La propriété `dao.target` choisit l'implémentation dans
`src/main/resources/tp3-app.properties`. La valeur initiale `daoApi` produit
`440.0`. Les valeurs reconnues sont `dao`, `dao2`, `daoFile` et `daoApi`.

Le test `OcpSelectionTest` vérifie le résultat pour les quatre choix :

| `dao.target` | Implémentation | Résultat |
|---|---|---:|
| `dao` | `DaoImpl` | 200.0 |
| `dao2` | `DaoImpl2` | 300.0 |
| `daoFile` | `DaoFile` | 360.0 |
| `daoApi` | `DaoApi` | 440.0 |

Pour tester le TP3 :

```shell
mvn clean test
mvn -q exec:java -Dexec.mainClass=com.example.tp3.presentation.Main
```

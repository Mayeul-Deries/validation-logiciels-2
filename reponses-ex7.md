# Exercice 7

## Commande : `mvn clean`

### Phases du cycle de vie Maven exécutées

- `clean` - suppression du répertoire target/

### Nouveaux fichiers/dossiers dans `target/` :

**Aucun nouveau fichier** - Le répertoire `target/` est complètement supprimé par cette commande.

La commande `mvn clean` supprime tous les artefacts de build précédents (classes compilées, JARs, rapports de tests, etc.). C'est utile pour repartir d'un état propre.

## Commande : `mvn test`

### Phases du cycle de vie Maven exécutées

1. `ressources` - copie les ressources du projet (fichiers non-Java) vers `target/classes`
2. `compile` - compile le code source principal (`src/main/java`)
3. `testResources` - copie les ressources de test vers `target/test-classes`
4. `testCompile` - compile le code source des tests (`src/test/java`)
5. `test` - exécute les tests unitaires

### Nouveaux fichiers/dossiers dans `target/` :

- `target/classes/` - contient les fichiers `.class` compilés du code principal
- `target/generated-sources/` - contient les sources générées automatiquement par des plugins
- `target/generated-test-sources/` - contient les sources de test générées automatiquement
- `target/maven-status/` - contient des informations internes sur l’état de compilation Maven
- `target/test-classes/` - contient les fichiers `.class` des tests compilés

### Observations :

Cette commande compile le code source et les tests, puis exécute uniquement les tests unitaires. Aucun artefact JAR n'est créé.

---

## Commande : `mvn package`

### Phases du cycle de vie Maven exécutées

**Toutes les phases de `mvn test`**

**Plus les phases suivantes :**

- `jar` - **crée le fichier JAR**

### Nouveaux fichiers/dossiers dans `target/` :

Tous les fichiers créés par `mvn test` **plus** :

- `target/maven-archiver/` - contient les métadonnées du JAR (manifest, informations de build)
- `target/maven-status/` - contient les informations de suivi de compilation Maven
- `target/bank-application-1.0-SNAPSHOT.jar` - le fichier JAR contenant l'application empaquetée

### Observations :

Cette commande fait tout ce que fait `mvn test`, mais en plus elle crée l'artefact JAR distribuable. C'est la commande utilisée pour préparer l'application au déploiement.

---

## Commande : `mvn verify`

### Phases du cycle de vie Maven exécutées

**Toutes les phases de `mvn package`**

**Plus les phases suivantes :**

- `verify` - **effectue des vérifications supplémentaires pour valider que le projet respecte les règles de qualité et d’intégrité**

### Différence entre `verify` et `package`

`mvn verify` exécute toutes les phases jusqu’à `package`, puis lance la phase verify.
Contrairement à `test`, qui se limite à l’exécution des tests unitaires, et à `package`, qui produit l’artefact final (JAR), verify sert à effectuer des vérifications supplémentaires de qualité avant une intégration ou un déploiement.

Dans un projet simple, `verify` ne fait souvent rien de plus, mais dans un projet réel il peut inclure des tests d’intégration, des analyses de qualité ou des règles de conformité.

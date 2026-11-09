
### Le sujet :

On doit faire une manipulation d'image.
Ca consiste a faire trois image, l'une a coté de l'autre.
La première image c'est l'original, la deuxieme image est en négative et la troisième image n'a que les contours en noir et le reste en blanc selon un seuil que l'on définie.

### Commandes:

Se mettre dans le dossier principal pour l'utilisation des commandes

#### Générer la documentation:

Nous pouvons générer la documentation de l'ensemble des classes dans un dossier docs en exécutant cette commande : 

```bash
javadoc -sourcepath src -subpackages image -d docs
```

##### Comment compiler les classes du projet ?

Nous pouvons compiler les classes du projet en exécutant cette commande :  

```bash
 javac -sourcepath src src/image/*.java -d classes

```

### Comment compiler les tests du projet ?

```bash
 javac -classpath junit-console.jar:classes test/image/*.java
```

### Comment exécuter les tests du projet ?

```bash
java -jar junit-console.jar -classpath test:classes -scan-classpath
```

##### Comment tester l'exécution du programme ?

Si on n'indique pas de paramètres on a des rectangles de différentes couleurs qui s'affiche.
Le premier paramètre c'est le lien de l'image et le deuxième c'est le seuil.
Si on n'indique pas de seuil on a 10 par défaut.

Sans paramètre:
```bash
java -classpath classes image.ImageMain 
```

 Avec paramètre : 

```bash
java -classpath classes image.ImageMain /images/fruit.pgm 15
```

### Comment créer le jar exécutable 

```bash
jar cvfe image.jar image.ImageMain images -C classes image
```


### Exécution du jar exécutable

Exécution du jar sans paramètres : ce sont les rectangles

```bash
 java -jar image.jar
 ```

Exécution du jar avec des paramètres et un seuil

```bash
java -jar image.jar /images/chat.pgm 15
```

Exécution du jar sans seuil donc un seuil par défaut est mis
```bash
java -jar image.jar /images/body.pgm 
```
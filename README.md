# L’arbre aléatoire

Objectif : Simuler la pousse d’un arbre aléatoire
Implémentation

1. Créer une classe Feuille
   a. Elle dispose d’un poids aléatoire entre 10 et 70 grammes
2. Créer une classe Branche
   a. Elle dispose d’une liste de Branches et de Feuilles, ainsi que de son propre poids, aléatoire entre 250 grammes et 25 Kg
   b. Lors de la construction, on effectue un jet aléatoire entre 1 et 10
   i. A 1, on lui ajoute 5 feuilles
   ii. A 2, on lui ajoute 3 feuilles
   iii. de 3 à 8 compris, la branche ne contient rien
   iv. A 9, la branche contient une branche et 2 feuilles
   v. A 10, la branche contient 2 branches et une feuille
3. Créer une classe Arbre
   a. Lui ajouter un attribut de type Branche appelé tronc
   b. Lui ajouter une méthode permettant d’afficher le poids total de l’arbre
   c. Lui ajouter une méthode permettant de compter le nombre de feuilles
   Questions
4. Quel design-pattern utilise en grande partie l’arbre envers les classes Branche et Feuille ?
5. Quel autre design pattern pourrait également convenir ?

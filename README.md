# Projet Grapheur

Ce projet consiste à développer une application de type Grapheur de fonction en Java, permettant d’afficher graphiquement des fonctions mathématiques a une variable.

Ce rapport va permettre de montrer la manière dont j'ai résolu le projet.

## Le package grapheur

Au niveau graphique, je me suis inspiré de celui qui était donné dans le sujet du projet. On a donc 5 éléments distincts, positionné grâce au **BorderLayout** :

- Au nord : C'est ici que s'afficheront les coordonnées de la souris ;
- À l'ouest : C'est la partie menue du grapheur. On peut y modifier plusieurs paramètres visuels ;
- Au sud : Il s'agit de la partie où l'utilisateur va pouvoir choisir la fonction a évaluer ;
- Au centre : C'est dans ce panel que la courbe est tracé ;
- À l'est : Un simple panel pour éviter que le graphe touche le bord de la fenêtre.

Revenons sur chacun de ses parties pour expliquer leur fonctionnement.

### Nord : Coordonnées de la souris

​	Pour la création de ce panel, je me suis contenté de créer 3 zones de textes et 3 labels afin d'afficher les coordonnées de la souris. Ils ont été placés grâce à la méthode de **FlowLayout**. J'ai aussi créé une méthode par élément afin de changer les valeurs de nos zones de textes.

### Sud : Evaluation de la fonction 

​	Pour cette partie, ainsi que les suivantes, j'ai créé 2 classes : une classe qui s'occupe du visuel et une autre de la partie fonctionnelle.

​	La partie visuel de ce panel se limite à un bouton, un label et une zone de texte. Ces éléments ont été agencés avec un **FlowLayout**. Une méthode a été créée pour cette partie, elle permet de récupérer la fonction qui est entrée dans la zone de texte. J'ai aussi rajouté une écoute d'évenement sur le bouton.

​	Pour la partie fonctionnel, elle impacte les panels du Sud, du Nord et du Centre. La seule méthode qui a été nécessaire était l'action qu'effectue le bouton. Elle va récupérer la fonction rentrée par l'utilisateur. Si elle n'est pas vide, elle va évaluer la fonction grâce à la classe **Evaluateur** (décrite plus tard) avant de l'envoyer au panel du centre, qui va gérer l'affichage.

### Ouest : Gestion de paramètres

​	Au niveau du visuel, ce panel est plus chargé. Nous avons des zones de textes, des labels, des boutons et des cases à cocher. Chacun des éléments de cette partie est rangé à l'aide de **GridBagLayout**. Un layout très pratiques pour ce genre d'interface, car permet de ranger les éléments dans une grille. Pour faciliter davantage leur placement, j'ai créé une méthode qui permet de les ranger selon une contrainte précise. Chacun des éléments de ce menu va avoir une influence sur l'affichage de la fonction. Nous avons donc plusieurs méthodes qui vont permettre : de récupérer les paramètres et de les modifier. On rajoute ensuite des écouteur d'actions sur les boutons et les cases à cocher.

​	La partie fonctionnel va donner des méthodes à chacun des boutons et cases à cocher. Tout d'abord, le bouton "*Rafraîchir*". Il va prendre chacune des valeurs des paramètres afin de les envoyer au panel central pour qu'il mette à jour son affichage. Le bouton "*Réinitialiser*" va réinitialiser les paramètres du centre avant de les récupérer et de les afficher dans le menu. Les boutons "+" et "-" permettent simplement de zoomer sur la fonction. Les valeurs dans le panel ouest sont d'ailleurs mises à jour automatiquement. La case "*AutoPas*", si elle est activée, va activer la méthode **AutoPas** du centre et l'afficher sur l'autoPas. Et enfin, une petite touche personnelle, j'ai rajouté une case "*Thème sombre*" qui va juste rendre l'affichage plus sombre. Il va prendre tous les panels et changer leur couleur.

### Centre : La fonction tracée

​	Attaquons-nous à la partie qui définit ce projet. Il s'agit d'une classe qui possède un grand nombre de méthode. Chacune de ses méthodes va permettre le bon fonctionnement du grapheur et un affichage correct.

​	La classe fonctionnelle du centre permet de rajouter les actions possibles avec la souris ainsi que la modification de la taille de la fenêtre. Grâce à cette classe, on peut donc : zoomer et dézoomer, récupérer les coordonnées de la souris ou encore déplacer le visuel pour se déplacer.

### Résultat

​	Avec tout ça, nous avons donc une fenêtre qui permet d'afficher nos fonctions et de jouer sur les paramètres d'affichage. Mais que se cache derrière cette évaluation de la fonction ?

## La package Eval

​	L'évaluateur est une classe assez complexe qui a pour but de convertir une fonction sous forme de chaîne de caractère en quelque chose d'utilisable pour Java. Cette partie utilise le principe d'Arbre Binaire. En effet, lors du parcours de la fonction d'entrée, on va chercher les opérateurs selon un ordre prédéfini [+, -, sin, *, /]. Et lorsque l'on en trouve un, le code va créer un objet correspondant à celui-ci. Prenons l'exemple de la fonction suivante :
$$
f(x) = 3+sin(x)*x
$$
​	Notre évaluateur va parcourir la chaîne de caractère, trouver les opérateurs, et les classer en Nœud binaire qu'il est capable de comprendre. Ce qui donne ceci :

​	Afin de créer notre arbre binaire, deux classes abstraites ont été nécessaires. Tout d'abord, la classe **Noeud**, qui implémente deux méthode **eval()** et **toString()**. La seconde est **Noeud Binaire**, qui est une classe enfant de **Noeud**. Elle prend en paramètre 2 **Noeud **et possède les même méthode que ce dernier. 

### La classe Noeud

​	La classe **Noeud** permet la création des éléments de l'arbre n'ayant qu'une variable. Ça permet d'intégrer les valeurs, les variables ainsi que les fonctions de types sinus ou cosinus. Si on prend l'exemple des variables, ça donne ceci :

```java
public class Variable extends Noeud {
	public Variable() {
	}
	
	public float eval(float x) {
		return x;
	}
	
	public String toString() {
		return "x";
	}
}
```

​	On voit bien que **Variable** est hérité de **Noeud** et qu'elle possède les deux méthodes : **eval()** pour qu'il puisse renvoyer sa valeur et **toString()** qui permettra d'afficher un "x".

### La classe NoeudBinaire

​	Quant à cette classe, elle va permettre d'intégrer la notion d'opérateur. On aura donc les opérateurs mathématiques de base comme "+", "-", "/" et "*". Prenons l'exemple de la multiplication :

```java
public class Mult extends NoeudBinaire {
		public Mult(Noeud fg, Noeud fd) {
			super(fg, fd);
		}
		public float eval(float x) {
			return fg.eval(x) * fd.eval(x);
		}
		public String toString() {
			return "("+fg.toString()  + " * " + fd.toString()+")";
		}
}
```

​	On a donc en paramètre deux **Noeud**, qui seront donc soit des valeurs, des variables, des fonctions, mais aussi d'autre opérateurs. Sa méthode **eval()** va renvoyer le résultat de la multiplication des deux **Noeuds** et **toString()** affiche le calcul de manière compréhensible par l'utilisateur.

### La classe Evaluateur

​	Et enfin, la classe qui englobe ce package : la classe **Evaluateur**. Elle va analyser la chaîne de caractère et créer l'arbre au fur et à mesure afin de renvoyer le résultat. Voici le résultat :

![image-20211126232632264](C:\Users\lebre\AppData\Roaming\Typora\typora-user-images\image-20211126232632264.png)
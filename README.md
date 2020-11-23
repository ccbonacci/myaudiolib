Liens des cours : 

https://pjvilloud.github.io/ipi-java-320-cours/index.html#/accueil

https://pjvilloud.github.io/ipi-java-310-cours/#/accueil

https://pjvilloud.github.io/ipi-java-330-th-cours/#/accueil

- Récupérer le client Web : https://github.com/pjvilloud/ipi-mdd-050-eval-web/releases/download/V1.2/ipi-mdd-050-eval-web-v1.2.zip
- Paramétrer votre application pour qu'elle puisse se connecter à la base de données précédemment créée (modification du fichier application.properties, vous pouvez vous inspirer de celui du TP)
- Créer les classes (dans le bon package) permettant de modéliser les objets de l'application (les tables de la base de données) et mettre en place le mapping adéquat.
- Créer les repository (dans le bon package) permettant d'accéder aux données de la base
- Créer éventuellement un MyRunner (comme dans le TP) pour tester vos repository et s'assurer que la récupération de données se passent bien

Ajouter les classes nécessaires (avec les URLs standards) afin de créer un service web (URL de base /api) permettant de :
- Afficher un artiste (gérer les 404)
- Recherche par nom (exemple lorsqu'on recherche le nom "rosmi" dans la barre de recherche, on obtient bien une liste paginée de résultats avec "Aerosmith" ainsi que sur "Aerosmith & Sierra Leone's Refugee Allstars". Lorsqu'on recherche un artiste inexistant commme "ABCDEF", on obtient une liste vide).
- Liste des artistes avec pagination
- Création d'un artiste (gestion de l'erreur 409 s'il existe déjà un artiste de même nom)
- Modification d'un artiste (Méthode PUT avec gestion des 404)
- Suppression d'un artiste (gérer les 404 et le bon code HTTP de retour, gérer de manière cohérente le cascading...)
- Ajout d'un album à un artiste (gérer les 404 et les 409 en cas d'album déjà existant).
- Suppression d'un album (gérer les 404)

Ajouter les classes et templates nécessaires afin de créer une application Web avec Thymeleaf permettant d'effectuer les fonctionnalités suivantes (s'aider des pages HTML statiques fournies, le "look" de votre application ne sera pas évalué, uniquement le contenu)
- Page d'accueil avec liste paginée et triable des artistes avec lien pour chaque ligne redirigeant vers le détail d'un artiste
- Barre de menu permettant la recherche par nom (fonctionnalités identiques au service web) si l'artiste est trouvé on le redirige vers la page de détail de l'artiste sinon on affiche un message "Aucun résultat trouvé pour '...'"
- Page de détail de l'artiste permettant la consultation et la modification de l'artiste, l'affichage de ses albums (et du coup la possibilité d'ajouter/supprimer/modifier les albums) et la suppression de l'artiste (on redirige sur la page d'accueil une fois la suppression effectuée)
- Page de création (pratiquement identique à la page de détail)

Plus de précisions à venir sur la partie Web de l'évaluation.

Pour rappel, les liens dont vous pourrez avoir besoin pour les cours à venir :
- https://pjvilloud.github.io/ipi-java-210-cours/ Bases de Java
- https://pjvilloud.github.io/ipi-java-220-cours/ POO
- https://pjvilloud.github.io/ipi-java-230-cours/ BDD avec Java
- https://pjvilloud.github.io/ipi-java-310-cours/ Cours sur le web en général
- https://pjvilloud.github.io/ipi-java-320-cours/ Cours sur les Servlet et Spring
- https://pjvilloud.github.io/ipi-mdd-050-cours/ Quelques slides supplémentaires sur la gestion des erreurs et des cas particuliers
- https://pjvilloud.github.io/ipi-java-330-th-cours/ Cours sur Thymeleaf

- https://github.com/pjvilloud/ipi-mdd-050-ex TP API REST
- https://github.com/pjvilloud/ipi-java-330-th-ex TP Thymeleaf

A bientôt !

PS1 : C'est possible qu'il faille modifier le server.port dans application.properties au port demandé par le client : 5366?
server.port=5366

PS2 : Vous allez sans doute rencontrer un problème de récursion infinie dans votre mapping car effectivement, lorsque vous allez afficher un artiste, vous afficherez ses albums, mais quand vous afficherez un album, vous afficherez aussi son artiste, mais quand vous afficherez l'artiste... Bref cela provoque la récursion. Pour éviter cela, le plus simple consiste à ajouter l'annotation @JsonIgnoreProperties("artist"). Cette annotation aura pour effet de briser cette récursion et est à placer au niveau de ton mapping dans la classe Artiste au niveau de l'attribut contenant l'ensemble des albums de l'artiste.


Pour faire marcher la partie web, il faut également que les attributs soient nommés :


Pour artiste
- id
- name
- albums


Pour album :
- id
- title
- artist

PS3 : Attention pour la recherche par nom, il y a aussi les paramètres de pagination qui sont transmis, si bien que la requête envoyée ressemble à http://localhost:5366/artists?name=fdgfg&page=0&size=10&sortDirection=ASC&sortProperty=name
Il faut donc au niveau de votre contrôleur renvoyer une Page d'Artists et non pas une liste

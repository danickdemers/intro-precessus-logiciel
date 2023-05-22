# TP3

_____

### Captures d'écrans
Projet:
![image](https://user-images.githubusercontent.com/46549578/161325093-9cd5fa0b-b94a-4c81-8fc4-c5cbd64da08a.png)

Milestone:
![image](https://user-images.githubusercontent.com/46549578/161325416-52e9e41c-1663-4c81-b2b7-ae1b4629070f.png)
![image](https://user-images.githubusercontent.com/46549578/161325475-b6634b58-1e3c-47ac-af80-8a4181fc6597.png)

Issues:
1.
![image](https://user-images.githubusercontent.com/46549578/161325682-58564a47-826a-458a-aeb1-4cdc64aafa50.png)
![image](https://user-images.githubusercontent.com/46549578/161325763-b441b64f-ebd7-47f0-b48e-7ef66e178300.png)

2.
![image](https://user-images.githubusercontent.com/46549578/161325979-4b92f244-4232-47e9-a8d9-638435d0ec64.png)
![image](https://user-images.githubusercontent.com/46549578/161326040-5c43aa19-6754-4787-acd8-aaece29c3c46.png)

3.
![image](https://user-images.githubusercontent.com/46549578/161326287-3c433f0b-1fc9-401f-97a5-2e7c463d8455.png)
![image](https://user-images.githubusercontent.com/46549578/161326328-1f1b8cf4-340f-4626-8f31-4bbff9bd84af.png)

PR:
1.
![image](https://user-images.githubusercontent.com/46549578/161326473-b23f8b6f-84ca-4a76-96cc-e5e9c254bbe3.png)
![image](https://user-images.githubusercontent.com/46549578/161326567-876a1843-085a-4701-b485-3c2a350801a1.png)
![image](https://user-images.githubusercontent.com/46549578/161326606-8c9d890b-c600-4b24-8625-880b1392dd53.png)

2.
![image](https://user-images.githubusercontent.com/46549578/161326695-f5dda651-cc3e-491c-bcaa-f21d770290cb.png)
![image](https://user-images.githubusercontent.com/46549578/161326757-f5da8b83-4b97-4e5f-ad06-ec0c97c111de.png)

3.
![image](https://user-images.githubusercontent.com/46549578/161326841-b772d126-29ec-4bfe-9118-1e048f6a0db6.png)

Arbre de commit:
![image](https://user-images.githubusercontent.com/46549578/161327099-d3d9baa1-69ce-464f-96ae-c02e8de74f6a.png)



### Stories
*1* : Un vendeur peut créer une enchère pour un produit sur lequel un utilisateur peut faire une offre qui surpasse l'offres précédente dans un délai déterminé.  
*2* : Un utilisateur de l’api peut entrer une évaluation pour un vendeur et obtenir la moyenne de ceux-ci.  
*3* : Un utilisateur de l’api peut créer un fil de discussion permettant d’échanger des messages concernant un produit.  

### Rétrospective - Intégration continue et tests
# Pipeline CI
**Combien de temps passiez-vous à vérifier et tester manuellement le code lors des intégrations et des remises avant l'implantation du pipeline de tests automatisés?** 
Notre équipe passait 10-15 mins pour les PR et 1h avant la remise complete du projet.  
  
**Combien de temps passiez-vous à faire ces vérifications après l'implantation du CI?**
Notre équipe passait aussi 10-15 mins pour les PR et 1h avant la remise complete du projet puisque nous testions toujours mannuellement.  

**Quels sont les points positifs que le CI a apporté à votre processus? Donnez-en au moins 3.**  
-Le checkstyle était automatique et évite de perdre des points pour le clean code.  
-Empêche de détruite l'intégrité de l'architecture en décourageant des changements rapides par un seul membre de l'équipe.  
-Nous pouvons avoir une rétroaction sur la qualité du code avant de commit des changements, réduisant le nombre total des commits de type fix.

**Le pipeline CI amène-t-il un élément qui pourrait devenir négatif ou dangeureux pour le processus, le produit et/ou l'équipe? Justifiez.**  
L'ajout de fonctionnalités majeures demande de modifier plusieurs tests. De plus, nous pouvons devenir dépendant du pipeline CI et l'équipe pourrait baisser leur guarde et être susceptible à moin tester le code fonctionnel et produire moin de tests d'exploration de vulnérabilité.  
  
# Tests  
**Quel proportion de temps passez-vous à faire l'implémentation du code fonctionnel versus celui des tests? Est-ce que cette proportion évolue au fil du temps? Pourquoi?**
Nous passions un peu plus de temps sur les tests que sur le code fonctionnel et ce pour la majorité des fonctions du système. Dans notre cas, la proportion n'a pas changé au fil du temps. Notre équipe avait déja une bonne expérience avec la matière des tests unitaires en début de projet, donc il y a eu peu d'évolution au cours de ce projet.
  
**L'implémentation de tests augmente naturellement la charge de travail. Comment cela a-t-il affecté votre processus? (ex : taille des issues/PRs, temps d'implémentation, planification, etc.)**  
L'implementation des tests était souvent sous-estimée. Ils poussait souvent la remise d'une fonctionnalité puisque notre équipe étions en accord que les tests devaient être implementé avec le code fonctionnel en tout temps. Donc une fonctionnalité dépendante était souvent reportée. De plus, des tests courants qui échouent démotivait souvent l'équipe.  
  
**Avez-vous plus confiance en votre code maintenant que vous avez des tests? Justifiez.**  
Pas nécessairement. Si les tests que nous implementions étaient plus variés et cherchent les vulnérabilités de l'api nous aurrions peut être été plus confiants. Par contre, nos tests étaient souvent isolés/trop unitaires et ne visaient que l'essentiel.  
  
**Que pouvez-vous faire pour améliorer l'état actuel (début TP2) de vos tests? Donnez au moins 3 solutions.**  
-Ajouter des tests unitaires plus "exotiques" pour trouver les vulnérabilités du système.  
-Avoir des tests e2e qui prend des parcours différents.  
-Nous pourrions écrire certains tests avant d'implementer le code. Donc faire du test driven development.

Projet de Simulation de Robots Miniers 

Objectif

Dans la continuité des projets SAÉ 1.01 et SAÉ 1.02, ce projet porte sur la conception et la simulation de robots miniers opérant dans un environnement inédit.

Description du Projet:

Mines et Entrepôts:

Le monde du projet est parsemé de mines, chacune renfermant entre 50 et 100 minerais de nickel ou d’or. Il existe deux types de mines : les mines de nickel (1 ou 2) et les mines d’or (1 ou 2). Les minerais extraits sont stockés dans deux entrepôts distincts : un pour l’or et un pour le nickel.

Robots Miniers:

Le monde est exploré par des robots miniers (au moins un et au plus cinq pour chaque type de minerai). Chaque robot est spécialisé dans l’extraction d’un type de minerai (nickel ou or). Ils ont pour mission d’explorer le monde, d’extraire les minerais des mines et de les transporter vers les entrepôts correspondants. Chaque robot possède une capacité de stockage de minerais (entre 5 et 9) et une capacité d’extraction de minerais (entre 1 et 3).

Monde:

L’environnement est une grille de 100 secteurs (10 x 10), composée de terrains (au moins 90) et de plans d’eau. Les robots peuvent se déplacer sur les terrains non occupés par d’autres robots, mais ne peuvent pas traverser les plans d’eau. Un terrain peut être vide, contenir un entrepôt ou une mine, mais jamais les deux à la fois.

Actions des Robots:  

Les robots peuvent effectuer les actions suivantes :

•	Se déplacer dans une direction (Nord, Est, Sud, Ouest),

•	Extraire des minerais s’ils se trouvent dans une mine correspondant à leur spécialité et s’il reste des minerais. Ils extraient autant de minerais que leur capacité d’extraction le permet, ou le reste si la quantité de minerais est insuffisante.

•	Déposer leurs minerais s’ils se trouvent dans l’entrepôt correspondant à leur spécialité.

Chaque robot, chaque mine et chaque entrepôt possèdent un numéro unique. À chaque tour, chaque robot peut effectuer une seule action valide.

Pour voir nos livrables de cette SAE , il suffit d'aller dans le dossier livrable de la branche master pour les voirs.


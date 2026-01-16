# Exercice 8

Les tests couvrent les cas normaux et les cas limites des méthodes `depositMoney` et `withdrawMoney`.
La méthode privée `canWithdraw` est testée indirectement via `withdrawMoney`.
Chaque règle métier (montant négatif, dépassement du solde, limite de retrait) est validée par au moins un test.
Tous les tests passent après exécution de `mvn test`.

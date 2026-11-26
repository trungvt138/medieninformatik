# Filmreviews klassifizieren

Öffne die Notebooks in der angegebenen Reihenfolge. Oben findest du die Leitfrage und Links zu deinen Aufgaben. Führe die Zellen von oben nach unten aus. An den mit `# TODO` markierten Stellen ergänzt du den Code. Jedes Notebook lässt sich mit einem frischen Kernel starten.

| Notebook | Inhalt |
| --- | --- |
| [0. CountVectorizer](00_count_vectorizer.ipynb) | Sage die Wortzählungen von C und D voraus. Vergleiche die vollständige Tabelle mit den gespeicherten Einträgen der dünnbesetzten Matrix. |
| [1. Vektorähnlichkeit](01_vector_similarity.ipynb) | Kurze Filmreviews in Wortzählungen übersetzen und das Urteil der nächsten Review übernehmen. |
| [2. kNN mit Kurzreviews](02_knn.ipynb) | Klassifiziere zwei neue Kurzreviews und erkläre ein Urteil mit den Stimmen der drei Nachbarn. |
| [3. kNN auf IMDb, Modellwahl und Evaluation](03_model_selection.ipynb) | Demonstration: IMDb-Dateien laden und eine erste Trefferquote bestimmen. Übung: Vergleiche eigene Einstellungen auf dem Validierungsset, wähle ein Modell und prüfe deine Prognose am Testset. |
| [4. Kennzahlen pro Klasse](04_evaluation_metrics.ipynb) | Zähle die vier Fälle der Konfusionsmatrix mit der Gruppe. Implementiere anschließend Precision, Recall und F₁, vergleiche beide Klassen und prüfe die Ergebnisse mit scikit-learn. |

Die [Kursdaten](data/imdb/README.md) liegen lokal vor. Für den Start auf deinem Rechner folge der [Anleitung für Python und JupyterLab](local/README.md).

# IMDb-Kursauswahl

Jede UTF-8-Textdatei enthält eine Filmreview. Der Ordnername liefert das Label: `pos` bedeutet positiv, `neg` negativ. Zum Beispiel enthält `train/pos/001.txt` die erste Trainingsreview. Jeder Dateiname ist in der gesamten Kursauswahl eindeutig. Die Trainingsreviews heißen `001.txt` bis `500.txt`, die Validierungsreviews `501.txt` bis `600.txt` und die Testreviews `601.txt` bis `700.txt`. Der Klassifikator erhält als Eingabe nur den Text. HTML-Zeilenumbrüche wurden durch Leerzeichen ersetzt.

| Ordner | Reviews | Verwendung |
| --- | ---: | --- |
| [train/](train/) | 500 | Wortschatz bestimmen und den Klassifikator trainieren |
| [validation/](validation/) | 100 | Einstellungen vergleichen und Fehler untersuchen |
| [test/](test/) | 100 | Gewählte Einstellung zum Abschluss auswerten |

[Testvorhersagen für Notebook 04](reference_test_predictions.csv): Jede Zeile enthält `file_name`, `actual` und `predicted`. Mit dem tatsächlichen Label und dem Dateinamen findest du die Review unter `test/<actual>/<file_name>`, zum Beispiel [test/pos/601.txt](test/pos/601.txt). Die Reihenfolge entspricht den Testdateien, sortiert nach Dateinummer. Die Werte stammen aus dem Referenzlauf von [Notebook 03](../../03_model_selection.ipynb) mit Einzelwortzählungen, sieben Nachbarn, Kosinusdistanz und gleichen Stimmgewichten. Der Export am Ende des Notebooks speichert den eigenen Lauf im selben Ordner als `my_test_predictions.csv`. Um diese Vorhersagen auszuwerten, wähle den Dateinamen in Abschnitt 1.1 von [Notebook 04](../../04_evaluation_metrics.ipynb).

Alle Teilmengen enthalten gleich viele positive und negative Reviews. Die Texte stammen aus dem Stanford Large Movie Review Dataset v1.0. Die Originalbeschreibung verlangt die Zitation des Papers und nennt keine ausdrückliche Lizenz. [Maas et al. (2011), *Learning Word Vectors for Sentiment Analysis*](https://aclanthology.org/P11-1015/) · [Datensatzbeschreibung der Autoren](https://ai.stanford.edu/~amaas/data/sentiment/).

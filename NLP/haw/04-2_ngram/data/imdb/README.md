# IMDb-Kursauswahl

Jede UTF-8-Textdatei enthält eine Filmreview. Der Ordnername gibt das Label an: `pos` für positiv und `neg` für negativ. Die Review [train/pos/001.txt](train/pos/001.txt) hat beispielsweise das Label `pos`. Der Klassifikator erhält nur den Text als Eingabe. HTML-Zeilenumbrüche wurden durch Leerzeichen ersetzt.

| Ordner und Dateinamen | Reviews | Verwendung |
| --- | ---: | --- |
| [train/](train/) · `001.txt` bis `500.txt` | 500 | Wortschatz lernen, kNN trainieren und die Folgewörter für die Sprachmodelle zählen |
| [validation/](validation/) · `501.txt` bis `600.txt` | 100 | In Notebook 02 die kNN-Vorhersagen vergleichen und in Notebook 04 die Perplexität der Sprachmodelle bestimmen |

Alle Teilmengen enthalten gleich viele positive und negative Reviews. Die Texte stammen aus dem Stanford Large Movie Review Dataset v1.0. Die Originalbeschreibung verlangt die Zitation des Papers und nennt keine ausdrückliche Lizenz. [Maas et al. (2011), *Learning Word Vectors for Sentiment Analysis*](https://aclanthology.org/P11-1015/) · [Datensatzbeschreibung der Autoren](https://ai.stanford.edu/~amaas/data/sentiment/).

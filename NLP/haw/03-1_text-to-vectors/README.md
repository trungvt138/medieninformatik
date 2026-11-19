# Tokenisierung, Normalisierung und Bag of Words

Wie lässt sich aus einem Text ablesen, ob jemand einen Film mochte? Du zerlegst englische Filmreviews in Wörter, bewertest sie mit Wortlisten und stellst sie als Zählvektoren dar.

| Datei | Inhalt |
| --- | --- |
| [01_tokenization.ipynb](01_tokenization.ipynb) | Vergleiche verschiedene Tokenisierungen desselben Satzes. Zähle die Wortvorkommen und bestimme das Vokabular. |
| [02_movie_reviews.ipynb](02_movie_reviews.ipynb) | Lies Filmreviews aus Textdateien ein, untersuche häufige Wörter und bewerte die Texte mit einer positiven und einer negativen Wortliste. |
| [03_normalization.ipynb](03_normalization.ipynb) | Wende Stemming und Lemmatisierung auf Reviews und Wortlisten an. Prüfe, welche Treffer hinzukommen und wie sich die Bewertungen ändern. |
| [04_bag_of_words.ipynb](04_bag_of_words.ipynb) | Wandle Review-Texte in Zählvektoren um und untersuche, wann unterschiedliche Texte denselben Vektor ergeben. |
| [data/imdb/](data/imdb/README.md) | 100 Filmreviews für die Notebooks 02–04, je 50 positive und negative. |
| [local/](local/README.md) | Anleitung für den lokalen Start mit Python oder Docker. |

## Schnellstart im JupyterHub

1. Öffne den [JupyterHub der Hochschule](https://jupyterhub.informatik.haw-hamburg.de), melde dich mit deiner HAW-Kennung an und wähle eine CPU-Umgebung. Weitere Hinweise findest du in der [HAW-Anleitung zu JupyterHub](https://doc.inf.haw-hamburg.de/Dienste/jupyterhub/).
2. Öffne ein Terminal in JupyterLab. Kopiere die Klon-Adresse des Studierenden-Repositories aus GitLab und setze sie in den Befehl ein.

   ```bash
   git clone '<git-clone-url>'
   ```

   Alternativ kannst du das Repository als ZIP-Datei herunterladen und entpackt in JupyterLab hochladen. Behalte dabei die Ordnerstruktur mit `data/` bei.

3. Öffne im Dateibrowser `01_tokenization.ipynb`, wähle bei Bedarf den Kernel **Python 3** und führe die Zellen der Reihe nach aus.

Die Grundübungen verwenden die Python-Standardbibliothek. Für die optionale Vertiefung in Abschnitt 4 der Notebooks 03 und 04 brauchst du **NLTK**. Beide nutzen den Porter-Stemmer. Notebook 03 verwendet zusätzlich WordNet und ein englisches Modell zur Wortartbestimmung. Die Startzellen im jeweiligen Abschnitt bereiten die benötigten Pakete und Daten vor.

## Auf dem eigenen Rechner arbeiten

Klone das Repository oder entpacke das ZIP-Archiv. Die [lokale Startanleitung](local/README.md) zeigt dir, wie du JupyterLab und NLTK mit Python oder Docker einrichtest.

# Wortähnlichkeit und Rechtschreibkorrektur

In den Notebooks vergleichst du Wörter anhand ihrer Zeichen und nutzt die
Levenshtein-Distanz, um Korrekturvorschläge für unbekannte Wörter zu finden.

| Datei | Inhalt |
| --- | --- |
| [01_word_similarity.ipynb](01_word_similarity.ipynb) | Gleichheit, Hamming-Distanz, Jaccard-Ähnlichkeit, längster gemeinsamer Substring, längste gemeinsame Teilsequenz und Levenshtein-Distanz. Ergänze die Funktionsgerüste und prüfe deine Lösungen mit den Testzellen. |
| [02_dictionary_check.ipynb](02_dictionary_check.ipynb) | Erstelle ein Wörterbuch aus Textdateien und korrigiere einen Beispieltext mit Levenshtein-Distanz. |
| [data/](data/) | Trainingsgeschichten und ein Text mit Schreibfehlern für die Wörterbuchprüfung. |
| [local/](local/README.md) | Optionaler lokaler Start mit Python oder Docker. |

## Schnellstart im JupyterHub

1. Öffne den [JupyterHub der Hochschule](https://jupyterhub.informatik.haw-hamburg.de) im Browser, melde dich mit deiner HAW-Kennung an und wähle eine CPU-Umgebung. Details zum Zugang stehen in der [HAW-Anleitung zu JupyterHub](https://doc.inf.haw-hamburg.de/Dienste/jupyterhub/).
2. Klone dieses Repository im JupyterLab-Terminal. Kopiere dafür die Klon-Adresse aus GitLab und ersetze den Platzhalter.

   ```bash
   git clone '<git-clone-url>'
   ```

   Alternativ kannst du das vollständige Repository als ZIP-Datei aus GitLab
   herunterladen, entpacken und die Dateien im JupyterLab hochladen. Für das
   zweite Notebook benötigst du auch den Ordner `data/`.
3. Öffne `01_word_similarity.ipynb` im Dateibrowser. Wähle bei Bedarf den
   Kernel **Python 3** und führe die Zellen der Reihe nach aus. Die Testzellen
   in beiden Notebooks funktionieren, sobald du die jeweilige Funktion ergänzt hast.

Im JupyterHub ist JupyterLab bereits eingerichtet. Für die beiden Notebooks
reicht der vorhandene Python-Kernel.

## Auf dem eigenen Rechner arbeiten

Klone das Repository lokal oder lade es als ZIP-Datei aus GitLab herunter und
entpacke es. Die [Kurzbefehle für den lokalen Start](local/README.md) findest
du unter `local/`.

# Lokal starten

Führe die Befehle im Repository-Ordner, also von einer Ebene über `local/`, aus.
Du benötigst Python ab Version 3.10. JupyterLab öffnet die Notebooks im Browser. Notebook 03 verwendet in der Vertiefung WordNet und ein englisches Modell für die Wortartbestimmung. Notebook 04 nutzt den Porter-Stemmer aus NLTK. Für die erste Installation und die Sprachdaten brauchst du Internetzugang.

## Python-Installation mit `venv`

### Windows (PowerShell)

```powershell
python -m venv venv
.\venv\Scripts\activate
python -m pip install -r local/requirements.txt
python -m nltk.downloader -e wordnet averaged_perceptron_tagger_eng
python -m jupyterlab --no-browser
```

### macOS oder Linux

```bash
python3 -m venv venv
source venv/bin/activate
python -m pip install -r local/requirements.txt
python -m nltk.downloader -e wordnet averaged_perceptron_tagger_eng
python -m jupyterlab --no-browser
```

## Docker Compose

Wechsle vom Repository-Ordner in `local/` und starte dort JupyterLab. Der Build installiert NLTK und lädt beide Sprachressourcen in das Image. Deine Notebooks und Änderungen liegen im Repository auf deinem Rechner.

```sh
cd local
docker compose up --build
```

## Zugriff auf das JupyterLab

Der letzte Befehl der obigen Code-Blöcke gibt eine URL mit Token aus.
Kopiere diese URL in den Browser, um die Webansicht des JupyterLabs zu öffnen.

Wenn du eine IDE mit `venv` verwendest, wähle dort nach der Installation den Python-Kernel aus:

- Windows:  `venv/Scripts/python.exe`
- macOS oder Linux: `venv/bin/python`

und öffne die Jupyter-Notebooks innerhalb der IDE per Doppelklick.

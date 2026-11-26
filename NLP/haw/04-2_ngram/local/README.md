# Lokal starten

Starte im Ordner mit den vier Notebooks. Im HAW-JupyterHub beginnst du mit [Notebook 01](../01_word_ngrams.ipynb) in einem Python-Kernel. Die Installationszelle in [Notebook 02](../02_knn_ngrams.ipynb) richtet scikit-learn ein. Für Notebook 01, den [Generator](../03_ngram_language_model.ipynb) und die [Sprachmodellbewertung](../04_language_model_evaluation.ipynb) genügt die Python-Standardbibliothek.

## Python-Umgebung mit venv

Verwende Python 3.12 oder 3.13. Die [Paketliste](requirements.txt) enthält JupyterLab und scikit-learn.

### Windows (PowerShell)

```powershell
python -m venv venv
.\venv\Scripts\Activate.ps1
python -m pip install -r local/requirements.txt
python -m jupyterlab --no-browser
```

### macOS oder Linux

```sh
python3 -m venv venv
source venv/bin/activate
python -m pip install -r local/requirements.txt
python -m jupyterlab --no-browser
```

## Docker Compose

Starte Docker. Führe im Ordner mit den Notebooks die folgenden Befehle aus:

```sh
cd local
docker compose up --build
```

Der Container bindet den Ordner mit den vier Notebooks und den Kursdaten ein. Änderungen an den Notebooks bleiben auf deinem Rechner gespeichert. Mit Strg+C beendest du den Lauf.

## JupyterLab oder IDE öffnen

Öffne die im Terminal ausgegebene Adresse mit `127.0.0.1` und Token in deinem Browser und öffne ein Notebook aus der [Übersicht](../README.md).

Für eine IDE mit venv wählst du den Kernel deiner gerade erstellten Umgebung. Unter Windows ist das `venv/Scripts/python.exe`, unter macOS und Linux `venv/bin/python`.

# Lokal starten

Starte im Repository-Ordner, in dem die fünf Notebooks liegen. Wähle entweder eine Python-Umgebung mit `venv` oder Docker Compose. Im HAW-JupyterHub kannst du direkt mit [Notebook 00](../00_count_vectorizer.ipynb) beginnen. Die erste Codezelle installiert scikit-learn im aktiven Kernel.

## Python-Installation mit `venv`

Verwende Python 3.12 oder 3.13. Die [Paketliste](requirements.txt) enthält JupyterLab und scikit-learn.

### Windows (PowerShell)

```powershell
python -m venv venv
.\venv\Scripts\Activate.ps1
python -m pip install -r local/requirements.txt
python -m jupyterlab --no-browser
```

### macOS oder Linux

```bash
python3 -m venv venv
source venv/bin/activate
python -m pip install -r local/requirements.txt
python -m jupyterlab --no-browser
```

## Docker Compose

Starte Docker und führe die folgenden Befehle im Repository-Ordner aus. Beim ersten Start baut Docker das Image mit Python, JupyterLab und scikit-learn. Der Container bindet das Repository als Arbeitsordner ein. Änderungen an den Notebooks und eigene Vorhersagen bleiben auf deinem Rechner gespeichert.

```sh
cd local
docker compose up --build
```

## JupyterLab oder IDE öffnen

Kopiere die im Terminal ausgegebene URL mit `127.0.0.1` und Token in deinen Browser. Öffne dort [00_count_vectorizer.ipynb](../00_count_vectorizer.ipynb).

Wenn du die Notebooks mit `venv` in einer IDE öffnest, wähle den Python-Kernel der gerade erstellten Umgebung:

- Windows: `venv/Scripts/python.exe`
- macOS oder Linux: `venv/bin/python`

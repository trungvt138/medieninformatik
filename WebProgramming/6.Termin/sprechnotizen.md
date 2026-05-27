# Sprechnotizen – State Management in React (20 Min)

## Folie 1 – Titel (30 Sek)
"Hi zusammen, ich präsentiere heute State Management in React. Wir gehen von den Basics bis zu globalen Lösungen – also alles was ihr braucht um State in euren Projekten sauber zu verwalten."

## Folie 2 – Agenda (30 Sek)
"Kurzer Überblick: Wir starten mit dem Problem – warum brauchen wir State Management überhaupt? Dann gehen wir durch die React-eigenen Tools – useState kennt ihr ja schon, dann useReducer und Context API. Am Ende schauen wir uns kurz externe Libraries an und ich geb euch ne Faustregel wann man was nimmt."

## Folie 3 – Warum State Management? (1,5 Min)
"State sind die Daten die sich ändern während die App läuft. Das kann alles sein – was der User in ein Formular tippt, Daten die wir vom Server holen, ob ein Modal gerade offen ist, oder ob jemand eingeloggt ist. Das Problem: In einer großen App brauchen viele verschiedene Komponenten denselben State. Und da wird's schnell unübersichtlich."

## Folie 4 – Prop Drilling (2 Min)
"Prop Drilling heißt: Wir reichen Props durch jede Ebene der Komponentenhierarchie weiter. Hier im Beispiel: App hat den User-State. UserAvatar ganz unten braucht den. Aber dazwischen liegen Layout und Sidebar – die brauchen den User überhaupt nicht. Die reichen ihn nur durch. Bei 2-3 Ebenen geht das noch, aber stellt euch vor da sind 5 oder 6 Ebenen dazwischen. Das wird schnell zum Wartungsproblem – wenn sich die Props ändern, muss man überall anpassen."

## Folie 5 – useState Recap (1,5 Min)
"Kurzer Recap: useState kennt ihr schon – einfach, intuitiv, perfekt für lokalen State. Counter, Toggle, ein einzelner String – dafür ist useState super. Aber es hat Grenzen: Wenn ihr mehrere States habt die zusammengehören, wenn Updates voneinander abhängen, oder wenn eine andere Komponente den State braucht."

## Folie 6 – useState Grenzen Code (2 Min)
"Hier seht ihr das Problem konkret. Wir haben 3 States: items, loading, error. Die gehören zusammen – es ist ein Fetch-Vorgang. Aber bei jedem Schritt müssen wir alle 3 einzeln setzen. setLoading true, setError null – das vergisst man leicht. Und wenn ein Fehler kommt, muss man wieder setLoading false setzen. Das ist fehleranfällig und unübersichtlich. Genau dafür gibt's useReducer."

## Folie 7 – useReducer Konzept (2 Min)
"useReducer hat 3 Teile. State – ein einziges Objekt mit allem Zustand. Action – ein Zettel der sagt was passieren soll, zum Beispiel type fetch_start. Und der Reducer – eine Funktion die den alten State plus die Action nimmt und den neuen State zurückgibt. Statt 3 mal setState zu rufen, schicken wir eine einzige Action: dispatch fetch_start. Der Reducer weiß dann: loading auf true, error auf null. Alles in einem Schritt, keine Chance was zu vergessen."

## Folie 8 – useReducer Code (1,5 Min)
"So sieht das konkret aus. Der Reducer hat einen switch-case für jeden Action-Type. fetch_start setzt loading true und error null. fetch_success setzt die Items und loading false. fetch_error setzt die Fehlermeldung. Alles konsistent, an einer Stelle, und super einfach zu testen – der Reducer ist ja nur eine reine Funktion."

## Folie 9 – Context API (2 Min)
"Context API löst das Prop Drilling Problem. In 3 Schritten: Erstens – Context erstellen mit createContext. Zweitens – den Provider um eure App oder einen Teil davon wrappen und den Wert übergeben. Drittens – in jeder beliebigen Komponente, egal wie tief verschachtelt, mit useContext direkt auf den Wert zugreifen. Kein Props-Weiterreichen mehr nötig."

## Folie 10 – Context vs Prop Drilling (1 Min)
"Links seht ihr nochmal das Prop Drilling – der User wird durch jede Ebene gereicht. Rechts mit Context: Der Provider wrapped alles, und Avatar kann direkt zugreifen. Layout und Sidebar müssen nichts mehr weiterreichen."

## Folie 11 – Context Grenzen (1,5 Min)
"Aber Context hat auch Grenzen. Erstens: Jede Änderung am Context-Wert rendert alle Consumer neu – auch wenn sie nur einen Teil brauchen. Zweitens: Für schnelle Updates wie Slider oder Drag and Drop ist Context zu langsam. Drittens: Viele Contexts führen zu Provider Hell – Provider in Provider in Provider. Trotzdem perfekt für Sachen die sich selten ändern: Theme, Auth, Sprache."

## Folie 12 – Externe Libraries (2 Min)
"Wenn Context nicht reicht, gibt es externe Libraries. Redux ist der Klassiker seit 2015 – sehr mächtig, globaler Store, aber relativ viel Boilerplate. Redux Toolkit macht das deutlich angenehmer. Zustand ist der moderne Trend – minimal API, kein Provider nötig, und die Syntax ist extrem einfach. Hier seht ihr: ein Store in 4 Zeilen. In der Community wird Zustand immer beliebter, weil es den sweet spot trifft zwischen Einfachheit und Mächtigkeit."

## Folie 13 – Vergleich (1,5 Min)
"Hier nochmal im Überblick. useState – niedrige Komplexität, lokal, für einfache Werte. useReducer – mittlere Komplexität, auch lokal, aber für komplexe Logik. Context – niedrig, global, für Theme und Auth und sowas. Redux – hohe Komplexität, global, für große Enterprise Apps. Zustand – niedrige Komplexität, global, und wird gerade zum modernen Standard. Die Faustregel: Starte einfach mit useState, wird's komplex nimm useReducer, brauchst du's global nimm Context oder Zustand."

## Folie 14 – Zusammenfassung (30 Sek)
"Sechs Takeaways: State Management heißt Daten zur Laufzeit verwalten. useState für einfache lokale Werte. useReducer für komplexe zusammenhängende Logik. Context löst Prop Drilling. Externe Libs für große Projekte. Und das Wichtigste: Starte einfach und skaliere bei Bedarf."

## Folie 15 – Fragen (Rest)
"Danke fürs Zuhören! Gerne Fragen."

---

## Tipps für die Präsentation
- **Tempo:** Pro Folie ca. 1-2 Minuten, bei Code-Folien langsamer
- **Code erklären:** Zeile für Zeile durchgehen, nicht vorlesen
- **Interaktion:** Bei Slide 4 (Prop Drilling) fragen: "Kennt ihr das Problem?" 
- **Falls Fragen kommen zu Redux:** "Redux Toolkit ist heute Standard, niemand schreibt mehr vanilla Redux"
- **Falls die Zeit knapp wird:** Folie 10 (Context Visual) und 11 (Context Grenzen) zusammenfassen

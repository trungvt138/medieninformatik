# Sprechnotizen – State Management in React (20 Min)

## 1 – Was ist State? (3 Min)
"Hi zusammen, heute geht's um State Management in React. Aber erstmal ganz von vorne – was ist State überhaupt?"

"Stellt euch eine ganz normale Webseite vor. Die hat HTML, CSS, sieht hübsch aus – aber sie ist statisch. Nichts verändert sich. State ist das, was eine App dynamisch macht. State sind Daten, die sich verändern während der User die App benutzt."

"Ein paar Beispiele: Ihr klickt auf einen Button und ein Zähler geht hoch – die Zahl ist State. Ihr tippt etwas in ein Suchfeld – der Text ist State. Ihr loggt euch ein – ob ihr eingeloggt seid oder nicht, ist State. Ein Modal das auf- und zugeht – ob es offen ist, ist State."

"Also kurz gesagt: Alles was sich zur Laufzeit ändern kann, ist State. Und State Management heißt einfach: Wie organisieren wir diese Daten in unserer App?"

"Es gibt verschiedene Level. Manchmal braucht nur eine einzige Komponente den State – das ist lokaler State. Manchmal brauchen viele Komponenten denselben State – das ist globaler State. Und je nachdem wie komplex das wird, gibt es verschiedene Werkzeuge. Wir fangen heute ganz einfach an und arbeiten uns hoch."

---

## 2 – useState: Counter + Custom Hook – Live Coding (5 Min)
"Das erste Werkzeug ist useState – ein sogenannter Hook, den React uns gibt. Damit können wir einer Komponente einen State geben. Ich zeig euch das jetzt live mit einem einfachen Counter."

*[Counter coden / zeigen]*

"Schauen wir uns das Zeile für Zeile an. Oben importieren wir useState aus React. Dann in der Komponente: `const [count, setCount] = useState(0)`. Das gibt uns zwei Sachen zurück – den aktuellen Wert, hier `count`, und eine Funktion um den Wert zu ändern, `setCount`. Die 0 in den Klammern ist der Startwert."

"Dann haben wir drei Funktionen: increment ruft setCount auf mit count + 1, decrement mit count - 1, und reset setzt auf 0 zurück. Unten im Return rendern wir den Wert und drei Buttons."

"Das Wichtige: Wir ändern count nie direkt. Wir rufen immer setCount auf. Warum? Weil React dann weiß, dass sich was geändert hat, und die Komponente neu rendert – also die Anzeige aktualisiert."

"Und noch was: Dieser State ist lokal. Nur diese eine Counter-Komponente kennt den Wert. Keine andere Komponente weiß was count gerade ist."

"Jetzt schaut euch die Komponente nochmal an. Der State, die drei Funktionen und das JSX – alles in einer Komponente. Das geht hier noch, aber stellt euch vor die Logik wird komplexer. Dann wird das schnell unübersichtlich."

"Dafür gibt es Custom Hooks. Das Prinzip: Wir nehmen die State-Logik – also useState und die Funktionen – und packen sie in eine eigene Funktion. Die muss mit 'use' anfangen, zum Beispiel useCounter. Die Komponente selber hat dann nur noch das JSX."

*[useCounter Hook coden / zeigen]*

"Seht ihr den Unterschied? Die Counter-Komponente ruft jetzt nur noch useCounter() auf und bekommt count, increment, decrement und reset zurück. Die ganze Logik steckt im Hook. Das hat zwei Vorteile: Erstens ist der Code übersichtlicher. Zweitens können wir den Hook wiederverwenden – wenn eine andere Komponente auch einen Counter braucht, importiert sie einfach useCounter."

---

## 3 – useState: TodoList – Live Coding (4 Min)
"Okay, das war ein einzelner Zahlenwert. Jetzt wird's spannender. Wir bauen eine TodoList, auch mit useState, aber mit komplexeren Daten."

*[TodoList coden / zeigen]*

"Hier haben wir jetzt zwei States. `todos` ist ein Array von Objekten – jedes Todo hat eine id, einen text und ein done-Flag. `input` ist der Text im Eingabefeld. Zwei States, weil es zwei verschiedene Sachen sind."

"addTodo: Wir nehmen das todos-Array und erstellen ein neues mit dem Spread-Operator – alles was vorher drin war, plus ein neues Objekt am Ende. Dann leeren wir das Input-Feld."

"toggleTodo: Wir gehen mit map über alle Todos. Wenn die id passt, flippen wir das done-Flag. Alle anderen bleiben wie sie sind. Wichtig – wir verändern nie das Original, sondern erstellen immer ein neues Array."

"deleteTodo: Wir filtern das Todo mit der passenden id raus."

"Jetzt stellt euch mal folgendes vor: Wir wollen eine Navbar die die Anzahl der offenen Todos anzeigt. Die Navbar ist eine ganz andere Komponente. Wie kommt die an die todos ran? Selbst wenn wir einen Custom Hook useTodos bauen – wenn die Navbar useTodos() aufruft, bekommt sie ihren eigenen, separaten State. Nicht denselben! Jeder Aufruf von useState erzeugt einen neuen, unabhängigen State. Wir müssten den State also nach oben schieben und als Props durch jede Ebene weiterreichen. Das nennt man Prop Drilling – und genau das wird bei größeren Apps zum Problem."

---

## 4 – Context API: Level Up (5 Min)
"Okay, Level Up. Wir haben gesehen: useState ist super für lokalen State, aber wenn mehrere Komponenten denselben State brauchen, wird's mit Props schnell unübersichtlich."

"React hat dafür eine eingebaute Lösung: die Context API. Die funktioniert in drei Schritten."

"Erstens: Wir erstellen einen Context mit createContext(). Das ist wie ein Container den wir später befüllen."

"Zweitens: Wir wrappen unsere App – oder einen Teil davon – mit einem sogenannten Provider. Der Provider bekommt einen value – das sind die Daten die wir teilen wollen. Alle Komponenten innerhalb des Providers haben Zugriff darauf."

"Drittens: In jeder Komponente, egal wie tief verschachtelt, können wir mit useContext() direkt auf den Wert zugreifen. Kein Props weiterreichen, kein Prop Drilling."

"Ein praktisches Beispiel: Ein Theme-Switcher. Dark Mode und Light Mode – das braucht fast jede Komponente in der App für die Farben. Mit Context erstellen wir einen ThemeProvider, wrappen die App damit, und jede Komponente kann mit useContext direkt das aktuelle Theme lesen und zwischen dark und light wechseln."

"Context ist perfekt für Dinge die sich selten ändern und von vielen Komponenten gebraucht werden: Theme, ob jemand eingeloggt ist, die Sprache der App. Für State der sich ständig und schnell ändert – zum Beispiel ein Slider oder Drag and Drop – ist Context nicht ideal, weil es Performance-Probleme geben kann."

---

## 5 – Redux: Ausblick (2 Min)
"Wenn eine App richtig groß wird – viele Features, viele Komponenten, vielleicht mehrere Teams – dann kommt man manchmal auch an die Grenzen von Context. Da kommen externe Libraries ins Spiel."

"Die bekannteste ist Redux. Redux hat einen sogenannten Store – eine zentrale Stelle wo der gesamte App-State lebt. Alle Änderungen laufen über feste Regeln, sogenannte Actions und Reducers. Das macht den Datenfluss sehr vorhersagbar und debugbar."

"Heute nutzt man Redux Toolkit, das die Einrichtung deutlich einfacher macht als das originale Redux. Aber Redux lohnt sich wirklich erst bei größeren Projekten – für die meisten Apps reichen useState und Context völlig aus."

---

## 6 – Zusammenfassung & Fragen (1 Min)
"Zusammengefasst: State sind Daten die sich zur Laufzeit ändern. useState gibt einer einzelnen Komponente einen lokalen State – perfekt für einfache Sachen wie Counter oder Formulare. Context API löst das Problem wenn mehrere Komponenten denselben State brauchen – ohne Props durch jede Ebene zu reichen. Und für richtig große Apps gibt es Redux."

"Die Faustregel: Starte immer mit useState. Wenn du merkst du brauchst den State an mehreren Stellen – nimm Context. Wenn die App wirklich komplex wird – dann Redux."

"Danke fürs Zuhören! Gerne Fragen."

---

## Tipps
- **Live Coding:** Counter und TodoList vorher einmal durchüben, damit's flüssig läuft
- **Begriffe erklären:** Hook, State, Render, Props – nicht voraussetzen, kurz einordnen wenn du sie benutzt
- **Tempo:** Bei Code-Teilen langsam, Zeile für Zeile erklären – die Leute sehen das zum ersten Mal
- **Interaktion:** Nach der TodoList fragen: "Was wenn die Navbar die Anzahl offener Todos zeigen soll? Wie machen wir das?" → Überleitung zu Context
- **React DevTools:** Chrome Extension "React Developer Tools" vorher installieren. Nach dem Counter oder der TodoList kurz DevTools (F12) öffnen → Tab "Components" → auf die Komponente klicken → zeigt Props, State und Hooks live. Auf einen Button klicken und zeigen wie sich `count` oder `todos` in Echtzeit ändert. Macht State super greifbar.
- **Redux:** Noch nicht zu tief reingehen, nur Ausblick – "damit beschäftigen wir uns später ausführlicher" ist völlig okay
- **Falls die Zeit knapp wird:** Redux-Teil kürzen, der ist am wenigsten kritisch

---

## Falls jemand fragt...

**"Warum steht key auf `<Todo>` und nicht auf dem `<li>`?"**
→ "`key` ist ein spezielles React-Attribut. Es wird nur auf dem Element gebraucht, das in einer map() erzeugt wird – also auf `<Todo>`. React benutzt key intern um zu tracken welches Element welches ist. Aber key kommt nicht als prop an – wenn du in der Todo-Komponente `props.key` liest, ist das undefined. Deshalb übergeben wir die id nochmal separat als eigene Prop, falls wir sie in der Kind-Komponente brauchen."

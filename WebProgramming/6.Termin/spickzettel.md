# State Management in React – Spickzettel

## Was ist State?
Daten, die sich zur Laufzeit ändern: User-Eingaben, API-Daten, UI-Zustand, Auth-Status.

## Prop Drilling
= Props durch jede Ebene weiterreichen, auch wenn Zwischenkomponenten sie gar nicht brauchen.
Problem: Wird schnell unübersichtlich, schwer zu warten, fehleranfällig.

---

## useState
```jsx
const [count, setCount] = useState(0);
```
- **Wann:** Einfache, lokale Werte (Counter, Toggle, String)
- **Grenzen:** Viele zusammengehörige States → vergisst man leicht, kein globaler Zugriff

## useReducer
```jsx
const [state, dispatch] = useReducer(reducer, initialState);
```
- **3 Teile:** State (Objekt), Action (Zettel mit `type`), Reducer (Regeln-Funktion)
- **Wann:** Mehrere States gehören zusammen, Updates hängen voneinander ab, Logik ist komplex
- **Vorteil:** Alles in einem Schritt, testbar, übersichtlich

```jsx
function reducer(state, action) {
  switch (action.type) {
    case 'fetch_start':
      return { ...state, loading: true, error: null };
    case 'fetch_success':
      return { ...state, items: action.data, loading: false };
    case 'fetch_error':
      return { ...state, error: action.msg, loading: false };
    default: return state;
  }
}
```

## Context API
```jsx
// 1. Erstellen
const UserCtx = createContext();

// 2. Provider wrappen
<UserCtx.Provider value={user}>
  <App />
</UserCtx.Provider>

// 3. Konsumieren
const user = useContext(UserCtx);
```
- **Löst:** Prop Drilling – jede Komponente kann direkt zugreifen
- **Grenzen:** Re-Render-Problem (alle Consumer bei jeder Änderung), nicht für High-Frequency Updates, Provider Hell bei vielen Contexts
- **Ideal für:** Theme, Auth, Sprache (Dinge die sich selten ändern)

---

## Externe Libraries

**Redux / Redux Toolkit**
- Globaler Store + Actions + Reducers
- Viel Boilerplate (Toolkit macht's besser)
- Ideal für große Enterprise Apps

**Zustand**
```jsx
const useStore = create((set) => ({
  count: 0,
  increment: () => set((s) => ({ count: s.count + 1 })),
}));
```
- Minimale API, kein Provider nötig
- Leichtgewicht, moderner Trend

---

## Entscheidungsbaum

| Tool | Komplexität | Scope | Ideal für |
|------|------------|-------|-----------|
| useState | Niedrig | Lokal | Einfache Werte |
| useReducer | Mittel | Lokal | Komplexe Logik |
| Context | Niedrig | Global | Theme, Auth, i18n |
| Redux | Hoch | Global | Enterprise Apps |
| Zustand | Niedrig | Global | Moderner Standard |

**Faustregel:** Starte mit useState → wird's komplex → useReducer → brauchst du's global → Context oder Zustand.

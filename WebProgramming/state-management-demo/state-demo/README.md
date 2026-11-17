# State Management in React – Demo

```
npm install
npm run dev
```

Dann http://localhost:5173 öffnen.

## Aufbau

```
src/
  hooks/
    useCounter.js        Custom Hook (Folie 6–7)
  context/
    ThemeContext.jsx     Context + Provider + useTheme (Folie 11–13)
  components/
    SnapshotDemo.jsx     State ist ein Snapshot (Folie 5)
    Counter.jsx          Counter mit useCounter (Folie 6–7)
    TodoList.jsx         Arrays immutable ändern (Folie 8)
    ContextDemo.jsx      Theme umschalten ohne Props (Folie 11–12)
    ReducerCounter.jsx   useReducer (Folie 14–15)
  App.jsx                alle Demos in Folien-Reihenfolge
  main.jsx
  index.css
```

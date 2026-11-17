import { useState } from "react";

// Custom Hook: State-Logik ausgelagert (Folie 6–7)
// Teilt die Logik, nicht den State: Jede Komponente bekommt ihren eigenen Zähler.
export function useCounter(startValue = 0) {
  const [count, setCount] = useState(startValue);

  const increment = () => setCount(c => c + 1);
  const decrement = () => setCount(c => c - 1);
  const reset = () => setCount(startValue);

  return { count, increment, decrement, reset };
}

import { useState } from "react";

// Folie 4: Counter mit useState
export default function SimpleCounter() {
  const [count, setCount] = useState(0);

  function increment() {
    setCount(count + 1);
  }
  function decrement() { setCount(count - 1); }
  function reset() { setCount(0); }

  return (
    <section>
      <h2>Zähler: {count}</h2>
      <button type="button" onClick={increment}>+1</button>
      <button type="button" onClick={decrement}>-1</button>
      <button type="button" onClick={reset}>Reset</button>
    </section>
  );
}
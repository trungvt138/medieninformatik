import { useCounter } from "../hooks/useCounter";

// Folie 6–7: Die Komponente kümmert sich nur noch um die Darstellung
export default function Counter() {
  const { count, increment, decrement, reset } = useCounter();

  return (
    <section>
      <h2>Counter mit Custom Hook</h2>
      <p className="zahl">{count}</p>
      <button type="button" onClick={increment}>+</button>
      <button type="button" onClick={decrement}>-</button>
      <button type="button" onClick={reset}>Reset</button>
    </section>
  );
}

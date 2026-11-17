import { useState } from "react";

// Folie 5: State ist ein Snapshot
export default function SnapshotDemo() {
  const [count, setCount] = useState(0);

  // Falsch: count ist in diesem Render immer derselbe Wert → nur +1
  function handleClickFalsch() {
    setCount(count + 1);
    setCount(count + 1);
    setCount(count + 1);
  }

  // Richtig: funktionale Schreibweise bekommt immer den neuesten Wert → +3
  function handleClickRichtig() {
    setCount(c => c + 1);
    setCount(c => c + 1);
    setCount(c => c + 1);
  }

  return (
    <section>
      <h2>State ist ein Snapshot</h2>
      <p className="zahl">{count}</p>
      <button type="button" onClick={() => setCount(0)}>Reset</button>
      <button type="button" className="falsch" onClick={handleClickFalsch}>+3 falsch</button>
      <button type="button" className="richtig" onClick={handleClickRichtig}>+3 richtig</button>
    </section>
  );
}

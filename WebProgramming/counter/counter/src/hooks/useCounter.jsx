import { useState } from "react";

function useCounter(startValue = 0) {
  const [count, setCount] = useState(startValue);

  function increment() {
    setCount(count + 1);
  }
  function decrement() {
    setCount(count - 1);
  }
  function reset() { setCount(startValue); }

  return { count, increment, decrement, reset };
}

import { useReducer } from "react";

// Folie 14–15: Die Logik steht zentral im Reducer
function reducer(state, action) {
  switch (action.type) {
    case "increment":
      return { count: state.count + 1 };
    case "decrement":
      return { count: state.count - 1 };
    case "reset":
      return { count: 0 };
    default:
      return state;
  }
}

export default function ReducerCounter() {
  const [state, dispatch] = useReducer(reducer, { count: 0 });

  return (
    <section>
      <h2>Counter mit useReducer</h2>
      <p className="zahl">{state.count}</p>
      <button type="button" onClick={() => dispatch({ type: "increment" })}>+</button>
      <button type="button" onClick={() => dispatch({ type: "decrement" })}>-</button>
      <button type="button" onClick={() => dispatch({ type: "reset" })}>Reset</button>
    </section>
  );
}

import { useState } from "react";

// Folie 8: Arrays im State nie direkt ändern – immer eine neue Kopie
export default function TodoList() {
  const [todos, setTodos] = useState([]);
  const [input, setInput] = useState("");

  function addTodo() {
    if (input.trim() === "") return;
    setTodos([...todos, { id: Date.now(), text: input, done: false }]);
    setInput("");
  }

  function toggleTodo(id) {
    setTodos(todos.map(t => (t.id === id ? { ...t, done: !t.done } : t)));
  }

  function deleteTodo(id) {
    setTodos(todos.filter(t => t.id !== id));
  }

  return (
    <section>
      <h2>Todos</h2>
      <input
        value={input}
        onChange={e => setInput(e.target.value)}
        onKeyDown={e => e.key === "Enter" && addTodo()}
        placeholder="Neues Todo …"
      />
      <button type="button" onClick={addTodo}>Hinzufügen</button>
      <ul>
        {todos.map(t => (
          <li key={t.id}>
            <span
              onClick={() => toggleTodo(t.id)}
              style={{ textDecoration: t.done ? "line-through" : "none" }}
            >
              {t.text}
            </span>
            <button type="button" onClick={() => deleteTodo(t.id)}>✕</button>
          </li>
        ))}
      </ul>
      {todos.length === 0 && <p className="muted">Noch keine Todos.</p>}
    </section>
  );
}

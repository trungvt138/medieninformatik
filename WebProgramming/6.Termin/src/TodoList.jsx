import { useState } from "react";

function TodoList() {
  const [todos, setTodos] = useState([]);
  const [input, setInput] = useState("");

  function addTodo() {
    if (input.trim() === "") return;
    setTodos([...todos, { id: Date.now(), text: input, done: false }]);
    setInput("");
  }

  function toggleTodo(id) {
    setTodos(todos.map(todo =>
      todo.id === id ? { ...todo, done: !todo.done } : todo
    ));
  }

  function deleteTodo(id) {
    setTodos(todos.filter(todo => todo.id !== id));
  }

  return (
    <div>
      <h2>Meine Todos</h2>
      <input
        value={input}
        onChange={(e) => setInput(e.target.value)}
        placeholder="Neue Aufgabe..."
      />
      <button onClick={addTodo}>Hinzufügen</button>

      <ul>
        {todos.map(todo => (
          <li key={todo.id}>
            <span
              onClick={() => toggleTodo(todo.id)}
              style={{
                textDecoration: todo.done ? "line-through" : "none",
                cursor: "pointer"
              }}
            >
              {todo.text}
            </span>
            <button onClick={() => deleteTodo(todo.id)}>Löschen</button>
          </li>
        ))}
      </ul>
    </div>
  );
}

export default TodoList
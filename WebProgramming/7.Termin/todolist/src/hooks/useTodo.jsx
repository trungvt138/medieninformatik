import { createContext, useContext, useState } from "react"

const TodoContext = createContext()

function TodoProvider({ children }) {
    const [text, setText] = useState("")
    const [todos, setTodos] = useState([])
    const [filter, setFilter] = useState("all")

    function addTodo() {
        if (text.trim() === "") return
        setTodos([...todos, { id: Date.now(), text, done: false }])
        setText("")
    }

    function toggleTodo(id) {
        setTodos(
            todos.map((todo) =>
                todo.id === id ? { ...todo, done: !todo.done } : todo,
            ),
        )
    }

    function deleteTodo(id) {
        setTodos(todos.filter((todo) => todo.id !== id))
    }

    function clearCompleted() {
        setTodos(todos.filter((todo) => !todo.done))
    }

    function clearAll() {
        setTodos([])
    }

    const visibleTodos = todos.filter((todo) => {
        if (filter === "active") return !todo.done
        if (filter === "completed") return todo.done
        return true
    })

    return (
        <TodoContext.Provider value={{ text, setText, todos, visibleTodos, filter, setFilter, addTodo, toggleTodo, deleteTodo, clearCompleted, clearAll }}>
            {children}
        </TodoContext.Provider>
    )
}

function useTodo() {
    return useContext(TodoContext)
}

export { TodoProvider, useTodo }
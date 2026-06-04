import { useState } from 'react'

export default function useTodos() {
    const [todos, setTodos] = useState([])
    const [input, setInput] = useState('')

    function addTodo() {
        setTodos([...todos, input])
        setInput('')
    }

    function handleInput(e) {
        setInput(e.target.value)
    }

    // deleteTodo(id) function can be added here

    // toggleTodo(id) function can be added here

    
    return {todos, input, handleInput, addTodo}
}
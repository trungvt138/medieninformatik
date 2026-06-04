import React from 'react'
import { useState } from 'react'
import useTodos from './hooks/useTodos'

export default function App() {
    const { todos, input, handleInput, addTodo } = useTodos()

    return (
        <div>
            <h1>Todo List</h1>
            <input type="text" placeholder="Enter a todo" value={input} onChange={handleInput} />
            <button type="button" onClick={addTodo}>Add</button>
            <ul>
                {todos.map((todo, index) => (
                    <li key={index}>{todo}</li>
                ))}
            </ul>
        </div>
    )
}
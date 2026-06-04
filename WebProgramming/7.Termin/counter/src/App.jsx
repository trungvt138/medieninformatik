import React from 'react'
import { useState } from 'react'

export default function App() {
    const [counter, setCounter] = useState(0)

    function increment() {
        setCounter(counter + 1)
    }

    function decrement() {
        setCounter(counter - 1)
    }

    function reset() {
        setCounter(0)
    }
    return (
        <div>
        <h1>Counter</h1>
        <h1>{counter}</h1>
        <button type="button" onClick={increment}>+</button>
        <button type="button" onClick={decrement}>-</button>
        <button type="button" onClick={reset}>Reset</button>
        </div>
    )
}
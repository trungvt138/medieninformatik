import { StrictMode } from 'react'
import { createRoot } from 'react-dom/client'
import App from './App.jsx'
import Counter from './Counter.jsx'
import UserList from './UserList.jsx'
import TodoList from './TodoList.jsx'

createRoot(document.getElementById('root')).render(
  <StrictMode>
    <TodoList />
  </StrictMode>
)

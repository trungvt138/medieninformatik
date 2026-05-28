import { StrictMode } from 'react'
import { createRoot } from 'react-dom/client'
import App from './components/App.jsx'
import Counter from './components/Counter.jsx'
import UserList from './components/etc/UserList.jsx'
import TodoList from './components/TodoList.jsx'

createRoot(document.getElementById('root')).render(
  <StrictMode>
    <App />
  </StrictMode>
)

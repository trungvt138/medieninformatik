import { useReducer, useEffect } from "react";

// Initial State – alles in einem Objekt
const initialState = {
  items: [],
  loading: false,
  error: null,
};

// Reducer – die Regeln
function reducer(state, action) {
  switch (action.type) {
    case "fetch_start":
      return { ...state, loading: true, error: null };
    case "fetch_success":
      return { ...state, loading: false, items: action.data };
    case "fetch_error":
      return { ...state, loading: false, error: action.message };
    default:
      return state;
  }
}

function UserList() {
  const [state, dispatch] = useReducer(reducer, initialState);

  function loadUsers() {
    dispatch({ type: "fetch_start" });

    fetch("https://jsonplaceholder.typicode.com/users")
      .then((res) => res.json())
      .then((data) => dispatch({ type: "fetch_success", data }))
      .catch((err) => dispatch({ type: "fetch_error", message: err.message }));
  }

  useEffect(() => {
    loadUsers();
  }, []);

  if (state.loading) return <p>Laden...</p>;
  if (state.error) return <p>Fehler: {state.error}</p>;

  return (
    <div>
      <ul>
        {state.items.map((user) => (
          <li key={user.id}>{user.name}</li>
        ))}
      </ul>
      <button onClick={loadUsers}>Reload</button>
    </div>
  );
}

export default UserList;
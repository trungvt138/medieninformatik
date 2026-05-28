function Todo(props) {
    return (
        <li key={props.id}>
            <span
              onClick={props.onChange}
              style={{
                textDecoration: props.done ? "line-through" : "none",
                cursor: "pointer"
              }}
            >
              {props.text}
            </span>
            <button onClick={props.onClick}>Löschen</button>
          </li>
    )
}

export default Todo;
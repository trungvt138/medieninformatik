import Projekt from "./Projekt"
import projekte from "./Data"

function App() {
  return (
    <div>
      {projekte.map((projekt, index) => (
        <Projekt 
          key={index}
          titel={projekt.titel}
          bild={projekt.bild}
          beschreibung={projekt.beschreibung}
        />
      ))}
    </div>
  )
}

export default App

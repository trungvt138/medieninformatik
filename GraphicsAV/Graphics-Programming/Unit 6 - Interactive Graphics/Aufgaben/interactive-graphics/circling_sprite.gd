extends Sprite2D

# Mittelpunkt der Kreisbahn
var center = Vector2(500, 500)

# Radius
var radius = 150.0

# Winkel in Radiant
var angle = 0.0

# Winkelgeschwindigkeit
# (1.0 = etwa 57° pro Sekunde)
var angular_speed = 1.5


func _ready():

	texture = load("res://spaceship.png")
	position = Vector2(300, 200)
	scale = Vector2(0.5, 0.5)

func _process(delta: float) -> void:
	
	# Winkel zeitbasiert erhöhen
	angle += angular_speed * delta

	# Kreisbahn berechnen
	position.x = center.x + radius * cos(angle)
	position.y = center.y + radius * sin(angle)

extends Sprite2D

var speed = 90.0

func _ready():

	texture = load("res://spaceship.png")
	position = Vector2(300, 200)
	scale = Vector2(0.5, 0.5)

func _process(delta: float) -> void:
	rotation_degrees += speed * delta

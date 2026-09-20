extends Sprite2D

var speed = 300.0

func _ready():
	texture = load("res://spaceship.png")
	position = Vector2(400, 300)
	scale = Vector2(0.25, 0.25)

func _process(delta):

	if Input.is_action_pressed("ui_right"):
		position.x += speed * delta

	if Input.is_action_pressed("ui_left"):
		position.x -= speed * delta

	if Input.is_action_pressed("ui_down"):
		position.y += speed * delta

	if Input.is_action_pressed("ui_up"):
		position.y -= speed * delta

	# Fenstergrenzen
	var screen_size = get_viewport_rect().size

	position.x = clamp(position.x, 0, screen_size.x)
	position.y = clamp(position.y, 0, screen_size.y)

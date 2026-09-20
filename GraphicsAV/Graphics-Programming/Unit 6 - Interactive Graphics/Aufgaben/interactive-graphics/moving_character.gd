extends Sprite2D

var speed = 250.0

func _ready():

	texture = load("res://spaceship.png")
	position = Vector2(300, 200)
	scale = Vector2(0.5, 0.5)
	
func _process(delta):

	if Input.is_action_pressed("ui_right"):
		position.x += speed * delta

	if Input.is_action_pressed("ui_left"):
		position.x -= speed * delta

	if Input.is_action_pressed("ui_down"):
		position.y += speed * delta

	if Input.is_action_pressed("ui_up"):
		position.y -= speed * delta

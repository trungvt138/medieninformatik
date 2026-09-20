extends AnimatedSprite2D

func _ready():

	play("jump")

func _process(delta):

	if Input.is_action_just_pressed("toggle_animation"):

		if is_playing():
			stop()
		else:
			play()

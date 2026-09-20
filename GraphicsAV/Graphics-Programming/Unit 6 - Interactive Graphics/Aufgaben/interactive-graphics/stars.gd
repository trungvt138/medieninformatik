extends Node2D

const NUM_STARS = 100
var stars = []
var star_speed = 100.0

func _ready():

	randomize()

	var screen_size = get_viewport_rect().size

	for i in range(NUM_STARS):
		stars.append(
			Vector2(
				randf() * screen_size.x,
				randf() * screen_size.y
			)
		)

func _process(delta):

	var screen_size = get_viewport_rect().size

	for i in range(stars.size()):
		stars[i].y += star_speed * delta
		if stars[i].y > screen_size.y:
			stars[i].y = 0
			stars[i].x = randf() * screen_size.x

	queue_redraw()

func _draw():

	for star in stars:
		draw_circle(star, 2, Color.WHITE)

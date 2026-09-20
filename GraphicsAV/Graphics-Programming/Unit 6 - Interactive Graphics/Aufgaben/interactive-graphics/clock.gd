extends Node2D

@onready var hour_hand = $HourHand
@onready var minute_hand = $MinuteHand
@onready var second_hand = $SecondHand
@onready var pendulum = $Pendulum

var running = true

var current_time = 0.0

func _ready():

	# Beispiel:
	# 10:15:30
	current_time = 10 * 3600 + 15 * 60 + 30

func _process(delta):

	if running:
		current_time += delta

	update_clock()
	update_pendulum()

func update_clock():

	var seconds = fmod(current_time, 60)
	var minutes = fmod(current_time / 60, 60)
	var hours = fmod(current_time / 3600, 12)

	# Sekundenzeiger
	second_hand.rotation = deg_to_rad(seconds * 6)

	# Minutenzeiger
	minute_hand.rotation = deg_to_rad(minutes * 6)

	# Stundenzeiger
	hour_hand.rotation = deg_to_rad(hours * 30)

func update_pendulum():

	var max_angle = 25.0

	pendulum.rotation = deg_to_rad(max_angle * sin(TAU * current_time))


func _on_button_pressed() -> void:
	running = !running

# Metronom-Snippet (aus Moodle, Thema 7)
...
click_duration
= 0.03
# 30
ms
click_freq
= 1000
# 1000 Hz
click_volume
= 0.35
beat_interval
= 60.0 /
bpm
metronome
=
np.zeros_like
(
music_segment
,
dtype
=np.float32)
#
Klicksignal: kurzer Sinuston mit Ausblendung
t_click
=
np.linspace
(0,
click_duration
,
int
(
sr
*
click_duration
),
endpoint
=
False
)
click
=
np.sin
(2 *
np.pi
*
click_freq
*
t_click
).
astype
(np.float32)
# einfache Hüllkurve, damit der Klick nicht hart endet
envelope
=
np.linspace
(1.0, 0.0,
len
(
click
),
dtype
=np.float32)
click
*=
envelope
click
*=
click_volume
...
mix =
music_gain
*
music_segment
+
metronome

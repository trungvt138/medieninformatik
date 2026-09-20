# Musik abspielen Snippet (aus Moodle, Thema 7)
sd.play(mix.astype(np.float32), sr, blocking=True)
# blocking = True: der aktuelle Python-Thread an dieser Stelle wartet, bis die komplette Audiowiedergabe beendet ist.

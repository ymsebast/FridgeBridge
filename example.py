import RPi.GPIO as GPIO
import time
import sys
from hx711 import HX711

def cleanAndExit():
    print "Cleaning..."
    GPIO.cleanup()
    print "Bye!"
    sys.exit()

hx = HX711(5, 6)
refval = 100

hx.set_reading_format("LSB", "MSB")

hx.set_reference_unit(refval)

hx.reset()

while True:
    try:

        val = max(0, int(hx.get_weight(5)))
	#val = hx.read_long()
        print val
	tval = val/refval
	print "True value in grams is: %d" % (tval)


        hx.power_down()
        hx.power_up()
        time.sleep(0.1)
    except (KeyboardInterrupt, SystemExit):
        cleanAndExit()

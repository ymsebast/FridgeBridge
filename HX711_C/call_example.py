import os

offset = 35717
gain = 0.00245
os.system("sudo ./example")

fo = open("weight.txt", "r")

string = fo.read(10)
#print("Read string is: ", string)
num = float(string)

grams = gain * (num - offset)
grams = grams - 1
print("Weight is %f g" % (grams))

file = open("weight_in_grams.txt", "w")
file.write("%d" % (int(grams)))
file.close()

fo.close()

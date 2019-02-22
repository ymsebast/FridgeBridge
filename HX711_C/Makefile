CC=gcc
CFLAGS=-Wall -std=gnu99 -march=native -mtune=native

default: read_scale

gb_common.o: gb_common.c gb_common.h
	$(CC) $(CFLAGS) -g -c gb_common.c -o gb_common.o

hx711.o: hx711.c hx711.h gb_common.h
	$(CC) $(CFLAGS) -g -c -o hx711.o hx711.c

example: example.c gb_common.o hx711.o
	$(CC) $(CFLAGS) -g -o example example.c gb_common.o hx711.o

clean:
	rm -f *.o
	rm -f example
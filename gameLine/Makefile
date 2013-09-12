CC = g++
CFLAGS = -c -Wall
LDFLAGS=
SOURCES = gameLine.cpp classes.cpp
ODJECTS = $(SOURCES:.cpp = .o)
EXECUTABLE = LineGamePath

all: $(SOURCES) $(EXECUTABLE)

$(EXECUTABLE): $(OBJECTS)
	$(CC) $(LDFLAGS) $(SOURCES) -o $@

.cpp.o:
	$(CC) $(CFLAGS) $< -o $@ 

clean:
	rm LineGamePath

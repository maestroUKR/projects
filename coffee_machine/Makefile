CC = g++
CFLAGS = -c -Wall
LDFLAGS=
SOURCES = main.cpp realization.cpp
ODJECTS = $(SOURCES:.cpp = .o)
EXECUTABLE = coffeeMachine

all: $(SOURCES) $(EXECUTABLE)

$(EXECUTABLE): $(OBJECTS)
	$(CC) $(LDFLAGS) $(SOURCES) -o $@

.cpp.o:
	$(CC) $(CFLAGS) $< -o $@ 

#include "classes.h"

int main(int argc, char** argv)
{
    Map* field = new Map(4,7);

    field->setValue(2,5,0);
    field->setValue(0,6,1000);
    field->setValue(1,4,1000);
    field->setValue(1,5,1000);
    field->setValue(2,3,1000);
    field->setValue(3,0,1000);

    field->fillMap(2,5);

    field->showMap();
    field->computePath(0,0,2,5);
    field->printPath();

    field->~Map();
    return 0;
}



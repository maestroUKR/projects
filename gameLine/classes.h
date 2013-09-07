#ifndef CLASSES_H
#define CLASSES_H

#include "iostream"
#include <string.h>
#include <vector>

using namespace std;

class Cell
{
private:
    int _value;
public:
    Cell();
    ~Cell(){}
    void setValue(int value){_value = value;}
    int getValue(){return _value;}
};

class Map : public Cell
{
private:
    Cell *cells;
    int _rows;
    int _columns;
    vector<string> _path;
public:
    Map(int rows, int columns);
    ~Map();
    void showMap();
    void setValue(int x, int y, int value);
    bool verifyCell(int x, int y);
    void fillMap(int x, int y);
    void computePath(int startX, int startY, int endX, int endY);
    void printPath();
};

#endif // CLASSES_H

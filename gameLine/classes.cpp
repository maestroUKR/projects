#include "classes.h"

// -----------------------------------------------

Cell :: Cell()
{
   _value = 100;
}

// -------------------------------------------------

Map :: Map(int rows, int columns)
{
    _rows = rows;
    _columns = columns;
    int CELL_NUMBER = _rows*_columns;

    // allocate memory for array-field of Cell's obj
    cells = (Cell*)operator new(sizeof(Cell)*CELL_NUMBER);
    //create Cel's obj
    for(int i = 0; i < CELL_NUMBER; ++i)
        new(&cells[i])Cell();
}

Map ::~Map()
{
    int CELL_NUMBER = _rows*_columns;
    // delete Cell's obj
    for(int i = 0; i < CELL_NUMBER; ++i)
        cells[i].~Cell();
    //delete allocate array pointer
    operator delete(cells);
}

void Map :: showMap()
{
    for(int i = 0; i< _rows; ++i)
    {
        for(int j = 0; j < _columns; ++j)
            cout << cells[i*_columns + j].getValue() << "\t";
        cout << "\n";
    }

}

void Map ::setValue(int x, int y, int value)
{
    cells[x*_columns + y].setValue(value);
}

bool Map ::verifyCell(int x, int y)
{
    if(x >= 0 & x < _rows & y >=0 & y < _columns)
            if(cells[x*7+y].getValue() != 1000)
                return true;
    else
        return false;
}

void Map :: fillMap(int x, int y)
{
    if(verifyCell(x, y) == true)
    {
        if(verifyCell(x-1, y) == true)
        {
            if((cells[(x-1)*_columns+y].getValue() >= cells[x*_columns+y].getValue()))
            {
                cells[(x-1)*_columns+y].setValue(cells[x*_columns+y].getValue() + 1);
                fillMap( x-1, y);
            }
        }
        if(verifyCell( x+1, y) == true)
        {
            if((cells[(x+1)*_columns+y].getValue() >= cells[x*_columns+y].getValue()) )
            {
                cells[(x+1)*_columns+y].setValue(cells[x*_columns+y].getValue() + 1);
                fillMap( x+1, y);
            }
        }
        if(verifyCell( x, y-1) == true)
        {
            if((cells[x*_columns+(y-1)].getValue() >= cells[x*_columns+y].getValue()))
            {
                cells[x*_columns+(y-1)].setValue(cells[x*_columns+y].getValue() + 1);
                fillMap( x, y-1);
            }
        }
        if(verifyCell( x, y+1) == true)
        {
            if((cells[x*_columns+(y+1)].getValue() >= cells[x*_columns+y].getValue()))
            {
                cells[x*_columns+(y+1)].setValue(cells[x*_columns+y].getValue() + 1);
                fillMap( x, y+1);
            }
        }

    }
}

void Map ::printPath()
{
    vector<int>::size_type sz = _path.size();
    for(int i=0; i<sz; ++i)
    {
        cout << " --> " << _path[i];
    }
    cout << '\n';
}

void Map ::computePath(int startX, int startY, int endX, int endY)
{
    // put into register to more work speed
    register int curX = startX;
    register int curY = startY;

    bool done = true;
    while(done)
    {
        // condition to finish algorithm
        if((curX == endX) & (curY == endY))
        {
                   done = false;
        }
        if(verifyCell(curX+1, curY) == true)
        {
            if((cells[(curX+1)*_columns+curY].getValue() <= cells[(curX)*_columns+curY].getValue()))
            {
                curX += 1;
                _path.push_back("Right");
            }
        }
        else if(verifyCell(curX-1, curY) == true)
        {
            if((cells[(curX-1)*_columns+curY].getValue() <= cells[(curX)*_columns+curY].getValue()))
            {
                curX -= 1;
                _path.push_back("Left");
            }
        }
        if(verifyCell(curX, curY+1) == true)
        {
            if((cells[curX*_columns+(curY+1)].getValue() <= cells[(curX)*_columns+curY].getValue()) )
            {
                curY += 1;
                _path.push_back("Up");
            }
        }
        if(verifyCell(curX, curY-1) == true)
        {
            if((cells[curX*_columns+(curY-1)].getValue() <= cells[(curX)*_columns+curY].getValue()))
            {
             curY -= 1;
              _path.push_back("Down");
            }
        }
    }

    cout << "---------Path is finded!------------\n";
}

// -------------------------------------------------

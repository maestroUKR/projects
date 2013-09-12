#include "prototypes.h"


Cup :: Cup()
{
    price = 0;
    sug = 0;
    name = "None";
}

Cup :: Cup(float price, int sug, string name)
{
    Cup :: price = price;
    Cup :: sug = sug;
    Cup :: name = name;
}

Cup :: ~Cup()
{}

float Cup :: GetCupPrice() 
{
    return price;
}

int Cup :: GetCupSug() 
{
    return sug;
}

string Cup :: GetCupName() 
{
    return name;
}

void Cup :: SetCupPrice(float cprice)
{
    price = cprice;
}

void Cup :: SetCupSug(int csug)
{
    sug = csug;
}

void Cup :: SetCupName(string cname)
{
    name = cname;
}

//-----------------------------------------------------------------------------

Sugar :: Sugar()
{
    sug = 0;
}

Sugar :: Sugar(int sug)
{
 Sugar :: sug = sug;
}

Sugar :: ~Sugar()
{}

int Sugar :: GetSugar() 
{
    cout << "\nSelect the number of sugar cubes(from 0 to 5) : " ;
    cin >> sug;
    /*
   *    check correct value and
   *    repeat operation if it's need
   */
    if((sug < 0) | (sug > 5) )
    {
        Sugar::GetSugar();
    }
    else
    {
        cout << "Get the sugar in the number " << sug << " of cubes." << endl;
        return sug;
    }
    return 0;
}

void Sugar :: Press(Cup& a)
{
    a.SetCupSug(sug);
}

//---------------------------------------------------------------------------

Coffee :: Coffee()
{
    name = "None";
    price = 0;
}

Coffee :: Coffee (string name, float price)
{
    Coffee :: name = name;
    Coffee :: price = price;
}

Coffee :: ~Coffee()
{}

string Coffee :: GetName() 
{
    return name;
}

float Coffee :: GetPrice() 
{
    return price;
}

void Coffee :: SetName(string itsname)
{
    name = itsname;
}

void Coffee :: SetPrice(float itsprice)
{
    price = itsprice;
}

void Coffee :: Press(Cup& a)
{
    a.SetCupName(name);
    a.SetCupPrice(price);
}

void Coffee :: GetNamePrice() 
{
 cout << "\n\n 1. Latte - 4. \n 2. Jacobs - 3. \n 3. Jardin - 4. \n 4. Espresso - 5. \n 5. GreenTea - 3.\n";
 cout << "\n Input your choice: ";
     int a;
     cin >> a;
     switch(a)
     {
     case 1:
     {
         SetName("Latte");
         SetPrice(4);
         cout << "\nYou have " << GetName() <<". Price = " << GetPrice() <<endl;
     }
         break;

     case 2:
     {
         SetName("Jacobs");
         SetPrice(3);
         cout << "\nYou have " << GetName() <<". Price = " << GetPrice() << endl;
     }
         break;

     case 3:
     {
         SetName("Jardin");
         SetPrice(4);
         cout << "\nYou have " << GetName() << ". Price = " << GetPrice() << endl;
     }
         break;

     case 4:
     {
         SetName("Espresso");
         SetPrice(5);
         cout << "\nYou have " << GetName() << ". Price = " << GetPrice() << endl;
     }
         break;

     case 5:
     {
         SetName("GreenTea");
         SetPrice(3);
         cout << "\nYou have " << GetName() << ". Price = " << GetPrice();
     }
         break;
     default:
     {
         cout << "\nIncorrect input. Repeat operation, please." << endl;
         Coffee::GetNamePrice();
     }
    }
}

//---------------------------------------------------------------------------

void Cancel :: Press(Cup &a)
{
    a.SetCupPrice(0);
    a.SetCupSug(0);
    a.SetCupName("None");
}

void Cancel :: Exit()
{
    cout << "\nYou have abolished all activity. Take away your money." << endl;
    cout<< "To buy a coffee You need to repeat the whole process again!\n" << endl;

}
//-----------------------------------------------------------------------------


 ForMoney :: ForMoney()
{
    money = 0;
}

ForMoney :: ForMoney(float money)
{
     ForMoney :: money = money;
}

ForMoney :: ~ForMoney()
{}

float ForMoney :: InputMoney()
{
     cout << "\nThrow a coin into the coffee-machine: ";
     cin >> money;
     if(money <= 0)
     {
         cout << "Incorrect input. Repeat, please." << endl;
         ForMoney::InputMoney();
     }
     cout << "You have " << money << " coins." << endl;
     return money;
}
//-----------------------------------------------------------------------------


void Run :: Press(Cup& a, ForMoney &b)
{
    /*
     *  compute rest of money
     */
    int m = b.InputMoney();
    int n = a.GetCupPrice();
    float rest = m-n;
    if(m >= n)
    {
        cout << "\nYou bought " << a.GetCupName() << " at the price of coffee " << a.GetCupPrice()<<
                " with " <<  a.GetCupSug() << " sugar cubes." << endl;
        cout << "\nSubmit your :" << rest << endl;

        a.SetCupPrice(0);
        a.SetCupSug(0);
        a.SetCupName("None");
    }
    else
    {
        cout << "\nYour money is not enough to buy coffee. Repeat operation!";
        cout << "\nSubmit your :" << m << endl;
    }
 }
//----------------------------------------------------------------------------

 void Automate :: generalmenu()
{
 cout << "\n\tMenu\t\n 1. Cancel\n 2. Select Coffee\n 3. Add Sugar \n 4. Press\n Your choice: ";
}

 void Automate :: generalmenuselect()
{

     int a;
     while(a != 0)
     {
         generalmenu();
         cin >> a;
         switch(a)
         {
         case 1:
         {
             E.Press(C);
             E.Exit();
             break;
         }
         case 2:
         {
             A.GetNamePrice();
             A.Press(C);
             break;
         }
         case 3:
         {
             B.GetSugar();
             B.Press(C);
             break;
         }
         case 4:
         {
             F.Press(C, D);
             break;
         }
         }
     }
}

//-----------------------------------------------------------------------------

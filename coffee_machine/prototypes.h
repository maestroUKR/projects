#include <iostream>
#include <string>

using namespace std;

class Cup
{
 private:
 float price;
 int sug;
 string name;

 public:
 Cup();
 Cup(float price, int sug, string name);
 ~Cup();

 float GetCupPrice();
 int GetCupSug();
 string GetCupName();

 void SetCupPrice(float cprice);
 void SetCupSug(int csug);
 void SetCupName(string cname);
};

//--------------------------------------------------------------

/*
 *  abstract class for all buttons on machine
 */

class Buttons
{
 virtual void Press(Cup& a) = 0;
};

//--------------------------------------------------------------

class Sugar : public Buttons
{
 private:
   int sug;

 public:
   Sugar();
   Sugar(int sug);
   ~Sugar();

   void Press(Cup& a);
   int GetSugar();
};

//--------------------------------------------------------------
 class Coffee : public Buttons
{
    private:
     string name;
     float price;

    public:
    Coffee();
    Coffee(string name, float price);
    ~Coffee();

    string GetName();
    float  GetPrice();

    void SetName(string itsname);
    void SetPrice(float itsprice);

    void GetNamePrice();
    void Press(Cup& a);


};

//--------------------------------------------------------------

class Cancel : public Buttons
{
 public:
 void Press(Cup &a);
 void Exit();
};

 //--------------------------------------------------------------

class ForMoney
{
 private:
 float money;

 public:
 float InputMoney();
 ForMoney();
 ForMoney(float money);
 ~ForMoney();
};

 //--------------------------------------------------------------

class Run
{
 public:
 void Press(Cup& a, ForMoney &b);
};

  //--------------------------------------------------------------

class Automate
{
 private:
 Coffee A;
 Sugar B;
 Cup C;
 ForMoney D;
 Cancel E;
 Run F;

 public:
 void generalmenu();
 void generalmenuselect();

};

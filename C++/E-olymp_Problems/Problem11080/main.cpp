#include <iostream>
#include <cstdio>
#include <string>
using namespace std;
int main ( )
{
    string d1 , d2 ;
    cin>>d1>>d2;
    string a=" ",b=" ",c=" ",d=" ";
    int x1,x2;
    x1= d1.find("|");
    x2= d2.find("|");
    a=d1.substr(0,x1);
    b=d1.substr((x1+1));
    c=d2.substr(0,x2);
    d=d2.substr((x2+1));
    if(a==c || a==d || b==c || b==d)
        cout<<"Yes"<<endl;
    else if ( ((a==b && (a==c || a==d)) ) || (c==d && (c==a || c==b) ) )
             cout<<"Yes"<<endl;
    else
             cout<<"No"<<endl;
}

#include <iostream>
#include <cmath>
using namespace std;
int main ( )
{
    int a,b,c,av,k;
    cin>>a>>b>>c;
    av=(a+b+c)/3;
    k=(abs(a-av)+abs(b-av)+abs(c-av))/2;
    if((a+b+c)%3!=0)
        cout<<"IMPOSSIBLE"<<endl;
    else
        cout<<k<<endl;
    return 0;
}

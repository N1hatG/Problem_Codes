#include <iostream>
#include <cmath>
using namespace std;
int main ( )
{
    long long a,b;
    cin>>a>>b;
    long long x;
    x=a+b;
    if (x%2!=0)
        cout<<"-"<<endl;
    else if (abs(a-x/2)==abs(b-x/2))
        cout<<x/2<<endl;
    else
        cout<<"-"<<endl;
}

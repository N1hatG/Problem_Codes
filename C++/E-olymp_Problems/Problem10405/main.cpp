#include <iostream>
#include <cmath>
using namespace std;
int main( )
{
    int a,b,x,y,n,m,t,k;
    cin>>a>>b>>x>>y;
    if(x>y)
        swap(x,y);
    if(a>b)
        swap(a,b);
    n=abs(x-a);
    m=abs(y-b);
    t=b-a;
    k=n+m;
    if(k<t)
        cout<<k<<endl;
    else
        cout<<t<<endl;
}

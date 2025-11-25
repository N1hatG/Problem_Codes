#include <iostream>
#include <cmath>
using namespace std;
int main ( )
{
    int a,b,c,x1,x2,D;
    cin>>a>>b>>c;
    D=b*b-4*a*c;
    if(D<0)
        cout<<"No roots"<<endl;
    else if (D==0)
    {
        x1=(-1*b+sqrt(D))/(2*a);
        cout<<"One root: "<<x1<<endl;
    }
    else
    {
        x1=(-1*b+sqrt(D))/(2*a);
        x2=(-1*b-sqrt(D))/(2*a);
        if(x1>x2)
            swap(x1,x2);
        cout<<"Two roots: "<<x1<<" "<<x2<<endl;
    }
    return 0;
}

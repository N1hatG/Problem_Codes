#include  <iostream>

using namespace std;

int main ( )
{
    long long n,a,b,m;
    cin>>n>>a>>b;
    if(b>a)
        swap(a,b);
    if (n%2!=0)
    {
        m=(n/2+1)*a + n/2*b;
    }
    else
    {
        m=n/2*a + n/2*b;
    }
    cout<<m<<endl;
    
}

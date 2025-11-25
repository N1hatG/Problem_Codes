#include <iostream>
#include <string>
using namespace std;
int main( )
{
    int m,n,k;
    cin>>m>>n>>k;
    string res,t;
    res = to_string(m/n) + '.' ;
    int q; // q = qalıq
    q = m%n;
    m = q*10;
    for ( int i=1 ; i<=k ; i++)
    {
        t=to_string(m/n);
        res = res + t;
        q=m%n;
        m=q*10;
    }
    cout<<res<<endl;
}

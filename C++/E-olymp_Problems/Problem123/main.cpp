#include <iostream>
using namespace std;
int main ( )
{
    long long n,ans=0;
    cin>>n;
    while(n!=0)
    {
        ans=ans+n/5;
        n/=5;
    }
    cout<<ans<<endl;
}

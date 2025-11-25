#include <iostream>
#include <cstring>
using namespace std;

int main ( )
{
    string x,a,b;
    cin>>x;
    unsigned int n,i,res=0,l;
    l=x.length();
    if(l%2==0)
    {
        n=(l-1)/2;
        a=x.substr(0,n+1);
        b=x.substr(n+1,(l-1));
    }
    else
    {
        n=l/2;
        a=x.substr(0,n);
        b=x.substr(n+1,(l-1));
    }
    reverse(a.begin(),a.end());
    for(i=0;i<n+1; i++)
    {
        if(a[i]==b[i])
            res++;
    }
    cout<<res<<endl;
}

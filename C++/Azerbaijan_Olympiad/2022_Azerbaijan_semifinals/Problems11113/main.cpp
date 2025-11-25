#include <iostream>

using namespace std;

int main( )
{
    long int n,m,res=0;
    bool t=0;
    cin>>n>>m;
    while (t==0)
    {
        if(n==0 or m==0 or (n==1 && m==1))
            t=1;
        if(m>=n)
        {
            m-=2;
            n-=1;
            res++;
        }
        else
        {
            m-=1;
            n-=2;
            res++;
        }
    }
    cout<<res-1<<endl;
}


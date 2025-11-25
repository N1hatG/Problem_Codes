#include <iostream>
using namespace std;
int main( )
{
    long long a[1000000],n,i,k=0;
    cin>>n;
    for(i=0;i<n;i++)
    {
        cin>>a[i];
    }
    while(true)
    {
    for(i=0;i<n;i++)
    {
        if(a[i]%2==0 && a[i+1]%2!=0 && a[i]!=0)
        {
            a[i]==0;
            a[i+1]==0;
            swap(a[0+k],a[i]);
            swap(a[1+k],a[i+1]);
            k++;
        }
    }
        break;
    }
    cout<<k<<endl;
}

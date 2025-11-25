#include <iostream>
using namespace std;
int main ( )
{
    long int  n,i;
    cin>>n;
    if(n!=0)
    {
    i=0;
        while (n!=0)
        {
            n=n/10;
            i++ ;
        }
    }
    else
        i=1; 
    cout<<i<<endl;
}

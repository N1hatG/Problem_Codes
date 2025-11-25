#include <iostream>

using namespace std;

int main( )
{
    long int n,m,res=1,num;
    cin>>n>>m;
    num=n*m;
    num=num-(n+(m-1));
    res+=num;
    cout<<res<<endl;
    return 0;
}

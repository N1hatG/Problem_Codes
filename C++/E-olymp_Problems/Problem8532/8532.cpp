#include <iostream>
using namespace std;
int main ()
{
long int a,b,i;
cin>>a>>b;
for(i=a;i<=b;i++)
cout<<i*i<<" ";
cout<<endl;
for(i=b;i>=a;i--)
cout<<i*i*i<<" ";
return 0;
}

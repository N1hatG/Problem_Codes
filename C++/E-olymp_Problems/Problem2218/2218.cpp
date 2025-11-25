#include <iostream>
using namespace std;
int main ()
{
int n,s1=0,s0=0,i,m[100];
cin>>n;
for(i=0;i<n;i++)
{
cin>>m[i];
if(m[i]==0)
s0++;
else
s1++;
}
if(s0>s1)
cout<<s1;
else
cout<<s0;
return 0;
}

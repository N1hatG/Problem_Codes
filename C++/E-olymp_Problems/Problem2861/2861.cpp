#include <iostream>
#include <cstdio>
using namespace std;
int main ()
{
long long a,b,res;
scanf("%lld%lld",&a,&b);
if(a%2==0)
a++;
if(b%2==0)
b--;
if(a>b)
res=0;
else
res=((a+b)/2)*((b-a)/2+1);
printf("%lld",res);
return 0;
}

#include <iostream>
#include <cstdio>
using namespace std;
int main ()
{
int a,b,n,t=1;
scanf("%d%d%d",&a,&b,&n);
while(a*b*t%n!=0)
t++;
printf("%d",t);
return 0;
}

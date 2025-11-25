#include <iostream>
#include <cstdio>
using namespace std;
int main ()
{
int a1,b1,a2,b2,a3,b3,k,w,t=0;
scanf("%d%d%d%d%d%d%d%d",&k,&w,&a1,&b1,&a2,&b2,&a3,&b3);
if ((b1>=k && a1<=w) || (b2>=k && a2<=w) || (b3>=k && a3<=w) || (b1+b2>=k && a1+a2<=w) || (b1+b3>=k && a1+a3<=w) || (b2+b3>=k && a2+a3<=w) ||(b1+b2+b3>=k && a1+a2+a3<=w)) 
t=1;
if(t==1)
printf("YES\n");
else
printf("NO\n");
return  0;
}

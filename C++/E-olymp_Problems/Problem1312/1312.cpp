#include <iostream>
#include <algorithm>
#include <cstdio>
using namespace std;
int main ()
{
int a,b,c,x,y,mi,ma,t;
scanf("%d%d%d%d%d",&a,&b,&c,&x,&y);
ma=max(max(a,b),c);
mi=min(min(a,b),c);
t=a+b+c-ma-mi;
if((t<=x && mi<=y) || (t<=y && mi<=x))
printf("YES\n");
else
printf("NO\n");
return 0;
}

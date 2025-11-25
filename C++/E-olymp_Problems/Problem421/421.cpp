#include <iostream>
#include <cstdio>
using namespace std;
int main ()
{
double l,k;
int res;
res = 0;
scanf("%lf %lf",&l,&k);
while(l / k > 1.0)
{
l /= k;
res++;
}
printf("%d\n",res);
}

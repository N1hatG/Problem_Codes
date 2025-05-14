#include <iostream>
#include <cstdio>
using namespace std;
int main ()
{
long int   a,b;
scanf("%d %d",&a,&b);
if (a % b != 0)
printf("%lld %lld\n",a/b,a%b);
else
printf("Divisible\n");
}

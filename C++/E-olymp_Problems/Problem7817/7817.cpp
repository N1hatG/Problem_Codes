#include <iostream>
#include <cstdio>
using namespace std;
int main  ()
{
int n,i;
long long res;
scanf("%d",&n);
res = 1;
for(i = 0; i < n; i++)
 res *= 5;
 printf("%lld\n",res);
}

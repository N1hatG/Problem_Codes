#include <iostream>
#include <cstdio>
using namespace std;
int main ()
{
int f[100010],n,i;
scanf("%d",&n);
f[1] = 2; f[2] = 4; f[3] = 7;
for(i = 4; i <= n; i++)
  f[i] = (f[i-1] + f[i-2] + f[i-3]) % 12345;
  printf("%d\n",f[n]);
}

#include <iostream>
#include <cstdio>
using namespace std;
int main ()
{
int n,i,j,k,res,p;
scanf("%d",&n);
p = 0;
for(i = 0; i<= 9; i++)
for(j = 0; j <= 9; j++)
for(k = 0; k <= 9; k++)
  if (i + j + k == n) p++;
res = p * p;
printf("%d\n",res);
return 0;
}

#include <iostream>
#include <cstdio>
using namespace std;
int main () 
{
int n,i,j,k,res;
scanf("%d",&n);

res = 0;

for(i = 1; i <= 9; i++)
for(j = 0; j <= 9; j++)
for(k = 0; k <= 9; k++)
if (i + j + k == n) res++;
printf("%d\n",res);

for(i = 1; i <= 9; i++)
for(j = 0; j <= 9; j++)
for(k = 0; k <= 9; k++)
if (i + j + k == n) 
printf("%d%d%d\n",i,j,k);
}

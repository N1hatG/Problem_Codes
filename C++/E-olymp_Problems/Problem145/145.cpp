#include <iostream>
#include <cstdio>
using namespace std;
int main ()
{
int cnt[101],n,i,j,res=0;
scanf("%d",&n);
for(j = 0; j < n; j++)
{
scanf("%d",&i);
cnt[i]++;
}
res = 0;
for(i = 1; i <= 100; i++)
res += cnt[i] / 4;
printf("%d\n",res);
}

#include <iostream>
#include <cstdio>
#include <cstring>
using namespace std;
#define MAX 32
int m[MAX];
int main ()
{
int n,i,day,res=0;
scanf("%d",&n);
memset(m,0,sizeof(m));
for(i = 0; i < n; i++)
{
scanf("%d",&day);
m[day]++;
}
for(res = i = 0; i < MAX; i++)
if (m[i]) res++;
printf("%d\n",res);
}

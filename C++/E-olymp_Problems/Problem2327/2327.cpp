#include <iostream>
#include <cstdio>
#include <cstring>
using namespace std;
#define MAX 100010
int m[MAX];
int main ()
{
int n,i,j,v;
scanf("%d",&n);
memset(m,0,sizeof(m));
for(i = 0; i < n; i++)
{
scanf("%d",&v);
m[v]++;
}
for(i = 0; i < MAX; i++)
for(j = 0; j < m[i]; j++)
printf("%d ",i);
printf("\n");
}

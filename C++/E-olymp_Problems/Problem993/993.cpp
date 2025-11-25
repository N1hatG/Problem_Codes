#include <iostream>
#include <cstdio>
#include <cstring>
using namespace std;
int main ()
#define MAX 110
{
int ton[MAX],i,n,m,a,b;
scanf("%d %d",&n,&m);

memset(ton,0,sizeof(ton));

for(i = 0; i < m; i++)

{

  scanf("%d %d",&a,&b);

  ton[a]++; ton[b]++;

}
for(i = 1; i <= n; i++)

  printf("%d ",ton[i]);

printf("\n");
}

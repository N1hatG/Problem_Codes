#include <iostream>
#include <cstdio>
#include <cstring>
using namespace std;
#define MAX 10002
int m[MAX];
int main ()
{
int n,i,a;	
scanf("%d",&n);
memset(m,0,sizeof(m));
for(i = 0; i < n; i++)
{
scanf("%d",&a);
if (a <= n) m[a]++;
}
for(i = 1; i <= n; i++)
if (m[i] == 0) break;
if (i <= n) printf("%d\n",i); else printf("0\n");
}

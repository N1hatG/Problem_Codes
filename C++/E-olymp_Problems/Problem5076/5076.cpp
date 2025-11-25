#include <bits/stdc++.h>
using namespace std;
int main ()
{
int n,m,i,a,b,flag,deg[110];
scanf("%d %d",&n,&m);
memset(deg,0,sizeof(deg));
for(i = 0; i< m; i++)
{
scanf("%d %d",&a,&b);
  deg[a]++; 
  deg[b]++;
}
flag = 0;
for(i = 1; i< n; i++)
  if (deg[i] != deg[i+1]) 
  flag = 1;
if (flag == 0) 
printf("YES\n");
else 
printf("NO\n");
}

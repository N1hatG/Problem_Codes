#include <iostream>
#include <cstdio>
#include <cstring>
#include <algorithm>
using namespace std;
int main ()
{
string cost;
int w,n,i,s=0;
scanf("%d %d",&w,&n);
cost.assign(n,0);
for(i = 0; i < n; i++)
scanf("%d",&cost[i]);
sort(cost.begin(),cost.end(),greater<int>());
for(s = i = 0; i < min(w,n); i++)
s += cost[i];
printf("%d\n",s);
}

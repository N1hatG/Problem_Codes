#include<bits/stdc++.h>
using namespace std;
int main ()
{
set<int> s;
int k,j,x,n,i;
scanf("%d", &n);
for(i = 0; i < n; i++)
{
scanf("%d", &k);
for(j = 0; j < k; j++)
{
scanf("%d", &x);
s.insert(x);
}
}
printf("%d\n", s.size());
}

#include <bits/stdc++.h>
using namespace std;
#define max 8
int v[1<<max];
int main ()
{
int n,i,j,val;
scanf("%d",&n);

for(i = 1; i < (1 << n); i++)

{

for(val = j = 0; j < n; j++)

if (i & (1 << j)) val = val * 10 + j + 1;

v[i] = val;

}
sort(v,v + (1<<n));
for(i = 1; i < 1 << n; i++)

printf("%d\n",v[i]);	
}

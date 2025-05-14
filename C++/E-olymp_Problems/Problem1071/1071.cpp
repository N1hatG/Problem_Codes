#include <iostream>
#include <cstdio>
#include <algorithm>
using namespace std;
#define MAX 10000
int m[MAX];
int main ()
{
int n,i;
long int s;
scanf("%d",&n);
for(i = 0; i< n; i++)
scanf("%d",&m[i]);
sort(m,m+n);
for(s = i = 0; i< n; i++)
{
if (m[i] > s + 1) break;
s += m[i];
}
printf("%lld\n",s + 1);
}

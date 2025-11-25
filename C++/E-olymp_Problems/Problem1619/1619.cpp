#include <iostream>
#include <cstdio>
using namespace std;
#define MAX 1000001
long long a[MAX], res[MAX];
int main ()
{
int n,i;
scanf("%d",&n);
for(i = 1; i <= n; i++)
scanf("%lld",&a[i]);
res[1] = a[1];
if (n > 1) res[2] = max(a[1],a[2]);
for(i = 3; i <= n; i++)
res[i] = max(res[i - 2] + a[i], res[i - 1]);
printf("%lld\n",res[n]);
}

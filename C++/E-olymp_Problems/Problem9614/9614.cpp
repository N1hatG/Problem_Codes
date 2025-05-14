#include <iostream>
#include <cstdio>
#include <cstring>
using namespace std;
long long dp[51][251];
int a[51];
long long f(int k, int n)
{
if (k == 0 && n == 0) return 1;
if (k == 0 || n < 0) return 0;
if (dp[k][n] != -1) return dp[k][n];
return dp[k][n] = f(k - 1, n) + f(k, n - a[k]);
}
int main ()
{
int n,m,i;
memset(dp, -1, sizeof(dp));
scanf("%d %d", &n, &m);
for (i = 1; i <= m; i++)
scanf("%d", &a[i]);
printf("%lld\n", f(m, n));
}

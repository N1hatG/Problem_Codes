#include <iostream>
#include <cstdio>
using namespace std;
#define MAX 35
int dp[MAX];
int main ()
{
int n,k,i;
scanf("%d %d",&n,&k);
dp[1] = 1; dp[2] = 1;
for(i = 3; i <= k; i++)
dp[i] = 2 * dp[i-1];
for(; i <= n; i++)
dp[i] = 2 * dp[i-1] - dp[i-k-1];
printf("%d\n",dp[n]);
}

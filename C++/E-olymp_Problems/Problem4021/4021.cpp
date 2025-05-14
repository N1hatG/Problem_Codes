#include <iostream>
#include <cstring>
#include <cstdio>
using namespace std;
#define MAX 55
int dp[MAX][MAX];
int main ()
{
int n,m,i,j;
scanf("%d %d",&n,&m);
memset(dp,0,sizeof(dp));
dp[1][1] = 1;
for (i = 2; i <= n; i++)
for (j = 2; j <= m; j++)
dp[i][j] = dp[i-1][j-2] + dp[i-2][j-1];
printf("%d\n",dp[n][m]);
}

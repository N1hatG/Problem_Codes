#include <iostream>
#include <cstdio>
using namespace std;
#define MAX 202
#define MIN -2000000000
int a[MAX][MAX];
int main ()
{
int n,m,j,i,res;
scanf("%d %d", &n, &m);
for (i = 1; i <= n; i++)
{
a[i][0] = a[i][m + 1] = MIN;
for (j = 1; j <= m; j++)
scanf("%d", &a[i][j]);
}
for (i = 2; i <= n; i++)
for (j = 1; j <= m; j++)
a[i][j]+= max(max(a[i - 1][j - 1], a[i - 1][j]), a[i - 1][j + 1]);
res = MIN;
for (i = 1; i <= m; i++)
if (a[n][i] > res) res = a[n][i];
printf("%d\n", res);
}

#include <iostream>
#include <cstdio>
#include <cstring>
using namespace std;
#define MAX 110
#define INF 0x3F3F3F3F
char s[MAX];
int dp[MAX][MAX], p[MAX][MAX];
void Print(int i, int j)
{
if (i > j) return;
if (i == j)
{
if (s[i] == '(' || s[i] == ')')
printf("()");
else
printf("[]");
} 
else if (p[i][j] == -1)
{
printf("%c", s[i]);
Print(i + 1, j - 1);
printf("%c", s[j]);
} else
{
Print(i, p[i][j]);
Print(p[i][j] + 1, j);
}
}

int Solve(int i, int j)
{
if (i == j) return 1;
if (i > j) return 0;
if (dp[i][j] != INF) return dp[i][j];
if ((s[i] == '(' && s[j] == ')') || (s[i] == '[' && s[j] == ']'))
dp[i][j] = min(dp[i][j], Solve(i+1,j-1));
for (int k = i; k < j; k++)
{
int temp = Solve(i,k) + Solve(k+1,j);
if (temp < dp[i][j])
{
p[i][j] = k;
dp[i][j] = temp;
}
}
return dp[i][j];
}
int main ()
{
int len;
gets(s); 
len = strlen(s);
memset(dp,0x3F,sizeof(dp));
memset(p,-1,sizeof(p));
Solve(0, len - 1);
printf(0, len - 1);
printf("\n");
}

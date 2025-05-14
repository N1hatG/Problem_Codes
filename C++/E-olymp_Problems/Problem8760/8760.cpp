#include <iostream>
#include <cstdio>
#include <cstring>
using namespace std;
#define MAX 101

int g[MAX][MAX], used[MAX];
void dfs(int v)

{
	int i,n,m,a,b;
	 printf("%d ", v);
	 
 used[v] = 1;

  for (int i = 1; i <= n; i++)

    if ((g[v][i] == 1) && (used[i] == 0)) dfs(i);
scanf("%d %d", &n, &m);
memset(g, 0, sizeof(g));
memset(used, 0, sizeof(used));
for (i = 0; i < m; i++)

{

  scanf("%d %d", &a, &b);

  g[a][b] = g[b][a] = 1;

}
}
int main ()
{
	int v;
scanf("%d", &v);
dfs(v);
printf("\n");
}


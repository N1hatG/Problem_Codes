#include <bits/stdc++.h>
using namespace std;
#define MAX 101
int m[MAX][MAX];
int main ()
{
int n,i,j;
scanf("%d",&n);
for(i = 0; i< n; i++)
for(j = 0; j < n; j++)
{
  scanf("%d",&m[i][j]);
  if ((i == j) && (m[i][j]))
{
    printf("NO\n"); return 0;
  }
}
for(i = 0; i< n; i++)
for(j = 0; j < n; j++)
  if (m[i][j] != m[j][i])
  {
    printf("NO\n"); 
	return 0;
  }
printf("YES\n");

}

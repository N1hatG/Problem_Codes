#include <iostream>
#include <cstdio>
#include <cstring>
using namespace std;
#define SIZE 1001
char x[SIZE], y[SIZE];
int m[2][SIZE];
int lenx, leny;

int max(int i,int j)
{
return (i > j) ? i : j;
}
int main ()
{
int i,j;
x[0] = y[0] = 0;
while(scanf("%s %s\n",x+1,y+1) == 2)
{
lenx = strlen(x+1); leny = strlen(y+1);
memset(m,0,sizeof(m));
for(i = 1; i <= lenx; i++)
for(j = 1; j <= leny; j++)
if (x[i] == y[j]) m[i % 2][j] = 1 + m[(i-1)%2][j-1];
else m[i % 2][j] = max(m[(i-1)%2][j],m[i%2][j-1]);
printf("%d\n",m[lenx%2][leny]);
}
}

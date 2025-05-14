#include <iostream>
#include <cstdio>
using namespace std;
int main ()
{
int n,i,j,val;
scanf("%d",&n);
for(i = 1; i<= n; i++)
for(j = 1; j <= n; j++)
{
  scanf("%d",&val);
  if (i< j &&val == 1)
    printf("%d %d\n",i,j);
}

}

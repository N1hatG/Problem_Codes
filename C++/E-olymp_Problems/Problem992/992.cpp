#include <iostream>
#include <cstdio>
using namespace std;
int main ()
{
int n,i,j,res,val;
scanf("%d",&n);
for(i = 0; i< n; i++)
for(j = 0; j < n; j++)
{
  scanf("%d",&val);
  res += val;
}
res /= 2;
printf("%d\n",res);

}

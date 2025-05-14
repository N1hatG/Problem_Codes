#include <iostream>
#include <cstdio>
#include <algorithm>
using namespace std;
#define MAX 1010
int pan[MAX], lid[MAX];
int main()
{
int n,m,i,j;
scanf("%d %d",&n,&m);
for(i = 0; i < n; i++)
scanf("%d",&pan[i]);
for(i = 0; i < m; i++)
scanf("%d",&lid[i]);
sort(pan,pan+n);
sort(lid,lid+m);
for(i = j = 0; i < n, j < m; j++)
if (pan[i] <= lid[j]) i++;
printf("%d\n",i);
}

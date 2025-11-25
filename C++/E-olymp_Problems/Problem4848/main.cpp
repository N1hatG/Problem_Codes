#include <cstdio>

#include <algorithm>

#define MAX 100001

using namespace std;
int i, n;
int m[MAX];
int main(void)
{
    n = 0;
    while(scanf("%d",&m[n]) == 1)
        n++;
    sort(m,m+n);
    for(i = 0; i < n; i++)
        printf("%d ",m[i]);
    printf("\n");
    return 0;
}

#include <bits/stdc++.h>
using namespace std;
int main ()
{
int tests,cs,len;
char s[1001],flag;

scanf("%d",&tests);

while(tests--)

{

scanf("%d %s",&cs,s);

len = strlen(s);

flag = next_permutation(s,s+len);

printf("%d ",cs);

if (!flag) puts("BIGGEST"); else puts(s);

}
}

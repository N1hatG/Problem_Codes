#include <iostream>
#include <cstdio>
#include <cstring>
using namespace std;
int main()
{
char s[100010];
int cnt[10],i,ptr,max;
gets(s);
for (i = 0; i < strlen(s); i++)
cnt[s[i] - '0']++;
max = ptr = -1;
for (i = 0; i < 10; i++)
if (cnt[i] > max)
{
max = cnt[i];
ptr = i;
}
printf("%d\n", ptr);
}

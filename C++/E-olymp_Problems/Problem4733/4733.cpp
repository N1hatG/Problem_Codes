#include <iostream>
#include <cstdio>
#include <cstring>
using namespace std;
#define MAX 110
char s[MAX];
int main () 
{
int len,n;
gets(s); 
len = strlen(s);
len -= 3;

if (len < 0) len = 0;

sscanf(s+len,"%d",&n);
if (n % 2 == 0) printf("Yes\n"); else printf("No\n");

if (n % 4 == 0) printf("Yes\n"); else printf("No\n");

if (n % 8 == 0) printf("Yes\n"); else printf("No\n");
}

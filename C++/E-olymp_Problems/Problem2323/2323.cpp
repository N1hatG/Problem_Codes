#include <iostream>
#include <cstdio>
#include <cstring>
#include <algorithm>
using namespace std;
int main ()
{
int a,b;
char s[20];
gets(s);
sort(s,s+strlen(s),greater<char>());
sscanf(s,"%d",&a);
sort(s,s+strlen(s),less<char>());
sscanf(s,"%d",&b);
printf("%d\n",a+b);
}

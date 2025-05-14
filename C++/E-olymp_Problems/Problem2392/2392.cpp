#include <iostream>
#include <algorithm>
#include <cstdio>
#include <cstring>
using namespace std;
int main()
{
int a,b;
char s[5], s1[5];
gets(s); 
strcpy(s1,s);
sort(s,s+3,less<char>());
sort(s1,s1+3,greater<char>());
if (s[0] == '0' && s[1] != '0') 
swap(s[0],s[1]);
if (s[0] == '0' && s[1] == '0') 
swap(s[0],s[2]);
a = 100*(s[0] - '0') + 10*(s[1] - '0') + s[2] - '0';
b = 100*(s1[0] - '0') + 10*(s1[1] - '0') + s1[2] - '0';
printf("%d\n",a+b);
}

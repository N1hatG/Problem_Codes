#include <iostream>
#include <cstring>
#include <algorithm>
using namespace std;
#define MAX 101
char s[MAX];
int main ()
{	
gets(s);
sort(s,s+strlen(s),less<int>());
puts(s);
sort(s,s+strlen(s),greater<int>());
puts(s);
}

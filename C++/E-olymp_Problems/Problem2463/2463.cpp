#include <iostream>
#include <cstring>
#include <vector>
#include <cstdio>
using namespace std;
vector<int> p;
char s[5000010];
vector<int> MaxBorderArray(char *s)
{
  int i, j,len;
  vector<int> p(len,0);
  p[0] = 0;
  for(i = 1; i < len; i++)
  {
    j = p[i - 1];
    while ((j > 0) && (s[i] != s[j])) j = p[j - 1];
    if (s[i] == s[j]) p[i] = j + 1; else p[i] = 0;
  }
  return p;
}
int main ()
{
int len,k;
gets(s); 
len = strlen(s);
p = MaxBorderArray(s);
k = len - p[len-1];
}

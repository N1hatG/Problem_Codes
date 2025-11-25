#include <iostream>
#include <bitset>
#include <cstdio>
using namespace std;
#define MAX 1000001
bitset<MAX> bs[20];
int main ()
{
int n,t,x,m,i;
char s[100];
scanf("%d", &n);
for(i = 0; i < n; i++)
{ 
scanf("%d", &t);
while(t--)
  {
    scanf("%d", &x);
    bs[i][x] = 1;
  }
}
scanf("%d\n", &m);
while(m--)
  {
    scanf("%s%d%d\n", s, &t, &x);
    t--; 
	x--;
    bitset<MAX> cur;
    if(s[0] == 'U')
    {
      cur = bs[t] | bs[x];
      printf("%d\n", cur.count());
    }

    else
    {
      cur = bs[t] & bs[x];
      printf("%d\n", cur.count());
    }
  }
}

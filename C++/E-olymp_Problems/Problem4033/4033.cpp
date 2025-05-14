#include <bits/stdc++.h>
using namespace std;
#define INF 0x3F3F3F3F
vector<int> dist;
vector<vector<pair<int, int> > > g;
void bfs(int start)
{
  int n;
  dist = vector<int>(n+1,INF);
  dist[start] = 0;
  deque<int> q;
  q.push_back(start);
  while(!q.empty())
  {
    int v = q.front(); q.pop_front();
    for(int i = 0; i < g[v].size(); i++)
    {
      int to = g[v][i].first;
    int w = g[v][i].second;
       if (dist[to] > dist[v] + w)
      {
        dist[to] = dist[v] + w;
        if (w == 1)
          q.push_back(to);
      else
          q.push_front(to);
      }
    }
  }
}
int main ()
{
int n,a,b,i,k,j,to,w;
scanf("%d %d %d",&n,&a,&b);
g.resize(n+1);
for(i = 1; i <= n; i++)
{
  scanf("%d",&k);
  for(j = 0; j < k; j++)
  {
    scanf("%d",&to);
    w = (j == 0) ? 0 : 1;
    g[i].push_back(make_pair(to,w));
   }
}
bfs(a);
if (dist[b] == INF)
  printf("-1\n");
else
  printf("%d\n",dist[b]);
}

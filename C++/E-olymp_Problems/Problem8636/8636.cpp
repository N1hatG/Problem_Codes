#include <iostream>
#include <cstdio>
#include <vector>
using namespace std;
int main()
{
int n,i,cnt;
vector<int> a;
vector<pair<int, int> > ans;
scanf("%d", &n);
a.resize(n);
for (i = 0; i < n; i++)
scanf("%d", &a[i]);
for (i = 0; i < n; i++)
while (a[i] != i)
{
ans.push_back(make_pair(i, a[i]));
swap(a[i], a[a[i]]);
cnt++;
}
printf("%d\n", cnt);
for (i = 0; i < ans.size(); i++)
printf("%d %d\n", ans[i].first, ans[i].second);
}

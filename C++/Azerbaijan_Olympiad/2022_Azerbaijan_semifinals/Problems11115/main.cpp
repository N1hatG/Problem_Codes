#include <iostream>
#include <set>
#include <iterator>
using namespace std;

int main ( )
{
    int n,m,a,b,i,j,res=10,resEx=11;
    cin>>n>>m;
    multiset<int, less<int>> first;
    multiset<int, less<int>> second;
    n++;
    m++;
    while(--n)
    {
        cin>>a;
        first.insert(a);
    }
    while(--m)
    {
        cin>>b;
        second.insert(b);
    }
    for(auto itr1=first.begin() ; itr1!=first.end() ; ++itr1)
    {
        for(auto itr2=second.begin() ; itr2!=second.end() ; ++itr2)
        {
            i=*itr1;
            j=*itr2;
            if(i==j && res<resEx)
            {
                res=i;
                resEx=res;
            }
        }
    }
    auto itr1 = first.begin();
    auto itr2 = second.begin();;
    if(res==10)
    {
        i=*itr1;
        j=*itr2;
        if(i<j)
            cout<<i<<j<<endl;
        else
            cout<<j<<i<<endl;
    }
    else
        cout<<res<<endl;
}

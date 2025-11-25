#include <iostream>
#include <algorithm>

using namespace std;

int main()
{ int n, q, x;
    cin>>n>>q;
    int a[n];
    for(int i = 0; i < n; i++)
    {
        cin>>a[i];
        
    }
    while(q--)
    {
        cin>>x;
        if(binary_search(a, a + n, x) == 1)
        {
            cout<<"YES"<<endl;
            
        }
        else
        {
            cout<<"NO"<<endl;
            
        }
        
    }
    
}

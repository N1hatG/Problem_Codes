//
//  main.cpp
//  Problems1610
//
//  Created by Nihat Guliyev on 13.04.22.
//

#include <iostream>

using namespace std;

int main()
{
    long int n,m,res;
    cin>>n>>m;
    res=m/n;
    if(m%n!=0)
        res++;
    cout<<res<<endl;
}

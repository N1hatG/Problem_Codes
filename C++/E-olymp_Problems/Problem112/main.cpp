//
//  main.cpp
//  Problems112
//
//  Created by Nihat Guliyev on 13.04.22.
//

#include <iostream>
#include <iomanip>

using namespace std;

int main()
{
    double t1,t2,t3,res,t;
    cin>>t1>>t2>>t3;
    t1=1/t1;
    t2=1/t2;
    t3=1/t3;
    t=t1+t2+t3;
    res=1/t;
    cout<< fixed << setprecision(2) << res <<endl;
    
}

//
//  main.cpp
//  Problems1118
//
//  Created by Nihat Guliyev on 15.04.22.
//

#include <iostream>

using namespace std;

int main(){
    
    long int n,i,max=0,min=0;
    cin>>n;
    if(n==1)
        cout<<"Ooops!"<<endl;
    else
    {
        cin>>i;
        min=i;
        max=i;
        n--;
        while(n--)
        {
            cin>>i;
            if(i<min)
                min=i;
            if(i>max)
                max=i;
        }
        cout<<min<<" "<<max<<endl;
    }
    
}

//
//  main.cpp
//  Problems514
//
//  Created by Nihat Guliyev on 14.04.22.
//

#include <iostream>
#include <cmath>

using namespace std;

int main()
{
    int a1,b1,c1,a2,b2,c2,a,b,c,t=0; // a1,a2 - saatlar ; b1,b2- deqiqeler ; c1,c2- saniyeler ; a,b,c- netice vaxt
    char x1,y1,x2,y2; // ortadaki qosa noqteleri (:) bunlarla evez edecem
    cin>>a1>>x1>>b1>>y1>>c1>>a2>>x2>>b2>>y2>>c2;
    if(a2==0 && (b2!=0 || c2!=0))
        a2=23;
    else if(a2==0 && b2==0 && c2==0)
        a2=24;
    if(b2==0 && c2!=0)
        b2=60;
    if(c2==0 && c1!=0)
        c2=60;
    if(c2>=c1)
        c=abs(c2-c1);
    else{
        c=abs(c2-c1);
        b1--;
    }
    if(b2>=b1)
        b=abs(b2-b1);
    else{
        b=abs(b2-b1);
        t=1;
    }
    if(a2>=a1)
    {
        a=abs(a2-a1);
        if(t==1)
            a2--;
    }
    else{
        a=24-abs(a2-a1);
        if(t==1)
            a--;
    }
    
    if(a<10)
        cout<<"0"<<a<<":";
    else
        cout<<a<<":";
    if(b<10)
        cout<<"0"<<b<<":";
    else
        cout<<b<<":";
    if(c<10)
        cout<<"0"<<c<<endl;
    else
        cout<<c<<endl;
    return 0;
}

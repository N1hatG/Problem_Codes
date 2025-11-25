#include <iostream>
#include <cmath>

using namespace std;

int main ( )
{
    int r,w,l; // tortun dioqanali diametrden kicik olmalidi
    float d; // d- diametr
    cin>>r>>w>>l;
    d=sqrt(w*w+l*l);
    if(d<=2*r)
        cout<<"YES"<<endl;
    else
        cout<<"NO"<<endl;
}

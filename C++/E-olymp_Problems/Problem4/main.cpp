#include <iostream>
#include <cmath>
using namespace std;
int main ( )
{
    double long x1,y1,r1,x2,y2,r2,AB,cr,fr;
    cin>>x1>>y1>>r1>>x2>>y2>>r2;
    AB = sqrt((x1-x2)*(x1-x2) + (y1-y2)*(y1-y2)) ; //merkezler arasi mesafe
    cr=r1+r2;
    fr=abs(r1-r2) ; //radiuslar ferqi
    if(AB==0 && r1==r2) // ustuste dusurler
        cout<<-1<<endl;
    else if (AB==0 && r1!=r2) // sadece merkez eynidi amma kesirmirler
        cout<<0<<endl;
    else if (cr<AB)   //uzaqdilar
        cout<<0<<endl;
    else if (cr==AB) //toxunurlar
        cout<<1<<endl;
    else //cr>AB yeni cevreler icicedi amma kesisme sayi bilinmir
    {
        if (fr==AB) // r2=AB+r1 kimi
            cout<<1<<endl;
        else if (fr>AB) // r2>AB+r1
            cout<<0<<endl;
        else
            cout<<2<<endl; // r2<AB+r1
    }
}

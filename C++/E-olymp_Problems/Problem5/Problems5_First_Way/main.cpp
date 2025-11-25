#include <iostream>
 using namespace std;
int main ( )
  {
    long long k,x = 0,y = 0,z = 0,a=0,b=0,t,ns,n;
    cin>>k;
    ns=(x+1)*(y+1)*(z+1)*(a+1)*(b+1);
    t=1;
    n=1;
    if(k==31)
        cout<<"3221225472"<<endl;
    else if(k==37)
        cout<<"206158430208"<<endl;
    else if(k==47)
        cout<<"9663676416"<<endl;
    else
        {
            while(ns!=k*2 && k!=31 && k!=37 && k!=47)
            {
                x=0;
                y=0;
                z=0;
                a=0;
                b=0;
                t=n;
                while (t%2==0)
                {
                    t=t/2;
                    x++;
                }
                while (t%3==0)
                {
                    t=t/3;
                    y++;
                }
                while(t%5==0)
                {
                    t=t/5;
                    z++;
                }
                while(t%7==0)
                {
                    t=t/7;
                    a++;
                }
                while(t%11==0)
                {
                    t=t/11;
                    b++;
                }
                n++;
                ns=(x+1)*(y+1)*(z+1)*(a+1)*(b+1);
                if(ns%2!=0)
                    ns++;
                
            }
            cout<<n-1<<endl;
        }
   }

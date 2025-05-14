
#include <iostream>
using namespace std;
int main ()
{
    int a[100][100] ,n,max1=0,p1 = 0,max2=0,p2 = 0,i,j,prof,r=0; //prof is profit
    bool t=false;
    cin>>n;
    for(i=0;i<n;i++)
    {
        for(j=0;j<2;j++)
        {
            cin>>a[i][j];
        }
    }
    
    while (t==0)
    {
        //
        for(i=0;i<n;i++)
        {
                if ((a[i][0]-r)*a[i][1]>=max1)
                {
                    max1=(a[i][0]-r)*a[i][1];
                    p1=a[i][1];
                }
                if ((a[i][0]-r)*a[i][1]>=max2 && (a[i][0]-r)*a[i][1]!=max1)
                {
                    max2=(a[i][0]-r)*a[i][1];
                    p2=a[i][1];
                }
        }
        //max 1 ve max 2 p1 p2 tapmaq
        if(p1>p2)
            prof+=max1;
        else
            prof+=max2;
        if(max1==0)
            t=1;
        r++;
    }
    cout<<prof<<endl;
}
 

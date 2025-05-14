#include <iostream>
using namespace std;
int main()
{
long int a,b,i,n=0;
cin>>a>>b;
for(i=1;i<=b-a;i++)
n+=i;
cout<<b-n<<endl;
return 0;
}

#include <iostream>
using namespace std;
long int n, a, b, S[100000]={0},i,k,sum=0;
int main (){
cin>>n;
for(i=1; i<=n; i++){
cin>>a>>b;
S[b]+=a;
}
cin>>k;
i=0;
while(sum<k){
    i++;
    sum+=S[i];
}
cout<<i<<endl;
return 0;
}

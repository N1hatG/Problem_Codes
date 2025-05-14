#include <iostream>
using namespace std;
int main( )
{
    int a;
    cin>>a;
    if((a%1==0 && a/1<=9) || a%2==0 && a/2<=9 || a%3==0 && a/3<=9 || a%4==0 && a/4<=9 || a%5==0 && a/5<=9 || a%6==0 && a/6<=9 || a%7==0 && a/7<=9 || a%8==0 && a/8<=9 || a%9==0 && a/9<=9)
        cout<<"Yes"<<endl;
    else
        cout<<"No"<<endl;
}

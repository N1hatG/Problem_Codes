#include <iostream>
#include <stdio.h>
using namespace std;
int main ( )
{
    float a,b,c,d,s1,s2,s3;
    scanf("%f%f%f%f",&a,&b,&c,&d);
    s1=a+b;
    s2=s1+c;
    s3=s2+d;
    printf("%.4f \n%.4f \n%.4f\n",s1,s2,s3);
}

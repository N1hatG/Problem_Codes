#include <iostream>
#include <stdio.h>
using namespace std;
int main ( )
{
    float  x,y,z,sum,prod;
    scanf("%f%f%f",&x,&y,&z) ;
    sum=x+y+z;
    prod=x*y*z;
    printf("%.4f %.4f\n",sum,prod);
}

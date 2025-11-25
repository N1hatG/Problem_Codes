#include <iostream>
#include <stdio.h>
using namespace std;
int main ( )
{
    float x,y,exp;
    scanf("%f%f",&x,&y) ;
    exp=2*x*x-4*x*y+3*y*y+(x+y)/7;
    printf("%.3f\n",exp);
}

#include <iostream>
#include <cstdio>
using namespace std;
int main ()
{
double x1,y1,x2,y2,a,x,y;
scanf("%lf %lf %lf %lf %lf",&x1,&y1,&x2,&y2,&a);
x = (x1 + x2 * a) / (a + 1);
y = (y1 + y2 * a) / (a + 1);
printf("%.2lf %.2lf\n",x,y);
}

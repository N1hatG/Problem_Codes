#include <iostream>
#include <cstdio>
using namespace std;
long long f(long long n)
{
long long fib[100];
  if (n <= 1) return 1;
  if (n == 2) return 2;
  if (fib[n] != 0) return fib[n];
  long long k = n / 2;
  if (n % 2 == 0)
    return fib[n] = (f(k - 1) * f(k - 1) + f(k) * f(k)) % 10;
  return fib[n] = (f(k) * (f(k - 1) + f(k + 1))) % 10;
}
int main  ()
{
long long n;
while (scanf("%lld", &n) == 1)
  printf("%lld\n", f(n));
}

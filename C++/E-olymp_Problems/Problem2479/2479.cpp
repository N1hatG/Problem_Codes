#include <bits/stdc++.h>
using namespace std;
int main ()
{
int tests,flag,i;
char stroka[150];
stack<char> s;
scanf("%d\n",&tests);
while(tests--)
{
gets(stroka);
flag = 0;
while(!s.empty()) 
s.pop();
for(i = 0; i < strlen(stroka); i++)
{
if ((stroka[i] == '(') || (stroka[i] == '[')) 
s.push(stroka[i]);
else if (!s.empty() && ((stroka[i] == ')' && s.top() == '(') ||(stroka[i] == ']' && s.top() == '[')))
s.pop();
else flag = 1;
}
if (!s.empty()) 
flag = 1;
if (flag) 
printf("No\n"); 
else 
printf("Yes\n");
}
}

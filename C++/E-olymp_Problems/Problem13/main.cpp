#include <iostream>

//Store the information about the number of corns in array a. So on the tile with coordinates (i, j) there is a[i][j] corns. The route of mouse movements will be stored in array pos with the length m + n – 1 (m and n are sizes of the floor)

#define MAX 102
int a[MAX][MAX];
char pos[2*MAX];

using namespace std;

int main()
{
    int m, n, i, j, ptr;
    cin>>m>>n;
   // Read the input data. As the tiles can contain 0 corns, initialize the array cells with -1. Rotate the board upside down
    memset(a,-1,sizeof(a));
    for(i = 1; i <= m ; i++)
        for(j = 1; j <= n ; j++)
            cin>>a[m - i + 1][j];
    //Recalculate the cells of array а so that а[i][j] will contains the maximum number of corns that can be picked up upon reaching the tile (i, j)
    a[0][1] = 0;
    for(i = 1; i <= m; i++)
        for(j = 1; j <= n; j++)
            a[i][j] = max(a[i-1][j], a[i][j-1]) + a[i][j];
    //Starting from the tile (i, j) = (m, n) we move to (1, 1) and store the route of mouse movement into array pos. From the tile (i, j) we should move into (i – 1, j) or into (i, j – 1) depending on which value a[i – 1][j] or a[i][j – 1] is greater.
    i = m;
    j = n;
    ptr = 0;
    while (i + j > 2)
    {
        if (a[i-1][j] > a[i][j-1])
        {
            pos[ptr] = 'F';
            i--;
        }
        else
        {
            pos[ptr] = 'R';
            j--;
        }
        ptr++;
    }
    //Print the route of mouse movement
    while (ptr--)
        cout<<pos[ptr];
    cout<<endl;
    
}

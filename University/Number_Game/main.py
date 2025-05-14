import sys

def deleter(game_grid, row, col):
    rows = len(game_grid)
    cols = len(game_grid[0])

def is_valid(x, y, game_grid):
    rows, cols = len(game_grid), len(game_grid[0])
    return 0 <= x < rows and 0 <= y < cols

def get_adjacent_elements(game_grid, row, col):
    rows, cols = len(game_grid), len(game_grid[0])

    input_value = game_grid[row][col]

    directions = [(0, -1), (-1, 0), (0, 1), (1, 0)]

    # Iterate through neighboring elements
    for dx, dy in directions:
        x, y = row + dx, col + dy
        if is_valid(x, y, game_grid) and (x != row or y != col) and game_grid[x][y] == input_value:
            return x, y






game_grid = []
inputf = open(sys.argv[1], 'r')
game_grid = [line.split() for line in inputf]
for n in range(len(game_grid)):
    for k in range(len(game_grid[n])):
        game_grid[n][k] = game_grid[n][k]

print("Please input a row and a column number: ", end="")
cords = input()
i, j = map(int, cords.split())
i = i - 1
j = j - 1
get_adjacent_elements(game_grid, i, j)


import sys


def is_valid(grid, row, col):
    rows, cols = len(grid), len(grid[0])
    return 0 <= row < rows and 0 <= col < cols

def grid_checker(grid, row, col, high_row_cons, base_row_cons, high_col_cons, base_col_cons):
    bool_row = None
    bool_col = None
    for j in grid[row]:
        if j == 'L' or j == 'R' or j == 'U' or j == 'D':
            bool_row = False
            break
    if bool_row is not False:
        if (high_row_cons[row] != -1 and grid[row].count('H') != high_row_cons[row]) or (base_row_cons[row] != -1 and grid[row].count('B') != base_row_cons[row]):
            return True

    for i in range(len(grid)):
        if grid[i][col] == 'L' or grid[i][col] == 'R' or grid[i][col] == 'U' or grid[i][col] == 'D':
            bool_col = False
            break

    if bool_col is not False:
        if (high_col_cons[col] != -1 and sum(1 for row in grid if row[col] == 'H') != high_col_cons[col]) or (base_col_cons[col] != -1 and sum(1 for row in grid if row[col] == 'B') != base_col_cons[col]):
            return True

    return False

def move_checker(grid, row, col, val, high_row_cons, base_row_cons, high_col_cons, base_col_cons):
    if val == 'N':
        return True

    if col < len(grid[0]) - 1 and grid[row][col + 1] == val:
        return False
    # check left
    if col > 0 and grid[row][col - 1] == val:
        return False
    # check up
    if row > 0 and grid[row - 1][col] == val:
        return False
    # check down
    if row < len(grid) - 1 and grid[row + 1][col] == val:
        return False

    if val == 'H':
        if (high_row_cons[row] != -1 and grid[row].count('H') == high_row_cons[row]) or \
                (high_col_cons[col] != -1 and sum(1 for row in grid if row[col] == 'H') == high_col_cons[col]):
            return False

    elif val == 'B':
        if (base_row_cons[row] != -1 and grid[row].count('B') == base_row_cons[row]) or (base_col_cons[col] != -1 and sum(1 for row in grid if row[col] == 'B') == base_col_cons[col]):
            return False

    return True

def puzzle_over(grid):
    for i in range(len(grid)):
        for j in range(len(grid[0])):
            if grid[i][j] != 'H' and grid[i][j] != 'B' and grid[i][j] != 'N':
                return i, j
    return None

def solver(grid, high_row_cons, base_row_cons, high_col_cons, base_col_cons):

    cell = puzzle_over(grid)

    if cell == None:
        return True

    row, col = cell
    val = grid[row][col]
    combos = [["H", "B"], ["B", "H"], ["N", "N"]]
    for x in combos:
        booln = False

        if val == 'L':
            if move_checker(grid, row, col, x[0], high_row_cons, base_row_cons, high_col_cons, base_col_cons) and \
                    move_checker(grid, row , col + 1, x[1], high_row_cons, base_row_cons, high_col_cons, base_col_cons):

                    grid[row][col] = x[0]
                    grid[row][col + 1] = x[1]
                    booln = True

        else:
            if move_checker(grid, row, col, x[0], high_row_cons, base_row_cons, high_col_cons, base_col_cons) and \
                    move_checker(grid, row + 1, col, x[1], high_row_cons, base_row_cons, high_col_cons, base_col_cons):

                    grid[row][col] = x[0]
                    grid[row + 1][col] = x[1]
                    booln = True

        if booln is True and not grid_checker(grid, row, col, high_row_cons, base_row_cons, high_col_cons, base_col_cons):
            if solver(grid, high_row_cons, base_row_cons, high_col_cons, base_col_cons):
                return True

        if val == "L":
            grid[row][col] = "L"
            grid[row][col + 1] = "R"

        else:
            grid[row][col] = "U"
            grid[row + 1][col] = "D"

    return False

def main():
    input_file = sys.argv[1]

    cons = []
    grid = []

    with open(input_file, 'r') as file:
        lines = file.readlines()
        for i in range(4):
            row = lines[i].split()
            row = [int(num) for num in row]
            cons.append(row)

        file.seek(0)
        for line in lines[4:]:
            row = line.split()
            grid.append(row)

    high_row_cons = cons[0]
    base_row_cons = cons[1]
    high_col_cons = cons[2]
    base_col_cons = cons[3]

    for row in cons:
        print(row)

    for row in grid:
        print(row)

    if solver(grid, high_row_cons, base_row_cons, high_col_cons, base_col_cons):
        for i in grid:
            for j in i:
                print(j, end=" ")
            print()

    else:
        print("No solution!")

if __name__ == '__main__':
    main()



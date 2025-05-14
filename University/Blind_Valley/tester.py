import sys

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

def check_constraints(cells, high_constraint, base_constraint):

    count_H = cells.count('H')
    count_B = cells.count('B')

    if high_constraint != -1 and count_H != high_constraint:
        return False

    if base_constraint != -1 and count_B != base_constraint:
        return False

    return True

def move_checker(grid, row, col, val, high_row_cons, base_row_cons, high_col_cons, base_col_cons):
    #if the value is N, then we do not need to check the constraints
    if val == 'N':
        return True

    #function for checking the directions - up, down, right, and left
    def check_direction(direction):
        dr, dc = direction
        new_row, new_col = row + dr, col + dc
        return (
            0 <= new_row < len(grid) and 0 <= new_col < len(grid[0]) and grid[new_row][new_col] == val
        )

    directions = [(0, 1), (0, -1), (-1, 0), (1, 0)]

    #checking all of the constraints
    return all(not check_direction(direction) for direction in directions) and (
        not (
            (val == 'H' and (
                (high_row_cons[row] != -1 and grid[row].count('H') == high_row_cons[row]) or
                (high_col_cons[col] != -1 and sum(1 for r in grid if r[col] == 'H') == high_col_cons[col])
            )) or
            (val == 'B' and (
                (base_row_cons[row] != -1 and grid[row].count('B') == base_row_cons[row]) or
                (base_col_cons[col] != -1 and sum(1 for r in grid if r[col] == 'B') == base_col_cons[col])
            ))
        )
    )


def puzzle_over(grid):
    for i in range(len(grid)):
        for j in range(len(grid[0])):
            if grid[i][j] != 'H' and grid[i][j] != 'B' and grid[i][j] != 'N':
                return i, j #return empty cells if there is one
    return None

def solver(grid, high_row_cons, base_row_cons, high_col_cons, base_col_cons):
    empty_cell = puzzle_over(grid)

    if empty_cell is None:
        return True

    row, col = empty_cell
    val = grid[row][col]
    possible_values = [["H", "B"], ["B", "H"], ["N", "N"]]

    for values_pair in possible_values:
        if val == 'L':
            conditions_met = (
                move_checker(grid, row, col, values_pair[0], high_row_cons, base_row_cons, high_col_cons, base_col_cons) and
                move_checker(grid, row, col + 1, values_pair[1], high_row_cons, base_row_cons, high_col_cons, base_col_cons)
            )
        else:
            conditions_met = (
                move_checker(grid, row, col, values_pair[0], high_row_cons, base_row_cons, high_col_cons, base_col_cons) and
                move_checker(grid, row + 1, col, values_pair[1], high_row_cons, base_row_cons, high_col_cons, base_col_cons)
            )

        if conditions_met:
            grid[row][col], grid[row + (1 if val == 'U' else 0)][col + (1 if val == 'L' else 0)] = values_pair[0], values_pair[1]

            if not grid_checker(grid, row, col, high_row_cons, base_row_cons, high_col_cons, base_col_cons):
                if solver(grid, high_row_cons, base_row_cons, high_col_cons, base_col_cons):
                    return True

            grid[row][col], grid[row + (1 if val == 'U' else 0)][col + (1 if val == 'L' else 0)] = val, val

    return False


def read_input(input_file):
    #reads the input and returns constraints and grid
    with open(input_file, 'r') as input_f:
        lines = input_f.readlines()
        constraints = [list(map(int, line.split())) for line in lines[:4]]
        grid = [line.split() for line in lines[4:]]
        input_f.close()
    return constraints, grid

def writer(output_file, grid):
    #writes the correct output the the output file
    for i, row in enumerate(grid):
        for j, val in enumerate(row):
            print(val, end="", file=output_file)
            if j < len(row) - 1:
                print(end=" ", file=output_file)
        if i < len(grid) - 1:
            print(file=output_file)
def main():
    if len(sys.argv) != 3:
        print("Usage: python script.py input_file output_file")
        return

    input_file = sys.argv[1]
    output_file = sys.argv[2]

    cons, grid = read_input(input_file) #cons for constraints and grid for the puzzle board

    high_row_cons = cons[0]
    base_row_cons = cons[1]
    high_col_cons = cons[2]
    base_col_cons = cons[3]

    # Redirect standard output to the output file
    with open(output_file, 'w') as output_file:
        if solver(grid, high_row_cons, base_row_cons, high_col_cons, base_col_cons):
            writer(output_file, grid)
        else:
            print("No solution!", end="",file=output_file)

        output_file.flush()
        output_file.close()

if __name__ == '__main__':
    main()
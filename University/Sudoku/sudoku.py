import sys

def print_grid(sudoku_grid, outputf):
    """
    Prints the sudoku grid to the output file
    """
    for i in range(0, 9):
        for j in range(0, 9):
            outputf.write(str(sudoku_grid[i][j]) + " ")
        outputf.write("\n")

def zero_counter(sudoku_grid):
    """
       Counts the number of zeros in the sudoku grid.
       Returns True if there is at least one zero
    """
    zero_cnt = 0
    for i in range(0, 9):
        for j in range(0, 9):
            if sudoku_grid[i][j] == 0:
                zero_cnt += 1
    if zero_cnt != 0:
        return True
    else:
        return False

def step_writer(step_count, num, i, j, sudoku_grid, outputf):
    """
        Writes the step number, the changed value, and its position.
    """
    step_count += 1
    i += 1
    j += 1
    outputf.write("-"*18 + "\n")
    outputf.write(f"Step {step_count} - {num} @ R{i}C{j}\n")
    outputf.write("-"*18 + "\n")
    print_grid(sudoku_grid, outputf)
    solver(sudoku_grid, outputf, step_count)

def solver(sudoku_grid, outputf, step_count):
    for i in range(9):
        for j in range(9):
            numset = {1, 2, 3, 4, 5, 6, 7, 8, 9}
            if sudoku_grid[i][j] == 0:
                for n in range(9):
                    if sudoku_grid[i][n] in numset:
                        numset.discard(sudoku_grid[i][n])
                    if sudoku_grid[n][j] in numset:
                        numset.discard(sudoku_grid[n][j])
                i0 = (i // 3) * 3
                j0 = (j // 3) * 3
                for n in range(3):
                    for k in range(3):
                        if sudoku_grid[n + i0][k + j0] in numset:
                            numset.discard(sudoku_grid[n + i0][k + j0])
                if len(numset) == 1:
                    num = next(iter(numset))
                    sudoku_grid[i][j] = num
                    step_writer(step_count, num, i, j, sudoku_grid, outputf)
                    solver(sudoku_grid, outputf, step_count)
                    return  # Exit the function to prevent premature break
    return

def main():
    #Clearing the output file if it is not empty
    outputf = open(sys.argv[2], 'w')
    outputf.truncate(0)
    outputf.close()

    #Reading and converting the input values into integers
    sudoku_grid = []
    inputf = open(sys.argv[1], 'r')
    sudoku_grid = [line.split() for line in inputf]
    for n in range(len(sudoku_grid)):
        for k in range(len(sudoku_grid[n])):
            sudoku_grid[n][k] = int(sudoku_grid[n][k])
    inputf.close()

    outputf = open(sys.argv[2], 'a')
    step_count = 0
    solver(sudoku_grid, outputf, step_count)

    outputf.write("-"*18)
    outputf.flush()
    outputf.close()

if __name__ == "__main__":
    main()

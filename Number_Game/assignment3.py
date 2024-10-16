import sys

def is_valid(x, y, game_grid):

    rows, cols = len(game_grid), len(game_grid[0])
    return 0 <= x < rows and 0 <= y < cols

def correct_size(x, y, game_grid):

    if is_valid(x, y, game_grid) and game_grid[x][y] != " ":
        return True

    else:
        return False
def game_over(game_grid):
    count = 0

    for i in range(len(game_grid)):
        for j in range(len(game_grid[0])):
            adjacent_elements = []
            adjacent_elements = get_adjacent_elements(game_grid, i, j)
            if len(adjacent_elements) != 0:
                count += 1

    if count == 0:
        return False

    else:
        return True

def empty_column(game_grid, j):

    for i in range(len(game_grid)):
        if game_grid[i][j] != " " or not is_valid(i, j, game_grid):
            return False
    return True

def leftward(game_grid):
    done = False

    while not done:
        done = True
        for j in range(len(game_grid[0])-1):
            if empty_column(game_grid, j):
                for k in range(j + 1, len(game_grid[0])):
                    if not empty_column(game_grid, k):
                        for i in range(len(game_grid)):
                            game_grid[i][j], game_grid[i][k] = game_grid[i][k], game_grid[i][j]
                        done = False
                        break

def get_adjacent_elements(game_grid, row, col):
    rows, cols = len(game_grid), len(game_grid[0])

    input_value = game_grid[row][col]
    adjacent_elements = []

    directions = [(0, -1), (-1, 0), (0, 1), (1, 0)]

    # Iterate through neighboring elements
    for dx, dy in directions:
        x, y = row + dx, col + dy
        if is_valid(x, y, game_grid) and (x != row or y != col) and game_grid[x][y] == input_value and game_grid[x][y] != " ":
            adjacent_elements.append((x, y))

    return adjacent_elements

def deleter(game_grid, row, col, score):
    input_value = game_grid[row][col]
    adjacent_elements = get_adjacent_elements(game_grid, row, col)
    score += int(game_grid[row][col])

    # Replace the element at the current position with None
    game_grid[row][col] = " "

    # Recursively delete adjacent elements
    for x, y in adjacent_elements:
        if game_grid[x][y] == input_value:
            score = deleter(game_grid, x, y, score)
    return score


def downward(game_grid):
    rows = len(game_grid)
    cols = len(game_grid[0])

    for i in range(rows):
        for j in range(cols):
            if game_grid[i][j] == " ":
                if is_valid(i-1, j, game_grid) and game_grid[i-1][j] != " ":
                    game_grid[i-1][j], game_grid[i][j] = game_grid[i][j], game_grid[i-1][j]
                    downward(game_grid)


def main():
    game_grid = []

    inputf = open(sys.argv[1], 'r')
    game_grid = [line.split() for line in inputf]

    for n in range(len(game_grid)):
        for k in range(len(game_grid[n])):
            game_grid[n][k] = int(game_grid[n][k])  # Convert elements to integers
    score = 0

    for n in range(len(game_grid)):
        for k in range(len(game_grid[n])):
            print(game_grid[n][k], end=" ")
        print()

    while game_over(game_grid):
        print("\nYour score is:", score, "\n")
        print("Please enter a row and a column number: ", end="")

        cords = input()
        i, j = map(int, cords.split())
        i = i - 1
        j = j - 1

        if correct_size(i, j, game_grid):
            adjacent_elements = get_adjacent_elements(game_grid, i, j)
            if len(adjacent_elements) != 0:
                score = deleter(game_grid, i, j, score)
                downward(game_grid)
                leftward(game_grid)

            else:
                print("No movement happened try again")

        else:
            print("Please enter a correct size!")

        for n in range(len(game_grid)):
            for k in range(len(game_grid[n])):
                print(game_grid[n][k], end=" ")
            print()

    print("\nYour score is:", score)
    print("\nGame over")

if __name__ == '__main__':
    main()
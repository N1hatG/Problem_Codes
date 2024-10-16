import sys
import tkinter as tk
from tkinter import simpledialog
import time

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
            adjacent_elements = get_adjacent_elements(game_grid, i, j)
            if len(adjacent_elements) != 0:
                count += 1
    if count == 0:
        return False
    else:
        return True

def rightward(game_grid):
    for j in range(len(game_grid[0])):
        count = 0
        for i in range(len(game_grid)):
            if game_grid[i][j] != " ":
                count += 1
        if count == 0:
            for i in range(len(game_grid)):
                if is_valid(i, j + 1, game_grid):
                    game_grid[i][j], game_grid[i][j + 1] = game_grid[i][j + 1], game_grid[i][j]

def get_adjacent_elements(game_grid, row, col):
    rows, cols = len(game_grid), len(game_grid[0])

    input_value = game_grid[row][col]
    adjacent_elements = []

    directions = [(0, -1), (-1, 0), (0, 1), (1, 0)]

    # Iterate through neighboring elements
    for dx, dy in directions:
        x, y = row + dx, col + dy
        if is_valid(x, y, game_grid) and (x != row or y != col) and game_grid[x][y] == input_value and game_grid[x][
            y] != " ":
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
                if is_valid(i - 1, j, game_grid) and game_grid[i - 1][j] != " ":
                    game_grid[i - 1][j], game_grid[i][j] = game_grid[i][j], game_grid[i - 1][j]
                    downward(game_grid)

class GameApp:
    def delete_empty_columns(self):
        # Find the last non-empty column index
        last_non_empty_column = len(self.game_grid[0]) - 1
        while last_non_empty_column >= 0 and all(row[last_non_empty_column] == " " for row in self.game_grid):
            last_non_empty_column -= 1

        # Remove empty columns
        self.game_grid = [row[:last_non_empty_column + 1] for row in self.game_grid]

    def get_color(self, value):
        color_map = {
            1: "#2196F3",  # Material Blue
            2: "#4CAF50",  # Material Green
            3: "#FF5722",  # Material Deep Orange
            4: "#FFEB3B",  # Material Yellow
            5: "#FFC107",  # Material Amber
            6: "#9C27B0",  # Material Purple
            7: "#E91E63",  # Material Pink
            8: "#607D8B",  # Material Blue Grey
            9: "#FF9800",  # Material Orange
            0: "#8BC34A",  # Material Light Green
            " ": "#BDBDBD"  # Material Grey
        }
        return color_map.get(value, "#BDBDBD")

    def __init__(self, master, input_filename):
        self.master = master
        self.master.title("Colorful Game")

        self.game_grid = self.load_game(input_filename)
        self.score = 0

        self.create_widgets()

    def load_game(self, input_filename):
        with open(input_filename, 'r') as input_file:
            return [list(map(int, line.split())) for line in input_file]

    def create_widgets(self):
        self.canvas = tk.Canvas(self.master, width=400, height=400)
        self.canvas.pack()

        self.label_score = tk.Label(self.master, text="Score: 0", font=("Helvetica", 14), fg="#2196F3")  # Material Blue
        self.label_score.pack()

        self.button_input = tk.Button(self.master, text="Move", command=self.get_input, font=("Helvetica", 12), bg="#4CAF50", fg="white")  # Material Green
        self.button_input.pack()

        self.info_text = tk.StringVar()
        self.info_label = tk.Label(self.master, textvariable=self.info_text, font=("Helvetica", 12), fg="#FF5722")  # Material Deep Orange
        self.info_label.pack()

        self.update_display()

    def get_input(self):
        i, j = self.get_user_input()
        i -= 1
        j -= 1

        if correct_size(i, j, self.game_grid):
            adjacent_elements = get_adjacent_elements(self.game_grid, i, j)
            if len(adjacent_elements) != 0:
                self.score = deleter(self.game_grid, i, j, self.score)
                downward(self.game_grid)
                rightward(self.game_grid)
                self.delete_empty_columns()  # Call the function to delete empty columns

                self.update_display()
                self.info_text.set("")  # Clear the info message
            else:
                self.info_text.set("No movement happened. Try again.")
        else:
            self.info_text.set("Please enter a correct size!")

    def get_user_input(self):
        cords = simpledialog.askstring("Input", "Please enter a row and a column number (e.g., '1 2'):")
        i, j = map(int, cords.split())
        return i, j

    def show_game_over(self):
        self.canvas.create_text(
            200, 250,
            text="Game Over!",
            font=("Helvetica", 30, "bold"),
            fill="red"
        )

    def update_display(self):
        self.canvas.delete("all")

        for i in range(len(self.game_grid)):
            for j in range(len(self.game_grid[i])):
                value = self.game_grid[i][j]
                color = self.get_color(value)
                self.canvas.create_rectangle(
                    j * 50, i * 50, (j + 1) * 50, (i + 1) * 50,
                    fill=color, outline="#607D8B"  # Material Blue Grey
                )
                self.canvas.create_text(
                    (j + 0.5) * 50, (i + 0.5) * 50,
                    text=str(value) if value != " " else "",
                    fill="#263238"  # Dark Grey
                )

        self.label_score.config(text=f"Score: {self.score}")

        # Add a brief delay for a smoother visual effect
        self.master.after(500, self.check_game_over)

    def check_game_over(self):
        if not game_over(self.game_grid):
            self.show_game_over()
        else:
            self.get_input()


def main():
    root = tk.Tk()
    app = GameApp(root, sys.argv[1])
    root.mainloop()

if __name__ == "__main__":
    main()


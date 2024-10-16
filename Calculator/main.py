import sys
import os

def calc(equation):
    outputf = open(sys.argv[2], 'a')
    # Use eval to evaluate the expression and obtain the result
    result = eval(equation)
    result = round(result, 2)
    result = "{:.2f}".format(result)

    outputf.write(f"{equation}\n")
    outputf.write(f"={result}\n")

    outputf.flush()
    outputf.close()

def input_check():
    file_path = sys.argv[1]
    if os.path.isfile(file_path):
        return True
    return False

def com_line_args():
    if len(sys.argv) != 3:
        return False
    return True

def other_errors(line):
    outputf = open(sys.argv[2], 'a')
    parts = line.split()
    if len(parts) != 3:
        outputf.write(f"{line}\n")
        outputf.write("ERROR: Line format is erroneous!\n")
        return False
    elif not parts[0].isdigit():
        outputf.write(f"{line}\n")
        outputf.write("ERROR: First operand is not a number!\n")
        return False
    elif not parts[2].isdigit():
        outputf.write(f"{line}\n")
        outputf.write("ERROR: Second operand is not a number!\n")
        return False
    elif parts[1] != '+' and parts[1] != '-' and parts[1] != '*' and parts[1] != '/':
        outputf.write(f"{line}\n")
        outputf.write("ERROR: There is no such an operator!\n")
        return False
    else:
        return True

    outputf.flush()
    outputf.close()

def main():
    outputf = open(sys.argv[2], 'w')
    outputf.truncate()
    outputf.close()

    # Open the file for reading
    with open('input.txt', 'r') as file:
        # Read lines and remove leading/trailing whitespaces
        lines = [line.strip() for line in file if line.strip()]

    # Process the non-empty lines as needed
    if com_line_args():
        if input_check():
            for line in lines:
                if other_errors(line):
                    calc(line)
        else:
            filename = sys.argv[1]
            error_message = f"ERROR: There is either no such file namely {filename} or this program does not have permission to read it!"
            print(error_message)
            sys.exit(1)
    else:
        print("ERROR: This program needs two command line arguments to run, where the first one is the input file and the second one is the output file!")
        sys.exit(1)

if __name__ == '__main__':
    main()

import sys
import os

def calc(equation):

    outputf = open(sys.argv[2], 'a')

    try:
        # Use eval to evaluate the expression and obtain the result
        result = round(eval(equation), 2)
        result = "{:.2f}".format(result)
        outputf.write(f"{equation}\n")
        outputf.write(f"={result}\n")

    except Exception as e:
        outputf.write(f"{equation}\n")
        outputf.write(f"ERROR: {str(e)}\n")

    finally:
        outputf.flush()
        outputf.close()

def is_valid_operand(operand):
    try:
        float(operand)
        return True
    except ValueError:
        return False

def input_check():
    file_path = sys.argv[1]
    if not os.path.isfile(file_path):
        print(f"ERROR: There is either no such file namely {file_path} or this program does not have permission to read it!")
        print("Program is going to terminate!")
        sys.exit(1)

def com_line_args():
    return len(sys.argv) == 3

def other_errors(line):
    outputf = open(sys.argv[2], 'a')
    parts = line.split()
    if len(parts) != 3:
        outputf.write(f"{line}\n")
        outputf.write("ERROR: Line format is erroneous!\n")
        return False

    elif not is_valid_operand(parts[0]):
        outputf.write(f"{line}\n")
        outputf.write("ERROR: First operand is not a valid number!\n")
        return False

    elif not is_valid_operand(parts[2]):
        outputf.write(f"{line}\n")
        outputf.write("ERROR: Second operand is not a number!\n")
        return False

    elif parts[1] not in {'+', '-', '*', '/'}:
        outputf.write(f"{line}\n")
        outputf.write("ERROR: There is no such an operator!\n")
        return False

    outputf.flush()
    outputf.close()
    return True


def main():

    # Process the non-empty lines as needed
    if com_line_args():
        outputf = open(sys.argv[2], 'w')
        outputf.truncate()
        outputf.close()

        if input_check():
            with open('input.txt', 'r') as file:
                # Read lines and remove leading/trailing whitespaces
                lines = [line.strip() for line in file if line.strip()]

            for line in lines:
                if other_errors(line):
                    calc(line)

    else:
        print("ERROR: This program needs two command line arguments to run, where the first one is the input file and the second one is the output file!")
        sys.exit(1)


if __name__ == '__main__':
    main()

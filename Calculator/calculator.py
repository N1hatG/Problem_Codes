import sys
import os

def calc(equation, outputf):
    try:
        # Use eval to evaluate the expression and obtain the result
        result = round(eval(equation), 2)
        result_str = "{:.2f}".format(result)

        # Write to the output file
        outputf.write(f"{equation}\n")
        outputf.write(f"={result_str}\n")

    except Exception as e:
        # Write error message to the output file
        outputf.write(f"{equation}\n")
        outputf.write(f"ERROR: {str(e)}\n")

def input_check():
    file_path = sys.argv[1]
    if not os.path.isfile(file_path):
        print(f"ERROR: There is either no such file namely {file_path} or this program does not have permission to read it!")
        print("Program is going to terminate!")
        sys.exit(1)

def com_line_args():
    return len(sys.argv) == 3

def is_valid_operand(operand):
    try:
        float(operand)
        return True
    except ValueError:
        return False

def other_errors(line, outputf):
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
        outputf.write("ERROR: Second operand is not a valid number!\n")
        return False

    elif parts[1] not in {'+', '-', '*', '/'}:
        outputf.write(f"{line}\n")
        outputf.write("ERROR: There is no such an operator!\n")
        return False

    return True

def main():
    # Check if the input file exists
    input_check()

    # Open the output file for writing
    output_path = sys.argv[2]
    with open(output_path, 'w') as outputf:
        # Open the file for reading
        with open(sys.argv[1], 'r') as file:
            # Read lines and remove leading/trailing whitespaces
            lines = [line.strip() for line in file if line.strip()]

        # Process the non-empty lines as needed
        if com_line_args():
            for line in lines:
                if other_errors(line, outputf):
                    calc(line, outputf)
        else:
            print("ERROR: This program needs two command line arguments to run, where the first one is the input file and the second one is the output file!")
            sys.exit(1)

if __name__ == '__main__':
    main()

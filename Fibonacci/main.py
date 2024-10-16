import sys

def fibonacci_calc(n):
    if n == 1:
        return 1
    if n == 2:
        return 1
    else:
        return fibonacci_calc(n - 1) + fibonacci_calc(n - 2)

def fibonacci_naive(n, outputf):
    first = n - 1
    second = n - 2
    if n != 1 and n != 2:
        outputf.write(f"fib({n}) = fib({first}) + fib({second})\n")
    else:
        outputf.write(f"fib({n}) = 1\n")

    if second >= 1:
        fibonacci_naive(first, outputf)
        fibonacci_naive(second, outputf)

def naive(input_nums):
    with open(sys.argv[2], 'w') as outputf:
        for i in range(len(input_nums)):
            n = input_nums[i]

            if n > 0:
                outputf.write("-" * 32 + f"\nCalculating {n}. Fibonacci number:\n")
                fibonacci_naive(n, outputf)
                res = fibonacci_calc(n)
                outputf.write(f"{n}. Fibonacci number is: {res}\n")
            else:
                outputf.write("-" * 32 + f"\nCalculating {n}. Fibonacci number:\n")
                outputf.write("ERROR: Fibonacci cannot be calculated for the non-positive numbers!\n")
                outputf.write(f"{n}. Fibonacci number is: nan\n")

        outputf.write("-" * 32)

def fibonacci_eager(n, calcs, outputf):
    first = n - 1
    second = n - 2
    if n not in calcs.keys():
        outputf.write(f"fib({n}) = fib({first}) + fib({second})\n")
        if second >= 1:
            fibonacci_eager(first, calcs, outputf)
            fibonacci_eager(second, calcs, outputf)
    else:
        outputf.write(f"fib({n}) = {calcs.get(n)}\n")

    if second in calcs.keys():
        calcs[n] = fibonacci_calc(n)

def eager(input_nums):
    with open(sys.argv[3], 'w') as outputf:
        calcs = {
            1: 1,
            2: 1
        }
        for i in range(len(input_nums)):
            n = input_nums[i]
            res = 0
            if n > 0:
                outputf.write("-" * 32 + f"\nCalculating {n}. Fibonacci number:\n")
                fibonacci_eager(n, calcs, outputf)
                res = fibonacci_calc(n)
                outputf.write(f"{n}. Fibonacci number is: {res}\n")
            else:
                outputf.write("-" * 32 + f"\nCalculating {n}. Fibonacci number:\n")
                outputf.write("ERROR: Fibonacci cannot be calculated for the non-positive numbers!\n")
                outputf.write(f"{n}. Fibonacci number is: nan\n")

        outputf.write("-" * 32 + "\n")
        solution_structure = []
        solution_structure.extend(i for i in calcs.values())
        outputf.write("Structure for the eager solution:\n")
        outputf.write(f"{solution_structure}\n" + "-" * 32)

def main():
    inputf = open(sys.argv[1], 'r')

    input_nums = [line.split() for line in inputf]
    for i in range(len(input_nums)):
        input_nums[i] = int(input_nums[i][0])
    inputf.close()

    naive(input_nums)
    eager(input_nums)

if __name__ == '__main__':
    main()

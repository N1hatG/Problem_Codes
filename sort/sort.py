import copy
import sys

def bubble_sort(list_tbs):
    outputf1 = open(sys.argv[2], 'w')
    pass_count = 0
    sort_cond = False

    while not sort_cond:
        sort_cond = True
        for i in range(len(list_tbs) - 1):
            if list_tbs[i] > list_tbs[i + 1]:
                list_tbs[i], list_tbs[i + 1] = list_tbs[i + 1], list_tbs[i]
                sort_cond = False
        if sort_cond == False:
            pass_count += 1
            outputf1.write(f"Pass {pass_count}: ")
            for i in range(len(list_tbs)):
                outputf1.write(f"{list_tbs[i]} ")
            outputf1.write("\n")

    outputf1.close()

def insertion_sort(list_tbs):
    outputf = open(sys.argv[3], 'w')
    pass_count = 0
    for i in range(1, len(list_tbs)):
        j = i
        while j > 0 and list_tbs[j-1] > list_tbs[j]:
              list_tbs[j - 1], list_tbs[j] = list_tbs[j], list_tbs[j - 1]
              j -= 1
        pass_count += 1
        outputf.write(f"Pass {pass_count}: ")
        for k in range(len(list_tbs)):
            outputf.write(f"{list_tbs[k]} ")
        outputf.write("\n")

def main():
    inputf = open(sys.argv[1], 'r')

    list_tbs1 = inputf.read().split()
    inputf.close()

    for i in range(len(list_tbs1)):
        list_tbs1[i] = int(list_tbs1[i]) #converting the elements into integers

    list_tbs2 = copy.copy(list_tbs1)

    bubble_sort(list_tbs1)
    insertion_sort(list_tbs2)

if __name__ == '__main__':
    main()

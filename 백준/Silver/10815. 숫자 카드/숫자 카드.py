# 10815. 숫자 카드 [S5]

import sys

N1 = int(input())
arr1 = list(map(int, sys.stdin.readline().split()))
arr1.sort()
N2 = int(input())
arr2 = list(map(int, sys.stdin.readline().split()))

def binary_search(array, target, start, end):
    while start <= end:
        mid = (start+end) // 2

        if array[mid] == target:
            return mid
        elif array[mid] > target:
            end = mid - 1
        else:
            start = mid + 1
    return None

for i in range(N2):
    if binary_search(arr1, arr2[i], 0, N1-1) is not None:
        print(1, end=' ')
    else:
        print(0, end=' ')
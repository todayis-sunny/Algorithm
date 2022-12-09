# 10866. 덱 [S4]

import sys

def deque(array, key):
    order = key[0]
    if order == 'push_front':
        array.insert(0, key[1])
    elif order == 'push_back':
        array.append(key[1])
    elif order == 'pop_front':
        if len(array) >= 1:
            print(array[0])
            array.pop(0)
        else:
            print('-1')
    elif order == 'pop_back':
        if len(array) >= 1:
            print(array[-1])
            array.pop(-1)
        else:
            print('-1')
    elif order == 'size':
        print(len(array))
    elif order == 'empty':
        if len(array) >= 1:
            print('0')
        else:
            print('1')
    elif order == 'front':
        if len(array) >= 1:
            print(array[0])
        else:
            print('-1')
    elif order == 'back':
        if len(array) >= 1:
            print(array[-1])
        else:
            print('-1')
    else:
        return None

N =  int(sys.stdin.readline())
arr = []
for n in range(N):
    key = sys.stdin.readline().split()
    deque(arr, key)
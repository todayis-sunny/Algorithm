# 10828. 스택 [S4]
import sys

def stack(array, key):
    order = key[0]
    if order == 'push':
        array.append(key[1])
    elif order == 'pop':
        if len(array) >= 1:
            print(array[-1])
            array.pop()
        else:
            print('-1')
    elif order == 'size':
        print(len(array))
    elif order == 'empty':
        if len(array) >= 1:
            print('0')
        else:
            print('1')
    elif order == 'top':
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
    stack(arr, key)
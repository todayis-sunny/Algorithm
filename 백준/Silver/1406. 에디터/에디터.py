# 1406. 에디터 [S2]

import sys

st1 = list(sys.stdin.readline().rstrip())
st2 = []

for _ in range(int(sys.stdin.readline())):
    command = list(sys.stdin.readline().split())
    if command[0] == 'L':
        if st1:
            st2.append(st1.pop())
    elif command[0] == 'D':
        if st2:
            st1.append(st2.pop())
    elif command[0] == 'B':
        if st1:
            st1.pop()
    else:
        st1.append(command[1])

st1.extend(reversed(st2))
print(''.join(st1))

"""
import sys

def edit(Array, key, cursor):
    order = key[0]
    if order == 'L':
        cursor -= 1
    elif order == 'D':
        cursor += 1
    elif order == 'B':
        if cursor != 0:
            del Array[cursor-1]
            cursor -= 1
    elif order == 'P':
        Array.insert(cursor, key[1])
        cursor += 1
    return cursor

def cursor_setting(Array, cursor):
    if cursor < 0:
        return 0
    elif cursor > len(Array):
        return len(Array)
    else:
        return cursor

strArray = sys.stdin.readline().strip()
cursor = len(strArray)
N = int(input())
for n in range(N):
    key = sys.stdin.readline().split()
    cursor = edit(strArray, key, cursor)
    cursor = cursor_setting(strArray, cursor)
print(*strArray, sep='')
"""

from collections import deque

L = ['(', '[', '{']
R = [')', ']', '}']

def solution(s):
    ss = s * 2
    length = len(s)
    answer = 0
    for i in range(length):
        tmp = ss[i:i+length]
        dq = deque()
        for t in tmp:
            if t in L:
                dq.append(t)
            elif not dq:
                break
            else:
                if L.index(dq[-1]) == R.index(t):
                    dq.pop()
                else:
                    break
        else:
            if not dq:
                answer += 1
            
    return answer
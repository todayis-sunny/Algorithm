from collections import deque

left = ['(', '[', '{']
right = [')', ']', '}']

def solution(s):
    ss = s * 2
    length = len(s)
    answer = 0
    for i in range(length):
        tmp = ss[i:i+length]
        dq = deque()
        for t in tmp:
            if t in left:
                dq.append(t)
            elif not dq:
                break
            else:
                if left.index(dq[-1]) == right.index(t):
                    dq.pop()
                else:
                    break
        else:
            if not dq:
                answer += 1
            
    return answer
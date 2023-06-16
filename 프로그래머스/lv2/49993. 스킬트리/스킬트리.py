from collections import deque

def solution(skill, skill_trees):
    answer = 0
    for st in skill_trees:
        dq = deque(list(skill))
        for s in st:
            if s in dq:
                if s == dq[0]:
                    dq.popleft()
                else:
                    break
            if not dq:
                answer += 1
                break
        else:
            answer += 1
    return answer
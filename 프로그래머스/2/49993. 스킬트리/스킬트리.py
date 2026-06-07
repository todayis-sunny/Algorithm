from collections import deque

def solution(skill, skill_trees):
    reuslt = 0
    for st in skill_trees:
        dq = deque(list(skill))
        for s in st:
            if s in dq:
                if s == dq[0]:
                    dq.popleft()
                else:
                    break
            if not dq:
                reuslt += 1
                break
        else:
            reuslt += 1
    return reuslt
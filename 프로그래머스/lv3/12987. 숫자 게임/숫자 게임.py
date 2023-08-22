def solution(A, B):
    answer = 0
    A.sort(reverse = True)
    B.sort()
    for a in A:
        if a >= B[-1]:
            continue
        else:
            B.pop()
            answer += 1
    return answer
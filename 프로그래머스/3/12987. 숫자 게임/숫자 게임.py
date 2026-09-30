def solution(A, B):
    result = 0
    # B팀의 높은 수와 A팀의 높은 수를 비교하면서 처리
    A.sort(reverse = True)
    B.sort()
    for a in A:
        if a >= B[-1]:
            continue
        else:
            B.pop()
            result += 1
    return result
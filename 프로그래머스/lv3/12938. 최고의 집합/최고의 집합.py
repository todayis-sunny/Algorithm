def solution(n, s):
    if n > s:
        return [-1]
    answer = []
    tmp = s // n
    cnt = s - tmp*n
    for _ in range(n - cnt):
        answer.append(tmp)
    for _ in range(cnt):
        answer.append(tmp+1)
    return answer
def solution(n, s):
    if n > s:
        return [-1]
    ans = []
    tmp = s // n
    cnt = s - tmp*n
    for _ in range(n - cnt):
        ans.append(tmp)
    for _ in range(cnt):
        ans.append(tmp+1)
    return ans
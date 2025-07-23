def solution(n, m, section):
    curr = 0
    ans = 0
    for s in section:
        if curr < s:
            curr = s + (m - 1)
            ans += 1
        else:
            continue
    return ans
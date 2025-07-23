def solution(s):
    ans = ''
    arr = s.split(' ')
    for s in arr:
        for i, x in enumerate(s):
            ans += x.upper() if i % 2 == 0 else x.lower()
        ans += ' '
    return ans[:-1]
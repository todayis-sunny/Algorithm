def solution(x):
    ans = 0
    for e in str(x):
        ans += int(e)
    if x % ans == 0:
        return True
    return False
def solution(x):
    result = 0
    for e in str(x):
        result += int(e)
    if x % result == 0:
        return True
    return False
def solution(n):
    temp = sorted(list(map(str, str(n))), reverse = True)
    return int(''.join(temp))
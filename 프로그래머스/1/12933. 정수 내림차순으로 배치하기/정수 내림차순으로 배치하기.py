def solution(n):
    return int(''.join(sorted(list(map(str, str(n))), reverse = True)))
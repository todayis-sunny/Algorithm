def solution(n):
    answer = 0
    temp = n ** 0.5
    if temp == int(temp):
        return (int(temp) + 1) ** 2
    return -1
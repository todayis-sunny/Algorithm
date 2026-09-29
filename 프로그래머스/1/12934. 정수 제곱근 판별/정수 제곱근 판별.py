def solution(n):

    result = n ** 0.5
    if result == int(result):
        return (int(result) + 1) ** 2
    return -1
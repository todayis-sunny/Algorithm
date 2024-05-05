def solution(a, b):
    if int(str(a) + str(b)) > int(str(b) + str(a)):
        return int(str(a) + str(b))
    return int(str(b) + str(a))
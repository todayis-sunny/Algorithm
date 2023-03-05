def solution(sides):
    sides.sort()
    if max(sides) < sides[0] + sides[1]:
        return 1
    else:
        return 2
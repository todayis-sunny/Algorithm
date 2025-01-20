def solution(a):
    answer = 0
    leftMin = [10e9 + 1] * len(a)
    rightMin = [10e9 + 1] * len(a)
    leftMin[0] = a[0]
    for i in range(1, len(a)):
        leftMin[i] = min(leftMin[i - 1], a[i])
    rightMin[-1] = a[-1]
    for i in range(len(a) - 2, -1, -1):
        rightMin[i] = min(rightMin[i + 1], a[i])
    for i in range(len(a)):
        if leftMin[i] < a[i] and rightMin[i] < a[i]:
            answer += 1
    return len(a) - answer

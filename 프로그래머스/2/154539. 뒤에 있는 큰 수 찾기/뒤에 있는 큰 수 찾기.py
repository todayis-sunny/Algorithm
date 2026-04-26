def solution(numbers):
    stack = []
    result = [0] * len(numbers)
    for i in range(len(numbers)):
        while stack and numbers[stack[-1]] < numbers[i]:
            result[stack.pop()] = numbers[i]
        stack.append(i)
    while stack:
        result[stack.pop()] = -1
    
    return result
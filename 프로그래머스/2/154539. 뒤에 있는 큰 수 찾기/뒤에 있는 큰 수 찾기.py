def solution(numbers):
    stack = []
    ans = [0] * len(numbers)
    for i in range(len(numbers)):
        while stack and numbers[stack[-1]] < numbers[i]:
            ans[stack.pop()] = numbers[i]
        stack.append(i)
    while stack:
        ans[stack.pop()] = -1
    
    return ans
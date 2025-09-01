def solution(s):
    stack = []
    for letter in s:
        # 스택이 비어 있으면 추가
        if not stack:
            stack.append(letter)
        # 스택이 비어 있지 않고
        else:
            # 짝이면 제거
            if stack[-1] == letter:
                stack.pop()
            # 짝이 아니면 추가
            else:
                stack.append(letter)
                
    if len(stack) == 0:
        return 1
    return 0
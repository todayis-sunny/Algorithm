def solution(order):
    result = 0
    stack = []
    maxBox = 0
    for box in order:
        if maxBox < box:
            for b in range(maxBox + 1, box):
                stack.append(b)
            maxBox = box
            result += 1
        elif stack and stack[-1] == box:
            stack.pop()
            result += 1
        else:
            break
    return result
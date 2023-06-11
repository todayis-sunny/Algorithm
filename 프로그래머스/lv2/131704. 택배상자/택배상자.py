def solution(order):
    answer = 0
    stack = []
    max_box = 0
    for box in order:
        if max_box < box:
            for b in range(max_box+1, box):
                stack.append(b)
            max_box = box
            answer += 1
        elif stack and stack[-1] == box:
            stack.pop()
            answer += 1
        else:
            break
    return answer
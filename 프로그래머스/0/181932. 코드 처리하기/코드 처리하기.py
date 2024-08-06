def solution(code):
    answer = ''

    mode = 0
    for i in range(len(code)):
        if code[i] == '1':
            mode ^= 1
        else:
            if i % 2 == mode:
                answer += code[i]

    return answer if answer else 'EMPTY'
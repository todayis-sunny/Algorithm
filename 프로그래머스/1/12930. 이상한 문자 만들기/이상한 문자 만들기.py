def solution(s):
    answer = ''
    arr = s.split(' ')
    for s in arr:
        for i, x in enumerate(s):
            answer += x.upper() if i % 2 == 0 else x.lower()
        answer += ' '
    return answer[:-1]
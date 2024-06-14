def solution(myString):
    answer = [x if x > 'l' else 'l' for x in myString]
    return ''.join(answer)
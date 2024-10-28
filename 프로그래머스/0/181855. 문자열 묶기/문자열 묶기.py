def solution(strArr):
    answer = [len(i) for i in strArr]
    tmp = []
    for i in set(answer):
        tmp.append(answer.count(i))
    
    return max(tmp)
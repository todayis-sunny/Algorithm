def solution(participant, completion):
    dic = dict()
    for data in participant:
        if data not in dic:
            dic[data] = 1
        else:
            dic[data] += 1
    
    for data in completion:
        if dic[data] == 1:
            del dic[data]
        else:
            dic[data] -= 1
            
    
    return list(dic)[0]
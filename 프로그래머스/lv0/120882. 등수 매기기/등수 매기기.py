def solution(score):
    answer = []
    temp = []
    for element in score:
        temp.append(sum(element) / len(element))
    
    sort_temp = sorted(temp, reverse = True)
    for element in temp:
        answer.append(sort_temp.index(element) + 1) 
    return answer
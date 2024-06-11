def solution(numLog):
    answer = ''
    dic = { 1: "w", -1: "s", 10: "d", -10: "a" }
    
    for idx, val in enumerate(numLog):
        if idx != len(numLog)-1: 
            answer += dic[numLog[idx+1] - numLog[idx]]
    
    return answer
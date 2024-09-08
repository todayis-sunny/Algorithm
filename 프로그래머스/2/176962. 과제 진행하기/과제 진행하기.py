from collections import deque

def convert_time(s) :
    h, m = map(int, s.split(':'))
    return h*60 + m

def solution(plans):
    answer = []
    
    plans = [ (name, convert_time(start), int(playtime)) for name, start, playtime in plans]
    plans.sort(key = lambda x : x[1])
    
    assign_q = deque()
    left_time = 0
    
    for i in range(len(plans)) :
        name, start, playtime = plans[i]
        
        while assign_q :
            _name, _playtime = assign_q.pop()
            if left_time >= _playtime :
                left_time -= _playtime
                answer.append(_name)
            else :
                assign_q.append((_name, _playtime - left_time))
                break
                
        assign_q.append((name, playtime))
        
        if i < len(plans)-1 :
            next_start = plans[i+1][1]
            left_time = next_start - start
        
    while assign_q :
        _name, _ = assign_q.pop()
        answer.append(_name)
        
    
    return answer
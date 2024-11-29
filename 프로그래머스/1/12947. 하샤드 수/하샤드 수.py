def solution(x):
    answer = 0
    
    for e in str(x):
        answer += int(e)
        
    if x % answer == 0:
        return True
    return False
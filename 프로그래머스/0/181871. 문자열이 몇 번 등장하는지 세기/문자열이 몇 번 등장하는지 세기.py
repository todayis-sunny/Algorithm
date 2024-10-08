def solution(myString, pat):    
    start = 0
    cnt = 0
    
    while True:
        idx = myString.find(pat, start)
        if idx == -1:
            break
        cnt += 1
        start = idx + 1
    
    return cnt
def solution(sequence, k):
    cnt = len(sequence)
    tmp = 0
    R = 0
    answer = []
    for L in range(cnt):
        
        while R < cnt and tmp < k:
            tmp += sequence[R]
            R += 1
            
        if tmp == k:
            if not answer:
                answer = [L, R-1]
            else:
                answer = [L, R-1] if answer[1] - answer[0] > R - 1 - L else answer
        
        tmp -= sequence[L]
        
    return answer
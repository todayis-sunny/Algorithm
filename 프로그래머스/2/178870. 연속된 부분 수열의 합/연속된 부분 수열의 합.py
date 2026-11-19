def solution(sequence, k):
    cnt = len(sequence)
    tmp = 0
    R = 0
    result = []
    for L in range(cnt):
        
        while R < cnt and tmp < k:
            tmp += sequence[R]
            R += 1
            
        if tmp == k:
            if not result:
                result = [L, R-1]
            else:
                result = [L, R-1] if result[1] - result[0] > R - 1 - L else result
        
        tmp -= sequence[L]
        
    return result
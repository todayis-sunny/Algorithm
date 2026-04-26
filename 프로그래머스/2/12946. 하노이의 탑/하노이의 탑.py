def solution(n):
    result = []

    def hanoi(src, tgt, inter, n):
        if n == 1:
            result.append([src, tgt])
        else:
            hanoi(src, inter, tgt, n-1)
            hanoi(src, tgt, inter,1)
            hanoi(inter, tgt, src, n-1)
            
    hanoi(1, 3, 2, n)
    
    return result
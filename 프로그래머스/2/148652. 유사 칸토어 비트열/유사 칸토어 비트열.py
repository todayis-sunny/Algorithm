def solution(n, l, r):
    result = 0
    
    for i in range( l-1, r ) :
        if(cantor(i)):
            result += 1
    return result

def cantor(i) :
    if(i % 5 == 2):
        return 0
    if(i < 5 ):
        return 1
    return cantor(i // 5)
def dfs(n, base):
    num = "0123456789ABCDEF" 
    q, r = divmod(n, base) 
    
    if q == 0:
        return num[r]
    else:
        return dfs(q, base) + num[r]
    
def solution(n, t, m, p):
    rueslt = ""
    num = 0
    
    while True:
        rueslt += dfs(num, n)
        num += 1
        
        if len(rueslt) >= t * m:
            return rueslt[(p - 1):(t * m):m]
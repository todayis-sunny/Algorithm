def n_tran(k, q):
    num = ""
    
    while k > 0:
        k, mod = divmod(k, q)
        num += str(mod)
    return num[::-1]

def solution(n):
    temp = n_tran(n, 3)[::-1]
    answer = 0
    for i in range(len(temp)):
        answer += int(temp[-(i+1)]) * 3 ** i
    return answer
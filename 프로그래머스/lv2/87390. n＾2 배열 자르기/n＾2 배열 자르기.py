def solution(n, left, right):
    answer = []
    for num in range(left, right+1):
        div, mod = divmod(num, n)
        k = max(div,mod)
        answer.append(k+1)
    
    return answer
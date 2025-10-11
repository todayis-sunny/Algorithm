def solution(n, left, right):
    ans = []
    for num in range(left, right + 1):
        div, mod = divmod(num, n)
        k = max(div, mod)
        ans.append(k + 1)
    
    return ans
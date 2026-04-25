def solution(n, left, right):
    ans = []
    for num in range(left, right + 1):
        div, mod = divmod(num, n)
        ans.append(max(div, mod) + 1)
    
    return ans
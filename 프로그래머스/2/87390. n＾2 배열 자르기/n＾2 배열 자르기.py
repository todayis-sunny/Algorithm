def solution(n, left, right):
    result = []
    for num in range(left, right + 1):
        div, mod = divmod(num, n)
        result.append(max(div, mod) + 1)
    
    return result
def solution(n):
    answer = 0
    arr = [True] * (n + 1)
    
    for num in range(2, n // 2 + 1):
        for i in range(num * 2, n + 1, num):
            arr[i] = False
    for j in range(2, n + 1):
        if arr[j]:
            answer += 1
    return answer
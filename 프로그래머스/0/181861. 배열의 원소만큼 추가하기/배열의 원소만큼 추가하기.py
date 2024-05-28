def solution(arr):
    answer = []
    for num in arr:
        answer.extend([num for _ in range(num)])
    return answer
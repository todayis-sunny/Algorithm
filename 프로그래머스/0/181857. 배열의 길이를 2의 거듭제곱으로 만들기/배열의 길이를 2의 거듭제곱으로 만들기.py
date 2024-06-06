def solution(arr):
    for i in range(100):
        if len(arr) <= 2**i:
            for _ in range(2**i - len(arr)):
                arr.append(0)
            else:
                return arr
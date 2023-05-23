def solution(s):
    arr = s.split()
    max_n = int(arr[0])
    min_n = int(arr[0])
    for a in arr:
        max_n = max(max_n, int(a))
        min_n = min(min_n, int(a))
    answer = str(min_n) + ' ' + str(max_n)
    return answer
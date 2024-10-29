def solution(s):
    arr = s.split()
    maxN = int(arr[0])
    minN = int(arr[0])
    for a in arr:
        maxN = max(maxN, int(a))
        minN = min(minN, int(a))
    answer = str(minN) + ' ' + str(maxN)
    return answer
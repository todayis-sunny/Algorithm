def solution(arr, queries):
    for l,r in queries:
        for i in range(l,r+1): arr[i]+=1
    return arr
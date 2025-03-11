def solution(arr1, arr2):
    ans = []
    m, n, r = len(arr1), len(arr1[0]), len(arr2[0])
    for i in range(m):
        arr = arr1[i]
        result = []
        for j in range(r):
            hap = 0
            for k in range(n):
                hap += arr[k] * arr2[k][j]
            result.append(hap)
        ans.append(result)
    return ans
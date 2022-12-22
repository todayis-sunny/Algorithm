# 10867. 중복 빼고 정렬하기 [S5]

N = int(input())

arr = list(set(map(int , input().split())))
arr.sort()

print(*arr, sep=' ')
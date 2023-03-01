# 1037. 약수 [B1]

N = int(input())
K = list(map(int, input().split()))

num = max(K) * min(K)
print(num)
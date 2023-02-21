# 1158. 요세푸스 문제 [S4]
from collections import  deque

N, K = map(int, input().split())

queue = deque()
ans = []

for n in range(1,N+1):
    queue.append(n)

while len(queue) > 0:
    for _ in range(K-1):
        queue.append(queue.popleft())
    ans.append(queue.popleft())

print('<', end='')
print(*ans, sep=', ',end='')
print('>')
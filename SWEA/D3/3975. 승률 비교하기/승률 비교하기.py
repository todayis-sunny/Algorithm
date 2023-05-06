# 3975. 승률 비교하기 <D3>


result = ['PASS']
for t in range(1, int(input()) + 1):
    a, b, c, d = map(int, input().split())
    if a/b > c/d:
        result.append('ALICE')
    elif a/b < c/d:
        result.append('BOB')
    else:
        result.append('DRAW')
    
for t in range(1, len(result)):
    print(f'#{t} {result[t]}')
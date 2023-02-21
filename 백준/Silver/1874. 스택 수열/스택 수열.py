# 1874. 스택 수열 [S2]

n = int(input())
stack, ans, find = [], [], True

# 숫자 1부터 시작
now = 1
for _ in range(n):
    num = int(input())
    # 스택 쌓기 push
    while now <= num:
        stack.append(now)
        ans.append('+')
        now += 1
    # 스택 꺼내기 pop
    if stack[-1] == num:
        stack.pop()
        ans.append('-')
    # 불가능 한 경우
    else:
        find = False

# 정답 출력
if not find:
    print('NO')
else:
    for i in ans:
        print(i)
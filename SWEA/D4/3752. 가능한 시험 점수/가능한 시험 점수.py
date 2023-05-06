# 3752. 가능한 시험 점수 <D4>

for t in range(1, int(input()) + 1):
    n = int(input())
    nums = list(map(int, input().split()))
    result = [False] * (sum(nums) + 1)
    result[0] = True
    for k in nums:
        for i in range(len(result) - 1, -1, -1):
            if result[i]:
                result[i + k] = True
    print(f'#{t} {result.count(True)}')
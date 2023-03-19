def solution(s):
    answer = 0
    if len(s) == 1:
        return 1
    while s:
        x = s[0]
        cnt = 0
        yes = 1
        no = 0
        while True:
            cnt += 1
            if cnt == len(s):
                return answer + 1
            if x == s[cnt]:
                yes += 1
            else:
                no += 1

            if yes == no:
                answer += 1
                s = s[cnt+1:]
                break
            else:
                continue
    return answer
def solution(n, stations, w):
    answer = 0
    wave = w * 2 + 1 # 파동
    start = 1
    for s in stations:
        if s - w - start > 0:
            div, mod = divmod((s - w - start), wave)
            answer += div
            if mod: # 파동이 나누어 떨어지지 않는다면
                answer += 1
        start = s + w + 1 # start 지점 변경
    if n - start + 1 > 0:
        div, mod = divmod((n - start + 1), wave)
        answer += div
        if mod: # 파동이 나누어 떨어지지 않는다면
            answer += 1
    return answer
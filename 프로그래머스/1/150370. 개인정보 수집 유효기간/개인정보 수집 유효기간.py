def solution(today, terms, privacies):
    # dict로 약관 정리
    term = dict()
    for t in terms:
        # 약관 종류, 유효기간
        t1, t2 = t.split()
        t2 = int(t2)
        term[t1] = t2
    # today 변수 정리
    tod = today.split('.')
    for i in range(3):
        tod[i] = int(tod[i])
    # 개인정보 검사 (i 를 +1 증가시켜서 추가)
    result = []
    for i in range(len(privacies)):
        day, dt = privacies[i].split()
        d = day.split('.')
        # 기간 정리
        for j in range(3):
            d[j] = int(d[j])
        # 년, 월(1~12월 이므로 -1 가중치 적용후 +1 가중치 적용)
        div, mod = divmod(term[dt] + d[1] - 1, 12)
        d[0] += div
        d[1] = mod + 1
        # 1일 빼기
        if d[2] > 1:
            d[2] -= 1
        else:
            d[2] = 28
            if d[1] > 1:
                d[1] -= 1
            else:
                d[0] -= 1
                d[1] = 12
        # 비교하기
        for k in range(3):
            if tod[k] > d[k]:
                result.append(i + 1)
                break
            elif tod[k] < d[k]:
                break
    return result
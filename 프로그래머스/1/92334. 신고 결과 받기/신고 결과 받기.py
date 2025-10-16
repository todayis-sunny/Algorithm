def solution(id_list, report, k):
    idDict = dict()
    # 아이디 번호 받기
    for i in range(len(id_list)):
        idDict[id_list[i]] = i
    
    # 신고횟수 저장
    size = len(id_list)
    # 0-based [i][size]: 신고당한 누적 횟수
    # [i][j]: i가 j를 신고한 횟수
    reportCnt = [[0] * (size + 1) for _ in range(size)]
    
    # 신고 적용하기
    for rep in report:
        # 신고자, 대상자
        rF, rT = rep.split()
        rFi, rTi = idDict[rF], idDict[rT]
        # 이미 신고했으면 스킵
        if reportCnt[rFi][rTi]:
            continue
        # 누적 신고 적용
        reportCnt[rTi][size] += 1
        # 신고자 행위 적용
        reportCnt[rFi][rTi] = 1
    
    # 메일 발송하기
    mail = [0] * size
    for i in range(size):
        # k 기준치를 넘지 못하면 스킵
        if reportCnt[i][size] < k:
            continue
        for j in range(size):
            # 신고 이력이 있으면 메일 발송
            if reportCnt[j][i]:
                mail[j] += 1
                
    return mail
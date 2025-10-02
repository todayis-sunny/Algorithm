UNITS = ['R', 'T', 'C', 'F', 'J', 'M', 'A', 'N']

def solution(survey, choices):
    # 성격 점수
    score = {'R': 0, 'T': 0, 'C': 0, 'F': 0,
            'J': 0, 'M': 0, 'A': 0, 'N': 0}
    # 검사 실행 및 점수 집계
    for s, c in zip(survey, choices):
        if c > 4:
            score[s[1]] += c - 4
        elif c < 4:
            score[s[0]] += 4 - c
    # 결과 도출
    answer = ""
    for i in range(0, 8, 2):
        if score[UNITS[i]] >= score[UNITS[i + 1]]:
            answer += UNITS[i]
        else:
            answer += UNITS[i + 1]
    return answer
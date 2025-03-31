def convertTime(n): # 분 단위로 변환
    h = n // 100
    m = n % 100
    return h * 60 + m

def solution(schedules, timelogs, startday):
    answer = 0
    
    for i in range(len(schedules)):
        s = startday
        schedule = convertTime(schedules[i])
        for time in timelogs[i]:
            if s in [6, 7]: # 주말 제외
                s += 1
                if s == 8:  # 일요일 지나면 월요일로
                    s = 1
                continue
            t = convertTime(time)
            if schedule + 10 < t:   # 지각
                break
            else:
                s += 1
        else:
            answer += 1

    return answer
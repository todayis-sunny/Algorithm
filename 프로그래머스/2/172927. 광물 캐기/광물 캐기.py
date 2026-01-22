def solution(picks, minerals):
    values = []
    tmp = [0, 0, 0]
    for i in range(len(minerals)):
        if i % 5 == 0 and i != 0:
            values.append(tmp)
            tmp = [0, 0, 0]
        if minerals[i] == 'diamond':
            tmp[0] += 1
        elif minerals[i] == 'iron':
            tmp[1] += 1
        else:
            tmp[2] += 1
    if tmp != [0, 0, 0]:
        values.append(tmp)
    values = values[:sum(picks)]
    values = sorted(values, reverse = True)
    limit = len(values)
    
    result = 0
    idx = 0
    
    for i in range(3):
        pick = picks[i]
        while pick:
            if idx == limit:
                break
            if i == 0:
                result += values[idx][0] * 1 + values[idx][1] * 1 + values[idx][2] * 1
            elif i == 1:
                result += values[idx][0] * 5 + values[idx][1] * 1 + values[idx][2] * 1
            else:
                result += values[idx][0] * 25 + values[idx][1] * 5 + values[idx][2] * 1
            pick -= 1
            idx += 1
    return result
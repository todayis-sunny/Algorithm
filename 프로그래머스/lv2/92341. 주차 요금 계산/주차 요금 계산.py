import math

def convert(time):
    hh, mm = time.split(':')
    return int(hh) * 60 + int(mm)

def solution(fees, records):
    intime = {}
    result = {}
    
    for r in records:
        time, num, inout = r.split()
        if inout == "IN":
            intime[num] = convert(time)
            if num not in result:
                result[num] = 0
        else:
            result[num] += convert(time) - intime[num]
            del intime[num]
            
    for key, val in intime.items():
        result[key] += 23 * 60 + 59 - val
        
    answer = []
    for key, val in sorted(result.items()):
        if val <= fees[0]:
            answer.append(fees[1])
        else:
            answer.append(fees[1] + math.ceil((val - fees[0]) / fees[2]) * fees[3])

    return answer
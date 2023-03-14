def solution(polynomial):
    arr = list(polynomial.split(' + '))
    a = 0
    b = 0
    for element in arr:
        if 'x' in element:
            if len(element) == 1:
                a += 1
            else:
                a += int(element[:-1])
        else:
            b += int(element)
    temp = []
    if a != 0:
        if a == 1:
            temp.append('x')
        else:
            temp.append(str(a)+'x')
    if b != 0:
        temp.append(str(b))
    if len(temp) == 2:
        temp.insert(1, '+')
    answer = ' '.join(temp)
    return answer
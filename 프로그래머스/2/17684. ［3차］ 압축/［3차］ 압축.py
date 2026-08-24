def solution(msg):
    result = []
    dic = [chr(c) for c in range(64, 91)]
    length = len(msg)
    s = 1
    l = 2
    i = 0
    while i < length:
        s_msg = msg[i:i + s]
        l_msg = msg[i:i + l]
        if l_msg not in dic or (i + s) > length:
            dic.append(l_msg)
            result.append(dic.index(s_msg))
            i += s
            s = 1
            l = 2
        else:
            s += 1
            l += 1

    return result
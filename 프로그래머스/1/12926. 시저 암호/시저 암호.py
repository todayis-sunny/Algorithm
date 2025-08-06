def solution(s, n):
    ams = ""
    for i in s:
        if i == " ":
            ams += " "
        else:
            k = chr(ord(i) + n)
            if k.isupper() != i.isupper() or not k.isalpha():
                k = chr(ord(k) - 26)
            ams += k
    return ams
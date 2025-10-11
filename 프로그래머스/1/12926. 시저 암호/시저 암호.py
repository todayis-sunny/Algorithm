def solution(s, n):
    ans = ""
    for i in s:
        if i == " ":
            ans += " "
        else:
            k = chr(ord(i) + n)
            if k.isupper() != i.isupper() or not k.isalpha():
                k = chr(ord(k) - 26)
            ans += k
    return ans
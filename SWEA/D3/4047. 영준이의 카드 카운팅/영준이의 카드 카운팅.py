# 4047. 영준이의 카드 카운팅 <D3>

tc = int(input())

for t in range(1, tc+1):
    s = [0] * 14
    d = [0] * 14
    h = [0] * 14
    c = [0] * 14

    cards = str(input())
    ans = []
    for i in range(0, len(cards), 3):
        if cards[i] == 'S':
            s[int(cards[i+1:i+3])] += 1
        elif cards[i] == 'D':
            d[int(cards[i+1:i+3])] += 1
        elif cards[i] == 'H':
            h[int(cards[i+1:i+3])] += 1
        else:
            c[int(cards[i+1:i+3])] += 1
    if max(max(s), max(d), max(h), max(c)) > 1:
        print(f'#{t} ERROR')
        continue

    ans.append(s.count(0) - 1)
    ans.append(d.count(0) - 1)
    ans.append(h.count(0) - 1)
    ans.append(c.count(0) - 1)

    print(f'#{t}', *ans)

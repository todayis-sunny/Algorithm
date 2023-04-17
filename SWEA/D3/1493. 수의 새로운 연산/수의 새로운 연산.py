# 1493. 수의 새로운 연산 <D3>

tc = int(input())

x_marking = [0, 1]
k = 1
for i in range(2, 301):
    k += i
    x_marking.append(k)

for t in range(1, tc + 1):
    p, q = map(int, input().split())
    for px_idx in x_marking:
        if p <= px_idx:
            tmp = px_idx
            diff = tmp - p
            px = x_marking.index(tmp) - diff
            py = diff + 1
            break
    for qx_idx in x_marking:
        if q <= qx_idx:
            tmp = qx_idx
            diff = tmp - q
            qx = x_marking.index(tmp) - diff
            qy = diff + 1
            break

    nx, ny = px + qx, py + qy
    diff = ny - 1
    ans = x_marking[nx+diff] - diff
    print(f'#{t} {ans}')
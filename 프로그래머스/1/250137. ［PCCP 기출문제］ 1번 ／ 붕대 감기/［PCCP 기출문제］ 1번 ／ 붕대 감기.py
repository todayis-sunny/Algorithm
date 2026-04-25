def solution(bandage, health, attacks):
    # 현재 체력
    hp = health
    # 현재 시간
    currTime = 0
    # 공격 순회
    for att in attacks:
        # 추가되는 시간
        plusTime = att[0] - currTime
        # 붕대 연속 회복 처리
        div, mod = divmod(plusTime, bandage[0])
        # 체력 회복 (최대체력 이하만큼)
        hp = min(health, hp + plusTime * bandage[1] + div * bandage[2])
        # 몬스터 공격
        hp -= att[1]
        # 현재 체력 0이하 시 종료
        if hp <= 0:
            return -1
        # 현재 시간 최신화
        currTime = att[0] + 1

    return hp
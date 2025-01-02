import java.util.*;
class Match {
    int a;
    int b;
    
    public Match(int a, int b) {
        this.a = a;
        this.b = b;
    }
}
class Solution {
    public Integer solution(int coin, int[] cards) {
        int n = cards.length;
        int target = n + 1;
        int initialCards = n / 3;
        
        Set<Integer> hand = new HashSet<>();
        Set<Integer> future = new HashSet<>();
        List<Match> matches = new ArrayList<>();
        
        for (int i = 0; i < initialCards; i++) {
            int card = cards[i];
            if (hand.contains(target - card)) {
                matches.add(new Match(card, target - card));
                hand.remove(target - card);
            } else {
                hand.add(card);
            }
        }
        
        int round = 1;
        for (int i = initialCards; i < n; i += 2) {
            if (i + 1 >= n) break;  // 배열 범위 체크 추가
            
            int card1 = cards[i];
            int card2 = cards[i + 1];
            boolean card1Contains = false;
            boolean card2Contains = false;
            
            // 1. 현재 매치가 있으면 카드 받고 다음 라운드로
            if (!matches.isEmpty()) {
                future.add(card1);
                future.add(card2);
                matches.remove(0);
                round++;
                continue;
            }
            
            
            // 2. 새 카드와 초기 카드 매칭 (코인 1개)
            if (matches.isEmpty() && coin > 0) {
                if (hand.contains(target - card1)) {
                    matches.add(new Match(card1, target - card1));
                    hand.remove(target - card1);
                    coin--;
                    card1Contains = true;
                } else if (hand.contains(target - card2)) {
                    matches.add(new Match(card2, target - card2));
                    hand.remove(target - card2);
                    coin--;
                    card2Contains = true;
                }
            }
            
            // 3. 초기 카드와 future 카드 매칭 (코인 1개)
            if (matches.isEmpty() && coin > 0) {
                for (int handCard : hand) {
                    if (future.contains(target - handCard)) {
                        matches.add(new Match(handCard, target - handCard));
                        future.remove(target - handCard);
                        coin--;
                        break;
                    }
                }
            }
            
            // 4. 새로 받은 두 카드로 매치 가능한지 확인 (코인 2개)
            if (coin >= 2 && card1 + card2 == target) {
                matches.add(new Match(card1, card2));
                coin -= 2;
                card1Contains = true;
                card2Contains = true;
            }
            
            // 4. 매칭되지 않은 카드를 future에 추가
            if (!card1Contains) {
                future.add(card1);
            }
            if (!card2Contains) {
                future.add(card2);
            }
            
            // 5. Future 카드 끼리 매칭
            if (matches.isEmpty() && coin >= 2) {
                for (int futureCard : new ArrayList<>(future)) {
                    if (future.contains(target - futureCard)) {
                        matches.add(new Match(futureCard, target - futureCard));
                        future.remove(futureCard);
                        future.remove(target - futureCard);
                        coin -= 2;
                        break;
                    }
                }
            }
            
            if (matches.isEmpty()) {
                break;
            }
            
            matches.remove(0);
            round++;
        }
        
        return round;
    }
}
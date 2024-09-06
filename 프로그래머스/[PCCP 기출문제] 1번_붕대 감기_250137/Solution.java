class Solution {
    public int solution(int[] bandage, int health, int[][] attacks) {
        int answer = 0;
        int time = bandage[0];
        int treatPerTime = bandage[1];
        int additionTreatTime = bandage[2];
        int maxHealth = health;
        
        int beforeAttack = attacks[0][0];
        int endAttackTime = attacks[attacks.length - 1][0];
        health -= attacks[0][1];
        
        for(int i = 1; i < attacks.length; i++){
            
            int[] attack = attacks[i];
            
            int cnt = 0;
            for(int second = beforeAttack + 1; second < attack[0]; second++){
                
                health += treatPerTime;
                
                cnt++;
                if(cnt == time){
                    health += additionTreatTime;
                    cnt = 0;
                }
                if(health >= maxHealth){
                    health = maxHealth;
                    break;
                }
            }
            
            health -= attack[1];
            beforeAttack = attack[0];
            if(health <= 0){
                break;
            }
        }
        
        if(health <= 0){
            return -1;
        }
        
        return health;
    }
}
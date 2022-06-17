package 로또최고최저순위;

import java.util.*;

class Solution {
    public int[] solution(int[] lottos, int[] win_nums) {
        int[] answer = new int[2];
        HashSet<Integer> known_number = new HashSet<>();
        int zero_count = 0;
        int win_count = 0;
        int[] score = {6, 6, 5, 4, 3, 2, 1};

        for(int i=0 ; i<6 ; i++) {
            if(lottos[i] == 0) {
                zero_count++;
            } else {
                known_number.add(lottos[i]);
            }
        }

        Iterator<Integer> iter = known_number.iterator();

        int cur;
        while(iter.hasNext()) {
            cur = iter.next();
            for(int i=0 ; i<6 ; i++) {
                if(cur == win_nums[i]){
                    win_count++;
                    break;
                }
            }
        }

        answer[0] = score[win_count + zero_count];
        answer[1] = score[win_count];

        return answer;
    }
}

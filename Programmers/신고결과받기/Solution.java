package 신고결과받기;

import java.util.*;

/*
* 몇 달간 쉬다가 감 잡기로 푼 문제
* Map 사용해서 다시 풀어보기 (2022.06.17)
*
* */

public class Solution {
    public int[] solution(String[] id_list, String[] report, int k) {

        int idLen = id_list.length;

        int[] result = new int[idLen];
        int[] answer = new int[idLen];

        boolean[][] isReported = new boolean[idLen][idLen];

        List<List<String>> reportResult = new ArrayList<>();
        for(int i=0 ; i<idLen ; i++){
            reportResult.add(new ArrayList<>());
        }

        int reportLen = report.length;
        String[] arr = new String[2];
        int reporter = -1, reported = -1;

        for(int i=0 ; i<reportLen ; i++) {
            arr = report[i].split(" ");
            // [0] : 신고한 사람
            // [1] : 신고당한 사람
            for(int j=0 ; j<idLen ; j++){
                if(arr[0].equals(id_list[j]))
                    reporter = j;
                if(arr[1].equals(id_list[j]))
                    reported = j;
                if(reporter > 0 && reported > 0)
                    break;
            }

            if(!isReported[reporter][reported]){
                result[reported]++;
                isReported[reporter][reported] = true;
            }

            reporter = -1;
            reported = -1;
        }

        for(int i=0 ; i<result.length ; i++){
            if(result[i] >= k) {
                for(int j=0 ; j<idLen ; j++){
                    if(isReported[j][i])
                        answer[j]++;
                }
            }
        }

        return answer;
    }
}

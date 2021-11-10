package BOJ2110;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.*;

public class Main {

    public static void main(String[] args) throws IOException {

        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st;

        // Input N, C
        String num = br.readLine();
        st = new StringTokenizer(num);
        int n = Integer.parseInt(st.nextToken());
        int c = Integer.parseInt(st.nextToken());

        // Insert house location
        int[] house = new int[n];
        String houseLoc;
        for(int i=0 ; i<n ; i++){
            houseLoc = br.readLine();
            house[i] = Integer.parseInt(houseLoc);
        }

        // Sort - for binary search
        Arrays.sort(house);

        // Binary search
        int start = 1;
        int end = house[n-1] - house[0];
        int ans = 0;
        while(start <= end) {
            int mid = (start + end) / 2;
            int prevHouse = house[0];
            int cnt = 1;
            for(int i=0 ; i<n ; i++){
                if(house[i] - prevHouse >= mid){
                    cnt++;
                    prevHouse = house[i];
                }
            }

            if(cnt >= c){
                start = mid + 1;
                ans = mid;
            }
            else {
                end = mid - 1;
            }
        }

        System.out.println(ans);

        br.close();
    }
}

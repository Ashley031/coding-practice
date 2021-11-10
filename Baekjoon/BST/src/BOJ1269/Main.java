package BOJ1269;


import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.*;

/*
* BST - TreeSet
* Duplicate number -> remove
*
* 참고)
* https://velog.io/@cchloe2311/%EC%95%8C%EA%B3%A0%EB%A6%AC%EC%A6%98-%EB%B0%B1%EC%A4%80-1269.-%EB%8C%80%EC%B9%AD-%EC%B0%A8%EC%A7%91%ED%95%A9
* */
public class Main {

    static TreeSet<Integer> set = new TreeSet<>();

    static BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
    static StringTokenizer st;

    public static void main(String[] args) throws IOException {

        String num = br.readLine();
        st = new StringTokenizer(num);
        int numOfA = Integer.parseInt(st.nextToken());
        int numOfB = Integer.parseInt(st.nextToken());

        insertNum(numOfA);
        insertNum(numOfB);

        System.out.println(set.size());
    }

    static void insertNum(int num) throws IOException {
        String elem = br.readLine();
        st = new StringTokenizer(elem);
        for(int i=0 ; i<num ; i++){
            String tmp = st.nextToken();
            if(!set.add(Integer.parseInt(tmp)))
                set.remove(Integer.parseInt(tmp));
        }
    }

}

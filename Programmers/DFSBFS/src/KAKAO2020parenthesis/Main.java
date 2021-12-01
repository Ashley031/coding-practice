package KAKAO2020parenthesis;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Stack;

public class Main {

    public static void main(String[] args) throws IOException {

        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        String p = br.readLine();

        String result = solution(p);
        System.out.println(result);
        br.close();
    }

    public static String solution(String p) {
        String answer = dfs(p);
        return answer;
    }

    public static boolean isCorrect(String p){
        Stack<Character> check = new Stack<>();
        for(int i=0 ; i<p.length() ; i++){
            if(p.charAt(i) == '(') check.push('(');
            else if(!check.isEmpty()) check.pop();
            else return false;
        }
        return true;
    }

    public static String dfs(String p){
        // 1단계
        if(p.length() == 0) return "";

        int open = 0, close = 0;
        for(int i=0 ; i<p.length() ; i++){
            if(p.charAt(i) == '(') open++;
            else close++;

            // 2단계
            if(open == close){
                /*TEST*/
//                System.out.println("u = " + p.substring(0, i+1) + ", v = " + p.substring(i+1));

                // 3단계
                if(isCorrect(p.substring(0, i+1))) {

                    /*TEST*/
//                    System.out.println("String u is correct!!");

                    // 3-1단계
                    return p.substring(0, i + 1) + dfs(p.substring(i + 1));
                }

                // 4단계
                else {

                    /*TEST*/
//                    System.out.println("let's fix String u...");

                    // 4-1 ~ 4-3단계
                    String v = '(' + dfs(p.substring(i+1))+ ')';
//                    System.out.println("[fix] v = " + v);

                    // 4-4단계
                    String tmp = "";
                    for(int j=1 ; j<i ; j++){
                        if(p.charAt(j) == '(')
                            tmp += ')';
                        else
                            tmp += '(';
                    }
                    // 4-5단계
                    return v + tmp;
                }
            }
        }
        return p;
    }
}

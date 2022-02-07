package Tree.Trie.BOJ9202;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Arrays;

class TrieNode {
    TrieNode[] children = new TrieNode[26];
    boolean isEnd;
    boolean isHit;

    void clearHit() {
        isHit = false;
        for(int i=0 ; i< children.length ; i++){
            if(children[i] != null) {
                children[i].clearHit();
            }
        }
    }

    boolean hasChild(char c) {
        return children[c - 'A'] != null;
    }

    TrieNode getChild(char c) {
        return children[c - 'A'];
    }

    @Override
    public String toString() {
        return "TrieNode{" +
                "children=" + Arrays.toString(children) +
                ", isEnd=" + isEnd +
                ", isHit=" + isHit +
                '}';
    }
}

public class Main {

    static int W, B;
    static int[] dx = {-1,1,0,0, -1,1,-1,1};
    static int[] dy = {0,0,1,-1,-1,-1,1,1};
    static int[] score = {0,0,0,1,1,2,3,5,11};

    static char[][] map;
    static boolean[][] visited;
    static String answer;
    static int maxScore, count;
    static TrieNode root;
    static StringBuilder sb;

    public static void main(String[] args) throws IOException {

        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        root = new TrieNode();

        W = Integer.parseInt(br.readLine());

        // Insert words
        for(int i=1 ; i<=W ; i++) {
            insert(br.readLine());
        }
        br.readLine();

        B = Integer.parseInt(br.readLine());
        StringBuilder result = new StringBuilder();
        for(int i=1 ; i<=B ; i++) {
            map = new char[4][4];
            visited = new boolean[4][4];
            answer = "";
            maxScore = 0;
            count = 0;
            sb = new StringBuilder();

            for(int r=0 ; r<4 ; r++){
                String str = br.readLine();
                for(int c=0 ; c<4 ; c++){
                    map[r][c] = str.charAt(c);
                }
            }

            for(int y=0 ; y<4 ; y++){
                for(int x=0 ; x<4 ; x++){
                    if(root.hasChild(map[y][x])){
                        search(y,x,1,root.getChild(map[y][x]));
                    }
                }
            }

            result.append(maxScore);
            result.append(" ");
            result.append(answer);
            result.append(" ");
            result.append(count);
            result.append("\n");
            root.clearHit();
            if(i != B) br.readLine();
        }

        System.out.println(result);
        br.close();
    }

    static void search(int y, int x, int length, TrieNode node) {
        // 1. 체크인 - 방문여부 체크
        visited[y][x] = true;
        sb.append(map[y][x]);

        // 2. 목적지인가?
        if(node.isEnd && !node.isHit) {
            count++;
            maxScore += score[length];
            node.isHit = true;
            String foundWord = sb.toString();
            if(compare(answer, foundWord) > 0) {
                answer = foundWord;
            }
        }
        // 3. 연결된 곳 순회
        int nx, ny;
        for(int i=0 ; i<8 ; i++) {
            ny = y + dy[i];
            nx = x + dx[i];
            // 4. 갈 수 있나?
            if(ny < 0 || nx < 0 || ny >= 4 || nx >= 4) continue;
            if(visited[ny][nx]) continue;
            if(!node.hasChild(map[ny][nx])) continue;
            // 5. 간다
            search(ny, nx, length+1, node.getChild(map[ny][nx]));
        }
        // 6. 체크아웃 - 방문여부 해제
        visited[y][x] = false;
        sb.deleteCharAt(length-1);
    }

    static void insert(String word) {
        TrieNode cur = root;
        for(int j=0 ; j<word.length() ; j++){
            if(!cur.hasChild(word.charAt(j))){
                cur.children[word.charAt(j) - 'A'] = new TrieNode();
            }
            cur = cur.getChild(word.charAt(j));
        }
        cur.isEnd = true;
    }

    static int compare(String word1, String word2){
        int comp = Integer.compare(word2.length(), word1.length());
        if(comp == 0) {
            return word1.compareTo(word2);
        } else
            return comp;
    }
}

#include <string>
#include <vector>

using namespace std;
int answer = 0;

void dfs(vector<int> numbers, int target, int sum, int count);
/*
	더하거나 빼기만 가능
	각 숫자마다 가능한 경우는 두 가지 -> + ,-
	가능한 모든 경우 계산하여 벡터에 저장한 뒤, 
	타겟넘버가 있다면 개수를 세고
	없다면 0

	DFS 알고리즘 사용
	
	참고 :
	https://blog.naver.com/3246902/221901391897
	https://rile1036.tistory.com/24

*/

int solution(vector<int> numbers, int target) {
	dfs(numbers, target, 0, 0);
	return answer;
}

void dfs(vector<int> numbers, int target, int sum, int count) {
	if (count == numbers.size()) {
		if (sum == target) answer++;
		return;
	}

	dfs(numbers, target, sum + numbers[count], count + 1);
	dfs(numbers, target, sum - numbers[count], count + 1);
}
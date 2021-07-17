#include <string>
#include <vector>
#include <queue>

using namespace std;

int solution(int bridge_length, int weight, vector<int> truck_weights) {
	queue<int> truck;
	queue<int> time;
	int answer = 0;	// 경과시간
	int sum = 0;
	int idx = 0;
	
	while (1) {
		answer++;

		if (answer - time.front() == bridge_length) {
			sum -= truck.front();
			truck.pop();
			time.pop();
		}

		if (weight - sum >= truck_weights[idx]) {
			if (idx == truck_weights.size() - 1) {
				answer += bridge_length; // 이 부분 중요
				break;
			}

			sum += truck_weights[idx];
			truck.push(truck_weights[idx++]);
			time.push(answer);
		}

	}

	return answer;
}

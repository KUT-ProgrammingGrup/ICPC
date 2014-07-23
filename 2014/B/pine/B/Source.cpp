#include <iostream>
#include <array>
#include <deque>

using namespace std;

#define W 5
#define L 3
typedef array<deque<int>, W> T;

int remove_pos(T &cols, int x, int y) {
	if (cols[x].size() <= y) return 0;
	int val = cols[x][y];

	for (int i = 1; i < L; ++i) { // >= L
		if (cols[x + i].size() <= y || cols[x + i][y] != val) {
			return 0; // can't remove
		}
	}

	int point = 0;

	for (int i = 0;
		x + i < cols.size() && cols[x + i].size() > y && cols[x + i][y] == val;
		++i)
	{
		point += cols[x + i][y];
		cols[x + i].erase(cols[x + i].begin() + y);
	}

	return point;
}

int remove(int h, T &cols) {
	for (int i = 0; i <= cols.size() - L; ++i) {
		for (int j = h - 1; j >= 0; --j) {
			int t = remove_pos(cols, i, j);
			if (t > 0) return t;
		}
	}

	bool removed = false;

	for (int i = 0; i < cols.size(); ++i) {
		if (!cols[i].empty()) {
			cols[i].pop_front();
			removed = true;
		}
	}

	return removed ? 0 : -1;
}

int solve(int h, T &cols) {
	int point = 0;

	while (1) {
		int t = remove(h, cols);

		if (t < 0) break;
		point += t;
	}

	return point;
}

int main(void) {
	int h;

	while (cin >> h, h > 0) {
		T cols;

		for (int i = 0; i < h; ++i){
			for (int j = 0; j < W; ++j){
				int t;
				cin >> t;

				cols[j].push_front(t);
			}
		}

		cout << solve(h, cols) << endl;
	}

	return 0;
}
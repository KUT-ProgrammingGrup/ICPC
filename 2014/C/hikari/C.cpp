// C.cpp
// hikari

#include <iostream>
#include <vector>
#include <algorithm>

using namespace std;

struct Input
{
	int l;
	int r;
	int h;
};

auto& operator >> (auto &is, Input &in)
{
	return is >> in.l >> in.r >> in.h;
}

struct Point
{
	Point(int ix, int iy) : x(ix), y(iy) {}
	int x;
	int y;
};

auto calc(vector<Input> &iv, int r)
{
	vector<Point> pv;
	for (auto &i : iv)
	{
		pv.push_back(Point(i.l, i.h));
		pv.push_back(Point(i.r, i.h));
	}

	sort(pv.begin(), pv.end(), [](auto &l, auto &r){return l.x < r.x;});

	return pv;
}

int main()
{
	while (1)
	{
		int r, n;
		cin >> r >> n;
		if (r == 0 && n == 0)
			break;

		vector<Input> iv(n);
		for (auto &i : iv)
			cin >> i;

		auto v = calc(iv, r);
		for (auto &i : v)
			cout << "x = " << i.x << ", y = " << i.y << endl;


	}
}

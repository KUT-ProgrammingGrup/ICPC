#define _CRT_SECURE_NO_WARNINGS
#include <cstdio>
#include <cmath>
#include <cctype>
#include <cstdint>
#include <cstring>
#include <cstdlib>

#include <iostream>
#include <algorithm>
#include <string>
#include <sstream>
#include <vector>
#include <map>
#include <unordered_set>
#include <stack>
#include <queue>
#include <list>
#include <functional>

using namespace std;

int del(list<int> col[5], int h)
{
	list<int>::iterator it[5];

	vector<list<int>::iterator> dl[5];

	for (int i = 0; i < 5; i++)
		it[i] = col[i].begin();
	
	for (int i = 0; i < h; i++)
	{
		for (int j = 0; j < 3; j++)
			if (it[j] != col[j].end()
				&& it[j + 1] != col[j + 1].end()
				&& it[j + 2] != col[j + 2].end()
				&& *it[j] == *it[j + 1]
				&& *it[j] == *it[j + 2])
				for (int k = 0; k < 3; k++)
					if (dl[k + j].end() == find(dl[k + j].begin(), dl[k + j].end(), it[k + j]))
						dl[k + j].push_back(it[k + j]);

		for (int j = 0; j < 5; j++)
		{
			if (it[j] != col[j].end())
				it[j]++;
		}
	}
	int count = 0;
	for (int j = 0; j < 5; j++)
	{
		for (auto &i : dl[j])
		{
			count += *i;
			col[j].erase(i);
			
		}
	}
	
	return count;
}


int main()
{
	while (1)
	{
		int h;
		cin >> h;
		if (h == 0)
			break;

		list<int> col[5];
		for (int i = 0; i < h; i++)
			for (int j = 0; j < 5; j++)
			{
				int tmp;
				cin >> tmp;
				col[j].push_front(tmp);
			}

		int count = 0;
		while (1)
		{
			int tmp = del(col, h);
			if (tmp == 0)
				break;

			count += tmp;
		}

		cout << count << endl;
	}

	return 0;
}

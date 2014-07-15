// B.cpp
// hikari

#include <iostream>
#include <vector>
#include <map>
#include <algorithm>

using namespace std;

struct Record
{
	int time = 0;
	int team = 0;
	int problem = 0;
	int judge = 0;
};

auto& operator >> (auto &lis, Record &rrec)
{
	return lis >> rrec.time >> rrec.team >> rrec.problem >> rrec.judge;
}

struct Team
{
	int num = 0;
	int problem = 0;
	int time = 0;

	auto operator == (Team &rt)
	{
		return problem == rt.problem && time == rt.time;
	}
};

auto& operator << (auto &los, Team &rt)
{
	return los << rt.num;
}

int main()
{
	while (true)
	{
		int maxTime, numTeam, numProblem, numRecord;
		cin >> maxTime >> numTeam >> numProblem >> numRecord;
		if (maxTime == 0)
			break;

		vector<Record> irecord(numRecord);
		for (auto &i : irecord)
			cin >> i;

		vector<Team> team(numTeam);
		for (int i = 0; i < numTeam;)
			team[i].num = ++i;

		map<int, map<int, multimap<int, int>>> mrecord;
		for (auto &i : irecord)
			mrecord[i.team][i.problem].insert(make_pair(i.judge, i.time));

		for (auto &i : mrecord)
			for (auto &j : i.second)
			{
				auto tmp = j.second.find(0);
				if (tmp != j.second.end())
				{
					team[i.first - 1].problem++;
					team[i.first - 1].time += (*tmp).second + (j.second.size() - 1) * 20;
				}
			}

		sort(team.begin(), team.end(), [](auto &lt, auto &rt){
			return lt.problem != rt.problem ? lt.problem > rt.problem
				: lt.time != rt.time ? lt.time < rt.time : lt.num > rt.num;
			});

		cout << team[0];
		for (int i = 1; i < numTeam; i++)
			cout << (team[i] == team[i - 1] ? "=" : ",") << team[i];
		cout << endl;
	}
}

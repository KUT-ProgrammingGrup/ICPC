// C_spirit.cpp
// hikari

#include <iostream>
#include <string>
#include <queue>

#define BOOST_SPIRIT_USE_PHOENIX_V3
#include <boost/spirit/include/qi.hpp>
#include <boost/spirit/include/phoenix.hpp>

namespace qi = boost::spirit::qi;

typedef std::priority_queue<int, std::vector<int>, std::greater<int>> que;

template<typename Iterator>
struct pars : qi::grammar<Iterator, int(), qi::locals<que>>
{
	qi::rule<Iterator, int(), qi::locals<que>> start;

	pars() : pars::base_type(start)
	{
		using boost::phoenix::bind;
		using namespace qi::labels;

		const auto f_push = [](auto &q, auto const &i){q.push(i);};
		const auto f_pop = [](auto &q)
			{
				int sum = 0;
				for (auto i = (q.size() + 1) / 2; i > 0; i--)
				{
					sum += q.top();
					q.pop();
				}
				return sum;
			};

		start = (+('[' >> qi::int_[bind(f_push, _a, (_1 + 1) / 2)] >> ']')
			| +('[' >> start[bind(f_push, _a, _1)] >> ']')
			)[_val = bind(f_pop, _a)];
	}
};

int main()
{
	int n;
	std::cin >> n;
	for (int i = 0; i < n; i++)
	{
		std::string str;
		std::cin >> str;

		pars<decltype(str.begin())> p;
		int result;
		qi::parse(str.begin(), str.end(), p, result);
		std::cout << result << std::endl;
	}
	return 0;
}

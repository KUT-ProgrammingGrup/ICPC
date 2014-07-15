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

<<<<<<< HEAD
		start =  +('[' >> qi::int_[boost::phoenix::bind(
			[](auto const& l, auto const& r)
			{
				return l.push(r);
			}
			, _a
			, _1)] >> ']')
			| +('[' >> start[push_back(at_c<0>(_val), _1)] >> ']');
=======
		start = (+('[' >> qi::int_[bind([](auto &q, auto const &i){q.push((i + 1) / 2);}, _a, _1)] >> ']')
			| +('[' >> start[bind([](auto &q, auto const &i){q.push(i);}, _a, _1)] >> ']')
			)[_val = bind([](auto &q)
			{
				int sum = 0;
				for (auto i = (q.size() + 1) / 2; i > 0; i--)
				{
					sum += q.top();
					q.pop();
				}
				return sum;
			}, _a)];
>>>>>>> origin/master
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

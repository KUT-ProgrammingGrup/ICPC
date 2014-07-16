// C_spirit3.cpp
// hikari

#include <iostream>
#include <string>
#include <boost/spirit/include/qi.hpp>
#include <boost/spirit/include/phoenix.hpp>

namespace qi = boost::spirit::qi;

template<typename Iterator>
struct pars : qi::grammar<Iterator, int()>
{
	qi::rule<Iterator, int()> start, first;

	pars() : pars::base_type(start)
	{
		using namespace boost::phoenix;
		using namespace qi::labels;

		first = '[' >> qi::int_[_val = (_1 + 1) / 2] >> ']';
		start = (+first | +('[' >> start >> ']'))[
			partial_sort(_1, begin(_1) + (size(_1) + 1) / 2),
			erase(_1, begin(_1) + (size(_1) + 1) / 2, end(_1)),
			_val = accumulate(_1, 0)];
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

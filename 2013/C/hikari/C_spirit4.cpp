// C_spirit4.cpp
// hikari

#include <iostream>
#include <string>
#include <boost/spirit/include/qi.hpp>
#include <boost/spirit/include/phoenix.hpp>

namespace qi = boost::spirit::qi;
namespace phoenix = boost::phoenix;

int main()
{
	// BNF  start ::= ("[" (integer | start) "]")+
	qi::rule<std::string::iterator, int()> start = (+('[' >> (qi::int_[qi::_1 = (qi::_1 + 1) / 2] | start) >> ']'))[
		phoenix::partial_sort(qi::_1, phoenix::begin(qi::_1) + (phoenix::size(qi::_1) + 1) / 2),
		phoenix::erase(qi::_1, phoenix::begin(qi::_1) + (phoenix::size(qi::_1) + 1) / 2, phoenix::end(qi::_1)),
		qi::_val = phoenix::accumulate(qi::_1, 0)];

	int n, result;
	std::cin >> n;
	for (int i = 0; i < n; i++)
	{
		std::string str;
		std::cin >> str;
		qi::parse(str.begin(), str.end(), start, result);
		std::cout << result << std::endl;
	}
}

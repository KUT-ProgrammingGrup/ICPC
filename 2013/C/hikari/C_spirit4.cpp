// C_spirit3.cpp
// hikari

#include <iostream>
#include <string>
#include <boost/spirit/include/qi.hpp>
#include <boost/spirit/include/phoenix.hpp>

namespace qi = boost::spirit::qi;
namespace ph = boost::phoenix;

int main()
{
	qi::rule<std::string::iterator, int()> start = ( +('[' >> ( qi::int_[ qi::_1 = ( qi::_1 + 1 ) / 2 ] | start ) >> ']') )[
		ph::partial_sort( qi::_1, ph::begin( qi::_1 ) + ( ph::size( qi::_1 ) + 1 ) / 2),
		ph::erase( qi::_1, ph::begin( qi::_1 ) + ( ph::size( qi::_1 ) + 1) / 2, ph::end( qi::_1 )),
		qi::_val = ph::accumulate( qi::_1, 0 ) ];

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

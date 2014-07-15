// C_spirit.cpp
// hikari

#include <iostream>
#include <string>
#include <vector>
#include <algorithm>

#include <boost/fusion/include/adapt_struct.hpp>
#include <boost/spirit/include/qi.hpp>
#include <boost/spirit/include/phoenix.hpp>

namespace phoenix = boost::phoenix;
namespace spirit = boost::spirit;
namespace qi = boost::spirit::qi;

struct node;

typedef boost::variant<
	boost::recursive_wrapper<node>,
	int
> constituency;

struct node
{
	std::vector<constituency> children;
};

BOOST_FUSION_ADAPT_STRUCT(
	node,
	(std::vector<constituency>, children)
)

template<typename Iterator>
struct pars : qi::grammar<Iterator, node()>
{
	qi::rule<Iterator, node()> start;
	pars() : pars::base_type(start)
	{
		using phoenix::push_back;
		using phoenix::at_c;
		using namespace qi::labels;

		start =  +('[' >> qi::int_[push_back(at_c<0>(_val), _1)] >> ']')
			| +('[' >> start[push_back(at_c<0>(_val), _1)] >> ']');
	}

};

struct calculator : public boost::static_visitor<int>
{
	int operator() (const int &v) const
	{
		return (v + 1) / 2;
	}

	int operator() (node &v) const
	{
		std::sort(v.children.begin(), v.children.end(),
				  [](constituency &l, constituency &r) {
			return boost::apply_visitor(calculator(), l)
				< boost::apply_visitor(calculator(), r);
		});
		int sum = 0;
		for (int i = 0; i < (v.children.size() + 1) / 2; i++)
			sum += boost::apply_visitor(calculator(), (v.children)[i]);
		return sum;
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

		auto it = str.begin();
		pars<decltype(it)> p;
		constituency tree;
		qi::parse(it, str.end(), p, tree);
		std::cout << boost::apply_visitor(calculator(), tree) << std::endl;
	}

	return 0;
}

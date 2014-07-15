// C.cpp
// hikari

#include <iostream>
#include <sstream>
#include <string>
#include <list>

using namespace std;

class Tree
{
public:
	int num = 0;
	int minnum = 0;
	Tree* parent;
	list<Tree*> child;

	Tree(Tree *p) : parent(p) {}
};

void delTree(Tree *node)
{
	for (auto &i : node->child)
		delTree(i);

	delete node;
}

void calcmin(Tree *node)
{
	if (node->child.empty())
	{
		node->minnum = (node->num + 1) / 2;
	}
	else
	{
		for (auto &i : node->child)
			calcmin(i);

		node->child.sort([](Tree *lt, Tree *rt){return lt->minnum < rt->minnum;});
		auto it = node->child.begin();
		for (int i = 0; i < (node->child.size() + 1) / 2; i++)
		{
			node->minnum += (*(it++))->minnum;
		}
	}
}

int main()
{
	int n;
	cin >> n;

	for (int i = 0; i < n; i++)
	{
		string dataSet;
		cin >> dataSet;

		Tree *root = nullptr;
		Tree *it = nullptr;
		for (int j = 0; j < dataSet.size(); j++)
		{
			if (dataSet[j] == '[')
			{
				Tree *ptree = new Tree(it);
				if (it)
					it->child.push_back(ptree);
				else
					root = ptree;

				it = ptree;
			}
			else if (dataSet[j] == ']')
			{
				int sum = 0;
				for (auto &k : it->child)
					sum += k->num;
				it->num = sum;
				it= it->parent;
			}
			else
			{
				int l = dataSet.find(']', j);
				istringstream is(dataSet.substr(j, l - j));
				is >> it->num;
				j = l;
				it = it->parent;
			}
		}

		calcmin(root);
		cout << root->minnum << endl;

		delTree(root);
	}

	return 0;
}

package com.prep.implement.tree;
//A class to represent a queue item. The queue is used to do Level
//order traversal. Every Queue item contains node and horizontal
//distance of node from root
class QItem
{
	TreeNode node;
	int hd;
   public QItem(TreeNode n, int h)
   {
        node = n;
        hd = h;
   }
}
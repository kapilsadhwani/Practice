package com.prep.implement.dp;

import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;

class Box{
	int height, weight, depth;

	public Box(int height, int weight, int depth) {
		super();
		this.height = height;
		this.weight = weight;
		this.depth = depth;
	}

	public int getHeight() {
		return height;
	}

	public void setHeight(int height) {
		this.height = height;
	}

	public int getWeight() {
		return weight;
	}

	public void setWeight(int weight) {
		this.weight = weight;
	}

	public int getDepth() {
		return depth;
	}

	public void setDepth(int depth) {
		this.depth = depth;
	}

	public boolean canBeAbove(Box bottom) {
		// TODO Auto-generated method stub
		return false;
	}
}

class BoxComparator implements Comparator<Box>{
	@Override
	public int compare(Box x, Box y) {
		return y.getHeight() - x.getHeight();
	}
}

public class StackOfBoxes {
	int createStack(ArrayList<Box> boxes) {
		Collections.sort(boxes, new BoxComparator());
		int[] stackMap = new int[boxes.size()];
		return createStack(boxes, null, 0, stackMap);
	}

	int createStack(ArrayList<Box> boxes, Box bottom, int offset, int[] stackMap) {
		if (offset >= boxes.size()) return 0; // Base case
			/* height with this bottom*/
			Box newBottom = boxes.get(offset);
			int heightWithBottom = 0;
			if (bottom == null || newBottom.canBeAbove(bottom)) {
			if (stackMap[offset] == 0) {
				stackMap[offset] = createStack(boxes, newBottom, offset+ 1, stackMap);
				stackMap[offset] += newBottom.height;
			}
			heightWithBottom = stackMap[offset];
		}

		/* without this bottom*/
		 int heightWithoutBottom = createStack(boxes, bottom, offset+ 1, stackMap);
		 
		/* Return better of two options. */
		return Math.max(heightWithBottom, heightWithoutBottom);
	}
}

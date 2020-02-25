package com.xuyao.test.sort;

import java.util.Arrays;

public abstract class BaseSort {
	protected static int[] arr = {9,5,2,7,8,3,1,0,4,4,23,13};
	public void baseSort(){
		long start = System.currentTimeMillis();
		sort();
		System.out.println(this.getClass().getName() + " cost: " + (System.currentTimeMillis()- start));
		System.out.println(Arrays.toString(arr));
	}
	public abstract void sort();
}

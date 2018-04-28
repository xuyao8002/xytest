package com.xuyao.test.sort;

public abstract class BaseSort {
	protected static int[] arr = {9,5,2,7,8,3,1,0,4,4,23,13};
	public void baseSort(){
		long start = System.currentTimeMillis();
		sort();
		System.out.println(this.getClass().getName() + " cost: " + (System.currentTimeMillis()- start));
		for(int i : arr){
			System.out.print(i + " ");
		}
	}
	public abstract void sort();
}

package com.xuyao.test.sort;

public class BubbleSort extends BaseSort{

	@Override
	public void sort() {
		int temp;
		for(int i = 1; i < arr.length; i++){
			for(int j = 0; j < arr.length - i; j++){
				if(arr[j] > arr[j+1]){
					temp = arr[j];
					arr[j] = arr[j+1];
					arr[j+1] = temp;
				}
			}
		}
	}
}

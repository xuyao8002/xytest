package com.xuyao.test.sort;

public class InsertionSort extends BaseSort{

	@Override
	public void sort() {
		int temp;
		int j;
		for(int i = 1; i < arr.length; i++){
			temp = arr[i];
			for(j = i; j > 0; j--){
				if(temp >= arr[j-1]) break;
				arr[j] = arr[j-1];
			}
			arr[j]= temp;
		}
	}
}

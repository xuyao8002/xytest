package com.xuyao.test.sort;

public class QuickSort extends BaseSort{

	@Override
	public void sort() {
		quickSort(arr, 0, arr.length - 1);
	}
	
	public void quickSort(int[] arr, int head, int tail){
		if(head >= tail || arr == null || arr.length == 1){
			return;
		}
		int i = head, j = tail, temp = arr[(head + tail) / 2];
		while(i <= j){
			while(arr[i] < temp){
				i++;
			}
			while(arr[j] > temp){
				j--;
			}
			if(i < j){
				int tmp = arr[i];;
				arr[i] = arr[j];
				arr[j] = tmp;
				i++;
				j--;
			}else if(i == j){
				i++;
			}
		}
		quickSort(arr, head, j);
		quickSort(arr, i, tail);
	}
}
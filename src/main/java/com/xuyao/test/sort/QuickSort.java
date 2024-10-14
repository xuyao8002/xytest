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
		int h = head;
		int t = tail;
		int temp = arr[(head + tail) / 2];
		while(h <= t){
			while(arr[h] < temp){
				h++;
			}
			while(arr[t] > temp){
				t--;
			}
			if(h <= t){
				int tmp = arr[h];;
				arr[h] = arr[t];
				arr[t] = tmp;
				h++;
				t--;
			}
		}
		quickSort(arr, head, t);
		quickSort(arr, h, tail);
	}
}
package com.xuyao.test.function;

import java.util.Arrays;
import java.util.List;
import java.util.function.Predicate;

public class MyTest extends Thread{

	public static void main(String[] args) throws Exception {
		new Thread(new Runnable() {
			@Override
			public void run() {
				System.out.println("hello, xuyao");
			}
		}).start();

		new Thread(() ->
				System.out.println("hello, xuyao 2")

		).start();

		final List<String> xuyao = Arrays.asList("0.0", "0_0", "-.-");
		for(String s : xuyao){
			System.out.println(s);
		}


		xuyao.stream().map(x -> x + " -> xy").forEach(x -> System.out.println(x));

		String str = xuyao.stream().map(x -> x + "#").reduce((a,b) -> a+b).get();
		System.out.println(str);

		final List<Integer> yao = Arrays.asList(9, 5, 2);
		int sum = yao.stream().filter(x -> x > 2).map(x -> x * 2).reduce((a,b) -> a + b).get();
		System.out.println(sum);
		System.out.println("2 <= x < 5");
		filterTest(yao, x -> x >= 2 && x < 5);

		System.out.println("x < 9");
		filterTest(yao, x -> x < 9);

		System.out.println("all");
		filterTest(yao, x -> true);
	}

	public static void filterTest(List<Integer> yao, Predicate<Integer> condition){
		yao.stream().filter(x -> condition.test(x)).forEach(x -> System.out.println(x + " "));
	}

}

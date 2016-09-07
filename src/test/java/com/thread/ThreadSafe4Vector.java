package com.thread;

import java.util.ArrayList;
import java.util.Vector;

import org.junit.Test;

public class ThreadSafe4Vector {

	@Test
	public void testSpeed4VectorAndList() {

		ArrayList<Integer> list = new ArrayList<Integer>();
		Vector<Integer> vector = new Vector<Integer>();
		long start = System.currentTimeMillis();
		for (int i = 0; i < 100000; i++)
			list.add(i);
		long end = System.currentTimeMillis();
		System.out.println("ArrayList进行100000次插入操作耗时：" + (end - start) + "ms");
		start = System.currentTimeMillis();
		for (int i = 0; i < 100000; i++)
			vector.add(i);
		end = System.currentTimeMillis();
		System.out.println("Vector进行100000次插入操作耗时：" + (end - start) + "ms");

	}

	Vector<Integer> vector = new Vector<Integer>();

	@Test
	public void testThreadSafe4Vector() {
		while (true) {
			for (int i = 0; i < 10; i++)
				vector.add(i);
			Thread thread1 = new Thread() {
				public void run() {
					for (int i = 0; i < vector.size(); i++)
						vector.remove(i);
				};
			};
			Thread thread2 = new Thread() {
				public void run() {
					for (int i = 0; i < vector.size(); i++)
						vector.get(i);
				};
			};
			thread1.start();
			thread2.start();
			while (Thread.activeCount() > 10) {

			}
		}
	}

}

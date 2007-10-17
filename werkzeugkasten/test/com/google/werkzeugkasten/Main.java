package com.google.werkzeugkasten;

import java.util.LinkedList;
import java.util.Queue;

public class Main {

	public static void main(String[] args) {
		Queue<String> q = new LinkedList<String>();
		q.offer("1");
		q.offer("2");
		q.offer("3");

		System.out.println(q.poll());
		System.out.println(q.poll());
		System.out.println(q.poll());
		System.out.println(q.poll());
		System.out.println(q.size());
	}
}

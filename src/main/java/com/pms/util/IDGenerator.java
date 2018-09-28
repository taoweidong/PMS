package com.pms.util;

import java.util.ArrayList;
import java.util.List;

/**
 * ID生成器
 * @author Taowd
 * @version 2018年9月28日
 * @see IDGenerator
 */
public final class IDGenerator {

	private static int ID_LEN = 9;
	private static IDGenerator generator = new IDGenerator();

	/**
	 * private and only constructor
	 */
	private IDGenerator() {
	}

	/**
	 * @return Generator
	 */
	public static IDGenerator getInstance() {
		if (generator == null)
			generator = new IDGenerator();
		return generator;
	}

	/**
	 * @return the iD
	 */
	public int getID() {
		int root = (int) Math.pow(10, ID_LEN);
		int id = 0;
		do {
			long tmp = Math.abs(Double.doubleToLongBits(Math.random()));
			id = (int) (tmp % root);
		} while (id < (root / 10));
		// System.out.println(id);
		return id;
	}

	/**
	 * @return the iD_LEN
	 */
	public static int getID_LEN() {
		return ID_LEN;
	}

	/**
	 * @param iD_LEN the iD_LEN to set
	 */
	public static void setID_LEN(int iD_LEN) {
		ID_LEN = iD_LEN;
	}

	public static void main(String[] args) {
		List<Integer> list = new ArrayList<Integer>();
		int x;
		for (int i = 0; i < 10000; i++) {
			System.out.println(generator.getID());
			x = generator.getID();
			if (!list.contains(x))
				list.add(x);
			else
				System.out.println(x);

		}
	}
}
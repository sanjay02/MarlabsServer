package com.marlabs.codingtest.service;

import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.ThreadLocalRandom;

import org.springframework.stereotype.Service;

@Service
public class ServiceReceiver {
	private static final int MAX = 100;
	private static final int MIN = 1;
	private static final int MAX_NUM_NUMBERS = 5;

	public String getQuestion() {
		int num = ThreadLocalRandom.current().nextInt(2, MAX_NUM_NUMBERS + 1);
		ArrayList<String> numbers = new ArrayList<String>();
		for (int i = 0; i < num; i++) {
			int randomNum = ThreadLocalRandom.current().nextInt(MIN, MAX + 1);
			numbers.add(String.valueOf(randomNum));
		}
		String s1 = String.join(",", numbers);
		String retStr = "Here you go, solve the question: \"Please sum the numbers " + s1 + "\".";
		return retStr;
	}

	public boolean vaidateInputRequest(String msg) {
		String modifiedMsg = msg.replaceAll("[^0-9]", " ").replaceAll("\\s+", " ");

		String[] numArr = modifiedMsg.split(" ");

		System.out.println("Modified Message: " + modifiedMsg);
		List<Integer> nums = new ArrayList<Integer>();
		for (String str : numArr) {
			if (str.equals(""))
				continue;
			System.out.println("[" + str + "]");
			nums.add(Integer.parseInt(str));
		}

		if (nums.size() < 3)
			return false;

		int trueSum = 0;

		for (int k = 0; k < nums.size() - 1; k++)
			trueSum += nums.get(k);

		int ansSum = nums.get(nums.size() - 1);

		if (trueSum == ansSum)
			return true;
		else
			return false;
	}

}

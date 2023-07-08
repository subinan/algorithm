package boj;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class BJ_1205_등수구하기 {

	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(br.readLine());
		
		int N = Integer.parseInt(st.nextToken());
		if (N == 0) {
			System.out.println(1);
			return ;
		}
		
		int newScore = Integer.parseInt(st.nextToken());
		int P = Integer.parseInt(st.nextToken());
		
		st = new StringTokenizer(br.readLine());
		int[] score = new int[N];
		for (int i = 0; i < N; i++) {
			score[i] = Integer.parseInt(st.nextToken());
		}
		
		System.out.println(binarySearch(score, newScore, P));
	}

	private static int binarySearch(int[] score, int newScore, int P) {
		
	    if (score.length >= P && newScore <= score[score.length - 1]) return -1;

	    int start = 0;
	    int end = score.length - 1;
	    int rank = 1;

	    while (start <= end) {
	        int mid = (start + end) / 2;
	        if (score[mid] > newScore) {
	            rank = mid + 2;
	            start = mid + 1;
	        } else {
	            end = mid - 1;
	        }
	    }

	    return rank > P ? -1 : rank;
	}
	
}

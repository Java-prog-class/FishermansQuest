package Game;

import java.util.Scanner;

public class MultipleWords {

		public static void main(String args[]) {
			new MultipleWords();	
		}
		
		MultipleWords() {
			Scanner sc = new Scanner(System.in);
			String text = sc.nextLine();
			String[] words = text.split(" ");
			
			String text2 = "";
			text2 = minusOneWord(words);
			System.out.println(words[0].toUpperCase() + " --- " + text2);
			text2 = minusTwoWords(words);
			System.out.println(words[0].toUpperCase() + " " + words[1].toUpperCase() + " --- " + text2);
		}
		String minusOneWord(String[] w) {
			String s = "";
			for (int i=1; i<w.length; i++) {
				s = s + w[i] + " ";
			}
			return s.trim();
		}
		
		String minusTwoWords(String[] w) {
			String s = "";
			for (int i=2; i<w.length; i++) {
				s = s + w[i] + " ";
			}
			return s.trim();
		}
	}

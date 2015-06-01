package WebForMvn.MyWeb.resources;

import java.util.ArrayList;

public  class Question {

	private String question;

	private ArrayList<Variant> variants = new ArrayList<>();

	public String getQuestion() {
		return question;
	}

	public void setQuestion(String question) {
		this.question = question;
	}

	public ArrayList<Variant> getVariants() {
		return variants;
	}

	void put(String var, Boolean varans) {
		variants.add(new Variant(var, varans));

	}

	public class Variant {
		String text;
		public String getText() {
			return text;
		}

		boolean ansver;

		Variant(String txt, boolean ans) {
			this.text = txt;
			this.ansver = ans;
		}

	}
}
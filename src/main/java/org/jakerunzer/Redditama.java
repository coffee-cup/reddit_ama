package org.jakerunzer;

import java.awt.Color;
import java.util.ArrayList;

import javax.swing.JPanel;

public class Redditama {
	private String base_url;
	private String sort;
	private String full_url;
	
	private ArrayList<Question> questions;
	private String op_post;
	private String op_author;

	/**
	 * Creates a Reddit AMA object
	 * 
	 * @param base_url
	 *            the reddit url which leads to an AMA where the json will be
	 *            fetched from
	 * @param sort
	 *            how to sort the comments from the AMA (ex "top", "new" etc...)
	 */
	public Redditama(String base_url, String sort) {
		this.base_url = base_url;
		this.sort = sort;
		this.full_url = "";

		getFullUrl();
	}

	public boolean fetchAMA() {
		String json = JsonRequest.requestJson(full_url);
		if (json == null) {
			return false;
		}
		questions = ParseJson.parseAMA(json);
		if (questions == null) {
			return false;
		}
		if (questions.size() > 0) {
			op_author = questions.get(0).getA_author();
		}
		op_post = ParseJson.getOriginalPost(json);
		return true;
	}

	/**
	 * Adds the question jpanels to another jpanel
	 * 
	 * @param content
	 *            the jpanel to add the questions to
	 */
	public void addToGUI(JPanel content) {
		if (questions == null) {
			return;
		}
		content.removeAll();
		for (int i = 0; i < questions.size(); i++) {
			Question ques = questions.get(i);
			content.add(ques.getPanel());
		}
		content.updateUI();
	}

	private void getFullUrl() {
		// MAKE BETTER EXPRESSION PARSING HERE!!!!
		full_url += base_url += ".json?sort=" + sort + "&limit=500";
	}

	public ArrayList<Question> getQuestions() {
		return questions;
	}

	public String getOp_post() {
		return op_post;
	}

	public String getOp_author() {
		return op_author;
	}
}

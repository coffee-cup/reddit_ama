package org.jakerunzer;

import java.util.ArrayList;
import java.util.Iterator;

import org.json.simple.JSONArray;
import org.json.simple.JSONObject;
import org.json.simple.parser.JSONParser;
import org.json.simple.parser.ParseException;

public class ParseJson {
	
	/**
	 * Parse a Reddit AMA from a json string
	 * 
	 * @param json
	 *            the json to parse
	 * @return an arraylist of questions and answers
	 */
	public static ArrayList<Question> parseAMA(String json) {
		ArrayList<Question> questions = null;
		try {
			questions = AMA(json);
		} catch (NullPointerException e) {
			return null;
		}
		return questions;
	}

	/**
	 * Get a JSONArray object of a json string
	 * 
	 * @param json
	 *            json string to get JSONArray from
	 * @return JSONArray object
	 */
	private static JSONArray getJsonArray(String json) {
		JSONParser parser = new JSONParser();
		Object json_obj = null;

		try {
			json_obj = parser.parse(json);
		} catch (ParseException e) {
			// e.printStackTrace();
			return null;
		}
		JSONArray jsonObject = (JSONArray) json_obj;
		return jsonObject;
	}

	/**
	 * Gets the original post body. The title of the AMA
	 * 
	 * @param json
	 *            the json string to parse
	 * @return string of the original post body.
	 */
	public static String getOriginalPost(String json) {
		JSONArray jsonObject = getJsonArray(json);
		JSONObject original_post = (JSONObject) jsonObject.get(0);
		JSONObject op_data = (JSONObject) original_post.get("data");
		JSONArray op_children = (JSONArray) op_data.get("children");
		JSONObject first_post = (JSONObject) op_children.get(0);
		JSONObject fp_data = (JSONObject) first_post.get("data");
		String op_post = (String) fp_data.get("title");
		return op_post;
	}

	/**
	 * Gets the author from a reddit json comment
	 * 
	 * @param jsonObject
	 *            the json object to get author from
	 * @return string of the author
	 */
	private static String getAuthor(JSONArray jsonObject) {
		JSONObject original_post = (JSONObject) jsonObject.get(0);
		JSONObject op_data = (JSONObject) original_post.get("data");
		JSONArray op_children = (JSONArray) op_data.get("children");
		JSONObject first_post = (JSONObject) op_children.get(0);
		JSONObject fp_data = (JSONObject) first_post.get("data");
		String op_author = (String) fp_data.get("author");
		return op_author;
	}

	/**
	 * Parses the ama with the given json string
	 * 
	 * @param json
	 *            the json to parse as a string
	 * @return an arraylist of question objects contains questions and answers
	 *         from the AMA
	 */
	private static ArrayList<Question> AMA(String json) {
		JSONArray jsonObject = getJsonArray(json);
		if (jsonObject == null) {
			return null;
		}

		// Get author from original post
		String op_author = getAuthor(jsonObject);

		// Get comments
		ArrayList<Question> questions = new ArrayList<Question>();
		JSONObject all_comments = (JSONObject) jsonObject.get(1);
		JSONObject cs_data = (JSONObject) all_comments.get("data");
		JSONArray cs_children = (JSONArray) cs_data.get("children");
		Iterator<JSONObject> comments = cs_children.iterator();
		while (comments.hasNext()) {
			JSONObject comment = comments.next();
			JSONObject op_reply = replyFromOp(comment, op_author);
			if (op_reply != null) {
				String question = getBodyFromComment(comment);
				String answer = getBodyFromComment(op_reply);
				String q_author = getAuthorFromComment(comment);
				Question ques = new Question(q_author, question, op_author,
						answer);
				questions.add(ques);
				/*
				 * Print out the AMA to the Console
				 */
				// System.out.println("QUESTION: " + question);
				// System.out.println();
				// System.out.println("ANSWER: " + answer);
				// System.out.println("--------------------------------------");
				// System.out.println();
			}
		}

		return questions;
	}

	/**
	 * Finds replies to a comment from the original author
	 * 
	 * @param comment
	 *            json object of the comment. replies from this comment will
	 *            searched to find a reply from the original author
	 * @return json object of the reply to the comment or null if no reply from
	 *         op found
	 */
	private static JSONObject replyFromOp(JSONObject comment, String op_author) {
		Iterator<JSONObject> iterator = getReplyIterator(comment);
		if (iterator == null) {
			return null;
		}
		JSONObject author_reply = null;
		while (iterator.hasNext()) {
			JSONObject reply = iterator.next();
			String r_author = getAuthorFromComment(reply);
			if (r_author != null && r_author.equals(op_author)) {
				return reply;
			}
		}
		return author_reply;
	}

	/**
	 * Get the text from a comment
	 * 
	 * @param comment
	 *            Comment to get body from
	 * @return body of comment
	 */
	private static String getBodyFromComment(JSONObject comment) {
		JSONObject c_data = (JSONObject) comment.get("data");
		String body = (String) c_data.get("body");
		return body;
	}

	/**
	 * Gets author of the comment
	 * 
	 * @param comment
	 *            the comment to get author from
	 * @return the author of the comment
	 */
	private static String getAuthorFromComment(JSONObject comment) {
		JSONObject c_data = (JSONObject) comment.get("data");
		String c_author = (String) c_data.get("author");
		return c_author;
	}

	/**
	 * Generates a iterator object of the replies on a comment
	 * 
	 * @param comment
	 *            the comments to create reply iterator from
	 * @return iterator of JSONObject's containing replies to comment
	 */
	private static Iterator<JSONObject> getReplyIterator(JSONObject comment) {
		String kind = (String) comment.get("kind");
		if (!kind.equals("t1")) {
			return null;
		}
		JSONObject c_data = (JSONObject) comment.get("data");
		if (c_data.get("replies").equals("")) {
			return null;
		}
		JSONObject c_replies = (JSONObject) c_data.get("replies");
		JSONObject r_data = (JSONObject) c_replies.get("data");
		JSONArray r_children = (JSONArray) r_data.get("children");
		if (r_children.size() <= 0) {
			return null;
		}
		Iterator<JSONObject> iterator = r_children.iterator();
		return iterator;
	}
}

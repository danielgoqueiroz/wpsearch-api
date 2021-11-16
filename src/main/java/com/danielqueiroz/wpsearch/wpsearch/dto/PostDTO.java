package com.danielqueiroz.wpsearch.wpsearch.dto;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.Set;

import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;

import com.alibaba.fastjson.JSONArray;
import com.alibaba.fastjson.JSONObject;
import com.danielqueiroz.wpsearch.wpsearch.model.Post;

public class PostDTO {

    private Integer id;
    private String date;
    private String link;	
    private Map<String, String> title;
    private Map<String, String> content;
    private Map<String, String> excerpt;
    private Map<String, Object> _embedded;

	public Integer getId() {
		return id;
	}

	public void setId(Integer id) {
		this.id = id;
	}

	public String getDate() {
		return date;
	}

	public void setDate(String date) {
		this.date = date;
	}

	public String getLink() {
		return link;
	}

	public void setLink(String link) {
		this.link = link;
	}

	public String getTitle() {
		return this.title.get("rendered");
	}

	public void setTitle(Map<String, String> title) {
		this.title = title;
	}

	public String getContent() {
		String html = content.get("rendered");
		Document soup = Jsoup.parse(html);
		String text = soup.text();
		return text;
	}

	public void setContent(Map<String, String> content) {
		this.content = content;
	}

	public String getExcerpt() {
		String text = excerpt.get("rendered");
		return text;
	}

	public void setExcerpt(Map<String, String> excerpt) {
		this.excerpt = excerpt;
	}

	public String getAuthor() {
		JSONArray embedded = new JSONArray((List<Object>) get_embedded().get("author"));
		JSONObject object = (JSONObject) embedded.get(0);
		String name = object.getString("name");
		return name;
	}

	private Map<String, Object> get_embedded() {
		return _embedded;
	}
	
	
	public List<String> getTerms() {
		return null;
	}
	public void set_embedded(Map<String, Object> _embedded) {
		this._embedded = _embedded;
	}

	public List<String> getTags() {
		JSONArray terms = new JSONArray((List<Object>) get_embedded().get("wp:term"));
		List<String> itensFinded = extractFromTerms(terms , "post_tag");		
		return itensFinded;
	}

	public List<String> getCategories() {
		JSONArray categories = new JSONArray((List<Object>) get_embedded().get("wp:term"));
		List<String> itensFinded = extractFromTerms(categories, "category");
		return itensFinded;
	}

	private List<String> extractFromTerms(JSONArray categories, String type) {
		List<String> itens = new ArrayList<String>();
		for (int i = 0; i < categories.size(); i++) {
			JSONArray itemArray = (JSONArray) categories.get(i);
			for (int j = 0; j < itemArray.size(); j++) {
				JSONObject object = (JSONObject) itemArray.get(j);
				String taxonomy = object.getString("taxonomy");
				String name = object.getString("name");
				if (taxonomy.equals(type)) {
					itens.add(name);
				}
			}
		}
		return itens;
	}

	
	public Post getPost() {
		Post post = new Post();
		post.setId(getId());
		post.setAuthor(getAuthor());
		post.setCategories(getCategories());
		post.setTags(getTags());
		post.setDate(getDate());
		post.setContent(getContent());
		post.setLink(getLink());
		post.setTitle(getTitle());
		post.setExcerpt(getExcerpt());
		return post;
	}


//    public Post getPost() {
//        Post post = new Post();
//        post.setId(this.id);
//        post.setDate(String.valueOf(this.date));
//        post.setTitle(title.rendered);
//        post.setContent(content.rendered);
//        post.setExcerpt(excerpt.rendered);
//        post.setAuthor(_embedded.author.stream().findFirst().get().name);
//        post.setLink(link);
//        // item.taxonomy.equals("taxonomy"))
//        // .map(item -> item.name).toArray(String[]::new));
//        // List<String> metas;
//        // List<String> categories;
//        // List<String> tags;
//        return post;
//    }

}

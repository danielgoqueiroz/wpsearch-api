package com.danielqueiroz.wpsearch.wpsearch.dto;

import java.util.Date;
import java.util.List;

import com.alibaba.fastjson.JSON;
import com.danielqueiroz.wpsearch.wpsearch.model.Post;
import com.fasterxml.jackson.annotation.JsonProperty;

public class PostDTO {

    public int id;
    public String date;
    public String modified;
    public String type;
    public String link;	
    public Title title;
    public Content content;
    public Excerpt excerpt;
    public int featured_media;
    public Embedded _embedded;

//    public PostDTO(String json) {
//    	PostDTO post = JSON.parseObject(json, PostDTO.class);
//        this.id = post.id;
//        this.date = post.date;
//        this.modified = post.modified;
//        this.type = post.type;
//        this.link = post.link;
//        this.title = post.title;
//        this.content = post.content;
//        this.excerpt = post.excerpt;
//        this.featured_media = post.featured_media;
//        this._embedded = post._embedded;
//
//    };

    public Post getPost() {
        Post post = new Post();
        post.setId(this.id);
        post.setDate(String.valueOf(this.date));
        post.setTitle(title.rendered);
        post.setContent(content.rendered);
        post.setExcerpt(excerpt.rendered);
        post.setAuthor(_embedded.author.stream().findFirst().get().name);
        post.setLink(link);
        // item.taxonomy.equals("taxonomy"))
        // .map(item -> item.name).toArray(String[]::new));
        // List<String> metas;
        // List<String> categories;
        // List<String> tags;
        return post;
    }

    public class Guid {
        public String rendered;
    }

    public class Title {
        public String rendered;
    }

    public class Content {
        public String rendered;
    }

    public class Excerpt {
        public String rendered;
    }

    public class Self {
        public String href;
    }

    public class Collection {
        public String href;
    }

    public class About {
        public String href;
    }

    public class Author {
        public boolean embeddable;
        public String href;
        public int id;
        public String name;
        public String url;
        public String description;
        public String link;
        public String slug;
        public AvatarUrls avatar_urls;
        public Links _links;
    }

    public class Reply {
        public boolean embeddable;
        public String href;
    }

    public class VersionHistory {
        public int count;
        public String href;
    }

    public class WpFeaturedmedia {
        public boolean embeddable;
        public String href;
        public int id;
        public Date date;
        public String slug;
        public String type;
        public Object link;
        public Title title;
        public int author;
        public Caption caption;
        public String alt_text;
        public String media_type;
        public String mime_type;
        public MediaDetails media_details;
        public String source_url;
        public Links _links;
    }

    public class WpAttachment {
        public String href;
    }

    public class WpTerm {
        public String taxonomy;
        public boolean embeddable;
        public String href;
    }

    public class Cury {
        public String name;
        public String href;
        public boolean templated;
    }

    public class Links {
        public List<Self> self;
        public List<Collection> collection;
        public List<About> about;
        public List<Author> author;
        public List<Reply> replies;
        @JsonProperty("version-history")
        public List<VersionHistory> versionHistory;
        @JsonProperty("wp:featuredmedia")
        public List<WpFeaturedmedia> wpFeaturedmedia;
        @JsonProperty("wp:attachment")
        public List<WpAttachment> wpAttachment;
        @JsonProperty("wp:term")
        public List<WpTerm> wpTerm;
        public List<Cury> curies;
    }

    public class AvatarUrls {
        @JsonProperty("24")
        public String _24;
        @JsonProperty("48")
        public String _48;
        @JsonProperty("96")
        public String _96;
    }

    public class Caption {
        public String rendered;
    }

    public class Medium {
        public String file;
        public int width;
        public int height;
        public String mime_type;
        public String source_url;
    }

    public class Large {
        public String file;
        public int width;
        public int height;
        public String mime_type;
        public String source_url;
    }

    public class Thumbnail {
        public String file;
        public int width;
        public int height;
        public String mime_type;
        public String source_url;
    }

    public class MediumLarge {
        public String file;
        public int width;
        public int height;
        public String mime_type;
        public String source_url;
    }

    public class _1536x1536 {
        public String file;
        public int width;
        public int height;
        public String mime_type;
        public String source_url;
    }

    public class _2048x2048 {
        public String file;
        public int width;
        public int height;
        public String mime_type;
        public String source_url;
    }

    public class CardSmall {
        public String file;
        public int width;
        public int height;
        public String mime_type;
        public String source_url;
    }

    public class CardLarge {
        public String file;
        public int width;
        public int height;
        public String mime_type;
        public String source_url;
    }

    public class CardExtraLarge {
        public String file;
        public int width;
        public int height;
        public String mime_type;
        public String source_url;
    }

    public class SmallResponsive {
        public String file;
        public int width;
        public int height;
        public String mime_type;
        public String source_url;
    }

    public class Full {
        public String file;
        public int width;
        public int height;
        public String mime_type;
        public String source_url;
    }

    public class Sizes {
        public Medium medium;
        public Large large;
        public Thumbnail thumbnail;
        public MediumLarge medium_large;
        @JsonProperty("1536x1536")
        public _1536x1536 _1536x1536;
        @JsonProperty("2048x2048")
        public _2048x2048 _2048x2048;
        @JsonProperty("card-small")
        public CardSmall cardSmall;
        @JsonProperty("card-large")
        public CardLarge cardLarge;
        @JsonProperty("card-extra-large")
        public CardExtraLarge cardExtraLarge;
        @JsonProperty("small-responsive")
        public SmallResponsive smallResponsive;
        public Full full;
    }

    public class ImageMeta {
        public String aperture;
        public String credit;
        public String camera;
        public String caption;
        public String created_timestamp;
        public String copyright;
        public String focal_length;
        public String iso;
        public String shutter_speed;
        public String title;
        public String orientation;
        public List<Object> keywords;
    }

    public class MediaDetails {
        public int width;
        public int height;
        public String file;
        public Sizes sizes;
        public ImageMeta image_meta;
        public String original_image;
    }

    public class Embedded {
        public List<Author> author;
        @JsonProperty("wp:featuredmedia")
        public List<WpFeaturedmedia> wpFeaturedmedia;
        @JsonProperty("wp:term")
        public List<List<String>> wpTerm;
    }
}

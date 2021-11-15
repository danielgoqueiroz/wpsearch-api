package com.danielqueiroz.wpsearch.wpsearch.DTO;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.junit.jupiter.api.Assertions.assertTrue;

import java.io.File;
import java.io.IOException;

import org.apache.commons.io.FileUtils;
import org.apache.commons.io.FilenameUtils;
import org.junit.jupiter.api.Test;

import com.alibaba.fastjson.JSON;
import com.danielqueiroz.wpsearch.wpsearch.dto.PostDTO;
import com.danielqueiroz.wpsearch.wpsearch.model.Post;

public class PostDTOTestjava {

	@Test
	public void deveConverterJsonEmObejtoPost() throws IOException {
		File json = new File("src/test/resources/jsonExemplo.json");
		assertTrue(json.exists());
		String jsonString = FileUtils.readFileToString(json);
		assertNotNull(jsonString != null);

		PostDTO post = JSON.parseObject(jsonString, PostDTO.class);
		System.out.println(post);

//		PostDTO dto = new PostDTO(jsonString);
//		assertNotNull(dto);
//        Post post = dto.getPost();
//        assertNotNull(post);
//        assertEquals(post.getId(), "");
//        assertEquals(post.getAuthor(), "");
//        assertEquals(post.getTitle(), "");
//        assertEquals(post.getContent(), "");
//        assertEquals(post.getDate(), "");
//        assertEquals(post.getCategories(), "");
//        assertEquals(post.getMetas(), "");
//        assertEquals(post.getTags(), "");
	}

}

package com.danielqueiroz.wpsearch.wpsearch.DTO;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.junit.jupiter.api.Assertions.assertTrue;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;

import java.io.File;
import java.io.IOException;
import java.util.List;

import org.apache.commons.io.FileUtils;
import org.apache.commons.io.FilenameUtils;
import org.assertj.core.util.Arrays;
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

		PostDTO postDTO = JSON.parseObject(jsonString, PostDTO.class);
		assertEquals(1605361, postDTO.getId());
		assertEquals("2021-11-12T17:00:52", postDTO.getDate());
		assertEquals("https://ndmais.com.br/seguranca/policia/homem-preso-em-hospital-suspeito-de-matar-ex-companheira-em-sc-e-indiciado-por-feminicidio/",postDTO.getLink());
		assertEquals("Homem preso em hospital suspeito de matar ex-companheira em SC é indiciado por feminicídio",postDTO.getTitle());
		assertEquals("O homem preso no Hospital Universitário, em Florianópolis, nessa terça-feira (9), suspeito de matar a ex-companheira, foi indiciado por feminicídio. Homem preso em hospital suspeito de matar ex-companheira em SC é indiciado por feminicídio – Foto: Arquivo/Bruno Golembiewski/ND A vítima, de 48 anos, foi encontrada por uma amiga na sala de casa na noite de segunda-feira (8). A mulher chegou a ser encaminhada ao hospital com vida, mas não resistiu aos ferimentos. Leia mais min Polícia Civil/Divulgação/ND Suspeito de matar ex-companheira é preso em hospital de Florianópolis min Arquivo/Bruno Golembiewski/ND Mulher morre após ser achada com marcas no pescoço dentro de casa na Grande Florianópolis De acordo com a delegada da Dpcami (Delegacia de Proteção à Criança, Adolescente, Mulher e Idoso), Débora Jardim, o homem, de 50 anos, foi indiciado no momento do auto de prisão em flagrante, que ocorreu nas dependências do hospital. Ele foi internado após tentativa de suicídio. A estimativa era de que ele fosse liberado na quarta-feira (10) e levado ao presídio. Contudo, a Polícia Civil não soube informar se o homem já recebeu alta do hospital. Ainda de acordo com a delegada Débora Jardim, o ex-companheiro confessou o crime durante conversas. O assassinato foi motivado por ciúmes. O homem e a vítima estavam separados há aproximadamente 20 dias. Eles moravam no mesmo terreno, porém, em casas separadas. O ex-casal possui dois filhos adultos. Os nomes dos envolvidos não foram divulgados. Conforme a delegada, a polícia ainda aguarda o laudo pericial para confirmar a causa da morte. Por causa de marcas no pescoço, a suspeita é de que ela tenha morrido por esganadura. A vítima era natural de Florianópolis. Relembre Uma mulher de 48 anos foi encontrada caída na sala de casa, com marcas no pescoço, por volta das 21h55 da última segunda-feira. O crime ocorreu no bairro Sul do Rio. No boletim divulgado pela Polícia Militar, conta que a mulher foi espancada. O crime teria sido cometido pelo ex-companheiro que estava foragido até a noite desta terça. A mulher teria sido encontrada por familiares ainda com vida e teria morrido no hospital. A vítima registrou boletins de ocorrência por violência doméstica contra o homem em 2018.", postDTO.getContent());
		assertEquals("Ele e a ex-mulher estavam separados há cerca de 20 dias, mas habitavam o mesmo terreno; assassinato foi motivado por ciúmes", postDTO.getExcerpt());
		assertEquals(("Bruna Stroisc​​h"), postDTO.getAuthor());
		assertEquals(Arrays.asList(new String[] {"Polícia"}), postDTO.getCategories());
		assertEquals(Arrays.asList(new String[] {"Notícia","Notícia", "Santo Amaro da Imperatriz"}), postDTO.getTags());
		
		Post post = postDTO.getPost();
		assertEquals(1605361, post.getId());
		assertEquals("2021-11-12T17:00:52", post.getDate());
		assertEquals("https://ndmais.com.br/seguranca/policia/homem-preso-em-hospital-suspeito-de-matar-ex-companheira-em-sc-e-indiciado-por-feminicidio/",post.getLink());
		assertEquals("Homem preso em hospital suspeito de matar ex-companheira em SC é indiciado por feminicídio",post.getTitle());
		assertEquals("O homem preso no Hospital Universitário, em Florianópolis, nessa terça-feira (9), suspeito de matar a ex-companheira, foi indiciado por feminicídio. Homem preso em hospital suspeito de matar ex-companheira em SC é indiciado por feminicídio – Foto: Arquivo/Bruno Golembiewski/ND A vítima, de 48 anos, foi encontrada por uma amiga na sala de casa na noite de segunda-feira (8). A mulher chegou a ser encaminhada ao hospital com vida, mas não resistiu aos ferimentos. Leia mais min Polícia Civil/Divulgação/ND Suspeito de matar ex-companheira é preso em hospital de Florianópolis min Arquivo/Bruno Golembiewski/ND Mulher morre após ser achada com marcas no pescoço dentro de casa na Grande Florianópolis De acordo com a delegada da Dpcami (Delegacia de Proteção à Criança, Adolescente, Mulher e Idoso), Débora Jardim, o homem, de 50 anos, foi indiciado no momento do auto de prisão em flagrante, que ocorreu nas dependências do hospital. Ele foi internado após tentativa de suicídio. A estimativa era de que ele fosse liberado na quarta-feira (10) e levado ao presídio. Contudo, a Polícia Civil não soube informar se o homem já recebeu alta do hospital. Ainda de acordo com a delegada Débora Jardim, o ex-companheiro confessou o crime durante conversas. O assassinato foi motivado por ciúmes. O homem e a vítima estavam separados há aproximadamente 20 dias. Eles moravam no mesmo terreno, porém, em casas separadas. O ex-casal possui dois filhos adultos. Os nomes dos envolvidos não foram divulgados. Conforme a delegada, a polícia ainda aguarda o laudo pericial para confirmar a causa da morte. Por causa de marcas no pescoço, a suspeita é de que ela tenha morrido por esganadura. A vítima era natural de Florianópolis. Relembre Uma mulher de 48 anos foi encontrada caída na sala de casa, com marcas no pescoço, por volta das 21h55 da última segunda-feira. O crime ocorreu no bairro Sul do Rio. No boletim divulgado pela Polícia Militar, conta que a mulher foi espancada. O crime teria sido cometido pelo ex-companheiro que estava foragido até a noite desta terça. A mulher teria sido encontrada por familiares ainda com vida e teria morrido no hospital. A vítima registrou boletins de ocorrência por violência doméstica contra o homem em 2018.", post.getContent());
		assertEquals("Ele e a ex-mulher estavam separados há cerca de 20 dias, mas habitavam o mesmo terreno; assassinato foi motivado por ciúmes", post.getExcerpt());
		assertEquals(("Bruna Stroisc​​h"), post.getAuthor());
		assertEquals(Arrays.asList(new String[] {"Polícia"}), post.getCategories());
		assertEquals(Arrays.asList(new String[] {"Notícia","Notícia", "Santo Amaro da Imperatriz"}), post.getTags());
		
	}

}

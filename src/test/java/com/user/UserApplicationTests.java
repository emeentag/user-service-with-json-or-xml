package com.user;

import java.nio.charset.StandardCharsets;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.List;
import java.util.Set;
import java.util.stream.Collectors;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.dataformat.xml.XmlMapper;
import com.user.controller.MemberController;
import com.user.entity.dao.member.Member;
import com.user.entity.dto.json.member.MemberJsonDTO;
import com.user.entity.dto.xml.member.MemberXmlDTO;
import com.user.service.member.MemberService;

import org.hamcrest.Matchers;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.Mockito;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.MediaType;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;
import org.springframework.test.web.servlet.result.MockMvcResultHandlers;
import org.springframework.test.web.servlet.result.MockMvcResultMatchers;

@RunWith(SpringRunner.class)
@WebMvcTest(controllers = { MemberController.class })
public class UserApplicationTests {

	@Autowired
	private MockMvc mockMvc;

	@Autowired
	private ObjectMapper objectMapper;

	@MockBean
	private MemberService memberService;

	private final Logger logger = LoggerFactory.getLogger(this.getClass());

	@Test
	public void getMember() throws Exception {

		/* <member>
			<id>1</id>
			<firstName>UserName</firstName>
			<lastName>LastName</lastName>
			<dateOfBirth>26-11-1987 09:34:29</dateOfBirth>
		</member>
		
		{"id":1,"firstName":"UserName","lastName":"LastName","dateOfBirth":"26-11-1987 09:34:29"}
		*/

		Member member = new Member();
		member.setId(1L);
		member.setFirstName("UserName");
		member.setLastName("LastName");
		Calendar cal = Calendar.getInstance();
		cal.add(Calendar.YEAR, -30);
		member.setDateOfBirth(cal.getTime());

		Mockito.when(this.memberService.getMember(1L)).thenReturn(member);

		this.mockMvc
				.perform(MockMvcRequestBuilders.get("/member/1").accept(MediaType.APPLICATION_JSON_UTF8_VALUE)
						.characterEncoding(StandardCharsets.UTF_8.name()))
				.andDo(MockMvcResultHandlers.print()).andExpect(MockMvcResultMatchers.status().isOk())
				.andExpect(MockMvcResultMatchers.content().contentType(MediaType.APPLICATION_JSON_UTF8_VALUE))
				.andExpect(MockMvcResultMatchers.jsonPath("$.firstName").value("UserName"))
				.andExpect(MockMvcResultMatchers.jsonPath("$.lastName").value("LastName"));

		this.mockMvc
				.perform(MockMvcRequestBuilders.get("/member/1").accept(MediaType.APPLICATION_XML_VALUE)
						.characterEncoding(StandardCharsets.UTF_8.name()))
				.andDo(MockMvcResultHandlers.print()).andExpect(MockMvcResultMatchers.status().isOk())
				.andExpect(MockMvcResultMatchers.content()
						.contentType(new MediaType(MediaType.APPLICATION_XML, StandardCharsets.UTF_8)))
				.andExpect(MockMvcResultMatchers.xpath("member").exists())
				.andExpect(MockMvcResultMatchers.xpath("member/firstName").exists())
				.andExpect(MockMvcResultMatchers.xpath("member/lastName").exists())
				.andExpect(MockMvcResultMatchers.xpath("member/dateOfBirth").exists())
				.andExpect(MockMvcResultMatchers.xpath("member/firstName").string("UserName"))
				.andExpect(MockMvcResultMatchers.xpath("member/lastName").string("LastName"));
	}

	@Test
	public void getMembers() throws Exception {

		Member member = new Member();
		member.setId(1L);
		member.setFirstName("UserName");
		member.setLastName("LastName");
		Calendar cal = Calendar.getInstance();
		cal.add(Calendar.YEAR, -30);
		member.setDateOfBirth(cal.getTime());

		Member member2 = new Member();
		member2.setId(2L);
		member2.setFirstName("UserName2");
		member2.setLastName("LastName");
		cal.add(Calendar.YEAR, -20);
		member2.setDateOfBirth(cal.getTime());

		Member member3 = new Member();
		member3.setId(3L);
		member3.setFirstName("UserName3");
		member3.setLastName("LastName");
		cal.add(Calendar.YEAR, -40);
		member3.setDateOfBirth(cal.getTime());

		List<Member> members = new ArrayList<>();
		members.add(member);
		members.add(member2);
		members.add(member3);

		Set<Member> setMembers = members.stream().collect(Collectors.toSet());

		Mockito.when(this.memberService.getMembers()).thenReturn(setMembers);

		this.mockMvc
				.perform(MockMvcRequestBuilders.get("/member/all").accept(MediaType.APPLICATION_JSON_UTF8_VALUE)
						.characterEncoding(StandardCharsets.UTF_8.name()))
				.andDo(MockMvcResultHandlers.print()).andExpect(MockMvcResultMatchers.status().isOk())
				.andExpect(MockMvcResultMatchers.content().contentType(MediaType.APPLICATION_JSON_UTF8_VALUE))
				.andExpect(MockMvcResultMatchers.jsonPath("$").value(Matchers.hasSize(3)));

		this.mockMvc
				.perform(MockMvcRequestBuilders.get("/member/all").accept(MediaType.APPLICATION_XML_VALUE)
						.characterEncoding(StandardCharsets.UTF_8.name()))
				.andExpect(MockMvcResultMatchers.status().isOk())
				.andExpect(MockMvcResultMatchers.content()
						.contentType(new MediaType(MediaType.APPLICATION_XML, StandardCharsets.UTF_8)))
				.andExpect(MockMvcResultMatchers.xpath("Set/item").nodeCount(3));
	}

	@Test
	public void createJSONMember() throws Exception {
		MemberJsonDTO member = new MemberJsonDTO();
		member.setId(1L);
		member.setFirstName("JsonUserName");
		member.setLastName("JsonUserLastName");
		Calendar cal = Calendar.getInstance();
		cal.add(Calendar.YEAR, -30);
		member.setDateOfBirth(cal.getTime());

		this.mockMvc
				.perform(MockMvcRequestBuilders.post("/member").accept(MediaType.APPLICATION_JSON_VALUE)
						.characterEncoding(StandardCharsets.UTF_8.name()).content(objectMapper.writeValueAsString(member))
						.contentType(MediaType.APPLICATION_JSON_VALUE))
				.andDo(MockMvcResultHandlers.print()).andExpect(MockMvcResultMatchers.status().isCreated());
	}

	@Test
	public void createXMLMember() throws JsonProcessingException, Exception {
		MemberXmlDTO member = new MemberXmlDTO();
		member.setId(2L);
		member.setFirstName("XMLUserName");
		member.setLastName("XMLUserLastName");
		Calendar cal = Calendar.getInstance();
		cal.add(Calendar.YEAR, -30);
		member.setDateOfBirth(cal.getTime());

		XmlMapper xmlMapper = new XmlMapper();

		this.mockMvc
				.perform(MockMvcRequestBuilders.post("/member").accept(MediaType.APPLICATION_XML_VALUE)
						.characterEncoding(StandardCharsets.UTF_8.name()).content(xmlMapper.writeValueAsString(member))
						.contentType(MediaType.APPLICATION_XML_VALUE))
				.andDo(MockMvcResultHandlers.print()).andExpect(MockMvcResultMatchers.status().isCreated());
	}

	@Test
	public void updateJsonMember() throws JsonProcessingException, Exception {

		Member member = new Member();
		member.setId(1L);
		member.setFirstName("UserName");
		member.setLastName("LastName");

		SimpleDateFormat df = new SimpleDateFormat("dd-MM-yyyy hh:mm:ss");

		member.setDateOfBirth(df.parse("20-12-2014 02:30:00"));

		Mockito.when(this.memberService.updateMember(member)).thenReturn(member);

		this.mockMvc
				.perform(MockMvcRequestBuilders.put("/member").accept(MediaType.APPLICATION_JSON_VALUE)
						.characterEncoding(StandardCharsets.UTF_8.name())
						.content(objectMapper.writeValueAsString(MemberJsonDTO.Map.toDto(member)))
						.contentType(MediaType.APPLICATION_JSON_VALUE))
				.andDo(MockMvcResultHandlers.print()).andExpect(MockMvcResultMatchers.status().isOk());
	}

	@Test
	public void updateXmlMember() throws JsonProcessingException, Exception {

		Member member = new Member();
		member.setId(1L);
		member.setFirstName("UserName");
		member.setLastName("LastName");

		SimpleDateFormat df = new SimpleDateFormat("dd-MM-yyyy hh:mm:ss");

		member.setDateOfBirth(df.parse("20-12-2014 02:30:00"));

		Mockito.when(this.memberService.updateMember(member)).thenReturn(member);

		XmlMapper xmlMapper = new XmlMapper();

		System.out.println(xmlMapper.writeValueAsString(member));

		this.mockMvc
				.perform(MockMvcRequestBuilders.put("/member").accept(MediaType.APPLICATION_XML_VALUE)
						.characterEncoding(StandardCharsets.UTF_8.name())
						.content(xmlMapper.writeValueAsString(MemberXmlDTO.Map.toDto(member)))
						.contentType(MediaType.APPLICATION_XML_VALUE))
				.andDo(MockMvcResultHandlers.print()).andExpect(MockMvcResultMatchers.status().isOk());
	}

	@Test
	public void deleteMember() throws JsonProcessingException, Exception {

		Member member = new Member();
		member.setId(1L);
		member.setFirstName("UserName");
		member.setLastName("LastName");
		Calendar cal = Calendar.getInstance();
		cal.add(Calendar.YEAR, -30);
		member.setDateOfBirth(cal.getTime());

		Mockito.when(this.memberService.deleteMember(1L)).thenReturn(1L);

		this.mockMvc.perform(MockMvcRequestBuilders.delete("/member/1").contentType(MediaType.APPLICATION_JSON_VALUE))
				.andDo(MockMvcResultHandlers.print()).andExpect(MockMvcResultMatchers.status().isOk());
	}

}

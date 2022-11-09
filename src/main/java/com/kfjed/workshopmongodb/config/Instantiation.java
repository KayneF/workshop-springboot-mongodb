package com.kfjed.workshopmongodb.config;

import java.text.SimpleDateFormat;
import java.util.Arrays;
import java.util.TimeZone;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.context.annotation.Configuration;

import com.kfjed.workshopmongodb.domain.Post;
import com.kfjed.workshopmongodb.domain.User;
import com.kfjed.workshopmongodb.dto.AuthorDTO;
import com.kfjed.workshopmongodb.repository.PostRepository;
import com.kfjed.workshopmongodb.repository.UserRepository;

@Configuration
public class Instantiation implements CommandLineRunner {
	
	@Autowired
	private UserRepository userRepository;
	
	@Autowired
	private PostRepository postRepository;

	@Override
	public void run(String... args) throws Exception {
		
		SimpleDateFormat sdf = new SimpleDateFormat("dd/MM/yyyy");
		sdf.setTimeZone(TimeZone.getTimeZone("GMT"));
		
		userRepository.deleteAll();
		postRepository.deleteAll();
		
		User u1 = new User(null, "Maria Brown", "maria@gmail.com");
		User u2 = new User(null, "Alex Green", "alex@gmail.com");
		User u3 = new User(null, "Bob Grey", "bob@gmail.com");
		userRepository.saveAll(Arrays.asList(u1, u2, u3));
		
		Post post1 = new Post(null, sdf.parse("21/03/2022"), "Going on a trip today!!!", "Nevada here i come!!!", new AuthorDTO(u1));
		Post post2 = new Post(null, sdf.parse("23/03/2022"), "Good morning!", "Nice day to have a icecream, isn't it?", new AuthorDTO(u1));
		postRepository.saveAll(Arrays.asList(post1, post2));
		
		u1.getPosts().addAll(Arrays.asList(post1, post2));
		userRepository.save(u1);
	}
}

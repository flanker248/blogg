package com.cbyk.blogg;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.autoconfigure.thymeleaf.ThymeleafProperties;
import org.springframework.cache.annotation.EnableCaching;
import org.springframework.context.annotation.Bean;
import org.springframework.data.mongodb.config.EnableMongoAuditing;
import org.springframework.data.mongodb.repository.config.EnableMongoRepositories;
import org.thymeleaf.templateresolver.FileTemplateResolver;
import org.thymeleaf.templateresolver.ITemplateResolver;

@SpringBootApplication
@EnableMongoRepositories
@EnableMongoAuditing
@EnableCaching
public class BloggApplication {

	public static void main(String[] args) {
		SpringApplication.run(BloggApplication.class, args);
	}

	@Autowired
	private ThymeleafProperties properties;

	@Value("${spring.thymeleaf.templates_root:}")
	private String templatesRoot;

//	@Bean
//	public ITemplateResolver defaultTemplateResolver() {
//		FileTemplateResolver resolver = new FileTemplateResolver();
//		resolver.setSuffix(properties.getSuffix());
//		resolver.setPrefix(templatesRoot);
//		resolver.setTemplateMode(properties.getMode());
//		resolver.setCacheable(properties.isCache());
//		return resolver;
//	}



}

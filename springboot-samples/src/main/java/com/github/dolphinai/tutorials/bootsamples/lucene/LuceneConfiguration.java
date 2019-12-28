package com.github.dolphinai.tutorials.bootsamples.lucene;

import com.github.dolphinai.tutorials.bootsamples.lucene.impl.LuceneIndexRepositoryImpl;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class LuceneConfiguration {

	@Bean
	public DocumentIndexRepository documentIndexRepository() {
		return new LuceneIndexRepositoryImpl();
	}

}

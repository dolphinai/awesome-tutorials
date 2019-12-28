package com.github.dolphinai.tutorials.bootsamples.lucene.controller;

import com.github.dolphinai.tutorials.bootsamples.lucene.DocumentEntry;
import io.swagger.annotations.Api;
import org.springframework.web.bind.annotation.*;

import java.util.Collections;
import java.util.List;

@Api(description = "Fulltext API")
@RestController
@RequestMapping("/fulltext")
public class FulltextController {

	@GetMapping("/query/{keywords}/{start}_{hitsPerPage}")
	@ResponseBody
	public List<DocumentEntry> queryAll(@PathVariable("keywords") String keywords,
									   @PathVariable("start") int start,
									   @PathVariable("hitsPerPage") int hitsPerPage) {

		return Collections.emptyList();
	}

}

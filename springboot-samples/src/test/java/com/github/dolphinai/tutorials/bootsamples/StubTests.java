package com.github.dolphinai.tutorials.bootsamples;

import static com.github.tomakehurst.wiremock.core.Options.DYNAMIC_PORT;
import static com.github.tomakehurst.wiremock.core.WireMockConfiguration.options;

import com.github.tomakehurst.wiremock.junit.WireMockRule;
import org.junit.Rule;
import org.junit.Test;

public class StubTests {

	@Rule
	public static final WireMockRule rule = new WireMockRule(options().port(DYNAMIC_PORT));

	@Test
	public static void user_findById_test() {

	}
}

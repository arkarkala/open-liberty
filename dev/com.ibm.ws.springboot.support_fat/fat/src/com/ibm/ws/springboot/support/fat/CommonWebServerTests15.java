/*******************************************************************************
 * Copyright (c) 2018 IBM Corporation and others.
 * All rights reserved. This program and the accompanying materials
 * are made available under the terms of the Eclipse Public License v1.0
 * which accompanies this distribution, and is available at
 * http://www.eclipse.org/legal/epl-v10.html
 *
 * Contributors:
 *     IBM Corporation - initial API and implementation
 *******************************************************************************/
package com.ibm.ws.springboot.support.fat;

import static org.junit.Assert.assertTrue;

import java.io.IOException;
import java.net.MalformedURLException;
import java.util.Arrays;
import java.util.HashSet;
import java.util.Set;

import org.junit.Test;
import org.junit.runner.RunWith;

import com.gargoylesoftware.htmlunit.FailingHttpStatusCodeException;
import com.gargoylesoftware.htmlunit.WebClient;
import com.gargoylesoftware.htmlunit.html.HtmlButton;
import com.gargoylesoftware.htmlunit.html.HtmlPage;

import componenttest.custom.junit.runner.FATRunner;

@RunWith(FATRunner.class)
public class CommonWebServerTests15 extends CommonWebServerTests {

    @Test
    public void testBasicSpringBootApplication15() throws Exception {
        testBasicSpringBootApplication();
    }

    @Override
    public Set<String> getFeatures() {
        return new HashSet<>(Arrays.asList("springBoot-1.5", "servlet-3.1"));
    }

    @Override
    public String getApplication() {
        return SPRING_BOOT_15_APP_BASE;
    }

    @Test
    public void test_useJarUrls_enabled() throws FailingHttpStatusCodeException, MalformedURLException, IOException {
        int httpPort = 8081;

        @SuppressWarnings("resource")
        WebClient webClient = new WebClient();
        HtmlButton button = ((HtmlPage) webClient.getPage("http://localhost:" + httpPort + "/useJarUrlsTest.html")).getHtmlElementById("button1");
        HtmlPage newPageText = ((HtmlPage) button.click());
        assertTrue("Button click unexpected:" + newPageText.toString(), newPageText.toString().contains("http://localhost:" + httpPort + "/buttonClicked"));
        String body = newPageText.getBody().asText();
        assertTrue("Expected content not returned from button push: \n" + body, body.contains("Hello. You clicked a button."));
    }
}
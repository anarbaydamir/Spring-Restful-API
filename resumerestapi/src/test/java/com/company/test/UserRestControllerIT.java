package com.company.test;

import com.company.contoller.UserRestController;
import com.company.dto.ResponseDTO;
import org.junit.Assert;
import org.junit.Rule;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowire;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.web.client.TestRestTemplate;
import org.springframework.boot.web.server.LocalServerPort;
import org.springframework.http.ResponseEntity;
import org.springframework.security.oauth2.client.test.OAuth2ContextConfiguration;
import org.springframework.security.oauth2.client.test.OAuth2ContextSetup;
import org.springframework.security.oauth2.client.test.RestTemplateHolder;
import org.springframework.security.oauth2.client.token.grant.password.ResourceOwnerPasswordResourceDetails;
import org.springframework.security.oauth2.common.AuthenticationScheme;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.web.client.RestOperations;

import java.util.ArrayList;
import java.util.LinkedHashMap;
import java.util.List;

@RunWith(SpringRunner.class)
@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.DEFINED_PORT)
@OAuth2ContextConfiguration(MyDetails.class)
public class UserRestControllerIT implements RestTemplateHolder {

    @LocalServerPort
    private int port;

    @Autowired
    private TestRestTemplate testRestTemplate;

    @Rule
    public OAuth2ContextSetup context = OAuth2ContextSetup.standard(this);

    RestOperations restOperations;

    @Override
    public void setRestTemplate(RestOperations restOperations) {
        this.restOperations = restOperations;
    }

    @Override
    public RestOperations getRestTemplate() {
        return restOperations;
    }

    @Test
    public void testUserRestController() throws Exception {
        String url = "http://localhost:" + port + "/";
        System.out.println("url: " + url);
        ResponseDTO resp = this.testRestTemplate.getForObject(url,ResponseDTO.class);

        System.out.println("found=" +resp.getObj());
        LinkedHashMap<String,Object> hashMap = (LinkedHashMap) resp.getObj();

        Assert.assertEquals("must be equal 1",1,hashMap.get("id")+"");
        System.out.println(resp);
    }

    public int getPort() {
        return port;
    }

    public void setPort(int port) {
        this.port = port;
    }

    public TestRestTemplate getTestRestTemplate() {
        return testRestTemplate;
    }

    public void setTestRestTemplate(TestRestTemplate testRestTemplate) {
        this.testRestTemplate = testRestTemplate;
    }
}

class MyDetails extends ResourceOwnerPasswordResourceDetails {
    public MyDetails(final Object obj) {
        UserRestControllerIT it = (UserRestControllerIT) obj;
        setAccessTokenUri("http://localhost:" + it.getPort() + "/oauth/token");
        setClientId("alma");
        setClientSecret("alma");

        List<String> scopes = new ArrayList<>();
        scopes.add("read");
        scopes.add("write");

        setScope(scopes);
        setUsername("aaa@gmail.com");
        setPassword("12345");
        setClientAuthenticationScheme(AuthenticationScheme.header);
    }
}

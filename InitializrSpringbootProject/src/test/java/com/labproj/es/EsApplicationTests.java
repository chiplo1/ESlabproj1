package com.labproj.es;

import java.io.IOException;
import org.apache.http.HttpResponse;
import org.apache.http.HttpStatus;
import org.apache.http.client.ClientProtocolException;
import org.apache.http.client.methods.HttpGet;
import org.apache.http.client.methods.HttpUriRequest;
import org.apache.http.impl.client.HttpClientBuilder;
import static org.junit.jupiter.api.Assertions.assertEquals;
import org.junit.jupiter.api.Test;
import org.springframework.boot.test.context.SpringBootTest;


@SpringBootTest
class EsApplicationTests {

    private static final String SERVICE_URL
      = "http://localhost:8080/resources/planes";
 
    @Test
    public void givenGetAllEmployees_whenCorrectRequest_thenResponseCodeSuccess() 
      throws ClientProtocolException, IOException {
  
        /*HttpUriRequest request = new HttpGet(SERVICE_URL);
 
        HttpResponse httpResponse = (HttpResponse) HttpClientBuilder.create().build().execute(request);
 
        assertEquals(httpResponse.getStatusLine().getStatusCode(), HttpStatus.SC_OK);
        */
    }

}

/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.labproj.es;

<<<<<<< HEAD
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.bind.annotation.RequestMapping;
=======
import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.MalformedURLException;
import java.net.URL;
import java.util.logging.Level;
import java.util.logging.Logger;
import org.apache.http.HttpResponse;
import org.apache.tomcat.util.http.fileupload.IOUtils;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import static org.toilelibre.libe.curl.Curl.curl;
import static org.toilelibre.libe.curl.Curl.$;
>>>>>>> b6f6ede8091a11fca03adda628924cd0375cb6c6

@RestController
public class HomeController {
<<<<<<< HEAD

	@RequestMapping("/")
	public String index() {
		return "Greetingssss from Spring Boot!";
	}

}
=======
    
    @RequestMapping("/")
    public String home() throws IOException {
        
    
        //curl -X POST https://postman-echo.com/post --data foo1=bar1&foo2=bar2
        
        String url = "https://opensky-network.org/api/states/all?icao24=3c6444&icao24=3e1bf9";
        String command = "curl -s 'https://opensky-network.org/api/states/all?icao24=3c6444&icao24=3e1bf9'";
        
       
        return MyGETRequest(url);
        
        
    }
    
    /*Process process = null;
        try {
            process = Runtime.getRuntime().exec(command);
        } catch (IOException ex) {
            Logger.getLogger(HomeController.class.getName()).log(Level.SEVERE, null, ex);
        }
        
        ProcessBuilder processBuilder = new ProcessBuilder(command.split(" "));
        //processBuilder.directory(new File("/home/"));
        Process process = null;
        try {
            process = processBuilder.start();
        } catch (IOException ex) {
            Logger.getLogger(HomeController.class.getName()).log(Level.SEVERE, null, ex);
        }

        InputStream inputStream = process.getInputStream();
        
        System.err.println(curl().d("").$(url));
        
        return "coco";
    }*/
    
    
    public static String MyGETRequest(String url) throws IOException {
        URL urlForGetRequest = new URL(url);
        String readLine = null;
        HttpURLConnection conection = (HttpURLConnection) urlForGetRequest.openConnection();
        conection.setRequestMethod("GET");
        conection.setRequestProperty("userId", "a1bcdef"); // set userId its a sample here
        int responseCode = conection.getResponseCode();
        if (responseCode == HttpURLConnection.HTTP_OK) {
            BufferedReader in = new BufferedReader(
                new InputStreamReader(conection.getInputStream()));
            StringBuffer response = new StringBuffer();
            while ((readLine = in .readLine()) != null) {
                response.append(readLine);
            } in .close();
            // print result
            System.out.println("JSON String Result " + response.toString());
            //GetAndPost.POSTRequest(response.toString());
            return response.toString();
        } else {
            System.out.println("GET NOT WORKED");
        }
        return "GET NOT WORKED";
    }
}
>>>>>>> b6f6ede8091a11fca03adda628924cd0375cb6c6

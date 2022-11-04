package edu.vt.cs.vtcare.meetingservices.services;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.URL;
import java.nio.charset.StandardCharsets;
import java.util.HashMap;
import java.util.Map;

public class HttpWebService {

    public String sendPostRequest(String url, String jsonObj,
                              HashMap<String, String> headerAttributes) throws IOException {
        try{
            HttpURLConnection con = (HttpURLConnection) new URL(url).openConnection();
            con.setRequestMethod("POST");
            con.setRequestProperty("Accept-Charset", "UTF-8");
            con.setRequestProperty("Content-Type", "application/json");
            con.setRequestProperty("Accept", "application/json");

            for (Map.Entry<String, String> entry : headerAttributes.entrySet()) {
                con.setRequestProperty(entry.getKey(), entry.getValue());
            }

            con.setDoOutput(true);

            con.getOutputStream().write(jsonObj.getBytes(StandardCharsets.UTF_8));
            BufferedReader br = new BufferedReader(
                    new InputStreamReader(con.getInputStream(), StandardCharsets.UTF_8));

            StringBuilder response = new StringBuilder();
            String responseLine = null;

            while ((responseLine = br.readLine()) != null) {
                response.append(responseLine.trim());
            }

            return response.toString();
        }
        catch (IOException e){
            System.out.println(e);
            throw e;
        }
    }
}

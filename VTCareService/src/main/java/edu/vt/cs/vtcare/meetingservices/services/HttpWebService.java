package edu.vt.cs.vtcare.meetingservices.services;

import edu.vt.cs.vtcare.meetingservices.models.RequestType;
import edu.vt.cs.vtcare.meetingservices.models.ZoomMeetingResponse;
import org.glassfish.jersey.client.HttpUrlConnectorProvider;

import javax.ws.rs.client.Client;
import javax.ws.rs.client.ClientBuilder;
import javax.ws.rs.client.Entity;
import javax.ws.rs.client.Invocation;
import javax.ws.rs.core.MediaType;
import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.lang.reflect.Type;
import java.net.HttpURLConnection;
import java.net.ProtocolException;
import java.net.URL;
import java.nio.charset.StandardCharsets;
import java.util.HashMap;
import java.util.Map;

public class HttpWebService {

    public <T> T sendRequest(String url, Object jsonObj,
                             HashMap<String, String> headerAttributes,
                             RequestType method, Class<T> responseType) {
        Invocation.Builder builder = ClientBuilder.newClient().target(url)
                .request(MediaType.APPLICATION_JSON_TYPE)
                .header("Accept-Charset", "UTF-8")
                .header("Content-Type", "application/json")
                .header("Accept", "application/json");

        if(method == RequestType.PATCH)
            builder = builder
                    .property(HttpUrlConnectorProvider.SET_METHOD_WORKAROUND,
                            true);

        for (Map.Entry<String, String> entry : headerAttributes.entrySet()) {
            builder.header(entry.getKey(), entry.getValue());
        }

        return builder.method(method.toString(),
                Entity.json(jsonObj), responseType);
    }
}

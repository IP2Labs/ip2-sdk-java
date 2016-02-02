/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package ug.co.intelworld.services;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.io.UnsupportedEncodingException;
import java.net.MalformedURLException;
import java.net.URL;
import java.util.Iterator;
import java.util.Map;
import javax.net.ssl.HttpsURLConnection;

/**
 *
 * @author Naphlin Peter Akena 01/20/2016
 * @version 0.1
 *
 */
public class NetworkHelpers {

    /**
     * Text encoding used when getting responses from return HTTP response
     * InputStream
     */
    private static final String ENCODING = "UTF-8";

    /**
     * Returns a String array of returned entity response message and the
     * response code to the invoker of the service This method should be called
     * in the event that the user does wants to specify their own timeouts for
     * the HTTP request
     *
     * @param url_ The endpoint URL to be called
     * @param headers Name value pairs of header values to be added to the
     * request
     * @param entity If the request requires any raw entity, it should be passed
     * with the entity parameter
     * @param read_timeout Any read timeouts to be specified
     * @param connect_timeout Any connect timeouts to be specified
     * @return String array of response body (index 0) and response code (index
     * 1)
     * @throws MalformedURLException When the URL is not as expected by the
     * underlying HTTP service, this exception will be thrown
     * @throws IOException Any input or output errors will raise IOException
     */
    protected static String postToRemoteService(
            String url_,
            Map<String, String> headers,
            String entity,
            int read_timeout,
            int connect_timeout) throws MalformedURLException, IOException {

        URL m_url = new URL(url_);

        HttpsURLConnection connection = (HttpsURLConnection) m_url.openConnection();
        connection.setReadTimeout(read_timeout);
        connection.setConnectTimeout(connect_timeout);
        connection.setRequestMethod("POST");

        if (headers != null && !headers.isEmpty()) {
            Iterator t = headers.entrySet().iterator();

            while (t.hasNext()) {
                Map.Entry entry = (Map.Entry<String, String>) t.next();
                connection.setRequestProperty((String) entry.getKey(), (String) entry.getValue());
            }

        }

        connection.setDoOutput(true);

        if (entity != null) {

            try (OutputStreamWriter out = new OutputStreamWriter(
                    connection.getOutputStream())) {
                out.write(entity);
            }
        }

        final InputStream is;
        final int responseCode = connection.getResponseCode();
        //connection.disconnect();
        if(responseCode != 200 || responseCode != 201){
            is = connection.getErrorStream();
        }else{
            is = connection.getInputStream();
        }
        final String resp = getResponseMessage(is);
        connection.disconnect();
        return resp;

    }
    
    
    /**
     *
     * Returns contents of InputStream that has been returned from a successful
     * HTTP request
     *
     * @param is InputStream that has been returned from a successful HTTP
     * request
     * @return A string of the response body parsed from the input stream
     * @throws UnsupportedEncodingException Default encoding of {@code ENCODING}
     * is specified, If this is not supported, this UnsupportedEncodingException
     * will be thrown
     * @throws IOException Any errors in reading contents of input stream will
     * thrown IOException
     */
    protected static String getResponseMessage(InputStream is) throws UnsupportedEncodingException, IOException {

        if (is == null) {
            return null;
        }

        StringBuilder builder;
        try (BufferedReader reader = new BufferedReader(new InputStreamReader(is, ENCODING))) {
            builder = new StringBuilder();
            String line = "";
            while ((line = reader.readLine()) != null) {
                builder.append(line);
            }
        }
        return builder.toString();

    }

}

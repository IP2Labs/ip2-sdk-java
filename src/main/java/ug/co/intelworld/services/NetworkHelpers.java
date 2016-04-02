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
import java.io.OutputStream;
import java.io.OutputStreamWriter;
import java.io.UnsupportedEncodingException;
import java.net.HttpURLConnection;
import java.net.MalformedURLException;
import java.net.URL;
import java.security.KeyManagementException;
import java.security.NoSuchAlgorithmException;
import java.security.cert.X509Certificate;
import java.util.Iterator;
import java.util.Map;
import javax.net.ssl.HostnameVerifier;
import javax.net.ssl.HttpsURLConnection;
import javax.net.ssl.SSLContext;
import javax.net.ssl.SSLSession;
import javax.net.ssl.TrustManager;
import javax.net.ssl.X509TrustManager;
import ug.co.intelworld.exceptions.IP2GatewayException;

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
     * @throws ug.co.intelworld.exceptions.IP2GatewayException when it fails to
     * bypass SSL verification for tests
     */
    protected static String productionHttpRequest(
            String http_method,
            String url_,
            Map<String, String> headers,
            String entity,
            int read_timeout,
            int connect_timeout) throws MalformedURLException, IOException, IP2GatewayException {

        URL m_url = new URL(url_);

        HttpsURLConnection connection = (HttpsURLConnection) m_url.openConnection();
        connection.setReadTimeout(read_timeout);
        connection.setConnectTimeout(connect_timeout);
        connection.setRequestMethod(http_method);

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
        if (responseCode != 200 || responseCode != 201) {
            is = connection.getErrorStream();
        } else {
            is = connection.getInputStream();
        }
        final String resp = getResponseMessage(is);
        connection.disconnect();
        return resp;

    }

    protected static String sandboxHttpRequest(
            String http_method,
            String url_,
            Map<String, String> headers,
            String entity,
            int read_timeout,
            int connect_timeout) throws MalformedURLException, IOException, IP2GatewayException {

        URL m_url = new URL(url_);

//        System.setProperty("jsse.enableSNIExtension", "false");
//        disableSslVerification();
        //   return postToTestBed(environment, url_, headers, entity, read_timeout, connect_timeout);

        HttpURLConnection connection = (HttpURLConnection) m_url.openConnection();
        connection.setReadTimeout(read_timeout);
        connection.setConnectTimeout(connect_timeout);
        connection.setRequestMethod(http_method);

            if (headers != null && !headers.isEmpty()) {
//                Iterator t = headers.entrySet().iterator();
//
//                while (t.hasNext()) {
//                    Map.Entry entry = (Map.Entry<String, String>) t.next();
//                    connection.setRequestProperty((String) entry.getKey(), (String) entry.getValue());
//                }

            }

            connection.setDoOutput(true);

            if (entity != null) {
                connection.setRequestProperty("Content-Type", "application/json; charset=UTF-8");
                
            try (OutputStream os = connection.getOutputStream()) {
                os.write(entity.getBytes("UTF-8"));
                os.flush();
            }
                
            }
       

        final InputStream is;
        final int responseCode = connection.getResponseCode();
        //connection.disconnect();
        if (responseCode == 200 || responseCode == 201) {
            is = connection.getInputStream();
            
        } else {
            is = connection.getErrorStream();
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

//    private static void disableSslVerification() throws IP2GatewayException {
//        try {
//            // Create a trust manager that does not validate certificate chains
//            TrustManager[] trustAllCerts = new TrustManager[]{new X509TrustManager() {
//                @Override
//                public java.security.cert.X509Certificate[] getAcceptedIssuers() {
//                    return null;
//                }
//
//                @Override
//                public void checkClientTrusted(X509Certificate[] certs, String authType) {
//                }
//
//                @Override
//                public void checkServerTrusted(X509Certificate[] certs, String authType) {
//                }
//            }
//            };
//
//            // Install the all-trusting trust manager
//            SSLContext sc = SSLContext.getInstance("SSL");
//            sc.init(null, trustAllCerts, new java.security.SecureRandom());
//            HttpsURLConnection.setDefaultSSLSocketFactory(sc.getSocketFactory());
//
//            // Create all-trusting host name verifier
//            HostnameVerifier allHostsValid = new HostnameVerifier() {
//                @Override
//                public boolean verify(String hostname, SSLSession session) {
//                    return true;
//                }
//            };
//
//            // Install the all-trusting host verifier
//            HttpsURLConnection.setDefaultHostnameVerifier(allHostsValid);
//        } catch (NoSuchAlgorithmException | KeyManagementException e) {
//            throw new IP2GatewayException("Exception while trying to disable ssl verfication ", e);
//        }
//    }

}

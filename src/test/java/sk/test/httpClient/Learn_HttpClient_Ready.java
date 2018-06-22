package sk.test.httpClient;

import java.io.IOException;
import java.security.cert.CertificateException;
import java.security.cert.X509Certificate;
import java.util.ArrayList;
import java.util.Hashtable;
import java.util.List;

import javax.net.ssl.SSLContext;
import javax.net.ssl.TrustManager;
import javax.net.ssl.X509TrustManager;

import org.apache.http.HttpEntity;
import org.apache.http.NameValuePair;
import org.apache.http.client.ClientProtocolException;
import org.apache.http.client.HttpClient;
import org.apache.http.client.config.RequestConfig;
import org.apache.http.client.entity.UrlEncodedFormEntity;
import org.apache.http.client.methods.CloseableHttpResponse;
import org.apache.http.client.methods.HttpGet;
import org.apache.http.client.methods.HttpPost;
import org.apache.http.conn.ssl.NoopHostnameVerifier;
import org.apache.http.conn.ssl.SSLConnectionSocketFactory;
import org.apache.http.impl.client.CloseableHttpClient;
import org.apache.http.impl.client.HttpClients;
import org.apache.http.message.BasicNameValuePair;
import org.apache.http.util.EntityUtils;
  
/** 
 * @Description httpclient处理http请求和https请求 
 */  
public class Learn_HttpClient_Ready {  
  
    /** 
     * @Description 处理http请求的post方法 
     * @param url 
     *            :url 
     * @param params 
     *            :参数 
     * @return 返回的字符串 
     */  
    public static String post(String url, Hashtable<String, String> params) {  
        CloseableHttpClient httpClient = null;  
        HttpPost httpPost = null;  
        String result = "";  
        try {  
            httpClient = HttpClients.createDefault();  
            RequestConfig requestConfig = RequestConfig.custom()  
                    .setSocketTimeout(4000).setConnectTimeout(4000).build();  
            httpPost = new HttpPost(url);  
            httpPost.setConfig(requestConfig);  
            List<NameValuePair> ps = new ArrayList<NameValuePair>();  
            for (String pKey : params.keySet()) {  
                ps.add(new BasicNameValuePair(pKey, params.get(pKey)));  
            }  
            httpPost.setEntity(new UrlEncodedFormEntity(ps, "utf-8"));  
            CloseableHttpResponse response = httpClient.execute(httpPost);  
            HttpEntity httpEntity = response.getEntity();  
  
            result = EntityUtils.toString(httpEntity, "utf-8");  
  
        } catch (ClientProtocolException e) {  
            result = "";  
        } catch (IOException e) {  
            result = "";  
        } finally {  
            try {  
                if (httpPost != null) {  
                    httpPost.releaseConnection();  
                }  
                if (httpClient != null) {  
                    httpClient.close();  
                }  
            } catch (IOException e) {  
                result = "";  
            }  
        }  
        return result;  
    }  
  
    /** 
     * @Description 处理http请求的get方法 
     * @param url 
     *            :url 
     * @param params 
     *            :参数 
     * @return 返回的字符串 
     */  
    public static String get(String url, Hashtable<String, String> params) {  
        CloseableHttpClient httpClient = null;  
        HttpGet httpGet = null;  
  
        String result = "";  
        try {  
            httpClient = HttpClients.createDefault();  
            RequestConfig requestConfig = RequestConfig.custom()  
                    .setSocketTimeout(4000).setConnectTimeout(4000).build();  
            String ps = "";  
            for (String pKey : params.keySet()) {  
                if (!"".equals(ps)) {  
                    ps = ps + "&";  
                }  
                // 处理特殊字符，%替换成%25，空格替换为%20，#替换为%23  
                String pValue = params.get(pKey).replace("%", "%25")  
                        .replace(" ", "%20").replace("#", "%23");  
                ps += pKey + "=" + pValue;  
            }  
            if (!"".equals(ps)) {  
                url = url + "?" + ps;  
            }  
            httpGet = new HttpGet(url);  
            httpGet.setConfig(requestConfig);  
            CloseableHttpResponse response = httpClient.execute(httpGet);  
            HttpEntity httpEntity = response.getEntity();  
            result = EntityUtils.toString(httpEntity, "utf-8");  
        } catch (ClientProtocolException e) {  
            result = "";  
        } catch (IOException e) {  
            result = "";  
        } catch (Exception e) {  
            result = "";  
        } finally {  
            try {  
                if (httpGet != null) {  
                    httpGet.releaseConnection();  
                }  
                if (httpClient != null) {  
                    httpClient.close();  
                }  
            } catch (IOException e) {  
                result = "";  
            }  
        }  
        return result;  
    }  
  
    /** 
     * @Description 处理https请求的post方法 
     * @param url 
     *            :url 
     * @param params 
     *            :参数 
     * @return 返回的字符串 
     */  
    public static String postSSL(String url, Hashtable<String, String> params) {  
        CloseableHttpClient httpClient = null;  
        HttpPost httpPost = null;  
        String result = "";  
        try {  
            httpClient = (CloseableHttpClient) wrapClient();  
            RequestConfig requestConfig = RequestConfig.custom()  
                    .setSocketTimeout(4000).setConnectTimeout(4000).build();  
            httpPost = new HttpPost(url);  
            httpPost.setConfig(requestConfig);  
            List<NameValuePair> ps = new ArrayList<NameValuePair>();  
            for (String pKey : params.keySet()) {  
                ps.add(new BasicNameValuePair(pKey, params.get(pKey)));  
            }  
            httpPost.setEntity(new UrlEncodedFormEntity(ps, "utf-8"));  
            CloseableHttpResponse response = httpClient.execute(httpPost);  
            HttpEntity httpEntity = response.getEntity();  
  
            result = EntityUtils.toString(httpEntity, "utf-8");  
  
        } catch (ClientProtocolException e) {  
            result = "";  
        } catch (IOException e) {  
            result = "";  
        } finally {  
            try {  
                if (httpPost != null) {  
                    httpPost.releaseConnection();  
                }  
                if (httpClient != null) {  
                    httpClient.close();  
                }  
            } catch (IOException e) {  
                result = "";  
            }  
        }  
        return result;  
    }  
  
    /** 
     * @Description 处理https请求的get方法 
     * @param url 
     *            :url 
     * @param params 
     *            :参数 
     * @return 返回的字符串 
     */  
    public static String getSSL(String url, Hashtable<String, String> params) {  
        CloseableHttpClient httpClient = null;  
        HttpGet httpGet = null;  
  
        String result = "";  
        try {  
            httpClient = (CloseableHttpClient) wrapClient();  
            RequestConfig requestConfig = RequestConfig.custom()  
                    .setSocketTimeout(4000).setConnectTimeout(4000).build();  
            String ps = "";  
            for (String pKey : params.keySet()) {  
                if (!"".equals(ps)) {  
                    ps = ps + "&";  
                }  
                // 处理特殊字符，%替换成%25，空格替换为%20，#替换为%23  
                String pValue = params.get(pKey).replace("%", "%25")  
                        .replace(" ", "%20").replace("#", "%23");  
                ps += pKey + "=" + pValue;  
            }  
            if (!"".equals(ps)) {  
                url = url + "?" + ps;  
            }  
            httpGet = new HttpGet(url);  
            httpGet.setConfig(requestConfig);  
            CloseableHttpResponse response = httpClient.execute(httpGet);  
            HttpEntity httpEntity = response.getEntity();  
            result = EntityUtils.toString(httpEntity, "utf-8");  
        } catch (ClientProtocolException e) {  
            result = "";  
        } catch (IOException e) {  
            result = "";  
        } catch (Exception e) {  
            result = "";  
        } finally {  
            try {  
                if (httpGet != null) {  
                    httpGet.releaseConnection();  
                }  
                if (httpClient != null) {  
                    httpClient.close();  
                }  
            } catch (IOException e) {  
                result = "";  
            }  
        }  
        return result;  
    }  
  
    /** 
     * @Description 处理http请求的post方法（含有大数据的参数） 
     * @param url 
     *            :url 
     * @param params 
     *            :参数 
     * @return 返回的字符串 
     */  
//    public static String postMultipart(String url, Map<String, String> params) {  
//        CloseableHttpClient httpClient = null;  
//        HttpPost httpPost = null;  
//        String result = "";  
//        String fileName = "";  
//        try {  
//            httpClient = HttpClients.createDefault();  
//            RequestConfig requestConfig = RequestConfig.custom()  
//                    .setSocketTimeout(4000).setConnectTimeout(4000).build();  
//            httpPost = new HttpPost(url);  
//            httpPost.setConfig(requestConfig);  
//  
//            List<NameValuePair> ps = new ArrayList<NameValuePair>();  
//            for (String pKey : params.keySet()) {  
//                ps.add(new BasicNameValuePair(pKey, params.get(pKey)));  
//            }  
//  
//            Hashtable<String, File> files = new Hashtable<String, File>();  
//            fileName = ProArgs.WorkPath + params.get("FileName") + ".txt";  
//            String content = params.get("Report");  
//            FileIOMethod.SaveTextFile(fileName, content, "utf-8");  
//            File file = new File(fileName);  
//            files.put("Report", file);  
//            HttpEntity entity = makeMultipartEntity(ps, files);  
//            httpPost.setEntity(entity);  
//            CloseableHttpResponse response = httpClient.execute(httpPost);  
//            HttpEntity httpEntity = response.getEntity();  
//            result = EntityUtils.toString(httpEntity, "utf-8");  
//        } catch (ClientProtocolException e) {  
//            result = "";  
//        } catch (IOException e) {  
//            result = "";  
//        } finally {  
//            FileIOMethod.DelFile(fileName);  
//            try {  
//                if (httpPost != null) {  
//                    httpPost.releaseConnection();  
//                }  
//                if (httpClient != null) {  
//                    httpClient.close();  
//                }  
//            } catch (IOException e) {  
//                result = "";  
//            }  
//        }  
//        return result;  
//    }  
  
    /** 
     * @Description 处理https请求的post方法（含有大数据的参数） 
     * @param url 
     *            :url 
     * @param params 
     *            :参数 
     * @return 返回的字符串 
     */  
//    public static String postMultipartSSL(String url,  
//            Hashtable<String, String> params) {  
//        CloseableHttpClient httpClient = null;  
//        HttpPost httpPost = null;  
//        String result = "";  
//        String fileName = "";  
//        try {  
//            httpClient = (CloseableHttpClient) wrapClient();  
//            RequestConfig requestConfig = RequestConfig.custom()  
//                    .setSocketTimeout(4000).setConnectTimeout(4000).build();  
//            httpPost = new HttpPost(url);  
//            httpPost.setConfig(requestConfig);  
//  
//            List<NameValuePair> ps = new ArrayList<NameValuePair>();  
//            for (String pKey : params.keySet()) {  
//                ps.add(new BasicNameValuePair(pKey, params.get(pKey)));  
//            }  
//  
//            Hashtable<String, File> files = new Hashtable<String, File>();  
//            fileName = ProArgs.WorkPath + params.get("FileName") + ".txt";  
//            String content = params.get("Report");  
//            FileIOMethod.SaveTextFile(fileName, content, "utf-8");  
//            File file = new File(fileName);  
//            files.put("Report", file);  
//            HttpEntity entity = makeMultipartEntity(ps, files);  
//            httpPost.setEntity(entity);  
//            CloseableHttpResponse response = httpClient.execute(httpPost);  
//            HttpEntity httpEntity = response.getEntity();  
//            result = EntityUtils.toString(httpEntity, "utf-8");  
//  
//        } catch (ClientProtocolException e) {  
//            result = "";  
//        } catch (IOException e) {  
//            result = "";  
//        } finally {  
//            FileIOMethod.DelFile(fileName);  
//            try {  
//                if (httpPost != null) {  
//                    httpPost.releaseConnection();  
//                }  
//                if (httpClient != null) {  
//                    httpClient.close();  
//                }  
//            } catch (IOException e) {  
//                result = "";  
//            }  
//        }  
//        return result;  
//    }  
  
    /** 
     * @Description 创建一个不进行正式验证的请求客户端对象 不用导入SSL证书 
     * @return HttpClient 
     */  
    public static HttpClient wrapClient() {  
        try {  
            SSLContext ctx = SSLContext.getInstance("TLS");  
            X509TrustManager tm = new X509TrustManager() {  
                public X509Certificate[] getAcceptedIssuers() {  
                    return null;  
                }  
  
                public void checkClientTrusted(X509Certificate[] arg0,  
                        String arg1) throws CertificateException {  
                }  
  
                public void checkServerTrusted(X509Certificate[] arg0,  
                        String arg1) throws CertificateException {  
                }  
            };  
            ctx.init(null, new TrustManager[] { tm }, null);  
            SSLConnectionSocketFactory ssf = new SSLConnectionSocketFactory(  
                    ctx, NoopHostnameVerifier.INSTANCE);  
            CloseableHttpClient httpclient = HttpClients.custom()  
                    .setSSLSocketFactory(ssf).build();  
            return httpclient;  
        } catch (Exception e) {  
            return HttpClients.createDefault();  
        }  
    }  
  
    /** 
     * @Description 创建一个HTTPEntity对象 
     * @param params 
     *            :字符串的入参 
     * @param files 
     *            :大数据的入参 
     * @return HttpClient 
     */  
//    public static HttpEntity makeMultipartEntity(List<NameValuePair> params,  
//            Map<String, File> files) {  
//  
//        MultipartEntityBuilder builder = MultipartEntityBuilder.create();  
//        builder.setMode(HttpMultipartMode.BROWSER_COMPATIBLE); // 如果有SocketTimeoutException等情况，可修改这个枚举  
//        if (params != null && params.size() > 0) {  
//            for (NameValuePair p : params) {  
//                builder.addTextBody(p.getName(), p.getValue(),  
//                        ContentType.TEXT_PLAIN.withCharset("UTF-8"));  
//            }  
//        }  
//  
//        if (files != null && files.size() > 0) {  
//            Set<Entry<String, File>> entries = files.entrySet();  
//            for (Entry<String, File> entry : entries) {  
//                builder.addPart(entry.getKey(), new FileBody(entry.getValue()));  
//            }  
//        }  
//  
//        return builder.build();  
//  
//    }  
  
    /** 
     * @param args 
     */  
    public static void main(String[] args) {  
        // TODO Auto-generated method stub  
  
        String url = "";  
        Hashtable<String, String> params = new Hashtable<String, String>();  
        params.put("username", "123");  
        params.put("password", "123");  
        String a = postSSL(url, params);  
        System.out.println("值为：" + a);  
  
  
  
          
    }  
  
}  
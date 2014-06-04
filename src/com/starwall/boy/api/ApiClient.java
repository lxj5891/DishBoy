package com.starwall.boy.api;


import com.starwall.boy.AppContext;
import com.starwall.boy.AppException;
import com.starwall.boy.bean.DeskList;
import com.starwall.boy.bean.URLs;
import com.starwall.boy.bean.User;
import org.apache.commons.httpclient.*;
import org.apache.commons.httpclient.cookie.CookiePolicy;
import org.apache.commons.httpclient.methods.GetMethod;
import org.apache.commons.httpclient.methods.PostMethod;
import org.apache.commons.httpclient.methods.multipart.FilePart;
import org.apache.commons.httpclient.methods.multipart.MultipartRequestEntity;
import org.apache.commons.httpclient.methods.multipart.Part;
import org.apache.commons.httpclient.methods.multipart.StringPart;
import org.apache.commons.httpclient.params.HttpMethodParams;
import org.json.JSONException;

import java.io.*;
import java.util.HashMap;
import java.util.Map;

/**
 * Created by mac on 14-5-31.
 */
public class ApiClient {

    public static final String UTF_8 = "UTF-8";
    public static final String DESC = "descend";
    public static final String ASC = "ascend";

    private final static int TIMEOUT_CONNECTION = 20000;
    private final static int TIMEOUT_SOCKET = 20000;
    private final static int RETRY_TIME = 3;

    private static String appCookie;
    private static String appUserAgent;

    private static String getCookie(AppContext appContext) {

        if (appCookie == null || appCookie == "") {
            appCookie = appContext.getProperty("cookie");
        }
        return appCookie;
    }

    private static String getUserAgent(AppContext appContext) {

        if (appUserAgent == null || appUserAgent == "") {
            StringBuilder ua = new StringBuilder("starwall.org");
            ua.append('/' + appContext.getPackageInfo().versionName + '_' + appContext.getPackageInfo().versionCode);//App版本
            ua.append("/Android");//手机系统平台
            ua.append("/" + android.os.Build.VERSION.RELEASE);//手机系统版本
            ua.append("/" + android.os.Build.MODEL); //手机型号
            ua.append("/" + appContext.getAppId());//客户端唯一标识
            appUserAgent = ua.toString();
        }
        return appUserAgent;
    }

    public static void cleanCookie() {
        appCookie = "";
    }


    private static HttpClient getHttpClient() {

        HttpClient httpClient = new HttpClient();
        // 设置 HttpClient 接收 Cookie,用与浏览器一样的策略
        httpClient.getParams().setCookiePolicy(CookiePolicy.BROWSER_COMPATIBILITY);
        // 设置 默认的超时重试处理策略
        httpClient.getParams().setParameter(HttpMethodParams.RETRY_HANDLER, new DefaultHttpMethodRetryHandler());
        // 设置 连接超时时间
        httpClient.getHttpConnectionManager().getParams().setConnectionTimeout(TIMEOUT_CONNECTION);
        // 设置 读数据超时时间
        httpClient.getHttpConnectionManager().getParams().setSoTimeout(TIMEOUT_SOCKET);
        // 设置 字符集
        httpClient.getParams().setContentCharset(UTF_8);
        return httpClient;
    }


    private static GetMethod getHttpGet(String url, String cookie, String userAgent) {
        GetMethod httpGet = new GetMethod(url);
        // 设置 请求超时时间
        httpGet.getParams().setSoTimeout(TIMEOUT_SOCKET);
        httpGet.setRequestHeader("Host", URLs.HOST);
        httpGet.setRequestHeader("Connection", "Keep-Alive");
        httpGet.setRequestHeader("Cookie", cookie);
        httpGet.setRequestHeader("User-Agent", userAgent);
        return httpGet;
    }

    private static PostMethod getHttpPost(String url, String cookie, String userAgent) {
        PostMethod httpPost = new PostMethod(url);
        // 设置 请求超时时间
        httpPost.getParams().setSoTimeout(TIMEOUT_SOCKET);
        httpPost.setRequestHeader("Host", URLs.HOST);
        httpPost.setRequestHeader("Connection", "Keep-Alive");
        httpPost.setRequestHeader("Cookie", cookie);
        httpPost.setRequestHeader("User-Agent", userAgent);
        return httpPost;
    }

    private static String _MakeURL(String p_url, Map<String, Object> params) {
        StringBuilder url = new StringBuilder(p_url);
        if (url.indexOf("?") < 0)
            url.append('?');

        for (String name : params.keySet()) {
            url.append('&');
            url.append(name);
            url.append('=');
            url.append(String.valueOf(params.get(name)));
            //不做URLEncoder处理
            //url.append(URLEncoder.encode(String.valueOf(params.get(name)), UTF_8));
        }

        return url.toString().replace("?&", "?");
    }

    /**
     * get请求URL
     *
     * @param url
     * @throws AppException
     */
    private static InputStream http_get(AppContext appContext, String url) throws AppException {
        String cookie = getCookie(appContext);
        String userAgent = getUserAgent(appContext);

        HttpClient httpClient = null;
        GetMethod httpGet = null;

        String responseBody = "";
        int time = 0;

        do {
            try {
                httpClient = getHttpClient();
                httpGet = getHttpGet(url, cookie, userAgent);

                int statusCode = httpClient.executeMethod(httpGet);

                if (statusCode != HttpStatus.SC_OK) {

                    throw AppException.http(statusCode);
                }

                Cookie[] cookies = httpClient.getState().getCookies();
                String tmpcookies = "";

                for (Cookie ck : cookies) {

                    tmpcookies += ck.toString() + ";";
                }

                //保存cookie
                if (appContext != null && tmpcookies != "") {

                    appContext.setProperty("cookie", tmpcookies);
                    appCookie = tmpcookies;
                }

                responseBody = httpGet.getResponseBodyAsString();
                break;

            } catch (HttpException e) {

                time++;

                if (time < RETRY_TIME) {

                    try {
                        Thread.sleep(1000);
                    } catch (InterruptedException e1) {
                    }
                    continue;
                }
                // 发生致命的异常，可能是协议不对或者返回的内容有问题
                e.printStackTrace();
                throw AppException.http(e);
            } catch (IOException e) {

                time++;
                if (time < RETRY_TIME) {
                    try {
                        Thread.sleep(1000);
                    } catch (InterruptedException e1) {
                    }
                    continue;
                }
                // 发生网络异常
                e.printStackTrace();
                throw AppException.network(e);
            } finally {
                // 释放连接
                httpGet.releaseConnection();
                httpClient = null;
            }
        } while (time < RETRY_TIME);


        return new ByteArrayInputStream(responseBody.getBytes());
    }


    /**
     * 公用post方法
     *
     * @param url
     * @param params
     * @param files
     * @throws AppException
     */
    private static InputStream _post(AppContext appContext, String url, Map<String, Object> params, Map<String, File> files) throws AppException {
        //System.out.println("post_url==> "+url);
        String cookie = getCookie(appContext);
        String userAgent = getUserAgent(appContext);

        HttpClient httpClient = null;
        PostMethod httpPost = null;

        //post表单参数处理
        int length = (params == null ? 0 : params.size()) + (files == null ? 0 : files.size());
        Part[] parts = new Part[length];
        int i = 0;

        if (params != null) {

            for (String name : params.keySet()) {
                parts[i++] = new StringPart(name, String.valueOf(params.get(name)), UTF_8);
                //System.out.println("post_key==> "+name+"    value==>"+String.valueOf(params.get(name)));
            }
        }

        if (files != null) {

            for (String file : files.keySet()) {

                try {
                    parts[i++] = new FilePart(file, files.get(file));
                } catch (FileNotFoundException e) {
                    e.printStackTrace();
                }
                //System.out.println("post_key_file==> "+file);
            }
        }


        String responseBody = "";
        int time = 0;
        do {
            try {

                httpClient = getHttpClient();
                httpPost = getHttpPost(url, cookie, userAgent);
                httpPost.setRequestEntity(new MultipartRequestEntity(parts, httpPost.getParams()));
                int statusCode = httpClient.executeMethod(httpPost);
                if (statusCode != HttpStatus.SC_OK) {

                    throw AppException.http(statusCode);
                } else if (statusCode == HttpStatus.SC_OK) {

                    Cookie[] cookies = httpClient.getState().getCookies();
                    String tmpcookies = "";

                    for (Cookie ck : cookies) {

                        tmpcookies += ck.toString() + ";";
                    }
                    //保存cookie
                    if (appContext != null && tmpcookies != "") {

                        appContext.setProperty("cookie", tmpcookies);
                        appCookie = tmpcookies;
                    }
                }
                responseBody = httpPost.getResponseBodyAsString();
                //System.out.println("XMLDATA=====>"+responseBody);
                break;
            } catch (HttpException e) {
                time++;
                if (time < RETRY_TIME) {

                    try {
                        Thread.sleep(1000);
                    } catch (InterruptedException e1) {
                    }
                    continue;
                }
                // 发生致命的异常，可能是协议不对或者返回的内容有问题
                e.printStackTrace();
                throw AppException.http(e);
            } catch (IOException e) {
                time++;
                if (time < RETRY_TIME) {
                    try {
                        Thread.sleep(1000);
                    } catch (InterruptedException e1) {
                    }
                    continue;
                }
                // 发生网络异常
                e.printStackTrace();
                throw AppException.network(e);
            } finally {
                // 释放连接
                httpPost.releaseConnection();
                httpClient = null;
            }
        } while (time < RETRY_TIME);

        return new ByteArrayInputStream(responseBody.getBytes());
    }


    public static User login(AppContext appContext, final String name, final String password) throws AppException {
        String loginUrl = _MakeURL(URLs.LOGIN_VALIDATE_HTTP, new HashMap<String, Object>() {{
            put("name", name);
            put("password", password);
        }});

        try {

            return User.parse(http_get(appContext, loginUrl));
        } catch (Exception e) {

            if (e instanceof AppException)
                throw (AppException) e;

            throw AppException.network(e);
        }
    }

    public static DeskList getDeskList(AppContext appContext) throws AppException {

        try {

            return DeskList.parse(http_get(appContext, URLs.API_DESK_LIST));
        } catch (IOException e) {

            e.printStackTrace();
            throw AppException.network(e);
        } catch (JSONException e) {

            e.printStackTrace();
            throw AppException.network(e);
        }
    }
}

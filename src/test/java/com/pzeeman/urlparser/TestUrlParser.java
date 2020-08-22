package com.pzeeman.urlparser;


import org.junit.Assert;
import org.junit.Test;
import org.mockito.Mock;

import java.net.MalformedURLException;
import java.util.Arrays;
import java.util.Iterator;

public class TestUrlParser {

    final String protocolHttps = "https";
    final String usernamePzeeman = "pzeeman";
    final String passwordl33t = "PA55w0rd";
    final String hostwww_Pzeeman_com = "www.pzeeman.com";
    final int port2112 = 2112;
    final String pathTest = "this/is/a/test";
    final String queryParamProject = "project=Url";
    final String queryParamTest = "test";
    final String refValidUrl = "validUrl";


    UrlParser testUrlParser = new UrlParser();

    @Test
    public void parseUrl_Complete_ExpectSuccess() throws MalformedURLException {
        String validUrl = buildUrl(protocolHttps,usernamePzeeman,passwordl33t,hostwww_Pzeeman_com,port2112,pathTest,refValidUrl,queryParamProject,queryParamTest);

        ParsedUrl parsedUrl = testUrlParser.getParsedUrl(validUrl);

        Assert.assertEquals(protocolHttps, parsedUrl.getProtocol());
        Assert.assertEquals(usernamePzeeman, parsedUrl.getUsername());
        Assert.assertEquals(passwordl33t, parsedUrl.getPassword());
        Assert.assertEquals(hostwww_Pzeeman_com, parsedUrl.getHost());
        Assert.assertEquals(port2112, parsedUrl.getPort());
        Assert.assertEquals(pathTest, parsedUrl.getPath().substring(1));
        Assert.assertEquals(2,parsedUrl.getQueryParameters().size());
        // Need a test for the values in the query parameters map
        Assert.assertEquals(refValidUrl, parsedUrl.getFragment());
    }

    @Test
    public void parseUrl_NoPort_ExpectDefault() throws MalformedURLException {
        String validUrl = buildUrl(protocolHttps,usernamePzeeman,passwordl33t,hostwww_Pzeeman_com,0,pathTest,refValidUrl,queryParamProject,queryParamTest);

        ParsedUrl parsedUrl = testUrlParser.getParsedUrl(validUrl);

        Assert.assertEquals(protocolHttps, parsedUrl.getProtocol());
        Assert.assertEquals(usernamePzeeman, parsedUrl.getUsername());
        Assert.assertEquals(passwordl33t, parsedUrl.getPassword());
        Assert.assertEquals(hostwww_Pzeeman_com, parsedUrl.getHost());
        Assert.assertEquals(80, parsedUrl.getPort());
        Assert.assertEquals(pathTest, parsedUrl.getPath().substring(1));
        Assert.assertEquals(2,parsedUrl.getQueryParameters().size());
        // Need a test for the values in the query parameters map
        Assert.assertEquals(refValidUrl, parsedUrl.getFragment());
    }
    @Test
    public void parseUrl_NoPassword() throws MalformedURLException {
        String validUrl = buildUrl(protocolHttps,usernamePzeeman,null,hostwww_Pzeeman_com,port2112,pathTest,refValidUrl,queryParamProject,queryParamTest);

        ParsedUrl parsedUrl = testUrlParser.getParsedUrl(validUrl);

        Assert.assertEquals(protocolHttps, parsedUrl.getProtocol());
        Assert.assertEquals(usernamePzeeman, parsedUrl.getUsername());
        Assert.assertEquals(0, parsedUrl.getPassword().length());
        Assert.assertEquals(hostwww_Pzeeman_com, parsedUrl.getHost());
        Assert.assertEquals(port2112, parsedUrl.getPort());
        Assert.assertEquals(pathTest, parsedUrl.getPath().substring(1));
        Assert.assertEquals(2,parsedUrl.getQueryParameters().size());
        // Need a test for the values in the query parameters map
        Assert.assertEquals(refValidUrl, parsedUrl.getFragment());
    }

    @Test
    public void parseUrl_NoUsername() throws MalformedURLException {
        String validUrl = buildUrl(protocolHttps,null,passwordl33t,hostwww_Pzeeman_com,port2112,pathTest,refValidUrl,queryParamProject,queryParamTest);

        ParsedUrl parsedUrl = testUrlParser.getParsedUrl(validUrl);

        Assert.assertEquals(protocolHttps, parsedUrl.getProtocol());
        Assert.assertEquals(null, parsedUrl.getUsername());
        Assert.assertEquals(null, parsedUrl.getPassword());
        Assert.assertEquals(hostwww_Pzeeman_com, parsedUrl.getHost());
        Assert.assertEquals(port2112, parsedUrl.getPort());
        Assert.assertEquals(pathTest, parsedUrl.getPath().substring(1));
        Assert.assertEquals(2,parsedUrl.getQueryParameters().size());
        // Need a test for the values in the query parameters map
        Assert.assertEquals(refValidUrl, parsedUrl.getFragment());
    }
    @Test
    public void parseUrl_NoPath() throws MalformedURLException {
        String validUrl = buildUrl(protocolHttps,usernamePzeeman,passwordl33t,hostwww_Pzeeman_com,port2112,null,refValidUrl,queryParamProject,queryParamTest);

        ParsedUrl parsedUrl = testUrlParser.getParsedUrl(validUrl);

        Assert.assertEquals(protocolHttps, parsedUrl.getProtocol());
        Assert.assertEquals(usernamePzeeman, parsedUrl.getUsername());
        Assert.assertEquals(passwordl33t, parsedUrl.getPassword());
        Assert.assertEquals(hostwww_Pzeeman_com, parsedUrl.getHost());
        Assert.assertEquals(port2112, parsedUrl.getPort());
        Assert.assertEquals(0, parsedUrl.getPath().substring(1).length());
        Assert.assertEquals(2,parsedUrl.getQueryParameters().size());
        // Need a test for the values in the query parameters map
        Assert.assertEquals(refValidUrl, parsedUrl.getFragment());
    }

    @Test
    public void parseUrl_NoFragment() throws MalformedURLException {
        String validUrl = buildUrl(protocolHttps,usernamePzeeman,passwordl33t,hostwww_Pzeeman_com,port2112,pathTest,null,queryParamProject,queryParamTest);

        ParsedUrl parsedUrl = testUrlParser.getParsedUrl(validUrl);

        Assert.assertEquals(protocolHttps, parsedUrl.getProtocol());
        Assert.assertEquals(usernamePzeeman, parsedUrl.getUsername());
        Assert.assertEquals(passwordl33t, parsedUrl.getPassword());
        Assert.assertEquals(hostwww_Pzeeman_com, parsedUrl.getHost());
        Assert.assertEquals(port2112, parsedUrl.getPort());
        Assert.assertEquals(pathTest, parsedUrl.getPath().substring(1));
        Assert.assertEquals(2,parsedUrl.getQueryParameters().size());
        // Need a test for the values in the query parameters map
        Assert.assertEquals(null, parsedUrl.getFragment());
    }

    @Test
    public void parseUrl_NoQueryParams() throws MalformedURLException {
        String validUrl = buildUrl(protocolHttps,usernamePzeeman,passwordl33t,hostwww_Pzeeman_com,port2112,pathTest,refValidUrl,null);

        ParsedUrl parsedUrl = testUrlParser.getParsedUrl(validUrl);

        Assert.assertEquals(protocolHttps, parsedUrl.getProtocol());
        Assert.assertEquals(usernamePzeeman, parsedUrl.getUsername());
        Assert.assertEquals(passwordl33t, parsedUrl.getPassword());
        Assert.assertEquals(hostwww_Pzeeman_com, parsedUrl.getHost());
        Assert.assertEquals(port2112, parsedUrl.getPort());
        Assert.assertEquals(pathTest, parsedUrl.getPath().substring(1));
        Assert.assertEquals(0,parsedUrl.getQueryParameters().size());
        // Need a test for the values in the query parameters map
        Assert.assertEquals(refValidUrl, parsedUrl.getFragment());
    }

    @Test
    public void parseUrl_OneQueryParam_NoValue() throws MalformedURLException {
        String validUrl = buildUrl(protocolHttps,usernamePzeeman,passwordl33t,hostwww_Pzeeman_com,port2112,pathTest,refValidUrl,queryParamTest);

        ParsedUrl parsedUrl = testUrlParser.getParsedUrl(validUrl);

        Assert.assertEquals(protocolHttps, parsedUrl.getProtocol());
        Assert.assertEquals(usernamePzeeman, parsedUrl.getUsername());
        Assert.assertEquals(passwordl33t, parsedUrl.getPassword());
        Assert.assertEquals(hostwww_Pzeeman_com, parsedUrl.getHost());
        Assert.assertEquals(port2112, parsedUrl.getPort());
        Assert.assertEquals(pathTest, parsedUrl.getPath().substring(1));
        Assert.assertEquals(1,parsedUrl.getQueryParameters().size());
        // Need a test for the values in the query parameters map
        Assert.assertEquals(refValidUrl, parsedUrl.getFragment());
    }

    @Test
    public void parseUrl_OneQueryParam() throws MalformedURLException {
        String validUrl = buildUrl(protocolHttps,usernamePzeeman,passwordl33t,hostwww_Pzeeman_com,port2112,pathTest,refValidUrl,queryParamProject);

        ParsedUrl parsedUrl = testUrlParser.getParsedUrl(validUrl);

        Assert.assertEquals(protocolHttps, parsedUrl.getProtocol());
        Assert.assertEquals(usernamePzeeman, parsedUrl.getUsername());
        Assert.assertEquals(passwordl33t, parsedUrl.getPassword());
        Assert.assertEquals(hostwww_Pzeeman_com, parsedUrl.getHost());
        Assert.assertEquals(port2112, parsedUrl.getPort());
        Assert.assertEquals(pathTest, parsedUrl.getPath().substring(1));
        Assert.assertEquals(1,parsedUrl.getQueryParameters().size());
        // Need a test for the values in the query parameters map
        Assert.assertEquals(refValidUrl, parsedUrl.getFragment());
    }

    @Test(expected = MalformedURLException.class)
    public void parseUrl_Malformed() throws MalformedURLException {
        ParsedUrl parsedUrl = testUrlParser.getParsedUrl("http://1.2.3.4.5.6.7:h");
    }

    private String buildUrl(String protocol,
                            String username,
                            String password,
                            String host,
                            int port,
                            String path,
                            String ref,
                            String... queryParams) {
        String returnUrl = new String();

        StringBuilder urlStringBuilder = new StringBuilder(protocol+"://");
        if (username != null) {
            urlStringBuilder.append(username);
            if (password != null)
                urlStringBuilder.append(":" + password);
            urlStringBuilder.append("@");
        }
        urlStringBuilder.append(host);
        urlStringBuilder.append(":"+port);
        urlStringBuilder.append("/");
        if (path != null)
            urlStringBuilder.append(path);
        if (queryParams != null) {
            urlStringBuilder.append("?");
            Iterator<String> paramIterator = Arrays.asList(queryParams).iterator();
            while (paramIterator.hasNext()) {
                urlStringBuilder.append(paramIterator.next());
                if (paramIterator.hasNext())
                    urlStringBuilder.append("&");
            }
        }
        if (ref != null)
            urlStringBuilder.append("#"+ref);

        returnUrl = urlStringBuilder.toString();

        return returnUrl;
    }
}

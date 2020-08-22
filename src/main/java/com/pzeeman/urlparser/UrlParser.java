package com.pzeeman.urlparser;

import com.google.common.base.Splitter;

import java.net.MalformedURLException;
import java.net.URL;
import java.util.HashMap;
import java.util.Map;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class UrlParser {

    public ParsedUrl getParsedUrl(String inputUrl) throws MalformedURLException {
        ParsedUrl returnParsedUrl = new ParsedUrl();

        URL url = new URL(inputUrl);

        returnParsedUrl.setProtocol(url.getProtocol());
        if (url.getUserInfo() != null) {
            returnParsedUrl.setUsername(getUsernameFromUserInfo(url.getUserInfo()));
            returnParsedUrl.setPassword(getPasswordFromUserInfo(url.getUserInfo()));
        }
        returnParsedUrl.setHost(url.getHost());
        int port = url.getPort();
        if (url.getPort() == -1)
            port = 80;
        returnParsedUrl.setPort(port);
        returnParsedUrl.setPath(url.getPath());
        returnParsedUrl.setQueryParameters(getParamaterMapFromQuery(url.getQuery()));
        returnParsedUrl.setFragment(url.getRef());

        return returnParsedUrl;
    }

    private String getUsernameFromUserInfo(String userInfo) {
        String username = new String();

            Pattern usernamePattern = Pattern.compile("^([^:]+)");
            Matcher usernameMatcher = usernamePattern.matcher(userInfo);

            if (usernameMatcher.find()) {
                username = usernameMatcher.group(1);
            } else {
                System.out.println("No username in " + userInfo);
            }

        return username;
    }

    private String getPasswordFromUserInfo(String userInfo) {
        String password = new String();

            Pattern passwordPattern = Pattern.compile(":([^:]+)");
            Matcher passwordMatcher = passwordPattern.matcher(userInfo);

            if (passwordMatcher.find()) {
                password = passwordMatcher.group(1);
            } else {
                System.out.println("No password in " + userInfo);
            }

        return password;
    }

    private Map<String, String> getParamaterMapFromQuery(String query) {
        Map<String, String> parameterMap = new HashMap<String,String>();

        if (query != null) {
            String[] queryParams = query.split("&");
            for (int i = 0; i < queryParams.length; i++) {
                String param = queryParams[i];
                if (param.contains("=")) {
                    parameterMap.put(param.split("=")[0], param.split("=")[1]);
                } else
                    parameterMap.put(param, "");
            }
        }
        return parameterMap;
    }
}

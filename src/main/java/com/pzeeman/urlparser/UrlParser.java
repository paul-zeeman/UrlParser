package com.pzeeman.urlparser;

import java.net.MalformedURLException;
import java.net.URL;
import java.util.HashMap;
import java.util.Map;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

/**
 * Used to parse URLs.
 */
public class UrlParser {

    /**
     * Accepts a String object and attempts to parse that string using RFC 3986.
     * See https://tools.ietf.org/html/rfc3986
     * Returns a ParsedUrl object that can be used to simply retrieve the elements of the URL
     *
     * @param inputUrl a String in the expected format of scheme://authority/path?query#fragment
     * @return a ParsedUrl object containing the elements of the URL
     * @throws MalformedURLException When a required part of the URL is missing or misformatted
     */
    public ParsedUrl getParsedUrl(String inputUrl) throws MalformedURLException {
        ParsedUrl returnParsedUrl = new ParsedUrl();

        // We're going to leverage the java.net.URL class to some of the heavy lifting
        URL url = new URL(inputUrl);

        returnParsedUrl.setScheme(url.getProtocol());
        // If there is user info, separate out the username and password
        if (url.getUserInfo() != null) {
            returnParsedUrl.setUsername(getUsernameFromUserInfo(url.getUserInfo()));
            returnParsedUrl.setPassword(getPasswordFromUserInfo(url.getUserInfo()));
        }
        String host = url.getHost();
        if (isValidHost(host))
           returnParsedUrl.setHost(url.getHost());
        else
            throw new MalformedURLException("Host is not valid - Must be a valid IPv4 address or FQDN");
        int port = url.getPort();
        // If no port was provided in the input string, we can assume the default value of 80
        if (url.getPort() < 1)
            // For reasons I don't understand, the default port sometimes doesn't work
            port = url.getDefaultPort();
        returnParsedUrl.setPort(port);

        String authority = url.getUserInfo()+"@"+url.getHost()+":"+url.getPort();
        returnParsedUrl.setAuthority(authority);

        returnParsedUrl.setPath(url.getPath());
        returnParsedUrl.setQuery(url.getQuery());
        // Let's break out the query parameters into a map
        returnParsedUrl.setQueryParameters(getParamaterMapFromQuery(url.getQuery()));
        returnParsedUrl.setFragment(url.getRef());

        return returnParsedUrl;
    }

    private String getUsernameFromUserInfo(String userInfo) {
        String username = new String();

        //Using a Regular Expression, pull out the username
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

        //Using a Regular Expression, pull out the password

        Pattern passwordPattern = Pattern.compile(":([^:]+)");
        Matcher passwordMatcher = passwordPattern.matcher(userInfo);

        if (passwordMatcher.find()) {
            password = passwordMatcher.group(1);
        } else {
            System.out.println("No password in " + userInfo);
            // Address the case where if there is a username and not a password, it comes back as an empty string
            // Make it null for consistency
            password = null;
        }

        return password;
    }

    private Map<String, String> getParamaterMapFromQuery(String query) {
        Map<String, String> parameterMap = new HashMap<String,String>();

        if (query != null) {
            String[] queryParams = query.split("&");
            for (int i = 0; i < queryParams.length; i++) {
                String param = queryParams[i];
                // It is possible to have a query parameter that has no value.  This needs to be accounted for.
                if (param.contains("=")) {
                    parameterMap.put(param.split("=")[0], param.split("=")[1]);
                } else
                    parameterMap.put(param, "");
            }
        }
        return parameterMap;
    }

    private boolean isValidHost(String host) {
        boolean isValid = true;

        String[] hostSections = host.split("[.]");

        try {
            Integer.parseInt(hostSections[0]);
            if (hostSections.length == 4) {
                for (String dec_octet : hostSections) {
                    if (Integer.getInteger(dec_octet) == null ||
                            Integer.getInteger(dec_octet) < 0 ||
                            Integer.getInteger(dec_octet) > 255) {
                        isValid = false;
                    } //endif is a valid number
                } //endfor each section
            } else { // there are more or less than 4 sections
                isValid = false;
            } //endelse there are more or less than 4 sections
        } catch (NumberFormatException nfe) { // the host starts with not a number
            if (hostSections.length > 1) {
                for (String domainName : hostSections) {
                    try {
                        Integer.parseInt(domainName);
                        isValid = false;
                    } catch (NumberFormatException nfe2) {
                        // So far it's valid!
                    }
                } // end for each section
            } else { // There's only one section to this URL
                isValid = false;
            } //endelse
        } // does the host start with a number?
        return isValid;
    }
}

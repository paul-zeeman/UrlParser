package com.pzeeman.urlparser;

import lombok.AccessLevel;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.util.Map;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

@NoArgsConstructor
@Setter(AccessLevel.PROTECTED)
@Getter
public class ParsedUrl {

    String protocol;
    String username;
     String password;
     String host;
     int port;
     String path;
     Map<String, String> queryParameters;
     String fragment;

}

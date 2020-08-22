package com.pzeeman.urlparser;

import lombok.AccessLevel;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.util.Map;

/**
 * The ParsedUrl class provides public getters through Lombok annotations
 *
 * A no argument constructor is provided through Lombok annotations
 *
 * Setters are Protected and provided through Lombok annotations
 *
 * getProtocol - String - Returns the protocol parsed from the input URL - REQUIRED
 * getAuthority - String Returns the Authority portion (username+password+host+port) of the URL - REQUIRED
 * getUsername - String - Returns the username parsed from the input URL - OPTIONAL
 *    value is null if no username was provided
 * getPassword - String - Returns the password parsed from the input URL - OPTIONAL
 *    value is null if no password was provided
 * getHost - String - Returns the host parsed from the input URL - REQUIRED
 * getPort - int - Returns the port parsed from the input URL - OPTIONAL
 *    value is the default port for the protocol if no port was provided
 * getPath - String - Returns the path parsed from the input URL - OPTIONAL
 *    value is null if no path was provided
 * getQueryParameters - Map<String, String> - Returns the query parameters parsed from the input URL - OPTIONAL
 *    value is null if no query was provided
 *    note: A query key can be present with a null value
 * getFragment - String - Returns the document reference parsed from the input URL - OPTIONAL
 *    value is null if no fragment was provided
 *
 */
@NoArgsConstructor
@Setter(AccessLevel.PROTECTED)
@Getter
public class ParsedUrl {

    String scheme;
    String authority;
    String username;
     String password;
     String host;
     int port;
     String path;
     String query;
     Map<String, String> queryParameters;
     String fragment;
}

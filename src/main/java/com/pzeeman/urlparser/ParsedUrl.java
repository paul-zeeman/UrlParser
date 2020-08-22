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
 * getUsername - String - Returns the username parsed from the input URL - OPTIONAL
 * getPassword - String - Returns the password parsed from the input URL - OPTIONAL
 * getHost - String - Returns the host parsed from the input URL - REQUIRED
 * getPort - int - Returns the port parsed from the input URL - OPTIONAL/DEFAULT
 * getPath - String - Returns the path parsed from the input URL - OPTIONAL
 * getQueryParameters - Map<String, String> - Returns the query parameters parsed from the input URL - OPTIONAL
 *    note: A query key can be present with a null value
 * getRef - String - Returns the document reference parsed from the input URL - OPTIONAL
 *
 */
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
     String ref;
}

# UrlParser
A basic URL parsing library implemented in Java 8

# Documentation

## `public class UrlParser`

Used to parse URLs.

## `public ParsedUrl getParsedUrl(String inputUrl) throws MalformedURLException`

Accepts a String object and attempts to parse that string using RFC 3986. See https://tools.ietf.org/html/rfc3986 Returns a ParsedUrl object that can be used to simply retrieve the elements of the URL

 * **Parameters:** `inputUrl` — a String in the expected format of scheme://authority/path?query#fragment
 * **Returns:** a ParsedUrl object containing the elements of the URL
 * **Exceptions:** `MalformedURLException` — When a required part of the URL is missing or misformatted
 
 # Documentation

## public class ParsedUrl

An object for accessing the components of a parsed URL

The ParsedUrl class provides public getters through Lombok annotations

A no argument constructor is provided through Lombok annotations

Setters are Protected and provided through Lombok annotations

* getProtocol - String - Returns the protocol parsed from the input URL - REQUIRED
* getAuthority - String Returns the Authority portion (username+password+host+port) of the URL - REQUIRED 
* getUsername - String - Returns the username parsed from the input URL - OPTIONAL 
  value is null if no username was provided 
* getPassword - String - Returns the password parsed from the input URL - OPTIONAL 
  value is null if no password was provided 
* getHost - String - Returns the host parsed from the input URL - REQUIRED 
* getPort - int - Returns the port parsed from the input URL - OPTIONAL 
  value is the default port for the protocol if no port was provided 
* getPath - String - Returns the path parsed from the input URL with no leading '/' - OPTIONAL 
  value is empty string if no path was provided 
* getQuery - String - Returns the complete query portion of the input URL - OPTIONAL 
  value is null if no query was provided 
* getQueryParameters - Map<String, String> - Returns the query parameters parsed from the input URL - OPTIONAL 
  value is an empty map if no query was provided 
  note: A query key can be present with a null value 
* getFragment - String - Returns the document reference parsed from the input URL - OPTIONAL 
  value is null if no fragment was provided

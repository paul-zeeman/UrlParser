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

# hypdf4j

## Overview
Simple [HyPDF](https://addons.heroku.com/hypdf) client for Java

## Dependency
This library requires following jars.

- http: [HttpComponents](http://hc.apache.org/)
- json: [Jackson](https://github.com/FasterXML/jackson) or [Google gson](https://code.google.com/p/google-gson/)

### Notice of SSL
HyPDF uses CA certificate of StartSSL.  
However this root CA certificate isn't included in the default keystore of JDK.

Therefore, to use this library, you must do one of the following.

- Install StartSSL root certificate to your keystore.(RECOMMEND)
- Set true to jp.co.flect.hypdf.transport.TransportFactory#setAlwaysIgnoreHostNameVerification.(Test environment)

See.
https://github.com/haron/startssl-java

JavaDoc
-------
http://oss.flect.co.jp/apidocs/hypdf4j/index.html

Usage
-----
See.
https://github.com/shunjikonishi/hypdf4j/blob/master/src/test/java/jp/co/flect/hypdf/HyPDFTest.java

License
-------
MIT


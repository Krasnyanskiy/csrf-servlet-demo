# Double Submit Cookies Pattern Demo

A simple web app which demonstrates how to protect your site from CSRF attacks with [Double Submit Cookies](https://www.owasp.org/index.php/Cross-Site_Request_Forgery_(CSRF)_Prevention_Cheat_Sheet#Double_Submit_Cookies) pattern.

#### How to run and check result

1. Start the app on any available servlet container (I tested it on Tomcat 7.0.62).
2. Press `Send` button and check the console.
3. Run [this malicious script](http://jsfiddle.net/krasnyanskiy/33tw4wnj/3/) from jsFiddle and check the console again.

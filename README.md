# Double Submit Cookies Pattern Demo

A simple web app which demonstrates how to protect your site from CSRF attacks with [Double Submit Cookies](https://www.owasp.org/index.php/Cross-Site_Request_Forgery_(CSRF)_Prevention_Cheat_Sheet#Double_Submit_Cookies) pattern.

#### How to run and check result

1. Start the app on any available servlet container (I tested it on Tomcat 7.0.62).
2. Run your browser in [Incognito](https://support.google.com/chromebook/answer/95464?hl=en) mode.
2. Open the app and press `Send` button (see the result).
3. Then run [this malicious script](http://jsfiddle.net/krasnyanskiy/33tw4wnj/3/) from jsFiddle (see the result).

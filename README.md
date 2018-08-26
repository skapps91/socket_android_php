# socket_android_php
Connect Android Socket with php server

First setup php project with your server (Xampp or Wamp)
PS: Enable Sockets from php.ini file.

Then setup Android code. Your Server IP can be get from command prompt -> ipconfig. Put that IP into "httpd.conf" file. In that file Search "Listen 12.34".
Below that line write your Server IP For E.g:
Listen 192.168.0.105:80. Now Start your server. Use this IP as your URL in Android Code.

PS. Image Added about how to use IP Server into XAMPP Server ( server_ip_settings.png ) .

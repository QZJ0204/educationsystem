# educationsystem
springboot+vue+sql Server编写的学生信息管理系统

### 环境
jdk:1.8
node.js:v18.17.0
npm：9.6.7
nginx：1.24.0
sql server（原本是mysql，因为需要更改了sql语句。）

#### nginx部署
![image](https://github.com/QZJ0204/educationsystem/assets/154688097/0aaddbdf-2d24-4494-99ab-f5adba19467f)
![image](https://github.com/QZJ0204/educationsystem/assets/154688097/da65ceb5-7820-4b59-ba91-e071c502276f)

#### nginx.conf配置文件内容
#user  nobody;
worker_processes  1;

#error_log  logs/error.log;
#error_log  logs/error.log  notice;
#error_log  logs/error.log  info;

#pid        logs/nginx.pid;


events {
    worker_connections  1024;
}


http {
    include       mime.types;
    default_type  application/octet-stream;

    #log_format  main  '$remote_addr - $remote_user [$time_local] "$request" '
    #                  '$status $body_bytes_sent "$http_referer" '
    #                  '"$http_user_agent" "$http_x_forwarded_for"';

    #access_log  logs/access.log  main;

    sendfile        on;
    #tcp_nopush     on;

    #keepalive_timeout  0;
    keepalive_timeout  65;

    #gzip  on;

    server {
        listen       8080;
        server_name  localhost;

        #charset koi8-r;

        #access_log  logs/host.access.log  main;

        location / {
            root   html/dist;
            index  index.html index.htm;

	    try_files  $uri $uri/ @router;
        }

	location @router {
            rewrite ^.*$ /index.html last;
        }

	 location /api {
	        proxy_set_header Host $host;
	        proxy_set_header X-Real-IP $remote_addr;
            proxy_set_header X-Forwarded-For $proxy_add_x_forwarded_for;		
            # 后台接口地址
	        proxy_pass https://localhost/api*;
            proxy_redirect default;
	        add_header Access-Control-Allow-Origin *;
	        add_header Access-Control-Allow-Headers X-Requested-With;
	        add_header Access-Control-Allow-Methods GET,POST,OPTIONS;
	    }

        #error_page  404              /404.html;

        # redirect server error pages to the static page /50x.html
        #
        error_page   500 502 503 504  /50x.html;
        location = /50x.html {
            root   html;
        }

        # proxy the PHP scripts to Apache listening on 127.0.0.1:80
        #
        #location ~ \.php$ {
        #    proxy_pass   http://127.0.0.1;
        #}

        # pass the PHP scripts to FastCGI server listening on 127.0.0.1:9000
        #
        #location ~ \.php$ {
        #    root           html;
        #    fastcgi_pass   127.0.0.1:9000;
        #    fastcgi_index  index.php;
        #    fastcgi_param  SCRIPT_FILENAME  /scripts$fastcgi_script_name;
        #    include        fastcgi_params;
        #}

        # deny access to .htaccess files, if Apache's document root
        # concurs with nginx's one
        #
        #location ~ /\.ht {
        #    deny  all;
        #}
    }


    # another virtual host using mix of IP-, name-, and port-based configuration
    #
    #server {
    #    listen       8000;
    #    listen       somename:8080;
    #    server_name  somename  alias  another.alias;

    #    location / {
    #        root   html;
    #        index  index.html index.htm;
    #    }
    #}


    # HTTPS server
    #
    #server {
    #    listen       443 ssl;
    #    server_name  localhost;

    #    ssl_certificate      cert.pem;
    #    ssl_certificate_key  cert.key;

    #    ssl_session_cache    shared:SSL:1m;
    #    ssl_session_timeout  5m;

    #    ssl_ciphers  HIGH:!aNULL:!MD5;
    #    ssl_prefer_server_ciphers  on;

    #    location / {
    #        root   html;
    #        index  index.html index.htm;
    #    }
    #}

}


#### 启动nginx
![image](https://github.com/QZJ0204/educationsystem/assets/154688097/d31a3d9d-6917-473f-a319-84751f844eb0)
#### 浏览器进入本地8080端口
![image](https://github.com/QZJ0204/educationsystem/assets/154688097/1f861a6b-05e6-4b3a-b3f6-4af330f0bdcf)

#### 配置java后端和数据库

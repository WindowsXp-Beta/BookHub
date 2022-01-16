# Clustering & Docker

> author:魏新鹏
>
> student ID:519021910888

以下配置文件均为容器化后的版本

## nginx负载均衡

nginx.conf配置文件

```
events {
    worker_connections  1024;
}
http {
				upstream bookhub {
        		server bookhub-1:8080;
		        server bookhub-2:8080;
    		}
				listen 80;

        location / {
            proxy_pass http://bookhub/;
        }
}
```

然后启动nginx即可，前端通过80端口访问即可实现load banlance。

## Redis存储session

在application.properties中添加

```
spring.session.store-type=redis
```

然后spring便会将session存入redis

### 检验

通过redis-cli查看存入的session

```bash
127.0.0.1:6379> keys *
spring:session:sessions:expires:c0d4f39a-d57f-4250-8b4f-3aca2d9ba048
bookBrief::page::0
spring:session:sessions:c0d4f39a-d57f-4250-8b4f-3aca2d9ba048
spring:session:expirations:1638711540000
```

## 多个Tomcat后端实例

### 未容器化

直接在本地idea中运行两个相同的backend在8080和8090端口，通过nginx进行负载均衡。

### 容器化

1. 构建backend war包。

   ```bash
   mvn install -Dmaven.test.skip=true -f pom.xml
   ```

2. 构建docker image。

   ```bash
   mvn spring-boot:build-image -Dmaven.test.skip=true -f pom.xml
   ```

   生成backend:0.0.1-SNAPSHOT image

3. docker-compose.yaml文件

   ```yaml
   version: '3.7'
   services:
     mysqldb:
       image: mysql:8.0
       container_name: bookstore-mysql
       ports:
         - 3306:3306
       environment:
         - MYSQL_ROOT_PASSWORD=iloveyou0118
         - MYSQL_DATABASE=bookstore
       volumes:
         - ./db/mysql:/var/lib/mysql
   
     redis:
       image: redis
       container_name: bookstore-redis
       ports:
         - "6379:6379"
       volumes:
         - ./db/redis:/data
   
     mongodb:
       image: mongo
       container_name: bookstore-mongodb
       ports:
         - "27017:27017"
       volumes:
         - ./db/mongodb:/data/db
   
     nginx:
       image: nginx:1.21.4
       volumes:
         - ./nginx/nginx.conf:/etc/nginx/nginx.conf
       ports:
         - "80:80"
   
     bookhub-1:
       image: backend:0.0.1-SNAPSHOT
       ports:
         - "8080:8080"
   
     bookhub-2:
       image: backend:0.0.1-SNAPSHOT
       ports:
         - "8090:8080"
   ```

   添加需要的mysql，nginx，mongodb，redis依赖，最后为两个TomCat docker实例。

4. 使用docker compose运行。

   ```bash
   docker compose up -d
   ```

   即可运行。
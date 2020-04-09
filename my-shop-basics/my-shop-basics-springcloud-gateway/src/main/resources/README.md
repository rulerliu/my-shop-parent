访问地址：

通过网关跳转swagger
http://127.0.0.1:81/swagger-ui.html

通过网关访问
http://127.0.0.1:81/mayikt-member/getTokenUser?token=MEMBER_LOGIN_c40ecff0eca847beb196f56f08ef0a27

通过nginx访问：
http://gw.mayikt.com/mayikt-member/getTokenUser?token=MEMBER_LOGIN_c40ecff0eca847beb196f56f08ef0a27

异常：
Parameter 0 of method modifyRequestBodyGatewayFilterFactory in org.springframework.cloud.gateway.config.GatewayAutoConfiguration required a bean of type 'org.springframework.http.codec.ServerCodecConfigurer' that could not be found.
把springboot-web依赖、feign依赖移到service-api工程的pom里面

nginx的核心配置：
# 访问nginx的80端口 跳转到gateway的81端口
upstream mayiktgwadds {
    server 127.0.0.1:81;
}
server {
    listen       80;
    server_name  gw.mayikt.com;
    location / {
        proxy_pass http://mayiktgwadds/;
        proxy_set_header   Host             $host;
        proxy_set_header   X-Real-IP        $remote_addr;						
        proxy_set_header   X-Forwarded-For  $proxy_add_x_forwarded_for;
    }
}
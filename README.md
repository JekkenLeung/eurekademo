# Spring Cloud Demo

 - Server:  8761
 <br>http://localhost:8761/
 - Client:  8763 8762
 <br>http://localhost:8762/hello
 - Feign:   8764 
 <br>http://localhost:8764/say 
 
 
 - Zuul:  8768
 <br>
 http://localhost:8768/fignService/say
 <br>
 http://localhost:8768/sayService/hello
 查看路由
 <br><a>http://localhost:8768/actuator/routes</a>
 <br><a>http://localhost:8768/actuator/routes/details</a>
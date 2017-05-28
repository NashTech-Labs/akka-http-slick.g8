A [Giter8][g8] template for providing a seed for starting with Slick and Akka-http.

akka-http-slick
----------

### Run unit test:
```
$ ./activator test
 
 ```

### Run http server(It automatically connect with H2 database):
```
$ ./activator run

```

### Test http rest point using curl:

1) Get Bank detail by bank id

 request:
 ```
$ curl localhost:9000/bank/1
 
 ```
response:
```
 {"name":"SBI bank","id":1}
 ```

2)Get all Bank detail


 request:
```
$ curl localhost:9000/bank/all
```
response:
```
[{"name":"SBI bank","id":1},{"name":"PNB bank","id":2},{"name":"RBS bank","id":3}]
```

3)Save new bank detail

 request:
 ```
   $  curl -XPOST 'localhost:9000/bank/save'  -d '{"name":"New Bank"}'
   ```
   
 response:
 
 Bank has  been saved successfully

3)Update new bank detail

  request:
  ```
  $  curl -XPOST 'localhost:9000/bank/update'  -d '{"name":"Updated bank", "id":1}'
  
  ```
  
  response:
  
   Bank has  been updated successfully

4)delete bank by id

  request:
    
  ```
  $ curl -XPOST 'localhost:9000/bank/delete/1
  
  ```
  response:
  
  Bank has been deleted successfully

Template license
----------------
Written in ​ 2017​ by ​ [Knoldus Software LLP](http://knoldus.com)

To the extent possible under law, the author(s) have dedicated all copyright and
related
and neighboring rights to this template to the public domain worldwide.
This template is distributed without any warranty. See
<http://creativecommons.org/publicdomain/zero/1.0/>.
[g8]: http://www.foundweekends.org/giter8/

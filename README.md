# My Fit App
## How to use my APP ?
### #1 Delete existing images in Docker
* `docker stop $(docker ps -aq)`
* `docker rm $(docker ps -aq)`
### #2 Create new image in Docker
* `docker run -p 3306:3306 --name mysql -e MYSQL_ROOT_PASSWORD=password -e MYSQL_DATABASE=example --rm -d mysql`
## OR
### #1 Run app using docker-compose
* `docker-compose up --build`
* `docker-compose down`
* `docker-compose start`
* `docker-compose stop`
## At the end...
### Check App's functioning 
* in your browser go to localhost with user port: 
`http://localhost:8080/`

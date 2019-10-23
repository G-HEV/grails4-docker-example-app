# Grails 4 example application using Docker

Grails 4 example application using Docker

## Instructions

Create docker image with:
```
docker build -t my-app:latest .
```

Run application with:
```
docker run -p 8080:8080 --name my_app_container my-app:latest
```

Access the application at: http://localhost:8080

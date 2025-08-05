# Demo TATA



## Proyecto Demo Tata

Proyecto Demo para Tata 

### Este proyecto tiene como objetivo mostrar la funcionalidad de Sprig Boot
### el proyecto viene integrado con Swagger como herramienta api
### Utilitarios previos
```
1. Con JDK 17 minimo
2. Docker Desktop para Windows
3. Intellij
4. Utiliza Gitlab como repositorio 
5. Postman
 
```
## Pasos
### 1. Clonamos el repositorio en el directorio a su eleccion con el comando
```
cd directorioproyecto
Abrimos gitbash
git init
git clone https://github.com/victorjaviercardenastrujillo/Demo-Tata.git

```

### 2. Creación Base de Datos
#### Siga los siguientes pasos para instalar y configurar la Base de Datos en Postgres
```
1. Instalar Docker Destop ambiente Windows, o tener inst6alado y configurado
   Docker y Docker  Compose  
2. Una vez descargado el proyecto desde GitLab ingresar a la raíz del proyecto
   y ejecutar el siguiente comando:
   docker-compose  up -d #para crear el contenedor de la base de datos 
3. Verificamos que el contenedor este creado con el siguiente comando:
   docker ps -a
   Salida:
   CONTAINER ID   IMAGE             COMMAND                  CREATED          STATUS         PORTS                                         NAMES
   7607263b0837   postgres:latest   "docker-entrypoint.s…"   13 seconds ago   Up 8 seconds   0.0.0.0:5432->5432/tcp, [::]:5432->5432/tcp   tata_db
4. Utilizar cualquier herramienta para bases de datos, de tal manera que 
   se verifique la creación de la base de datos y el schema 'tata'
   Credenciales:
   Base: tata_db
   Usuarion: tata_usr
   Contraseña: tata_pwd
5. Ejecutar el sript para crear info en la tabla Utils
   INSERT INTO tata.utils("name", value) VALUES('limite diario de retiro', '1000');
   
```

### 3. Ejecutamos Intellij y abrimos el proyecto
Esperamos a que GRADLE actualice el proyecto

Ejecutamos el archivo del proyecto: DemoApplication.java

### 4. URL Swagger
En un navegador por favor ingresar la siguiente URL para visualizar SWAGGER
```
http://localhost:8080/swagger-ui/index.html

```

### 5. Dockerizar la aplicación
Para dockerizar la aplicación siga los siguientes pasos
```
1. Abrir terminal del SO en Win 11 CMD o Powershell.
2. ingrese a la direción donde se descagó el proyecto desde GIT
3. Ejecute los siguientes comandos:
  a. Para crear el archivo .jar
     .\gradlew.bat clean bootJar 
  b. Para crear el contenedor:
     docker build -t spring-demo .
  c. Para conrrer el contenedor
     docker run -d -p 8080:8080 --name demo-container spring-demo
  d. Para verificar que el contenedor se encuentra ejecutando:
     docker ps -a
     Su salida:
     CONTAINER ID   IMAGE             COMMAND                  CREATED         STATUS         PORTS                                         NAMES
     a626f9634266   spring-demo       "java -jar app.jar"      9 seconds ago   Up 8 seconds   0.0.0.0:8080->8080/tcp, [::]:8080->8080/tcp   demo-container
     7607263b0837   postgres:latest   "docker-entrypoint.s…"   2 hours ago     Up 2 hours     0.0.0.0:5432->5432/tcp, [::]:5432->5432/tcp   tata_db
  e. ejecutar el comando:
     ipconfig #para Windows
     ifconfig #para Linux
     Salida Win 11:
      Dirección IPv4. . . . . . . . . . . . . . : [Mi_Ip]
  f. Verificación con Swagger en cualquier navegador:
     http://[Mi_Ip]:8080/swagger-ui/index.html
  
```
## Api Rest
### Clientes
```
Estados Genero: Masculino, Femenino
DNI: maximo 13 caracteres
URL: http://localhost:8080/api/clientes/create
Metodo: POST
Body:
{
    "password": "password",
    "state": true,
    "dni": "1755678543",
    "names": "XXXXX",
    "surnames": "XXXXX",
    "birthday":"1993-01-26T00:00:00.000Z",
    "address": "Direccion 1",
    "phone": "0979256644",
    "gender": "Masculino"
}
```

### Cuentas
```
accountType: Ahorros, Corriente
URL: http://localhost:8080/api/cuentas/create
Metodo: POST
Body:
{
    "accountNumber": "1234563",
    "accountType": "Corriente",
    "initialBalance": 1000.00,
    "state": true,
    "clients": [
        {"id": 1}
    ]
}
```

### Movimientos
```
transactionType: Retiro, Deposito
URL: http://localhost:8080/api/movimientos/create
Metodo: POST
Body:
{
    "transactionType": "Retiro",
    "accountNumber": "1234564",
    "amount": 10.00
}
```
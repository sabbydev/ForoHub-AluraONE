# ForoHub-AluraONE

**ForoHub** es una ***API REST*** diseñada para gestionar tópicos en un foro. Permite realizar operaciones ***CRUD*** (Crear, Leer, Actualizar, Eliminar) sobre tópicos, proporcionando un sistema sencillo y eficiente para interactuar con los datos. El proyecto está construido con **Spring Boot** y utiliza **MySQL** para el almacenamiento de datos.

## Funcionalidades

**ForoHub** permite realizar las siguientes operaciones:

1. **Registrar un tópico**: Permite crear un nuevo tópico en el foro.  
   **Método:** `POST`  
   **Ruta:** `/topics`
   
2. **Listar todos los tópicos**: Muestra una lista de todos los tópicos disponibles.  
   **Método:** `GET`  
   **Ruta:** `/topics`
   
3. **Detallar tópico**: Muestra los detalles de un tópico específico basado en su `id`.  
   **Método:** `GET`  
   **Ruta:** `/topics/{id}`
   
4. **Actualizar tópico**: Permite modificar la información de un tópico existente basado en su `id`.  
   **Método:** `PUT`  
   **Ruta:** `/topics/{id}`
   
5. **Eliminar tópico**: Elimina un tópico del foro basado en su `id`.  
   **Método:** `DELETE`  
   **Ruta:** `/topics/{id}`

## Requisitos

- **Java 17** o superior.
- **MySQL 8.0** o superior (recomendado 8.1).
- **Insomnia o Postman** (Insomnia recomendado).
- **¡**Tu IDE favorito**!** (IntelliJ IDEA recomendado).

## Configuración

### Variables de entorno

Se deben configurar las siguientes variables de entorno para conectar la aplicación con la base de datos:

- **DB_USER:** Usuario de la base de datos.
- **DB_PASSWORD:** Contraseña del usuario de la base de datos.
- **JWT_SECRET:** Clave secreta que se utiliza para firmar y verificar la autenticidad de un token JWT
- **JWT_EXPIRATION:** Atributo que se incluye en el payload del token para indicar el momento exacto en que el token expirará, basado en la hora actual más un intervalo de tiempo especificado.

**Ejemplo de configuración en la terminal:**

```bash
export DB_USER=usuario
export DB_PASSWORD=contraseña
export JWT_SECRET=mi_clave_secreta
export JWT_EXPIRATION=1 # El token expirará en 1 hora
```

En caso de usar IntelliJ IDEA, asegúrate de configurar estas variables en las opciones de ejecución del IDE.
Es fundamental utilizar variables de entorno para almacenar la URL de la base de datos en entornos de producción. Además, es recomendable configurar diferentes perfiles en el archivo application.properties para gestionar las configuraciones específicas de cada entorno de manera eficiente.

### Base de datos

- Crea una base de datos MySQL llamada forohub si aún no existe.

La conexión a la base de datos se establece automáticamente mediante JPA.

### Ejecución

Clona el repositorio.

```
git clone <URL_DEL_REPOSITORIO>
cd <NOMBRE_DEL_PROYECTO>
```

Construye y ejecuta la aplicación utilizando Maven:

```
mvn spring-boot:run
```

Una vez que hayas configurado todo y ejecutado la aplicación, las migraciones de Flyway generarán las tablas necesarias para la API y el servidor estará disponible en el puerto 8080.
La URL del servidor es: [http://localhost:8080](http://localhost:8080)

#### Esta URL sirve como prefijo para las rutas de las operaciones de la API.

### Inserción de usuario en la base de datos

La aplicación necesita que exista al menos un usuario en la tabla `usuarios`. Para ello, inserta un usuario manualmente en la base de datos utilizando el siguiente SQL:

```sql
INSERT INTO usuarios (login, clave) VALUES ('<usuario>', '<contraseña_encriptada_con_bcrypt>');
```

Asegúrate de encriptar la contraseña antes de insertarla en la base de datos.
Para generar la contraseña encriptada, puedes utilizar la herramienta disponible en el siguiente enlace:
- [Generador de Bcrypt](https://www.browserling.com/tools/bcrypt)

Una vez que hayas creado un usuario, puedes realizar una solicitud POST a la ruta /login, enviando un cuerpo JSON con tu login y clave (sin encriptar), para obtener un token JWT. Este token será necesario para autenticar todas las solicitudes posteriores.

## Autenticación

ForoHub utiliza JSON Web Tokens (JWT) para autenticar a los usuarios. Para obtener un token JWT, realiza una solicitud POST a la ruta /login enviando tus credenciales (login y contraseña sin encriptar) en el cuerpo de la solicitud.

**Método:** `POST`
**Ruta:** `/login`

Cuerpo de la solicitud (JSON):

```json
{
  "login": "usuario",
  "clave": "contraseña"
}
```

Respuesta de la API (Ejemplo):

```json
{
  "token": "eyJhbGciOiJIUzI1NiIsInR5cCI6IkpXVCJ9.eyJ1c2VyIjoiYWRtaW4iLCJleHBpcmVkX3N0YXR1cyI6MzYwMH0.V56dWR8jqlr...L6A"
}
```

Una vez que obtengas el token JWT, deberás incluirlo en el encabezado de todas las solicitudes HTTP como un Bearer token. Dependiendo de la herramienta que utilices (Insomnia o Postman), deberás configurarlo en el apartado Auth (Autenticación) de la solicitud.

### En Insomnia:
1. Ve a la pestaña Auth.
2. Selecciona Bearer Token.
3. En el campo Token, pega el token JWT obtenido.

### En Postman:
1. Ve a la pestaña Authorization.
2. En el campo Type, selecciona Bearer Token.
3. En el campo Token, pega el token JWT obtenido.


# ¡Y LISTO!
### ¡Ya puedes interactuar con la API ForoHub para realizar las operaciones disponibles utilizando Insomnia o Postman!

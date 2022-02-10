# rest-assured-Falabella
Rest-assured-Falabella


# falabella-test-api

###### ¿Cómo descargar el repositorio?
Para clonar este proyecto, se procede como con cualquier otro repositorio.

**1.** En el navegador, dirigete a *https://github.com/navaswendy22/rest-assured-Falabella* selecciona la opción *Clone or download* y copia la instrucción *SSH* o la *HTTPS* dependiendo de tu configuración de git. \
**2.** Abre la terminal y ubicate en la carpeta en donde quieras clonar el proyecto. \
**3.** Cuando estés en la carpeta deseada, usa el comando `git clone nombre_del_repo_ssh_o_https` 

Ahora puedes importar el proyecto en tu IDE favorito. \
Importar > Selecciona el proyecto > Importar como un proyecto Maven \
Listo 😃

### Running the tests
Ejemplo:
```
Dirigirse a la carpeta: src => main => java => Runner => Runner

Una vez allí, ingresas el tags que desea correr.
Sin embargo por defecto trae el tags test, si presiona segundo click, luego run, se ejecutarán todos los test creados.

Si desea ejecutar un solo test, solo se debe cambiar el tags por el de preferencia.
```

###### ¿Cómo agregar un test nuevo?
1. Agregar en la carpeta 'resources/feature' una carpeta con el nombre del microservicio al que le vamos a realizar los tests.
2. Agregar en esa carpeta un archivo '.feature' con un nombre representativo de los endpoint o funcionalidad a probar. En la primera linea agregar un tag representativo para ejecutar todos los tags dentro de ese feature.
3. Generar los escenarios correspondientes con la siguiente estructura:
 * **Given**: Aquí van todas las precondiciones antes de hacer el request al endpoint a probar. Por ejemplo:
 > Host, puerto, base path, timeout, headers, query params, login en caso de ser necesario, etcd.
 * **When**: Este se ejecuta luego de tener todas las precondiciones con Given hechas. Por ejemplo: 
 > GET, POST, UPDATE, DELETE, etcd.
    En caso de ser POST o PUT, agregar body.
 * **Then**: Aquí van todas las validacion del response. Por ejemplo:
 > Status code, response body, response headers, response cookies, **schema de la respuesta**, etcd.
    
###### Agregando un step nuevo
Para agregar un nuevo step, es necesario ver de que tipo es: **precondicion, de request, o de validacion**. 
* Si es de precondicion, debería agregarse en la carpeta 'java/steps/request_specification', y ver en que clase corresponde (HostSteps, PortSteps, etc).
* Si es de request debería ir en 'java/steps/crud', y ver a que metodo pertenece (PostSteps, GetSteps, etcd)
* Si es validacion, va en 'java/steps/response_validation', y ver a qué tipo de validacion corresponde (JsonResponseSteps si se valida un json, CookieResponseSteps si es validacion de cookies, etcd).

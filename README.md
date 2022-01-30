# reba - challenge

## Consigna 
El principal objetivo es construir una API REST con las operaciones CRUD
necesarias para gestionar el recurso Persona.
Se divide en 3 niveles de complejidad para que puedas demostrar tu experiencia y
conocimientos.
Se evaluará por cada nivel completo, pudiéndose entregar el desafío en forma
parcial incluyendo solo nivel 1 o solo nivel 1 y 2, o los 3 niveles completos.

## Architecture
Se utiliza el patron de arquitectura hexagonal en su estado simple, entre las ventajas que ofrece estan: desacoplamiento, escalabilidad, legibilidad.
![alt text](docs/hexagonal.png)


## Construccion de la solucion

### Endpoints
Se contruye un API CRUD con 4 endpoints:
1. POST -> /api/v1/challenge/persona
   1. Body: ```
      {
      "nroDocumento": "v180901199",
      "nombre": "Enrique",
      "edad": 17,
      "contactos": [
      {
      "nombre": "Persona",
      "telefono": "11111111"
      }
      ]
      } ```

2. PUT -> /api/v1/challenge/persona
   1. Body: ```
      {
      "nroDocumento": "v180901199",
      "nombre": "Enrique",
      "edad": 17,
      "contactos": [
      {
      "nombre": "Persona",
      "telefono": "11111111"
      }
      ]
      } ```
3. GET -> /api/v1/challenge/persona/{nroDocumento}
4. DELETE -> /api/v1/challenge/persona/{nroDocumento}

Nota: Para la validacion de los constraints como que la edad debe ser al menos 18 años y que la persona al menos debe tener 1 contacto, utilce la libreria `org.springframework.boot:spring-boot-starter-validation`, la cual me permite anotar los request con constraints como @Min, @Size...
Nota 2: Se agrega el controllerAdvice para la manipulacion de errores, y asi devolver los errores con algo de formato.

### [Casos de uso]
Aparte de los endpoints respectivos, se implementan las interfaces(port/in, port/out) para los distintos casos de uso
1. Por practicidad para la solucion del challenge todas las operaciones relacionadas a la manipulacion del recurso persona,
se incluyeron en CUDPersonaUseCase.class (Create, Update, Delete), en un entorno corporativo real, cada operacion deberia ser una caso de uso.

2. El caso de uso GetPersonaUseCase.class, IMHO es un domain service(utility class) dado que se debe utilizar en todo momento para las demas de operaciones, entonces por ende no tenia sentido duplicar el codigo en cada metodo.

### [Repository]
En los adapter, declare uno de tipo jdbc, el cual hace referencia a la manipulacion/conexion con la base de datos, se utiliza una implementacion de JPA, en la cual se usan dos entidades PersonaEntityModel y ContactoEntityModel(FK a Persona)
En mi opinion particular, prefiero manipular la base de datos en forma async(u Orientado a Eventos)  para evitar thread lock, pero para efectos del challenge me fui por la implementacion "simple"

### [Base de Datos]
Utilice H2 ofrecida por spring boot, para cumplir la consigna se ejecutan 2 scrips (schema.sql, data-h2.sql), uno para crear las tablas, otro para llenarla con algunos datos
Se agregan la propiedad ddl-auto: validate para validar que los *.sql que se ejecutaran estan correctos. 
Nombre del schema: challengeDB
Usuario: sa
password: [vacio] (Es decir, no escribir nada)

### [Unit test]

Se incluyen tests unitarios, no de todo el codigo(o casos), solo para fines de demostrar buenas practicas.

### [Gestor de dependencias]
Gradle, indexa mas rapido, maven genera mucho codigo, aunque los plugins estan buenos(tiene ventaja frente a gradle).

## Ejecucion en local
1. Pullear el codigo en el IDE(Espero usen intellij) desde: https://github.com/Enriquejpc/reba.git
2. Run application


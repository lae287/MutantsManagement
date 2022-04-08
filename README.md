# Mutants Tryout MELI

Para la ejecución de esta prueba se decidió hacer uso del siguiente stack tecnológico:

- Swagger
- Java 
- Maven
- MongoDB
- Github
- Eclipse
- Postman 
- Heroku

El reto como tal se encontraba dividido en 3 secciones, la primera correspondiente a la parte lógica en donde se esperaba generar un método que al recibir un String[] informara si éste cumplía con ciertas condiciones para separar entre DNA Mutante y DNA Humano.

Para resolver esta primera parte se realizó un método simple en java a través del cual recorrerá el input entregado al método y a través de validaciones internas se evaluará el cumplimiento de las condiciones para ser separado en cada uno de estos subgrupos, para el caso de mutantes bastaba con considerar que dentro de las cadenas recibidas se recibiera 4 caracteres iguales consecutivos de forma oblicua, horizontal o vertical.

En cuanto a la segunda sección se pedía generar un API Rest para el uso del servicio /mutant, en donde además de hacer uso del método creado en la primera sección se debería desplegar el mismo en un cloud libre y además entregar las siguientes respuestas:

200 - En caso de cumplir condición de mutante
403 - En caso contrario

Para esto se creo un API en swagger para su visualizacion y documentacion basica, la cual se puede encontrar en [Mutant API](https://app.swaggerhub.com/apis/pruebaMeli/Mutants/1.0.0)

Asimismo se creó un tercer código de respuesta para aquellos que no cumplieran la condición inicial de estar únicamente formados por los caracteres {A,T,C,G}.

406 - En caso de no cumplir la restricción de caracteres

Para la disponibilización de este API se recurre a Heroku , el cual nos permite ejecutar el código directamente desde la rama de Github sobre la cual estamos trabajando.

Dentro del repositorio, se puede encontrar la [colección](https://github.com/lae287/MutantsManagement/blob/master/MELI.postman_collection.json) de postman con la que se podra ejecutar el proyecto, ya sea en un ambiente local o directamente con Heroku (Esta coleccion incluye los 2 endpoint entregados).

En caso de no querer usar Postman como tal para la ejecucion de los endpoints las URL son las siguientes:

- https://mutantsmelile.herokuapp.com/mutant/ (POST)
- https://mutantsmelile.herokuapp.com/stats/  (GET)

Finalmente para la última parte de la solución se requería generar un segundo endpoint llamado /stats el cual se encargaría de entregar un json con las estadísticas generadas por el aplicativo una vez realizadas las respectivas validaciones del punto 1.

Por este punto es que se decidió incluir un nuevo código de error para evitar guardar data invalida dentro de la base de datos que se creó en mongo y así no caer en inconsistencias a la hora de generar un ratio mutante/humano fiable. Para el almacenamiento de las cadenas de DNA se utilizo mondoDB como base no relacional con eel fin de usar un modelo simple de clave/valor, asimismo para evitar generar mas carga en el servicio de consulta, se creo directamente una nueva entidad Stats que se actualiza al tiempo con la creacion de cada secuencia de DNA.

Dentro del código incluido en el repositorio se incluyen también pruebas de cobertura que se encuentran por encima del 80% esta validación se realizó directamente en eclipse, por lo que los resultados podrían variar de realizarse en otras herramientas como Jacoco, sonar, etc… ya que cada una de ellas aplica distintos tipos de reglas que podrían generar una fluctuación en el mismo.

Para poder realizar una prueba en un ambiente local, tan solo es necesario realizar un Pull desde esta rama, importar el proyecto en un IDE como Eclipse y ejecutar como aplicación Java la clase MutantsApplication.

Es posible que la primera ejecución desde el postman directamente a Heroku falle o tenga algo de latencia, debido a que Heroku como tal realiza apagado de aplicaciones inactivas, sin embargo una vez ejecutada la primera vez no se tendrá ningún tipo de latencia adicional.



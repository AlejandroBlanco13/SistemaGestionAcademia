# Sistema de Gestión Académica

![Java](https://img.shields.io/badge/Java-17-blue)
![License](https://img.shields.io/badge/License-MIT-green)
![Maven](https://img.shields.io/badge/Apache%20Maven-3.9.6-red)

Aplicación de consola en Java para gestionar estudiantes, profesores, cursos y matrículas en una academia, con persistencia en archivos de texto.

## Integrantes
- [Tu Nombre]
- [Nombre de tu compañero]

## Estructura del Proyecto
sistema-gestion-academia/
├── src/
│ ├── main/
│ │ ├── java/academia/
│ │ │ ├── data/GestorArchivos.java
│ │ │ ├── exceptions/
│ │ │ ├── model/
│ │ │ └── Main.java
│ │ └── resources/ (*.txt)
├── docs/
│ └── Readme.pdf
└── pom.xml

text

## Funcionalidades
- ✅ Registrar estudiantes, profesores y cursos
- ✅ Matricular estudiantes en cursos
- ✅ Consultar listados
- ✅ Persistencia en archivos .txt
- ✅ Validación de capacidad y duplicados

## Requisitos
- JDK 17+
- Apache Maven 3.9.6+

## Instalación
1. Clonar repositorio:
   ```bash
   git clone [URL_DEL_REPOSITORIO]
   cd sistema-gestion-academia
Compilar con Maven:

bash
mvn clean package
Ejecución
Opción 1: Ejecutar directamente con Maven

bash
mvn exec:java
Opción 2: Ejecutar el JAR generado

bash
java -jar target/sistema-gestion-academia-1.0-SNAPSHOT.jar
Uso del Sistema
Menú principal:

text
=== MENÚ PRINCIPAL ===
1. Registrar estudiante
2. Registrar profesor
3. Registrar curso
4. Matricular estudiante
5. Consultar cursos
6. Estudiantes por curso
7. Salir

Archivos de Datos
El sistema maneja estos archivos en src/main/resources/:

estudiantes.txt

profesores.txt

cursos.txt

matriculas.txt

Documentación
Ver archivo docs/Documentacion.pdf con:

Diagramas de clase

Manual de usuario

Especificaciones técnicas

Contribución
Haz fork del proyecto

Crea una rama (git checkout -b feature/nueva-funcionalidad)

Haz commit de tus cambios (git commit -m 'Agrega nueva funcionalidad')

Haz push a la rama (git push origin feature/nueva-funcionalidad)

Abre un Pull Request
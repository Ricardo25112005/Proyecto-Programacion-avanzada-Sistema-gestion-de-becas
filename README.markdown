# Sistema de Gestión de Becas

## Sinopsis del Código
Este sistema permite a los usuarios gestionar becas para estudiantes. Los usuarios pueden cargar datos de estudiantes, becas y postulaciones desde archivos CSV, registrar nuevos estudiantes, consultar información y generar reportes. El objetivo es facilitar la administración de becas, postulaciones y estudiantes en un entorno académico.

## Requisitos Previos
- Tener instalado NetBeans IDE o cualquier IDE para Java (como IntelliJ IDEA o Eclipse).
- Instalar JDK (Java Development Kit) versión 8 o superior.
- Asegurarse de que los archivos CSV (`estudiantes.csv`, `becas.csv`, `postulaciones.csv`) estén en la carpeta `src/resources`.

## Pasos para Compilar y Ejecutar
1. **Descarga y descomprime** el archivo .zip del repositorio o clona el proyecto a través del enlace proporcionado.
2. **Abre el proyecto en NetBeans**:
   - Inicia NetBeans.
   - Selecciona *Archivo > Abrir Proyecto...* y elige la carpeta del proyecto.
3. **Compila el código**:
   - En NetBeans, haz clic derecho en el proyecto y selecciona *Clean and Build*.
4. **Ejecuta el programa**:
   - Haz clic derecho en el proyecto y selecciona *Run*.
   - El programa se ejecutará en la consola integrada.
5. **Carga los archivos CSV**:
   - Selecciona la opción 1 en el menú para cargar los datos de `estudiantes.csv`, `becas.csv` y `postulaciones.csv`.

## Funciones Principales

### Menú Principal
El programa presenta un menú principal con las siguientes opciones:
1. Cargar Ajustes
2. Mostrar Estudiante
3. Registrar Alumno
4. Busqueda de Postulacion
5. Generar Reporte
6. Salir

### Cargar Ajustes
- **Descripción**: Carga los datos de estudiantes, becas y postulaciones desde archivos CSV utilizando la clase `DataLoader`. Valida y procesa cada línea para crear objetos `Student`, `Beca` y `Postulation`, almacenándolos en mapas para un acceso eficiente.
- **Complejidad temporal**: O(n) para cada archivo, donde n es el número de líneas.

### Mostrar Estudiante
- **Descripción**: Solicita el RUT del estudiante y muestra sus datos personales junto con sus postulaciones a becas.
- **Complejidad temporal**: O(1) promedio para buscar en el mapa, más O(m) para mostrar m postulaciones.

### Registrar Alumno
- **Descripción**: Permite registrar un nuevo estudiante a través de un terminal.
- **Complejidad temporal**: O(1) para validaciones y escritura en CSV, más O(1) promedio para agregar al mapa.

### Búsqueda de Postulación
- **Descripción:** Permite buscar postulaciones de estudiantes mediante un sub-menú con dos opciones:
  1. Buscar con ID de postulación y RUT del alumno: Busca una postulación específica usando el RUT del estudiante y el ID de la postulación.
  2. Buscar solo con RUT del alumno: Muestra todas las postulaciones asociadas a un estudiante según su RUT.
- **Complejidad temporal**: O(1) promedio para buscar un estudiante en el mapa, más O(m) para recorrer las m postulaciones del estudiante en ambas opciones.

### Generar Reporte
- **Descripción**: (Pendiente de implementación completa) Generará un reporte basado en los datos de estudiantes, becas o postulaciones.
- **Complejidad temporal**: Dependerá de la implementación final (e.g., O(n) para iterar sobre todos los datos).

## Ejemplo de Uso

```
=== Menú Administrador ===
1. Cargar Ajustes
2. Mostrar Estudiante
3. Registrar Alumno
4. Busqueda de Postulacion
5. Generar Reporte
6. Salir

```

### Cargar Ajustes
```
Seleccione una opción: 1

>> Ejecutando función: cargarAjustes()
Datos cargados correctamente.
```

### Mostrar Estudiante
```
Seleccione una opción: 2

>> Ejecutando función: mostrarBecas()
Ingrese el RUT del estudiante: 18.425.725-2
=== Datos del Postulante ===
Nombre: Pedro Campos
RUT: 18.425.725-2
Dirección: Región: Biobío, Ciudad: Concepción, Calle: Calle 951
Correo: pedrocampos@gmail.com
Teléfono: 981490936
Tramo Socioeconómico: 68.6
Carrera: Ingeniería Civil
Institución: Universidad de Concepción
Aprobación Estimada: 80.0

=== Lista de Postulaciones ===
ID Postulación: PST-014
ID Estudiante: 18.425.725-2
ID Beca: BCT-10
Estado: RECHAZADA
Fecha de Postulación: 12/09/25
Presione una tecla para continuar...
```

### Registrar Alumno
```
Seleccione una opción: 3

>> Ejecutando función: registrarAlumno()

Ingrese RUT del estudiante (formato XX.XXX.XXX-Y): 19.123.456-7
Ingrese nombre del estudiante: Ana López
Ingrese correo del estudiante: ana.lopez@gmail.com
Ingrese teléfono del estudiante: 912345678
Ingrese tramo socioeconómico (0.0 a 100.0): 45.5
Ingrese carrera del estudiante: Ingeniería Química
Ingrese dirección del estudiante (formato Región: X; Ciudad: Y; Calle: Z): Región: Metropolitana; Ciudad: Santiago; Calle: Calle 123
Ingrese institución del estudiante: Universidad de Chile
Ingrese aprobación estimada (0.0 a 100.0): 85.0
Estudiante registrado exitosamente: Ana López
```

### Búsqueda por postulación
```
Seleccione una opción: 4

>> Ejecutando función: busquedaPostulacion()
=== Búsqueda de Postulación ===
1. Buscar con ID de postulación y RUT del alumno
2. Buscar solo con RUT del alumno
Seleccione una opción: 1
Ingrese el RUT del alumno: 18.425.725-2
Ingrese el ID de la postulación: PST-014
Postulación encontrada:
===================================
ID Postulación: PST-014
ID Estudiante: 18.425.725-2
ID Beca: BCT-10
Estado: RECHAZADA
Fecha de Postulación: 12/09/25
```


## Notas y Consideraciones
- El sistema utiliza `HashMap` para un acceso eficiente a estudiantes, becas y postulaciones.
- Los archivos CSV deben seguir el formato especificado (e.g., `rut,nombre,correo,...` con `;` en el campo `direccion`).
- Si hay problemas al compilar, verifica que las rutas de los archivos CSV (`src/resources`) sean correctas.
- Para reflejar cambios en los CSV o releerlos, reinicia el programa.
- El sistema incluye validaciones robustas para formatos de RUT, correos, y rangos numéricos, manejando excepciones como `IOException` y `NumberFormatException`.
- Los campos de texto (nombre, carrera, institución) escapan comas para evitar errores en el formato CSV.

## Mejoras Sugeridas
- Implementar exportación/importación avanzada de reportes en formatos como PDF o Excel.
- Añadir una interfaz gráfica utilizando Swing o JavaFX para mejorar la usabilidad.
- Permitir la edición masiva de estudiantes, becas o postulaciones.
- Integrar una base de datos (e.g., MySQL o SQLite) en lugar de archivos CSV para una mejor persistencia.
- Generar gráficos (e.g., número de postulaciones por estudiante) usando una librería como JFreeChart.
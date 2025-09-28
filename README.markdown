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
   - El programa se ejecutará a traves de una ventana de tipo swing.
## Funciones Principales

### Menú Principal
El programa presenta un menú principal en ventana de tipo Swing con las siguientes opciones:
1. Resigtrar Estudiante
2. Registrar Beca
3. Registrar Postulacion
4. Filtrar Postulaciones por Estado/tipo
5. Mostrar Datos Estudiante
6. Buscar Postulaciones Estudiante
7. Buscar Postulaciones Beca
8. Buscar Beca
9. Modificar
10. Eliminar Datos
11. Generar Reporte TXT

### Registrar Estudiante
- **Descripción**: Permite registrar un nuevo estudiante a través de Ventanas.
- **Complejidad temporal**: O(1) para validaciones y escritura en CSV, más O(1) promedio para agregar al mapa.

### Registrar Beca
- **Descripción**: Permite registrar una nueva Beca a través de Ventanas.
- **Complejidad temporal**: O(1) para validaciones y escritura en CSV, más O(1) promedio para agregar al mapa.

### Registrar Postulaciones
- **Descripción**: Permite registrar una nueva Postulacion a través de Ventanas.
- **Complejidad temporal**: O(1) para validaciones y escritura en CSV, más O(1) promedio para agregar al mapa.

### Filtrar Postulaciones por Estado/Tipo
- **Descripción**: Permite filtrar las postulaciones por los 3 estados disponibles (En espera, Aprobada y Rechazada), ademas de los dos tipos de Becas disponibles (beca de arancel y beca de manutención).

### Mostrar Datos Estudiante
- **Descripción**: Permite mostrar los datos de un estudiante a traves de su rut, ademas de mostrar las postulaciones vinculadas a este estudiante.
- **Complejidad Temporal**: O(1) para encontrar el estudiante y mostrar sus datos, y O(n) para mostrar su lista de postulaciones.

### Buscar Postulaciones Estudiante
- **Descripción:** Permite buscar postulaciones de estudiantes mediante su rut y el ID de la postulacion, dependiendo de dos casos posibles:
  1. Si el usuario deja el ID de postulacion vacio muestra todas las postulaciones del Estudiante.
  2. Si el usuario proporciona un ID de postulacion solo se muestra la postulacion con ese ID.
- **Complejidad temporal**: O(1) promedio para buscar un estudiante en el mapa, más O(m) para recorrer las m postulaciones del estudiante en ambas opciones.

### Buscar Postulaciones Beca
- **Descripción:** Permite buscar postulaciones de Becas mediante su ID de beca y el ID de la postulacion, dependiendo de dos casos posibles:
  1. Si el usuario deja el ID de postulacion vacio muestra todas las postulaciones de la beca.
  2. Si el usuario proporciona un ID de postulacion solo se muestra la postulacion con ese ID.
- **Complejidad temporal**: O(1) promedio para buscar una beca en el mapa, más O(m) para recorrer las m postulaciones de la beca en ambas opciones.

### Buscar Beca
- **Descripción**: Permite mostrar los datos de una beca a traves de su ID, ademas de mostrar las postulaciones vinculadas a esta beca.
- **Complejidad Temporal**: O(1) para encontrar la beca y mostrar sus datos, y O(n) para mostrar su lista de postulaciones.

### Modificar
- **Descripción**: Se abre un nuevo submenú con las siguientes opciones vinculadas a la modificación de datos:
   1.Modificar Alumno: permite modificar los datos vinculados al estudiante a traves de una ventana especifica.
   2.Modificar Postulación: permite modificar el estado y la fecha de la postulación, esto a traves del rut del estudiante y el id de la beca.
   3.Modificar Beca: permite modificar los datos vinculados a las becas, incluyendo los datos de la subclase segun que tipo de beca sea, excluyendo el ID de la beca.
- **Complejidad Temporal**: para Modificar un alumno o beca la complejidad es de O(1) ya que soo debemos encontrar a estos con los mapas, para Modifcar postulacion es de O(n) porque debemos recorrer la lista de postulaciones del alumno hasta encontrar la ID de Beca y modifica la postulacion correspondiente.

### Eliminar Datos
- **Descripción**: se abre una mini ventana con opciones de eliminacion para las becas, estudiantes y postulaciones:
   1.Eliminar Postulación por rut  y beca: permite eliminar una postulacion especifica, esto a traves del rut del estudiante y del id de la beca, eliminandolo de la lista de estos.
   2.Elimnar Estuadiante: permite eliminar Estudiantes, eliminandolo del mapa correspondiente y eliminando las postulaciones vinculadas a estes.
   3.Eliminar Beca: permite eliminar una beca del mapa de becas, eliminando las postulaciones vinculadas a esta.
- **Complejidad Temporal**: O(1) para eliminar un estudiante o beca y O(n) para eliminar un postulación, ya que, se debe iterar hasta encontrar la postulación correspondiente

### Generar Reporte TXT
- **Descripción**: Generá un reporte basado en los datos de estudiantes, becas y postulaciones.
- **Complejidad temporal**: O(n) ya que itera sobre todos los datos.



## Notas y Consideraciones
- El sistema utiliza `HashMap` para un acceso eficiente a estudiantes, becas y postulaciones.
- Los archivos CSV deben seguir el formato especificado (e.g., `rut,nombre,correo,...` con `;` en el campo `direccion`).
- Si hay problemas al compilar, verifica que las rutas de los archivos CSV (`src/resources`) sean correctas.
- Para reflejar cambios en los CSV o releerlos, reinicia el programa.
- El sistema incluye validaciones robustas para formatos de RUT, correos, y rangos numéricos, manejando excepciones como `IOException` y `NumberFormatException`.
- Los campos de texto (nombre, carrera, institución) escapan comas para evitar errores en el formato CSV.

## Mejoras Sugeridas
- Implementar exportación/importación avanzada de reportes en formatos como PDF o Excel.
- Permitir la edición masiva de estudiantes, becas o postulaciones.
- Integrar una base de datos (e.g., MySQL o SQLite) en lugar de archivos CSV para una mejor persistencia.
- Generar gráficos (e.g., número de postulaciones por estudiante) usando una librería como JFreeChart.

# Prueba TecnicaBCNC - AdrianRuizOlivero
# Proyecto de Evaluación Técnica: API de Precios

## Descripción del Proyecto
Este proyecto es una implementación de un sistema de consulta de precios para un producto en función de parámetros como la fecha de aplicación, el identificador del producto y la marca. La solución ha sido diseñada y desarrollada aplicando principios **SOLID** y **Clean Code**, maximizando la claridad y la eficiencia, y empleando patrones de diseño que aseguran la separación de responsabilidades y la mantenibilidad del código.

### Objetivo
Proporcionar un endpoint REST que, dado:
- Fecha y hora de consulta.
- `productId` (identificador del producto).
- `brandId` (identificador de la marca).
  Devuelva el precio aplicable con mayor prioridad.

---

## Tecnologías Usadas

- **Spring Boot**: Framework principal para desarrollar el backend.
- **H2 Database**: Base de datos en memoria para pruebas rápidas.
- **Lombok**: Reducción de código repetitivo para getters, setters y constructores.
- **JaCoCo**: Medición de cobertura de pruebas.
- **MockMvc**: Pruebas de integración para controladores.
- **JUnit 5**: Framework de pruebas unitarias.
- **Maven**: Gestión de dependencias y construcción del proyecto.
- **SonarLint**: Análisis de calidad del código estático.

---

## Arquitectura

### Estilo de Arquitectura: Hexagonal
Se optó por implementar una **arquitectura hexagonal** para maximizar la separación de responsabilidades y facilitar pruebas y cambios futuros. Este enfoque permite desacoplar los casos de uso del núcleo de negocio de las tecnologías externas como la base de datos o el framework web.

#### Componentes Principales:
1. **Adaptadores de Entrada**:
    - Controladores REST (`PriceController`).
2. **Casos de Uso**:
    - Servicios que implementan la lógica de negocio (`PriceService`).
3. **Adaptadores de Salida**:
    - Repositorios (`PriceRepository`) que interactúan con la base de datos.
4. **Core del Dominio**:
    - Entidades (`PriceEntity`, `ProductEntity`, `BrandEntity`).
    - DTOs (`PriceDTO`, `ProductDTO`, `BrandDTO`).
    - Clases abstractas (`BaseEntity`, `BaseDTO`).

---

## Patrones de Diseño

### Principios y Patrones Aplicados
1. **Principios SOLID**:
    - **Responsabilidad Única**: Cada clase tiene una responsabilidad única (e.g., `PriceController` solo maneja la lógica del endpoint REST).
    - **Inversión de Dependencias**: Uso de interfaces como `IRepository` para abstraer la interacción con la base de datos.
2. **DTOs y Entidades**:
    - Separación clara entre los objetos utilizados en la lógica del negocio (`Entity`) y los datos enviados/recibidos a través del API (`DTO`).
3. **Copiers Abstractos**:
    - Transformación entre entidades y DTOs a través de una clase base (`AbstractCopier`).
4. **GlobalExceptionHandler**:
    - Gestión centralizada de excepciones para mantener el código del controlador limpio.
5. **Template Method**:
    - Uso en `getRepository()` y `getCopier()` dentro de `PriceService` para definir la estructura general de las operaciones de acceso a datos y transformaciones, delegando los detalles específicos a las subclases.
6. **Builder**:
    - Utilización del Patrón Builder proporcionado por Lombok para crear instancias de DTOs y Entidades de manera eficiente y legible.

---

## Implementación de la Solución

### Endpoint REST
- **Ruta**: `/api/prices/applicable`
- **Método**: GET
- **Parámetros**:
    - `productId`: ID del producto.
    - `brandId`: ID de la marca.
    - `date`: Fecha en formato ISO (`yyyy-MM-dd'T'HH:mm:ss`).
- **Respuesta**:
    - JSON con el precio aplicable o un error HTTP 404 si no se encuentra un precio.

Ejemplo:
```json
{
  "id": 1,
  "brandId": 1,
  "productId": 1,
  "startDate": "2020-06-14T00:00:00",
  "endDate": "2020-12-31T23:59:59",
  "price": 35.50,
  "currency": "EUR"
}
```

---

## Pruebas

### Cobertura de Pruebas
- **Pruebas Unitarias**:
    - Validación de la lógica en `PriceService`.
    - Simulación de datos con Mockito para pruebas precisas.
- **Pruebas de Integración**:
    - Validación de flujo completo desde el controlador hasta la base de datos usando MockMvc y H2.
- **Cobertura con JaCoCo**:
    - **Cobertura total alcanzada**: 100%.

### Casos de Prueba
Se implementaron los casos indicados en el enunciado, incluyendo:
1. Petición a las 10:00 del día 14 para el producto `1` y `brand 1`.
2. Petición a las 16:00 del día 14 para el producto `1` y `brand 1`.
3. Petición a las 21:00 del día 14 para el producto `1` y `brand 1`.
4. Petición a las 10:00 del día 15 para el producto `1` y `brand 1`.
5. Petición a las 21:00 del día 16 para el producto `1` y `brand 1`.

---

## Eficiencia y Optimización

1. **Consultas SQL**:
    - Uso de índices en `productId`, `brandId`, y `date` para optimizar las búsquedas.
    - Ordenamiento por prioridad directamente en la consulta.
2. **Delegación de Responsabilidades a la Base de Datos**:
    - Lógica compleja como filtrado por rango de fechas y ordenamiento gestionada por la consulta SQL.

---

## Gestión del Código en GitHub

### Estrategia
- **Ramas**:
    - Uso de ramas separadas (`develop`).
    - Una idea que se tuvo en cuenta lo que daba más trabajo era separar en features por cada funcionalidad (`feat/service`, `feat/entities`, ... )
- **Tags**:
    - Marcado de versiones clave (`v1.0.0`).
- **Commits**:
    - Mensajes claros y significativos, que aportan en ciertos momentos aplicaciones apropiadas para poder comprender mejor lo realizado.
---

## Herramientas de Calidad

1. **SonarLint**:
    - **Alertas corregidas**: 0 alertas activas tras análisis final.
2. **JaCoCo**:
    - Cobertura superior al 80%, cumpliendo el umbral definido.

---

## Conclusión
Este proyecto demuestra un enfoque sólido hacia el desarrollo limpio, escalable y mantenible. Aunque hay muchas formas de abordar este test, el diseño implementado prioriza los principios de SOLID, la separación de responsabilidades y la eficiencia en la consulta de datos, alineándose con los criterios de evaluación propuestos.

¿Dudas o mejoras? ¡Estaré encantado de debatirlas!


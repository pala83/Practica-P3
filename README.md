# Proyecto de Soluciones de Trabajos Prácticos

Este repositorio contiene las soluciones a los trabajos prácticos de la materia Programación 3.
Cada trabajo práctico está organizado en su propia rama siguiendo la semántica:

* `TP1`
* `TP2`
* `TP3`
* `TP4`
* `TP5`

Cada rama incluye la implementación de los ejercicios correspondientes al TP.

---

## Estructura de Ramas

| Rama  | Descripción                       |
| ----- | --------------------------------- |
| `TP1` | Soluciones del Trabajo Práctico 1 |
| `TP2` | Soluciones del Trabajo Práctico 2 |
| `TP3` | Soluciones del Trabajo Práctico 3 |
| `TP4` | Soluciones del Trabajo Práctico 4 |
| `TP5` | Soluciones del Trabajo Práctico 5 |

---

## Cómo Reportar Issues

Puedes crear Issues para reportar bugs, sugerir mejoras o solicitar nuevas funcionalidades.
Al crear un Issue, utiliza la siguiente convención en el título para identificar rápidamente a qué TP y ejercicio se refiere:

```
TP<numero> [Ej<numero>] - Breve descripción del problema
```

**Ejemplo:**

```
TP5 [Ej2] - Falta manejo de cero en la división
```

---

## Colaboraciones y Pull Requests

Las contribuciones mediante Pull Request (PR) son bienvenidas. Para contribuir al proyecto, sigue estos pasos:

1. Haz *fork* del repositorio.
2. Clona tu *fork* localmente:

   ```bash
   git clone https://github.com/tu-usuario/tu-repo.git
   ```
3. Crea una nueva rama basada en la rama correspondiente al TP:

   ```bash
   git checkout -b TP<n>-<descripcion-corta>
   ```

   Por ejemplo:

   ```bash
   git checkout -b TP5-corregir-division-cero
   ```
4. Realiza tus cambios y haz *commit*:

   ```bash
   git add .
   git commit -m "TP5 [Ej2] - Agrega validación de división por cero"
   ```
5. Empuja tu rama al *fork* remoto:

   ```bash
   git push origin TP<n>-<descripcion-corta>
   ```
6. Abre un Pull Request hacia la rama original del repositorio (`TP<n>`).
7. Cuando este aburrido reviso las pull requests y las incorporo si estan bien.
8. Este readme fue creado por una IA, asi que tambien se aceptan PR del readme.

---
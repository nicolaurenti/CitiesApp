# 🌍 Cities App  

## 📖 Description  
Cities App es una aplicación desarrollada para mostrar una lista de ciudades y permitir a los usuarios gestionar una  
selección de **"ciudades favoritas"**. Además, proporciona funcionalidades de **búsqueda eficiente**, permitiendo filtrar tanto  
ciudades generales como favoritas.  

---

## 🚀 Main Technologies  

| **Category**                  | **Technologies**                                          |
|--------------------------------|----------------------------------------------------------|
| **UI Design**                  | Jetpack Compose                                          |
| **Navigation**                 | Navigation Compose                                      |
| **Maps & Geolocation**         | Google Maps API                                         |
| **Dependency Injection**       | Hilt                                                   |
| **Concurrency & Async Operations** | Coroutines                                        |
| **Local Storage (Database)**   | Room                                                   |
| **Networking (API REST)**      | Retrofit                                               |
| **JSON Serializer/Deserializer** | Gson                                              |
| **Testing**                    | MockK (Unit Testing), Jetpack Compose Testing (UI Testing) |

---

## 🏗 Architecture  
Se implementó una **Clean Architecture** como estructura de paquetes, mientras que en la capa de presentación se utilizó **MVVM**.  
A continuación, se detalla cada una de las capas.  

### 📌 Layers View  
<div align="start">
<img width="500" alt="Architecture Layers View" src="https://github.com/user-attachments/assets/38ab5e98-60d1-4834-90eb-8fa0b9780321">
</div>

### 📁 Files View  
<div align="start">
<img height="400" alt="Files Structure" src="https://github.com/user-attachments/assets/5633484d-2048-468e-93bc-eb15fc74934c">
</div>

---

### 🔹 Layer Breakdown  

#### **📌 Presentation Layer**  
Capa encargada del manejo de **pantallas** e interacción con el usuario.  
Se utilizó **MVVM** (Model-View-ViewModel) junto con **StateFlow** para la comunicación entre el **ViewModel** y la vista.  

📌 **Incluye:**  
- Componentes en **Jetpack Compose**  
- **Pantallas**  
- Clases relacionadas a la **navegación** (**Navigation Compose**)  
- **Activities, Application y ViewModels**  

Este módulo **conoce la capa Domain** y es un **módulo Android**.

---

#### **📌 Domain Layer**  
Encargada de la **lógica de negocio**, es **agnóstica a la tecnología**, por lo que **no tiene dependencias de Android**.  

📌 **Incluye:**  
- **Use Cases**  
- **Modelos**  
- **Interfaces de repositorios**  
- **Handlers de respuestas**  

Es un **módulo Kotlin**, **no conoce los otros módulos de la aplicación**, lo que permite reutilizar la lógica de negocio en diferentes aplicaciones.  
Un caso de uso sería integrar este módulo en una aplicación **Kotlin Multiplatform (KMP)**.

---

#### **📌 Data Layer**  
Responsable de **obtener y almacenar información**. En este caso, maneja datos provenientes de un **JSON** y su almacenamiento local.  

📌 **Incluye:**  
- Implementación de **repositorios**  
- **Service**  
- **Base de datos y DAO**  
- **Modelos, responses y API**  

Es un **módulo Android** y **solo conoce la capa Domain**.

📌 **NOTA:** Cada capa tiene su carpeta **"di"** donde se encuentran los módulos de **Hilt**.

---

## 🗄 Database and optimization  
Para optimizar la carga de información y **mantener la persistencia de datos**, se utilizó una **base de datos local**.  
Se implementaron **queries** eficientes para obtener información sin necesidad de cargar todos los datos en el **StateFlow** del ViewModel.

---

## 🛠 Testing  

### ✅ **Unit Testing (UT)**  
#### ViewModel Test
![image](https://github.com/user-attachments/assets/c8d15a8c-9af0-4945-a4e6-57087c5746df)
#### UseCase Test
![image](https://github.com/user-attachments/assets/b95c26d5-a81f-456f-8ec1-0a3b9a798642)

---

### ✅ **UI Testing**  
#### CitiesListScreen Test
![image](https://github.com/user-attachments/assets/17347dfa-43be-48c2-b650-f1b8955a0d8b)

#### CityDetailScreen Test
![image](https://github.com/user-attachments/assets/8389e986-e286-455a-8eb4-3162c543dd4e)

---

## 📸 Screenshots  

#### Cities List


https://github.com/user-attachments/assets/424a0029-6a67-4ada-b466-8505cf36f74b


#### City Detail


https://github.com/user-attachments/assets/0a535ef1-995e-4e97-a7f4-e79b2be71937


#### Favorites


https://github.com/user-attachments/assets/000091ec-361a-4fc0-8c99-3c1470ee2f82



https://github.com/user-attachments/assets/eb89486c-90b1-4f3b-be2d-0fb4a89418bd



#### Search


https://github.com/user-attachments/assets/386630e9-1d23-4eba-8bdd-598bffa445b0


---

## ⚠️ Consideraciones  

- Se agregó la **API key de Google Maps** en `local.properties` para evitar exponerla públicamente y mejorar la seguridad.  
- Se aplicaron los principios **SOLID** en el desarrollo.  
- Se utilizó el patrón **Repository** para la gestión de datos en la capa Data.  
- Se adoptó una estrategia **Cache-First**, priorizando la información almacenada localmente, ya que los datos del **gist** no se actualizan dinámicamente.  

# 🌸 Code-Blossom: E-Commerce Floreale 🌸

Code-Blossom è un progetto di e-commerce floreale sviluppato con Spring Boot, realizzato nell’ambito del corso Cod(H)er di Generation. L'obiettivo è offrire una piattaforma intuitiva per l'acquisto e la gestione di fiori e composizioni floreali, sia per privati che per attività commerciali.

## Funzionalità principali 📌

| Area libera                          | Area amministrativa                            |
|--------------------------------------|----------------------------------------------|
| Funzione Cerca                       | Pulsanti per acquistare articoli floreali   |
| Elenco punti vendita                 | Carrello                                    |
| Catalogo articoli floreali           | Storico acquisti                            |
| Accesso login/registrazione          | Logout                                           |


## Tecnologie Utilizzate 💻

| Tecnologia    | Badge                     |
|---------------|---------------------------|
| HTML          | [![HTML5](https://img.shields.io/badge/html5-%23E34F26.svg?style=for-the-badge&logo=html5&logoColor=white)](https://www.w3.org/html/) |
| CSS           | [![CSS3](https://img.shields.io/badge/css3-%231572B6.svg?style=for-the-badge&logo=css3&logoColor=white)](https://www.w3.org/Style/CSS/)|
| JavaScript    | [![JavaScript](https://img.shields.io/badge/javascript-%23323330.svg?style=for-the-badge&logo=javascript&logoColor=%23F7DF1E)](https://www.javascript.com/)|
| Java          | [![Java](https://img.shields.io/badge/java-%23ED8B00.svg?style=for-the-badge&logo=java&logoColor=white)](https://www.java.com/it/)|
| Spring Boot   | [![Spring](https://img.shields.io/badge/spring-%236DB33F.svg?style=for-the-badge&logo=spring&logoColor=white)](https://spring.io/)|
| MySQL/phpMyAdmin | [![MySQL](https://img.shields.io/badge/mysql-%2300f.svg?style=for-the-badge&logo=mysql&logoColor=white)](https://www.mysql.com/) |

## Strumenti Utilizzati 💻

| Strumenti    | Badge                     |
|--------------|---------------------------|
| IntelliJ IDEA | [![IntelliJ IDEA](https://img.shields.io/badge/IntelliJ%20IDEA-%23000000.svg?style=for-the-badge&logo=intellij-idea&logoColor=white)](https://www.jetbrains.com/idea/) |
| Visual Studio | [![Visual Studio](https://img.shields.io/badge/Visual%20Studio-%235C2D91.svg?style=for-the-badge&logo=visual-studio&logoColor=white)](https://visualstudio.microsoft.com/) |


## Team di Sviluppo ‍💻👩🏻

*   Rossella Martino (rossellamrtn@gmail.com)
*   Maria Parasiliti Palumbo(marypp.work@gmail.com)
*   Miriana Francipane(francipanem@gmail.com)
*   Emanuela Di Giacomo(emanuela.digiacomo3@gmail.com)
*   Federica Greco(effegi.044377@gmail.com)
*   Chiara Dragotta(chiaradragotta@gmail.com)


## Installazione e Configurazione 🛠️

1.  **Clonare il repository:**
    ```bash
    git clone [URL del repository GitHub]
    cd code-blossom
    ```

2.  **Database (MySQL/phpMyAdmin):**
    *   Importare `database/code-blossom.sql` in phpMyAdmin.

3.  **Backend (Java/Spring):**
  
    *   Aggiungere `src/main/resources/application.properties`:
        ```properties
        spring.datasource.url=jdbc:mysql://localhost/code-blossom
        spring.datasource.username=[tuo username]
        spring.datasource.password=[tua password]
        ```
5. **Esecuzione:**
   
   * Lanciare il progetto su Intellij.
     
## Presentazione (Demo) 🎬

1.  **Nuovo Account:** Mostrare il processo di registrazione.
2.  **Login:** Accedere con un account esistente.
3.  **Acquisto prodotti:** Aggiungere/rimuovere prodotti dal carrello.
4.  **Storico Acquisti:** Visualizzare gli ordini fatti precedentemente dall'utente registrato.

## Note Aggiuntive ⚠️

*   Progetto demo, richiede ulteriori sviluppi per produzione.

## ТЗ выполнено.

### Описание работ
- Приложение работает на Spring Cloud: 3 отдельных микросервиса (Eureka, Backend, Frontend).
- Бэкенд работает c БД PostgreSQL 12 посредством Spring Data JPA. 
- Миграциями управляет Liquibase.
- Реализовано кэширование с помощью Redis. 
- Подключено логирование logback.
- Фронтенд реализован на шаблонах Thymeleaf с использованием HTML/CSS/JS.
- Добавлены Docker файлы для всех компонентов и оркестрация через docker-compose.yaml
- Приложение развёрнуто по адресу: http://89.111.137.56:8089


### Скриншот страницы
![](image.png)

### Самостоятельное развёртывание
Сначала нужно получить исходники: git clone https://github.com/pablos-v/Titanic
\
Далее, в файле Backend/src/main/resources/application.yaml в properties можно изменить 2 параметра:
\
URL загрузки файла - URL: https://web.stanford.edu/class/archive/cs/cs109/cs109.1166/stuff/titanic.csv
\
критерий совершеннолетия - adultAgeCriteria: 16 

#### Далее есть 2 варианта развёртывания
1. Запустить команду docker-compose up.
2. Для развёртывания приложения необходимо иметь запущенные БД Postgres и Redis, соответствующие описанию из
   docker-compose.yaml
   \
   Модули запускаются вручную консольными командами из общей папки:
   - mvn -f Eureka clean package && java -jar Eureka/target/Eureka-0.0.1.jar
   - mvn -f Backend clean package && java -jar Backend/target/Backend-0.0.1.jar
   - mvn -f Frontend clean package && java -jar Frontend/target/Frontend-0.0.1.jar

Страница со списком пассажиров будет доступна по адресу: http://localhost:8089

### Примечания
По пагинации: изначально хотел реализовать через Thymeleaf, чтобы каждая страница формировалась новыми запросами типа /?page=
\
Но потом подумал, что каждый раз слать запрос и отрисавывать страницу при таком малом объёме данных - так себе идея. 
\
Лучше сразу передавать весь отфильтрованный список, и уже на месте сделать пагинацию через JS. 
\
Сам я на JS писать не умею - нашёл готовое решение и подружил с шаблоном Thymeleaf.

### Возможные оптимизации
- бэкенд в ответе возвращает индикаторы фильтров, которые были в запросе - на их основе можно сделать на странице отображение всех параметров отправленного запроса - сейчас это можно понять только по адресной строке, а все фильтры сбрасываются на значения по умолчанию.
- создать стартер содержащий дублирующиеся DTO и Enum, чтобы избежать дублирования кода
- свести переменные вроде Url файла и критерия совершеннолетия в .env файл, и добавить их в environment докерфайлов


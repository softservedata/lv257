# Service "Resources" Demo version

Resources is an open source service with a web interface0 and a WebAPI. It lets easily manage resources registered and located in the country, look up resources by type, properties or owners and manage ownership relations.

# Architecture

The application's backend is developed using Spring and JPA/Hibernate. 

View technology may vary for each framework. Here, JSP views are (re)used for most of current implementations. Thymeleaf is also planned to be used as alternative to JSPs.

URLs under /users, /roles etc. must be accessible to only logged users with right privileges. This requirement implemented using Spring Security

# Build and run
1. Check out the project source code from github : git clone https://github.com/benas/todolist-mvc.git
2. Import resources_MYSQL_DB.sql to newly created schema named mydatabase
3. Open a terminal and run the following command from root directory : mvn install
4. Choose a web framework to test and run it. For example : cd todolist-web-springmvc && mvn tomcat7:run
5. Browse the following URL : localhost:8080/
6. You can register a new account or login using the following credentials : dbuser1 / 12345


# Todo



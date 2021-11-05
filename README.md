# CS201

## Set Up

1. Create a MYSQL Database named `db_cs201`
2. Run the application with the following environment variables
     1. MYSQL_HOST: Url of MYSQL server used to host the database
     2. MYSQL_PASSWORD: Password to the MYSQL server
     3. GOOGLE_API_KEY: API Key from Google Maps Platform
3. Put dataset ```result.json``` in ```target``` folder
4. To start the application, run: `./mvnw spring-boot:run` for Unix Terminals or `mvn spring-boot:run` for Windows Command Prompt
5. Visit `http://localhost:8080/load` to seed database with the dataset we will be using 

**Alternatively, execute the ```db_cs201_business.sql``` file to setup the database. This would allow you to skip steps 3 and 5, going straight to step 4.**

6. Follow the Next.js client README.md to setup the frontend
7. Open `http://localhost:3001` with your browser to access the application

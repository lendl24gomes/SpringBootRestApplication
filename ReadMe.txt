Download the code and navigate to the folder in the command prompt and run the mvn clean package command.

Then run the following command in command prompt:-
java -jar target/SpringBootWebService-0.0.1-SNAPSHOT.jar. 
The application should start without any errors and run on http://localhost:2400

In postman access URL http://localhost:2400/assignment with http Get method. This will return a JSON object with the content of https://jsonplaceholder.typicode.com/posts but with the title and body content on the reverse order in which is given.

In postman access URL http://localhost:2400/ingest with http Post method. Here we will also need to provide a body which will be the text to be displayed on the newly created image. In the Response a URL is returned which is to be called via a http GET method.

The images are created in C:\Server\Image folder. This directory structure will be created by the application automatically if it doesnt exist and the image will be created in this folder path from where we can download it.


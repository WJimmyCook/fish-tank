# Fish Tank
Fish Tank is a Java Web App built with Maven and Spring MVC. The frontend is built with Bootstrap. MySQL is used for persistent storage.

# Overview
<img src="/images/fish1.png" width="600">  
From this home screen you can add, edit, delete, or see more information on a fish.

<img src="/images/fish2.png" width="600">  
When you click on the fish name link this is the modal that pops up showing more information. To get this info an AJAX request is sent to a REST endpoint getting the fish object.

<img src="/images/fish3.png" width="600">  
This modal pops up when clicking on the edit link. This information is retrieved by an AJAX request to a REST endpoint. The returned info is filled in this editable form. Upon clicking the Edit Fish button an AJAX
PUT request is sent to the server to update the database.

<img src="/images/fish4.png" width="600">  
Utilizing Google Charts I added this bar graph showing the count of each fish type.

<img src="/images/fish5.png" width="600">  
Search page works via an AJAX request and SQL queries.

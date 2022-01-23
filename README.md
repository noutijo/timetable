# timtable
`timtable` is a desktop application for automatic generate timetables.

>Watch the demonstration on youTube: [timtable](https://youtu.be/HZGmThOR4FM)

# libs

For the functioning of the application, it requires that some libraries are integrated in the project. So, you have to integrate the libraries that are in the `/libs` folder in your project.
Note that this project is designed with `JDK 1.8`.

# configuration & database
The project needs to be connected to a database to work, so we used MYSQL.
You must first import the `.sql` file in the `/bd` folder into your database server.

Then you will modify `YOUR PASSWORD` in the file `AvoirConnection.java` at the root `src/cm/supptic/avoirConnection` by your own password to connect to the server. Leave a blank field if you don't have a password.

```java
   public class AvoirConnection {

    public Connection getConnnection() throws SQLException, ClassNotFoundException {

        Connection myConne = null;
        Class.forName("com.mysql.jdbc.Driver");
        myConne = DriverManager.getConnection("jdbc:mysql://127.0.0.1:3306/supptic", "root", "YOUR PASSWORD");
        System.out.println("Connecté !");

        return myConne;
    }
}
 ```

# login
Once you have launched the application you must log to manage.

Default id: `oreol`
Default password: `oreol`

![](/imgs/login.png)

# administrator

Some administrator side features

`Teachers list `

![](/imgs/teacherslist.png)

`View teacher infos`

![](/imgs/consultteacherinfos.png)




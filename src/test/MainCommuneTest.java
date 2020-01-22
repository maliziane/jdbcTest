package test;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

public class MainCommuneTest {

	public static void main(String[] args) throws ClassNotFoundException, SQLException {
		//nom de la classe du driver
		String driver= "com.mysql.cj.jdbc.Driver";
		//URL vers la BDD "protocol principal: proto secondaire : ordinateur:port d'écoute serveur/BDD  
		String url="jdbc:mysql://localhost:3306/communes-france";
		// utilisateur
		String user= "root";
		//mdp
		String pswd="";
		
		//chargement du driver: dire a la machine virt d'aller chercher le driver dans le class path
		//chargement du driver en mémoire
		//une fois pour toute l'application
		// automatiquement s'incrit aupres du driver manager sous le nom mysql
		Class.forName(driver);
		
		//pour interroger la base
		//1-obtenir une connexion
		//getConnection est une methode static (de la classe driver manager)
		//Classe Connexion java.sql dans les proposition
		Connection connection = DriverManager.getConnection(url,user,pswd);
		//2- creation de la requete SQL
		String sql="SELECT * FROM communes WHERE code_postal LIKE '9721%'";
		//3- recuperer statement (environnement de requetage)
		Statement statement= connection.createStatement();
		//4- exécution de la requete qui nous renvoie un resultset
		ResultSet rs= statement.executeQuery(sql);
		
		//exploitation du resultat
		while(rs.next()) {
			String nom=rs.getString("nom");
			String cp=rs.getString("code_postal");
			System.out.println(cp+" - "+nom);
		}
		
		//fermer la connexion
		connection.close();
		
		
		
	}

}

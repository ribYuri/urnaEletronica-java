package urna.dao;


import java.sql.Connection;
import java.sql.DriverManager;

public class PostgreSqlConnection {
	
	private String url = "jdbc:postgresql://localhost:5432/postgres";
	private String usuario = "postgres";
	private String senha = "1234";
	
	public Connection getConnection (){
		Connection con = null;
		
		try {
			Class.forName("org.postgresql.Driver");
			con = DriverManager.getConnection(this.url, this.usuario, this.senha);
		} catch (Exception e) {
			e.printStackTrace();
		}
		
		return con;
	}

}

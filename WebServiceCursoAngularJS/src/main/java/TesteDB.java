import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

public class TesteDB {

	public static void main(String[] args) {
		try {
	           Connection con = getConnection();
	           PreparedStatement stm = con.prepareStatement("SELECT * FROM usuarios");
	           ResultSet rs = stm.executeQuery();
	           while(rs.next()){
	               System.out.println(rs.getString("loginUsuario"));
	           }           
	        } catch (SQLException ex) {
	            ex.printStackTrace();
	        }
	}

	public static Connection getConnection() throws SQLException {
		try {
			Class.forName("org.h2.Driver"); // driver para h2 db
			return DriverManager.getConnection("jdbc:h2:tcp://localhost:9001/db/meudb", "sa", "123"); // retorna
																										// conexao
																										// h2
																										// db
		} catch (ClassNotFoundException e) {
			e.printStackTrace();
			throw new SQLException(e);
		}
	}
}

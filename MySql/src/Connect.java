import java.sql.*;

public class Connect {
    private String kullaniciAdi = "root";
    private String parola = "";
    private String dbIsmı = "demo";
    private String host = "localhost";
    private int port = 3306;
    private Connection con = null;
    private Statement statement = null;

    public void addTable() {
        try {
            statement = con.createStatement();
            String ad = "name";
            String soyAd = "surName";
            String email = "semihaktas@gmail.com";
            String sorgu = "INSERT INTO calisanlar (ad, soyad, email) VALUES ('" + ad + "', '" + soyAd + "', '" + email + "')";
            statement.executeUpdate(sorgu);
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }

    public void getTable() {
        String sorgu = "Select * From calisanlar";
        try {
            statement = con.createStatement();
            ResultSet rs = statement.executeQuery(sorgu);
            while (rs.next()) {
                int id = rs.getInt("id");
                String ad = rs.getString("ad");
                String soyad = rs.getString("soyad");
                String email = rs.getString("email");
                System.out.println(" Id :" + id + " Ad :" + ad + " Soyad :" + soyad + " Email :" + email);
            }
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }

    public void updateTable() {
        try {
            statement = con.createStatement();
            String sorgu = "Update calisanlar Set email = 'mysql@gmail.com' where id=1";
            statement.executeUpdate(sorgu);
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }

    public void deleteTable() {
        try {
            statement = con.createStatement();
            String sorgu = "Delete from calisanlar where id=5";
            statement.executeUpdate(sorgu);
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }

    public Connect() {
        String url = "jdbc:mysql://" + host + ":" + port + "/" + dbIsmı + "?useUnicode=true&characterEncoding=utf8";
        try {
            Class.forName("com.mysql.cj.jdbc.Driver");
        } catch (ClassNotFoundException e) {
            throw new RuntimeException(e);
        }
        try {
            con = DriverManager.getConnection(url, kullaniciAdi, parola);
            System.out.println("Baglantı Basarili.");
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }

    public static void main(String[] args) {
        Connect connect = new Connect();
        System.out.println("Table is not added");
        connect.getTable();
        System.out.println("**************************");
        connect.addTable();
        System.out.println("Table is added");
        connect.getTable();
        System.out.println("**************************");
        connect.updateTable();
        System.out.println("Table Is Updated...");
        connect.getTable();
        System.out.println("**************************");
        connect.deleteTable();
        System.out.println("The deleted version of the fifth element from the table.");
        connect.getTable();
    }
}

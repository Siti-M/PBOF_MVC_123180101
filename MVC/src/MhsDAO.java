
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import javax.swing.JOptionPane;

/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

/**
 *
 * @author ASUS
 */
public class MhsDAO {
    private int jmlData;
    private Connection koneksi;
    private Statement statement;
    private Object sql;
    //constructor berfungsi untuk melakukan sebuah koneksi saat ada object baru dibuat
    public MhsDAO(){
        try {
            Class.forName("com.mysql.jdbc.Driver");
            String url ="jdbc:mysql://localhost/mahasiswa";
            koneksi = DriverManager.getConnection(url, "root", "");
            statement = koneksi.createStatement();
            JOptionPane.showMessageDialog(null, "Koneksi Berhasil");
        } catch (ClassNotFoundException ex) {
            JOptionPane.showMessageDialog(null, "Class Not Found : " +ex);
        } catch (SQLException ex) {
            JOptionPane.showMessageDialog(null, "Sql Exception : " +ex);
        }
    }
    
    public void Insert(MhsModel Model){
        try {
            String query = "INSERT INTO data_mhs VALUES ('"+Model.getNama()+"','"+Model.getNim()+"',"
                + ""+ "'"+Model.getAlamat()+"')";
            statement.executeUpdate(query); //execute query
            JOptionPane.showMessageDialog(null, "Data disimpan");
        } catch (Exception sql) {
            JOptionPane.showMessageDialog(null, sql.getMessage());
        }
        
    }
    
    public void Update(MhsModel Model){
        try {
            String query = "UPDATE data_mhs SET nim = '"+Model.getNim()+"', nama = '"+Model.getNama()+"',"
                + "alamat = '"+Model.getAlamat()+"' WHERE nim = '"+Model.getNim()+"'";
            statement.execute(query);
            JOptionPane.showMessageDialog(null, "Berhasil diupdate");
        } catch (SQLException sql) {
            System.out.println(sql.getMessage());
        }
    }
    
    public void Delete(MhsModel Model){
        try {
            String query = "DELETE FROM data_mhs WHERE nim = '"+Model.getNim()+"'";
            statement.executeUpdate(query);
            JOptionPane.showMessageDialog(null, "Berhasil dihapus");
        } catch (SQLException sql) {
            System.out.println(sql.getMessage());
        }
    }
    
    //untuk mengambil data dari db dan mengatur ke dalam tabel
    public String[][] readMahasiswa(){
        try {
            int jmlData=0; //menampung jumlah data
            //baris sejumlah data, kolom ada 3
            String data[][] = new String[getJmldata()][3];
            //pengambilan data dalam java dari db
            String query = "SELECT * FROM data_mhs";
            ResultSet resultSet = statement.executeQuery(query);
            while(resultSet.next()){ //lanjut kedata selanjutnya jmlData bertambah
                data[jmlData][0] = resultSet.getString("nim"); //kolom harus sama besar kecilnya dengan data
                data[jmlData][1] = resultSet.getString("nama");
                data[jmlData][2] = resultSet.getString("alamat");
                jmlData++; //barisnya berpindah terus
            }
            return data;
        } catch (SQLException e) {
            System.out.println(e.getMessage());
            System.out.println("SQL Error");
            return null;
        }     
    }

    public int getJmldata() {
        int jmData = 0;
        //pengambilan data ke java dari db
        try {
            String query = "SELECT * FROM data_mhs";
            ResultSet resultSet;
            resultSet = statement.executeQuery(query);
            while(resultSet.next()){
            jmlData++;
            }
            return jmlData;
        } catch (SQLException e) {
            System.out.println(e.getMessage());
            System.out.println("SQL Error");
            return 0;
        }
        
    }
}

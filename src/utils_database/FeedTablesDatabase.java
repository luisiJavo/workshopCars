package utils_database;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;

import org.json.JSONArray;
import org.json.JSONObject;


public class FeedTablesDatabase {
	
	public void executeInsertBrandsTableQuery(String sqlQuery, String brand) throws SQLException {
		ConnectionMysql SQL = new ConnectionMysql();
    	Connection conn = SQL.conectMySQL();
		
		try {
    		PreparedStatement stmt=conn.prepareStatement(sqlQuery);  
			stmt.setString(1,brand);  
			  
			int i=stmt.executeUpdate();  
			System.out.println(i+" records inserted");  
        	
		} catch (SQLException e) {
			e.printStackTrace();
		} finally {
			conn.close();
		}
	}
	
    public void insertIntoTableBrands(JSONObject jsonObj) throws SQLException {
    	ArrayList<String> brandsUniqueArrayList = new ArrayList<String>();
    	JSONArray jsonArray = jsonObj.getJSONArray("vehicleData");
    	
    	for (int i=0;i<jsonArray.length();i++){
    		String brand = jsonArray.getJSONObject(i).getString("brand");
            System.out.printf("Brand : %s \n", jsonArray.getJSONObject(i).getString("brand"));
            
            if(brandsUniqueArrayList.isEmpty() || !brandsUniqueArrayList.contains(brand)) {
            	brandsUniqueArrayList.add(brand);
            	String sqlQuery = "INSERT INTO brands (brand) VALUES(?)";
            	this.executeInsertBrandsTableQuery(sqlQuery, brand);
            }
        }
    }
 
    public void executeInsertModelsTableQuery(String sqlQuery, String model, int brand_id) throws SQLException {
		ConnectionMysql SQL = new ConnectionMysql();
    	Connection conn = SQL.conectMySQL();
		
		try {
    		PreparedStatement stmt=conn.prepareStatement(sqlQuery);  
			stmt.setString(1,model);
			stmt.setInt(2,brand_id);
			  
			int i=stmt.executeUpdate();  
			System.out.println(i+" records inserted");  
        	
		} catch (SQLException e) {
			e.printStackTrace();
		} finally {
			conn.close();
		}
	}
    
    public int getBrandID(String brand) {
    	ConnectionMysql SQL = new ConnectionMysql();
    	Connection conn = SQL.conectMySQL();
    	String sqlQuery = "SELECT id FROM brands WHERE brand = ?";
    	int id = 0;
		
		try {
			PreparedStatement stmt=conn.prepareStatement(sqlQuery);  
			stmt.setString(1,brand);
			ResultSet resultSet=stmt.executeQuery(); 
			while (resultSet.next()) {
	                System.out.print(resultSet.getInt("id") + "\n");
	                id = resultSet.getInt("id");
	            }
		} catch (SQLException e) {
			e.printStackTrace();
		}
		
		return id;
    }
    
    public void insertIntoTableModels(JSONObject jsonObj) throws SQLException {
    	JSONArray jsonArray = jsonObj.getJSONArray("vehicleData");
    	
    	for (int i=0;i<jsonArray.length();i++){
    		String brand = jsonArray.getJSONObject(i).getString("brand");
    		String model = jsonArray.getJSONObject(i).getString("model");
    		int brand_id = getBrandID(brand); 
            //System.out.printf("Brand : %s \n", jsonArray.getJSONObject(i).getString("brand"));
            String sqlQuery = "INSERT INTO models (model, brand_id) VALUES(?,?)";
            this.executeInsertModelsTableQuery(sqlQuery, model, brand_id);
            
        }
    }
    
    // table suppliers
    public void executeInsertSuppliersTableQuery(String sqlQuery, String supplier) throws SQLException {
		ConnectionMysql SQL = new ConnectionMysql();
    	Connection conn = SQL.conectMySQL();
		
		try {
    		PreparedStatement stmt=conn.prepareStatement(sqlQuery);  
			stmt.setString(1,supplier);  
			  
			int i=stmt.executeUpdate();  
			System.out.println(i+" records inserted");  
        	
		} catch (SQLException e) {
			e.printStackTrace();
		} finally {
			conn.close();
		}
	}
	
    public void insertIntoTableSuppliers(JSONObject jsonObj) throws SQLException {
    	ArrayList<String> brandsUniqueArrayList = new ArrayList<String>();
    	JSONArray jsonArray = jsonObj.getJSONArray("suppliersData");
    	
    	for (int i=0;i<jsonArray.length();i++){
    		String supplier = jsonArray.getJSONObject(i).getString("supplier");
            System.out.printf("supplier : %s \n", jsonArray.getJSONObject(i).getString("supplier"));
            
            if(brandsUniqueArrayList.isEmpty() || !brandsUniqueArrayList.contains(supplier)) {
            	brandsUniqueArrayList.add(supplier);
            	String sqlQuery = "INSERT INTO suppliers (supplier) VALUES(?)";
            	this.executeInsertBrandsTableQuery(sqlQuery, supplier);
            }
        }
    }
    
    // table tires
    public void insertIntoTableTires(JSONObject jsonObj) throws SQLException {
    	ArrayList<String> brandsUniqueArrayList = new ArrayList<String>();
    	JSONArray jsonArray = jsonObj.getJSONArray("tiresData");
    	
    	for (int i=0;i<jsonArray.length();i++){
    		String title = jsonArray.getJSONObject(i).getString("title");
    		String reference = jsonArray.getJSONObject(i).getString("reference");
    		String EAN = jsonArray.getJSONObject(i).getString("EAN");
    		String model = jsonArray.getJSONObject(i).getString("model");
    		String brand = jsonArray.getJSONObject(i).getString("brand");
    		int width = jsonArray.getJSONObject(i).getInt("width");
    		int profile = jsonArray.getJSONObject(i).getInt("profile");
    		int RIM = jsonArray.getJSONObject(i).getInt("RIM");
    		String season = jsonArray.getJSONObject(i).getString("season");
    		Boolean anti_puncture = jsonArray.getJSONObject(i).getBoolean("anti_puncture");
    		Float price = jsonArray.getJSONObject(i).getFloat("price");
    		int supplier_id = this.getSupplierID(brand);
    		
            System.out.printf("title : %s \n", jsonArray.getJSONObject(i).getString("title"));
            
        	String sqlQuery = "INSERT INTO tires (title, reference, EAN, model, brand, width, profile, RIM, season, anti_puncture, price, supplier_id) VALUES(?,?,?,?,?,?,?,?,?,?,?,?)";
        	this.executeInsertTiresTableQuery(sqlQuery, title, reference, EAN, model, brand, width, profile, RIM, season, anti_puncture, price, supplier_id);
        }
    	
    }
 
    public void executeInsertTiresTableQuery(String sqlQuery, String title, String reference, String EAN, String model, String brand, int width, int profile, int RIM, String season,  Boolean anti_puncture, float price, int supplier_id) throws SQLException {
		ConnectionMysql SQL = new ConnectionMysql();
    	Connection conn = SQL.conectMySQL();
		
		try {
    		PreparedStatement stmt=conn.prepareStatement(sqlQuery);  
			stmt.setString(1,title);
			stmt.setString(2,reference);
			stmt.setString(3,EAN);
			stmt.setString(4,model);
			stmt.setString(5,brand);
			stmt.setInt(6,width);
			stmt.setInt(7,profile);
			stmt.setInt(8,RIM);
			stmt.setString(9,season);
			stmt.setBoolean(10,anti_puncture);
			stmt.setFloat(11,price);
			stmt.setInt(12,supplier_id);
			  
			int i=stmt.executeUpdate();  
			System.out.println(i+" records inserted");  
        	
		} catch (SQLException e) {
			e.printStackTrace();
		} finally {
			conn.close();
		}
	}
    
    public int getSupplierID(String supplier) {
    	ConnectionMysql SQL = new ConnectionMysql();
    	Connection conn = SQL.conectMySQL();
    	String sqlQuery = "SELECT id FROM suppliers WHERE supplier = ?";
    	int id = 0;
		
		try {
			PreparedStatement stmt=conn.prepareStatement(sqlQuery);  
			stmt.setString(1,supplier);
			ResultSet resultSet=stmt.executeQuery(); 
			while (resultSet.next()) {
	                System.out.print(resultSet.getInt("id") + "\n");
	                id = resultSet.getInt("id");
	            }
		} catch (SQLException e) {
			e.printStackTrace();
		}
		
		return id;
    }
    
    // table lubricants
    
 // table tires
    public void insertIntoTableLubricants(JSONObject jsonObj) throws SQLException {
    	ArrayList<String> brandsUniqueArrayList = new ArrayList<String>();
    	JSONArray jsonArray = jsonObj.getJSONArray("lubricantsData");
    	
    	for (int i=0;i<jsonArray.length();i++){
    		String title = jsonArray.getJSONObject(i).getString("title");
    		String reference = jsonArray.getJSONObject(i).getString("reference");
    		String brand = jsonArray.getJSONObject(i).getString("brand");
    		int supplier_id = this.getSupplierID(brand);
    		String EAN = jsonArray.getJSONObject(i).getString("EAN");
    		String normative = jsonArray.getJSONObject(i).getString("normative");
    		
    		int volume = jsonArray.getJSONObject(i).getInt("volume");
    		Float price = jsonArray.getJSONObject(i).getFloat("price");
    		
            System.out.printf("title : %s \n", jsonArray.getJSONObject(i).getString("title"));
            
        	String sqlQuery = "INSERT INTO lubricants (title, reference, brand, EAN, normative, volume, price, supplier_id) VALUES(?,?,?,?,?,?,?,?)";
        	this.executeInsertLubricantsTableQuery(sqlQuery, title, reference, brand, EAN, normative, volume, price, supplier_id);
        }
    	
    }
 
    public void executeInsertLubricantsTableQuery(String sqlQuery, String title, String reference, String brand, String EAN, String normative,  int volume, float price, int supplier_id) throws SQLException {
		ConnectionMysql SQL = new ConnectionMysql();
    	Connection conn = SQL.conectMySQL();
		
		try {
    		PreparedStatement stmt=conn.prepareStatement(sqlQuery);  
			stmt.setString(1,title);
			stmt.setString(2,reference);
			stmt.setString(3,brand);
			stmt.setString(4,EAN);
			stmt.setString(5,normative);
			stmt.setInt(6,volume);
			stmt.setFloat(7,price);
			stmt.setInt(8,supplier_id);
			  
			int i=stmt.executeUpdate();  
			System.out.println(i+" records inserted");  
        	
		} catch (SQLException e) {
			e.printStackTrace();
		} finally {
			conn.close();
		}
	}
    
}

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
    	JSONArray jsonArray = jsonObj.getJSONArray("brands_ModelsData");
    	
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
    
    public int getCustomerID(String customer) {
    	ConnectionMysql SQL = new ConnectionMysql();
    	Connection conn = SQL.conectMySQL();
    	String sqlQuery = "SELECT id FROM customers WHERE name = ? AND last_name = ?";
    	int id = 0;
    	String customer_split[] = customer.split(" ");
		
		try {
			PreparedStatement stmt=conn.prepareStatement(sqlQuery);  
			stmt.setString(1,customer_split[0]);
			stmt.setString(2,customer_split[1]);
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
    	JSONArray jsonArray = jsonObj.getJSONArray("brands_ModelsData");
    	
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
    
    public int getModelID(String model) {
    	ConnectionMysql SQL = new ConnectionMysql();
    	Connection conn = SQL.conectMySQL();
    	String sqlQuery = "SELECT id FROM models WHERE model = ?";
    	int id = 0;
		
		try {
			PreparedStatement stmt=conn.prepareStatement(sqlQuery);  
			stmt.setString(1,model);
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
    
 
    public void insertIntoTableLubricants(JSONObject jsonObj) throws SQLException {
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
		
		// table services
		public void insertIntoTableServices(JSONObject jsonObj) throws SQLException {
	    	JSONArray jsonArray = jsonObj.getJSONArray("servicesData");
	    	
	    	for (int i=0;i<jsonArray.length();i++){
	    		String service = jsonArray.getJSONObject(i).getString("service");
	    		String reference = jsonArray.getJSONObject(i).getString("reference");
	    		Float price = jsonArray.getJSONObject(i).getFloat("price");
	    		
	            System.out.printf("service : %s \n", jsonArray.getJSONObject(i).getString("service"));
	            
	        	String sqlQuery = "INSERT INTO services (service, reference, price) VALUES(?,?,?)";
	        	this.executeInsertServicesTableQuery(sqlQuery, service, reference, price);
	        }
	    	
	    }
	 
	    public void executeInsertServicesTableQuery(String sqlQuery, String service, String reference, float price) throws SQLException {
			ConnectionMysql SQL = new ConnectionMysql();
	    	Connection conn = SQL.conectMySQL();
			
			try {
	    		PreparedStatement stmt=conn.prepareStatement(sqlQuery);  
				stmt.setString(1,service);
				stmt.setString(2,reference);
				stmt.setFloat(3,price);
				  
				int i=stmt.executeUpdate();  
				System.out.println(i+" records inserted");  
	        	
			} catch (SQLException e) {
				e.printStackTrace();
			} finally {
				conn.close();
			}
	    }
			
			//table revisions
			public void insertIntoTableRevisions(JSONObject jsonObj) throws SQLException {
		    	JSONArray jsonArray = jsonObj.getJSONArray("revisionsData");
		    	
		    	for (int i=0;i<jsonArray.length();i++){
		    		String type = jsonArray.getJSONObject(i).getString("type");
		    		Boolean Liquid_refill = jsonArray.getJSONObject(i).getBoolean("Liquid_refill");
		    		Boolean Oil_change = jsonArray.getJSONObject(i).getBoolean("Oil_change");
		    		Boolean Oil_filter_change = jsonArray.getJSONObject(i).getBoolean("Oil_filter_change");
		    		Boolean Cabin_filter_change = jsonArray.getJSONObject(i).getBoolean("Cabin_filter_change");
		    		Boolean Air_filter_change = jsonArray.getJSONObject(i).getBoolean("Air_filter_change");
		    		Boolean Brake_fluid_replacement = jsonArray.getJSONObject(i).getBoolean("Brake_fluid_replacement");
		    		Boolean Resetting_the_revision_indicator = jsonArray.getJSONObject(i).getBoolean("Resetting_the_revision_indicator");
		    		Boolean Oil_included = jsonArray.getJSONObject(i).getBoolean("Oil_included");
		    		Float price = jsonArray.getJSONObject(i).getFloat("price");
		    		
		            System.out.printf("type : %s \n", jsonArray.getJSONObject(i).getString("type"));
		            
		        	String sqlQuery = "INSERT INTO revisions (type, Liquid_refill, Oil_change, Oil_filter_change, Cabin_filter_change, Air_filter_change, Brake_fluid_replacement, Resetting_the_revision_indicator, Oil_included ,price) VALUES(?,?,?,?,?,?,?,?,?,?)";
		        	this.executeInsertRevisionsTableQuery(sqlQuery, type, Liquid_refill, Oil_change, Oil_filter_change, Cabin_filter_change, Air_filter_change, Brake_fluid_replacement, Resetting_the_revision_indicator, Oil_included, price);
		        }
		    	
		    }
		 
		    public void executeInsertRevisionsTableQuery(String sqlQuery , String type, Boolean Liquid_refill, Boolean Oil_change, Boolean Oil_filter_change, Boolean Cabin_filter_change, Boolean Air_filter_change, Boolean Brake_fluid_replacement, Boolean Resetting_the_revision_indicator, Boolean Oil_included , float price) throws SQLException {
				ConnectionMysql SQL = new ConnectionMysql();
		    	Connection conn = SQL.conectMySQL();
				
				try {
		    		PreparedStatement stmt=conn.prepareStatement(sqlQuery);  
					stmt.setString(1,type);
					stmt.setBoolean(2,Liquid_refill);
					stmt.setBoolean(3,Oil_change);
					stmt.setBoolean(4,Oil_filter_change);
					stmt.setBoolean(5,Cabin_filter_change);
					stmt.setBoolean(6,Air_filter_change);
					stmt.setBoolean(7,Brake_fluid_replacement);
					stmt.setBoolean(8,Resetting_the_revision_indicator);
					stmt.setBoolean(9,Oil_included);
					stmt.setFloat(10,price);
					  
					int i=stmt.executeUpdate();  
					System.out.println(i+" records inserted");  
		        	
				} catch (SQLException e) {
					e.printStackTrace();
				} finally {
					conn.close();
				}
	}
		    
	// table customers
		    public void insertIntoTableCustomers(JSONObject jsonObj) throws SQLException {
		    	JSONArray jsonArray = jsonObj.getJSONArray("customersData");
		    	
		    	for (int i=0;i<jsonArray.length();i++){
		    		String name = jsonArray.getJSONObject(i).getString("name");
		    		String last_name = jsonArray.getJSONObject(i).getString("last_name");
		    		String address = jsonArray.getJSONObject(i).getString("address");
		    		String telephone = jsonArray.getJSONObject(i).getString("telephone");
		    		String email = jsonArray.getJSONObject(i).getString("email");
		    		Boolean debt = jsonArray.getJSONObject(i).getBoolean("debt");
		    		
		            System.out.printf("name : %s \n", jsonArray.getJSONObject(i).getString("name"));
		            
		        	String sqlQuery = "INSERT INTO customers (name, last_name, address, telephone, email, debt) VALUES(?,?,?,?,?,?)";
		        	this.executeInsertCustomersTableQuery(sqlQuery, name, last_name, address, telephone, email, debt);
		        }
		    	
		    }
		 
		    public void executeInsertCustomersTableQuery(String sqlQuery , String name, String last_name, String address, String telephone, String email, Boolean debt) throws SQLException {
				ConnectionMysql SQL = new ConnectionMysql();
		    	Connection conn = SQL.conectMySQL();
				
				try {
		    		PreparedStatement stmt=conn.prepareStatement(sqlQuery);  
					stmt.setString(1,name);
					stmt.setString(2,last_name);
					stmt.setString(3,address);
					stmt.setString(4,telephone);
					stmt.setString(5,email);
					stmt.setBoolean(6,debt);
					  
					int i=stmt.executeUpdate();  
					System.out.println(i+" records inserted");  
		        	
				} catch (SQLException e) {
					e.printStackTrace();
				} finally {
					conn.close();
				}
	}
	
	// table Vehicles vehicles
		    public void insertIntoTableVehicles(JSONObject jsonObj) throws SQLException {
		    	JSONArray jsonArray = jsonObj.getJSONArray("vehiclesData");
		    	
		    	for (int i=0;i<jsonArray.length();i++){
		    		String plate = jsonArray.getJSONObject(i).getString("plate");
		    		String customer = jsonArray.getJSONObject(i).getString("customer");
		    		String model = jsonArray.getJSONObject(i).getString("model");
		    		String brand = jsonArray.getJSONObject(i).getString("brand");
		    		int model_id = this.getModelID(model);
		    		int brand_id = this.getBrandID(brand);
		    		int customer_id = this.getCustomerID(customer);
		    		
		            System.out.printf("plate : %s \n", jsonArray.getJSONObject(i).getString("plate"));
		            
		        	String sqlQuery = "INSERT INTO vehicles (plate, customer_id, brand_id, model_id) VALUES(?,?,?,?)";
		        	this.executeInsertVehiclesTableQuery(sqlQuery, plate, customer_id, brand_id, model_id);
		        }
		    }
		 
		    public void executeInsertVehiclesTableQuery(String sqlQuery, String plate, int customer_id, int brand_id, int model_id) throws SQLException {
				ConnectionMysql SQL = new ConnectionMysql();
		    	Connection conn = SQL.conectMySQL();
				
				try {
		    		PreparedStatement stmt=conn.prepareStatement(sqlQuery);  
					stmt.setString(1,plate);
					stmt.setInt(2,customer_id);
					stmt.setInt(3,brand_id);
					stmt.setInt(4,model_id);
					  
					int i=stmt.executeUpdate();  
					System.out.println(i+" records inserted");  
		        	
				} catch (SQLException e) {
					e.printStackTrace();
				} finally {
					conn.close();
				}
		    }
			
}

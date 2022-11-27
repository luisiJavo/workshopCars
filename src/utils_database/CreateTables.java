package utils_database;

import java.sql.Connection;
import java.sql.SQLException;
import java.sql.Statement;
import utils_database.ConnectionMysql;

public class CreateTables {
	
	public void executeCreateTableQuery(String sqlQuery) {
		// Instancias la clase que hemos creado anteriormente
    	ConnectionMysql SQL = new ConnectionMysql();
    	// Llamas al método que tiene la clase y te devuelve una conexión
    	Connection conn = SQL.conectMySQL();
		
		try {
    		Statement statement = conn.createStatement();
            statement.executeUpdate(sqlQuery);
            System.out.println("Table Created");
			
		} catch (SQLException e) {
			e.printStackTrace();
		}
	}
	
    public void createTableCustomers() {
    	String sqlQuery = "CREATE TABLE IF NOT EXISTS customers (id INT NOT NULL AUTO_INCREMENT, name VARCHAR(100)," + 
    			"last_name VARCHAR(100), address VARCHAR(255), telephone VARCHAR(50), email VARCHAR(200), PRIMARY KEY (id))";
    	
    	this.executeCreateTableQuery(sqlQuery);
    }
    
    public void createTableVehicles() {
    	String sqlQuery = "CREATE TABLE IF NOT EXISTS vehicles (id INT NOT NULL AUTO_INCREMENT, plate VARCHAR(100)," + 
    			"customer_id INT, brand_id INT, model_id INT, email VARCHAR(200), PRIMARY KEY (id)," + 
    			" FOREIGN KEY (customer_id) REFERENCES customers(id), FOREIGN KEY (brand_id) REFERENCES brands(id), FOREIGN KEY (model_id) REFERENCES models(id))";
    	
    	this.executeCreateTableQuery(sqlQuery);
    }
    
    public void createTableBrands() {
    	String sqlQuery = "CREATE TABLE IF NOT EXISTS brands (id INT NOT NULL AUTO_INCREMENT, brand VARCHAR(100), PRIMARY KEY (id))";
    	
    	this.executeCreateTableQuery(sqlQuery);
    }
    
    public void createTableModels() {
    	String sqlQuery = "CREATE TABLE IF NOT EXISTS models (id INT NOT NULL AUTO_INCREMENT, model VARCHAR(100), brand_id INT, PRIMARY KEY (id), FOREIGN KEY (brand_id) REFERENCES brands(id))";
    	
    	this.executeCreateTableQuery(sqlQuery);
    }
    
    public void createTableServices() {
    	String sqlQuery = "CREATE TABLE IF NOT EXISTS services (id INT NOT NULL AUTO_INCREMENT, service VARCHAR(120), reference VARCHAR(30),price FLOAT(8,2), PRIMARY KEY (id))";
    	
    	this.executeCreateTableQuery(sqlQuery);
   }
    
    public void createTableRevisions() {
    	String sqlQuery = "CREATE TABLE IF NOT EXISTS revisions (id INT NOT NULL AUTO_INCREMENT, type VARCHAR(150), Liquid_refill BOOLEAN, Oil_change BOOLEAN, BOOLEAN, BOOLEAN, BOOLEAN, BOOLEAN,  BOOLEAN, BOOLEAN,price FLOAT(8,2), PRIMARY KEY (id))";
    	
    	this.executeCreateTableQuery(sqlQuery);
    	"type": "Basic revision",
		"Liquid_refill": true,
		"Oil_change": true,
		"Oil_filter_change": true,
		"Cabin_filter change": true,
		"Air_filter_change": false,
		"Brake_fluid_replacement": false,
		"Resetting_the_revision_indicator": true,
		"Oil_included": false,
		"price": 64.95 
    }
    
    
    public void createTableSuppliers() {
    	String sqlQuery = "CREATE TABLE IF NOT EXISTS suppliers (id INT NOT NULL AUTO_INCREMENT, supplier VARCHAR(200), PRIMARY KEY (id))";
    	
    	this.executeCreateTableQuery(sqlQuery);
    }
    
    public void createTableTires() {
    	String sqlQuery = "CREATE TABLE IF NOT EXISTS tires (id INT NOT NULL AUTO_INCREMENT, title VARCHAR(120), reference VARCHAR(12), EAN VARCHAR(15), model VARCHAR(100)," + 
    			"brand VARCHAR(100), width INT, profile INT, RIM INT, season VARCHAR(20), anti_puncture BOOLEAN, price FLOAT(8,2),supplier_id INT, PRIMARY KEY (id), FOREIGN KEY (supplier_id) REFERENCES suppliers(id))";
    	
    	this.executeCreateTableQuery(sqlQuery);
    	
    }
    
    public void createTableMaterials() {
    	String sqlQuery = "CREATE TABLE IF NOT EXISTS materials (id INT NOT NULL AUTO_INCREMENT, material VARCHAR(255), price_per_unit FLOAT(8,2)," + 
    	" stock INT, supplier_id INT, PRIMARY KEY (id), FOREIGN KEY (supplier_id) REFERENCES suppliers(id))";
    	
    	this.executeCreateTableQuery(sqlQuery);
    }
    
    public void createTableEmployees() {
    	String sqlQuery = "CREATE TABLE IF NOT EXISTS employees (id INT NOT NULL AUTO_INCREMENT, name VARCHAR(100)," + 
    			"last_name VARCHAR(100), address VARCHAR(255), telephone VARCHAR(50), email VARCHAR(200), salary FLOAT(7,2), PRIMARY KEY (id))";
    	
    	this.executeCreateTableQuery(sqlQuery);
    }
    
    public void createTableHistoricals() {
    	String sqlQuery = "CREATE TABLE IF NOT EXISTS historicals (id INT NOT NULL AUTO_INCREMENT, material_id INT, material_units INT, operation_id INT, operation_time float(6,2), employee_id INT," + 
    	" delivery_notes_id INT, date DATETIME, PRIMARY KEY (id), FOREIGN KEY (material_id) REFERENCES materials(id)," + 
    			" FOREIGN KEY (operation_id) REFERENCES operations(id), FOREIGN KEY (employee_id) REFERENCES employees(id))";
    	
    	this.executeCreateTableQuery(sqlQuery);
    }

    public void createTableDeliveryNotes() {
    	String sqlQuery = "CREATE TABLE IF NOT EXISTS delivery_notes (id INT NOT NULL AUTO_INCREMENT, delivery_number VARCHAR(8),date DATETIME," + 
    	" vehicle_id INT, customer_id INT, PRIMARY KEY (id), FOREIGN KEY (vehicle_id) REFERENCES vehicles(id), FOREIGN KEY (customer_id) REFERENCES customers(id))";
    	
    	this.executeCreateTableQuery(sqlQuery);
    }
    
    public void createAllTables() {
    	this.createTableBrands();
    	this.createTableModels();
    	this.createTableCustomers();
    	this.createTableVehicles();
    	this.createTableEmployees();
    	this.createTableOperations();
    	this.createTableSuppliers();
    	this.createTableTires();
    	this.createTableMaterials();
    	this.createTableDeliveryNotes();
    	this.createTableHistoricals();
    }

}

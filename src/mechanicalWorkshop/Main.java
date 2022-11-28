package mechanicalWorkshop;
import java.sql.SQLException;

import utils_database.CreateTables;
import utils_database.FeedTablesDatabase;
import utils_json.ParseJSONFiles;

public class Main {

	public static void main(String[] args) {
		// TODO Auto-generated method stub

		CreateTables createTables = new CreateTables();
		createTables.createAllTables();
		
		ParseJSONFiles parseBrandsJSON = new ParseJSONFiles();
		
		FeedTablesDatabase feedBrandsTable = new FeedTablesDatabase();
		try {
			feedBrandsTable.insertIntoTableBrands(parseBrandsJSON.readJSONFile("src/json_files/brands.json"));
			feedBrandsTable.insertIntoTableModels(parseBrandsJSON.readJSONFile("src/json_files/brands.json"));
			feedBrandsTable.insertIntoTableSuppliers(parseBrandsJSON.readJSONFile("src/json_files/suppliers.json"));
			feedBrandsTable.insertIntoTableTires(parseBrandsJSON.readJSONFile("src/json_files/tires.json"));
			feedBrandsTable.insertIntoTableLubricants(parseBrandsJSON.readJSONFile("src/json_files/lubricants.json"));
			feedBrandsTable.insertIntoTableServices(parseBrandsJSON.readJSONFile("src/json_files/services.json"));
			feedBrandsTable.insertIntoTableRevisions(parseBrandsJSON.readJSONFile("src/json_files/services.json"));
			feedBrandsTable.insertIntoTableCustomers(parseBrandsJSON.readJSONFile("src/json_files/customers.json"));
			feedBrandsTable.insertIntoTableVehicles(parseBrandsJSON.readJSONFile("src/json_files/vehicles.json"));
		}catch (SQLException e) {
			// TODO: handle exception
			System.out.println("error feeding tables");
		}
		
		}
	

}

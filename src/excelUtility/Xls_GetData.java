package excelUtility;

import java.util.ArrayList;

public class Xls_GetData {
	static Xls_Reader reader;

	public static ArrayList<Object[]> getDataFromExcel() {

		ArrayList<Object[]> myData = new ArrayList<Object[]>();

		// Read Test Data
		try {

			reader = new Xls_Reader("E:\\Workspace\\SeleniumEasyProject\\src\\testData\\SeleniumEasyProjectTestData.xlsx");
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

		//Simple Form WorkSheet
		for (int rowNumber = 2; rowNumber <= reader.getRowCount("SimpleForm"); rowNumber++) {


			String enterMsg = reader.getCellData("SimpleForm", "EnterMessage", rowNumber);
			System.out.println(enterMsg);

			String enterA = reader.getCellData("SimpleForm", "EnterA", rowNumber);
			System.out.println(enterA);

			String enterB = reader.getCellData("SimpleForm", "EnterB", rowNumber);
			System.out.println(enterB);
			
			String getTotal = reader.getCellData("SimpleForm", "GetTotal", rowNumber);
			
			Object obj[] = { enterMsg, enterA, enterB, getTotal };
			myData.add(obj);

		}

		return myData;

	}
}

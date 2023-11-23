package utilPack;

import java.io.File;
import java.io.IOException;
import java.util.Map;
import java.util.HashMap;

import org.apache.poi.ss.usermodel.DataFormatter;
import org.apache.poi.xssf.usermodel.XSSFCell;
import org.apache.poi.xssf.usermodel.XSSFRow;
import org.apache.poi.xssf.usermodel.XSSFSheet;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;

import com.relevantcodes.extentreports.model.SystemProperties;

import jxl.Workbook;
import jxl.read.biff.BiffException;
import jxl.Cell;
import jxl.Sheet;

public class ExcelRead {
	
	public static String[][] getExcelData(int sheetnum) {
		
		String fileLocation = System.getProperty("user.dir")+"\\src\\Resources\\TestData.xlsx";
		XSSFWorkbook wbook = null;
		try {
			wbook = new XSSFWorkbook(fileLocation);
		} catch (IOException e) {
			e.printStackTrace();
		}
		XSSFSheet sheet = wbook.getSheetAt(sheetnum);
		int lastRowNum = sheet.getLastRowNum();
		int physicalNumberOfRows = sheet.getPhysicalNumberOfRows();
		System.out.println("Inclusive of header: "+physicalNumberOfRows);
		System.out.println("No.of Rows: "+ lastRowNum);
		short lastCellNum = sheet.getRow(0).getLastCellNum();
		System.out.println("No.of cells:"+lastCellNum);
		String[][] data = new String[lastRowNum][lastCellNum];
		for (int i = 1; i <=lastRowNum; i++) {
			XSSFRow row = sheet.getRow(i);
			for (int j = 0; j < lastCellNum; j++) {
				XSSFCell cell = row.getCell(j);
				DataFormatter dft = new DataFormatter();
				String value = dft.formatCellValue(cell);
				data[i-1][j] = value;
			} 
		}
		try {
			wbook.close();
		} catch (IOException e) {
			e.printStackTrace();
		}
		return data;	
	}
	
	public static Object[][] getMapArray(String xlFilePath, String sheetName, String tableName) throws IOException, BiffException{
		 
		Workbook workbook = Workbook.getWorkbook(new File(xlFilePath));
		Sheet sheet = workbook.getSheet(sheetName);
		int startRow, startCol, endRow, endCol, ci, cj;
		Cell tableStart = sheet.findCell(tableName);
		startRow = tableStart.getRow();
		startCol = tableStart.getColumn();
		int endCols =sheet.getColumns();
		int endRows = sheet.getRows();
		System.out.println("startRow :" + startRow + "startCol :" + startCol);
		
		Cell tableEnd = sheet.findCell(tableName,startCol,startRow,endCols,endRows,true);
		endRow = tableEnd.getRow();
		endCol = tableEnd.getColumn();
		System.out.println("startRow=" + startRow +",endRow=" + endRow + "'" + "startCol=" + startCol + ", endCol=" + endCol);
		
		ci =0;
		int k =0;
		Object[][] obj = new Object[endRow - startRow - 1][1];
		
		for(int i= startRow + 1; i< endRow; i++, ci++) {
			cj=0;
			Map<Object, Object> datamap = new HashMap<>();
			for (int j= startCol + 1; j<endCol;j++,cj++) {
				datamap.put(sheet.getCell(j, startRow).getContents(), sheet.getCell(j,i).getContents());
			}
			datamap.remove("");
			obj[k][0] = datamap;
			k++;
			
		}		
		return (obj);
	}

}

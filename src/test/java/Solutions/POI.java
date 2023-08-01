package Solutions;

import org.apache.poi.openxml4j.exceptions.InvalidFormatException;
import org.apache.poi.xssf.usermodel.XSSFCell;
import org.apache.poi.xssf.usermodel.XSSFRow;
import org.apache.poi.xssf.usermodel.XSSFSheet;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;
import org.testng.annotations.Test;

import java.io.FileInputStream;
import java.io.IOException;

@Test
public class POI {
    public void readdatafromexcel() throws IOException {
        String excelpath=System.getProperty("user.dir")+"/src/test/java/Datafiles/POI.xlsx";
        FileInputStream fis = new FileInputStream(excelpath);
        XSSFWorkbook workbook = new XSSFWorkbook(fis);
//        XSSFSheet sheet = workbook.getSheet("POI");
        XSSFSheet sheet = workbook.getSheetAt(0);

        int rows = sheet.getLastRowNum();
        int cols = sheet.getRow(1).getLastCellNum();
        for (int r = 0; r <= rows; r++) {
            XSSFRow row = sheet.getRow(r);
            for (int c = 0; c <= cols; c++) {
                XSSFCell cell = row.getCell(c);

                switch (cell.getCellType()) {
                    case STRING: System.out.println(cell.getStringCellValue());
                    case NUMERIC: System.out.println(cell.getNumericCellValue());
                    case BOOLEAN: System.out.println(cell.getBooleanCellValue());
                }
            }
        }
    }
}

package testgroup.service.parseSchedules;


import org.apache.poi.ss.usermodel.Cell;
import org.apache.poi.ss.usermodel.Row;
import org.apache.poi.ss.usermodel.Sheet;
import org.apache.poi.ss.usermodel.Workbook;
import org.apache.poi.ss.util.CellRangeAddress;

/**
 * Created by admin on 23.01.2022.
 */
public class ExcelUtils {

    public void readExcel(Workbook wb) {
        Sheet sheet = wb.getSheetAt(1);
        Row row = null;

        for(int i=0; i<sheet.getPhysicalNumberOfRows(); i++)
        {
            row = sheet.getRow(i);
            for(int j = 0; j < row.getLastCellNum();j++)
            {
                Cell c = row.getCell(j);
                //boolean isMerge = isMergedRegion(sheet, i, c.getColumnIndex());
                // Определяем, есть ли объединенная ячейка
                //if(isMerge) {
                String rs = getMergedRegionValue(sheet, row.getRowNum(), c.getColumnIndex());
                System.out.print(rs+" ");
                //}else {
                System.out.print(c.getRichStringCellValue()+" ");
                //}
            }

        }

    }

    /**
     * Получить значение объединенной ячейки
     * @param sheet
     * @param row
     * @param column
     * @return
     */
    public String getMergedRegionValue(Sheet sheet ,int row , int column){
        int sheetMergeCount = sheet.getNumMergedRegions (); // Получить количество объединенных ячеек на листе

        for(int i = 0 ; i < sheetMergeCount ; i++){
            CellRangeAddress ca = sheet.getMergedRegion (i); // Получить объединенный регион
            int firstColumn = ca.getFirstColumn();
            int lastColumn = ca.getLastColumn();
            int firstRow = ca.getFirstRow();
            int lastRow = ca.getLastRow();


            // Определяем, находятся ли номер строки и номер столбца входящей ячейки в пределах диапазона объединенной ячейки, если он находится в диапазоне объединенной ячейки, выбираем значение первой ячейки в объединенной области
            if(row >= firstRow && row <= lastRow){

                if(column >= firstColumn && column <= lastColumn){
                    Row fRow = sheet.getRow(firstRow);
                    Cell fCell = fRow.getCell(firstColumn);
                    return getCellValue(fCell) ;
                }
            }
        }
        // Если номер строки ячейки и номер столбца не находятся ни в одной из объединенных областей, выбор вернет null
        return null ;
    }

    /**
     * Судя по тому, что строки объединены
     * @param sheet
     * @param row
     * @param column
     * @return
     */
    private boolean isMergedRow(Sheet sheet,int row ,int column) {
        int sheetMergeCount = sheet.getNumMergedRegions();
        for (int i = 0; i < sheetMergeCount; i++) {
            CellRangeAddress range = sheet.getMergedRegion(i);
            int firstColumn = range.getFirstColumn();
            int lastColumn = range.getLastColumn();
            int firstRow = range.getFirstRow();
            int lastRow = range.getLastRow();
            if(row == firstRow && row == lastRow){
                if(column >= firstColumn && column <= lastColumn){
                    return true;
                }
            }
        }
        return false;
    }

    /**
     * Определить, является ли указанная ячейка объединенной ячейкой
     * @param //sheet
     * @param //индекс строки строки
     * @param //индекс столбца столбца
     * @return
     */
    public static boolean isMergedRegion(Workbook wb, int sheetN,int row ,int column) {
        Sheet sheet = wb.getSheetAt(sheetN);
        int sheetMergeCount = sheet.getNumMergedRegions();
        for (int i = 0; i < sheetMergeCount; i++) {
            CellRangeAddress range = sheet.getMergedRegion(i);
            int firstColumn = range.getFirstColumn();
            int lastColumn = range.getLastColumn();
            int firstRow = range.getFirstRow();
            int lastRow = range.getLastRow();
            if(row >= firstRow && row <= lastRow){
                if(column >= firstColumn && column <= lastColumn){
                    return true;
                }
            }
        }
        return false;
    }

    /**
     * Определите, есть ли объединенные ячейки на странице листа
     * @param sheet
     * @return
     */
    private boolean hasMerged(Sheet sheet) {
        return sheet.getNumMergedRegions() > 0 ? true : false;
    }

    /**
     * Объединить ячейки
     * @param sheet
     * @param firstRow начальная строка
     * @param lastRow конечная строка
     * @param firstCol начальный столбец
     * @param lastCol конечный столбец
     */
    private void mergeRegion(Sheet sheet, int firstRow, int lastRow, int firstCol, int lastCol) {
        sheet.addMergedRegion(new CellRangeAddress(firstRow, lastRow, firstCol, lastCol));
    }

    /**
     * Получить значение ячейки
     * @param cell
     * @return
     */
    public String getCellValue(Cell cell){

        if(cell == null) return "";

        if(cell.getCellType() == Cell.CELL_TYPE_STRING){

            return cell.getStringCellValue();

        }else if(cell.getCellType() == Cell.CELL_TYPE_BOOLEAN){

            return String.valueOf(cell.getBooleanCellValue());

        }else if(cell.getCellType() == Cell.CELL_TYPE_FORMULA){

            return cell.getCellFormula() ;

        }else if(cell.getCellType() == Cell.CELL_TYPE_NUMERIC){

            return String.valueOf(cell.getNumericCellValue());

        }
        return "";
    }
}



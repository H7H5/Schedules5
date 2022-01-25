package testgroup.service.parseReplacement;

import org.apache.poi.ss.usermodel.Workbook;
import java.io.IOException;

public class ParseDay {
    public int sheetInExcel = 0;
    private Day day;
    private String[] months = {"січня","лютого","березня","квітня","травня","червня",
            "липня","серпня","вересня","жовтня","листопада","грудня"};

    public void getLastDay(Workbook wb)throws IOException {
        Day day1 = getDayDate(wb,0,7);
        Day day2 = getDayDate(wb,2,8);
        day = getLastDay(day1, day2);
    }

    private Day getDayDate(Workbook wb, int sheet, int startRow){
        String data = SortGroup.getCellText(wb.getSheetAt(sheet).getRow(startRow).getCell(0));
        String delimiter = "\"";
        String[] subData = data.split(delimiter);
        int day;
        int monthInt;
        int year;
        try {
            day = Integer.parseInt(subData[1]);
            delimiter = "\\s+";
            data = subData[2];
            subData = data.split(delimiter);
            monthInt = getNumberMonth(subData[1]);
            year = Integer.parseInt(subData[2]);
        }catch (Exception e){
            day = 999;
            monthInt = 999;
            year = 999;
        }
        return new Day(day,monthInt,year);
    }

    private int getNumberMonth(String strMonth){
        String s = strMonth.toLowerCase();
        for (int i = 0; i < months.length; i++) {
            if(s.equals(months[i])){
                return i+1;
            }
        }
        return 1;
    }

    private Day getLastDay(Day day1, Day day2){
        if(day1.getYear() > day2.getYear()){
            sheetInExcel = 0;
            return day1;
        }else if(day1.getYear() < day2.getYear()){
            sheetInExcel = 2;
            return day2;
        }
        if(day1.getMonth() > day2.getMonth()){
            sheetInExcel = 0;
            return day1;
        }else if(day1.getMonth() < day2.getMonth()){
            sheetInExcel = 2;
            return day2;
        }
        if(day1.getDay() > day2.getDay()){
            sheetInExcel = 0;
            return day1;
        }else if(day1.getDay() < day2.getDay()){
            sheetInExcel = 2;
            return day2;
        }
        return day1;
    }

    public Day getDay() {
        return day;
    }
}

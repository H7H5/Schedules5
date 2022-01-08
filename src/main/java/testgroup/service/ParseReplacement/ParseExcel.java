package testgroup.service.ParseReplacement;


import lombok.Cleanup;
import org.apache.poi.hssf.usermodel.HSSFWorkbook;
import org.apache.poi.ss.usermodel.Workbook;

import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.util.ArrayList;

public class ParseExcel {
    private int startRow = 12;
    private ParseDay parseDay = new ParseDay();
    private ArrayList<LessonReplacement> lessonReplacements;

    public boolean parse(File file)throws IOException{
        @Cleanup FileInputStream fis = new FileInputStream(file);
        Workbook wb;
        try {
            wb = new HSSFWorkbook(fis);
        }catch (Exception e){
            return false;
        }
        parseDay.getLastDay(wb);
        lessonReplacements = new SortGroup(parseDay.sheetInExcel,startRow).parseLessons(wb);
        return true;
    }

    public ParseDay getParseDay() {
        return parseDay;
    }

    public ArrayList<LessonReplacement> getLessonReplacements() {
        return lessonReplacements;
    }
}

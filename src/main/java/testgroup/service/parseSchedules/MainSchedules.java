package testgroup.service.parseSchedules;

import lombok.Cleanup;
import org.apache.poi.hssf.usermodel.HSSFWorkbook;
import org.apache.poi.ss.usermodel.Workbook;
import testgroup.model.Lesson;
import testgroup.service.parseReplacement.LessonReplacement;
import testgroup.service.parseReplacement.SortGroup;

import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.util.ArrayList;

public class MainSchedules {
    private static String nameFile = "РОЗКЛАД  ІІ семестр 20.01.2022.xls";

    public ArrayList<LessonReplacement> getLessons() throws IOException  {
        FileInputStream fis = new FileInputStream(nameFile);
        Workbook wb = new HSSFWorkbook(fis);
        new ParseExelSchedules().parseLessons(wb);
        return null;
    }
    public ArrayList<Lesson> parse(File file)throws IOException{
        @Cleanup FileInputStream fis = new FileInputStream(file);
        Workbook wb;
        try {
            wb = new HSSFWorkbook(fis);
        }catch (Exception e){
            return null;
        }
        return new ParseExelSchedules().parseLessons(wb);
    }
}

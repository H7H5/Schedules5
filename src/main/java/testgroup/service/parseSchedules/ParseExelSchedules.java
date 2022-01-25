package testgroup.service.parseSchedules;


import org.apache.poi.ss.usermodel.Cell;
import org.apache.poi.ss.usermodel.Workbook;
import testgroup.model.Lesson;

import java.util.ArrayList;

import static org.apache.poi.ss.usermodel.Cell.CELL_TYPE_STRING;

/**
 * Created by admin on 23.01.2022.
 */
public class ParseExelSchedules {
    ArrayList<GroupCellDTO> groups = new ArrayList<>();
    ArrayList<Lesson> lessonParseSchedules = new ArrayList<>();
    int [] cells = {3,8};
    int [] rows = {0,45,90,135,180,225,270};
    int [] sheets = {1,2,3,4,5,6};
    SelectedGroup selectedGroup = new SelectedGroup();
    public ArrayList<Lesson> parseLessons(Workbook wb){
        ArrayList<GroupCellDTO> dirtyGroups = new ArrayList<>();
        for (int a = 0; a <sheets.length ; a++) {
            for (int i = 0; i <rows.length ; i++) {
                for (int j = 0; j <cells.length ; j++) {
                    try {
                        String numGroup = getCellText(wb.getSheetAt(sheets[a]).getRow(rows[i]).getCell(cells[j]));
                        dirtyGroups.add(new GroupCellDTO(sheets[a],rows[i],cells[j],numGroup));
                    }catch (Exception e){
                        //System.out.println("error" + rows[i]);
                    }
                }
            }
        }
        groups = selectedGroup.selectGroup(dirtyGroups);
        ParseLessonSchedules parseLessonSchedules = new ParseLessonSchedules();
        lessonParseSchedules = parseLessonSchedules.parseLesson(wb, groups);
        return lessonParseSchedules;
    }



    public static String getCellText(Cell cell){
        cell.setCellType(CELL_TYPE_STRING);
        return cell.getRichStringCellValue().getString();
    }
}


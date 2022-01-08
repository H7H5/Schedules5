package testgroup.service.ParseReplacement;


import org.apache.poi.ss.usermodel.Cell;
import org.apache.poi.ss.usermodel.Workbook;
import java.util.ArrayList;

import static org.apache.poi.ss.usermodel.Cell.CELL_TYPE_STRING;

public class SortGroup {
    private int sheet;
    private int startRow;
    private ArrayList<LessonReplacement> lessonReplacements = new ArrayList<LessonReplacement>();

    public SortGroup(int sheet, int startRow) {
        this.sheet = sheet;
        this.startRow = startRow;
    }

    public ArrayList<LessonReplacement> parseLessons(Workbook wb){
        int numRow = startRow;
        while (true){
            String numGroup = getCellText(wb.getSheetAt(sheet).getRow(numRow).getCell(0));
            if (numGroup.equals("")){
                break;
            }else{
                String numLesson = getCellText(wb.getSheetAt(sheet).getRow(numRow).getCell(1)).replace("\"", "\'");
                String oldName = getCellText(wb.getSheetAt(sheet).getRow(numRow).getCell(2)).replace("\"", "\'");
                String oldTeacher = getCellText(wb.getSheetAt(sheet).getRow(numRow).getCell(3)).replace("\"", "\'");
                String newName = getCellText(wb.getSheetAt(sheet).getRow(numRow).getCell(4)).replace("\"", "\'");
                String newTeacher = getCellText(wb.getSheetAt(sheet).getRow(numRow).getCell(5)).replace("\"", "\'");
                String auditory = getCellText(wb.getSheetAt(sheet).getRow(numRow).getCell(6)).replace("\"", "\'");
                lessonReplacements.add(new LessonReplacement(numRow,numGroup,numLesson,oldName,oldTeacher,"",
                        newName,newTeacher,"",auditory));
            }
            numRow++;
        }
        correctByGroup();
        return new SortCouple(lessonReplacements).sortLessonsByCouple();
    }

    public static String getCellText(Cell cell){
        cell.setCellType(CELL_TYPE_STRING);
        return cell.getRichStringCellValue().getString();
    }

    private void correctByGroup(){
        lessonReplacements.addAll(findByCommaInGroup());
        lessonReplacements.addAll(findBySpaceInGroup());
    }

    private ArrayList<LessonReplacement> findByCommaInGroup(){
        ArrayList<LessonReplacement> lessonsInComaByGroup = new ArrayList<LessonReplacement>();
        for (int i = 0; i < lessonReplacements.size(); i++){
            if (lessonReplacements.get(i).getGroupp().contains(",")){
                lessonReplacements.get(i).setGroupp(lessonReplacements.get(i).getGroupp().replaceAll("\\s+",""));
                lessonsInComaByGroup.add(lessonReplacements.get(i));
                lessonReplacements.remove(i);
                i--;
            }
        }
        return subStringByCommaInGroup(lessonsInComaByGroup);
    }

    private ArrayList<LessonReplacement> subStringByCommaInGroup(ArrayList<LessonReplacement> lessonReplacements){
        ArrayList<LessonReplacement> lessonsIncludeComaByGroup = new ArrayList<LessonReplacement>();
        String delimiter = ",";
        for (int i = 0; i < lessonReplacements.size(); i++) {
            String str = lessonReplacements.get(i).getGroupp();
            String[] subStr = str.split(delimiter);
            for(int j = 0; j < subStr.length; j++) {
                LessonReplacement tempLessonReplacement = lessonReplacements.get(i);
                lessonsIncludeComaByGroup.add(new LessonReplacement(tempLessonReplacement.getNumberRow(),subStr[j], tempLessonReplacement.getCouple(),
                        tempLessonReplacement.getOldName(), tempLessonReplacement.getOldTeacher1(),"", tempLessonReplacement.getNewName(),
                        tempLessonReplacement.getNewTeacher1(),"", tempLessonReplacement.getAuditory()));
            }
        }
        return lessonsIncludeComaByGroup;
    }

    private   ArrayList<LessonReplacement> findBySpaceInGroup(){
        ArrayList<LessonReplacement> lessonsBySpaceInGroup = new ArrayList<LessonReplacement>();
        for (int i = 0; i< lessonReplacements.size(); i++){
            if (lessonReplacements.get(i).getGroupp().contains(" ")){
                lessonsBySpaceInGroup.add(lessonReplacements.get(i));
                lessonReplacements.remove(i);
                i--;
            }
        }
        return subStringBySpaceInGroup(lessonsBySpaceInGroup);
    }

    private   ArrayList<LessonReplacement> subStringBySpaceInGroup(ArrayList<LessonReplacement> lessonReplacements){
        ArrayList<LessonReplacement> lessonsIncludeSpaceByGroup = new ArrayList<LessonReplacement>();
        String delimiter = " "; // Разделитель
        for (int i = 0; i < lessonReplacements.size(); i++) {
            String str = lessonReplacements.get(i).getGroupp().replaceAll("\\s{2,}", " ").trim();
            String[] subStr = str.split(delimiter);
            for(int j = 0; j < subStr.length; j++) {
                LessonReplacement tempLessonReplacement = lessonReplacements.get(i);
                lessonsIncludeSpaceByGroup.add(new LessonReplacement(tempLessonReplacement.getNumberRow(),subStr[j], tempLessonReplacement.getCouple(),
                        tempLessonReplacement.getOldName(), tempLessonReplacement.getOldTeacher1(),"", tempLessonReplacement.getNewName(),
                        tempLessonReplacement.getNewTeacher1(),"", tempLessonReplacement.getAuditory()));
            }
        }
        return lessonsIncludeSpaceByGroup;
    }
}


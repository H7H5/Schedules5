package testgroup.service.parseSchedules;


import org.apache.poi.ss.usermodel.Cell;
import org.apache.poi.ss.usermodel.Workbook;
import testgroup.model.Lesson;

import java.util.ArrayList;

import static org.apache.poi.ss.usermodel.Cell.CELL_TYPE_STRING;

/**
 * Created by admin on 23.01.2022.
 */
public class ParseLessonSchedules {


    public ArrayList<Lesson> parseLesson(Workbook wb, ArrayList<GroupCellDTO> groupCellDTOS) {
        ArrayList<Lesson> lessonParseSchedules = new ArrayList<>();
        try {
            for (int j = 0; j <groupCellDTOS.size() ; j++) {
                GroupCellDTO groupCellDTO = groupCellDTOS.get(j);
                for (int i = 3; i < 45; i++) {
                    Lesson lessonParseSchedule = new Lesson();
                    String oneLesson = getCellText(wb.getSheetAt(groupCellDTO.sheet).
                            getRow(groupCellDTO.row + i).getCell(groupCellDTO.cell));
                    oneLesson = setOneSpace(oneLesson);
                    if (!oneLesson.isEmpty()) {
                        lessonParseSchedule.setGroupp(groupCellDTO.group);
                        lessonParseSchedule.setDay(getDayInWeek(groupCellDTO.row, groupCellDTO.row + i));
                        lessonParseSchedule.setNumber(Integer.parseInt(getNumber(wb, groupCellDTO, i)));
                        lessonParseSchedule.setName(oneLesson);
                        ArrayList<String> teachers = getTeachers(wb, groupCellDTO, i);
                        if (!teachers.get(0).isEmpty()) {
                            lessonParseSchedule.setTeacher(teachers.get(0));
                        }
                        if (teachers.size() > 1) {
                            lessonParseSchedule.setTeacher2(teachers.get(1));
                        }
                        lessonParseSchedule.setStudy(getStudy(wb, groupCellDTO, i, getNumerator(wb, groupCellDTO, i)));
                        lessonParseSchedule.setNumerator(getNumerator(wb, groupCellDTO, i));
                        ArrayList<Lesson> lessonsCoarch = checkOneLessonTwoNumerators(lessonParseSchedule, wb, groupCellDTO, i);
                        //if (lessonsCoarch.size()>1){
                            for (int k = 0; k <lessonsCoarch.size() ; k++) {
                                lessonParseSchedules.add(lessonsCoarch.get(k));
                            }
                        //}else {
                        //    lessonParseSchedules.add(lessonParseSchedule);
                        //}


                        //lessonParseSchedules.add(lessonParseSchedule);
                    }
                }
            }
        } catch (Exception e) {

        }

        return lessonParseSchedules;
    }

    private String getNumber(Workbook wb, GroupCellDTO groupCellDTO, int i) {
        if (i % 2 == 0) {
            i--;
        }
        String number = getCellText(wb.getSheetAt(groupCellDTO.sheet).
                getRow(groupCellDTO.row + i).getCell(groupCellDTO.cell - 2));

        return number;
    }

    private String getNumerator(Workbook wb, GroupCellDTO groupCellDTO, int i) {
        boolean num = ExcelUtils.isMergedRegion(wb, groupCellDTO.sheet, groupCellDTO.row + i, groupCellDTO.cell);
        String numerator = "null";
        if (!num) {
            if (i % 2 == 0) {
                numerator = "denominator";
            } else {
                numerator = "numerator";
            }
        }
        return numerator;
    }

    private String getDayInWeek(int generalRov, int row) {
        row = row-generalRov;
        String[] days = {"Monday", "Tuesday", "Wednesday", "Thursday", "Friday"};
        String day = "---";
        if (row >= 3 && row <= 12){
            day = days[0];
        }else
        if (row >= 13 && row <= 20){
            day = days[1];
        }else
        if (row >= 21 && row <= 28){
            day = days[2];
        }else
        if (row >= 29 && row <= 36){
            day = days[3];
        }else if (row >= 37 && row <= 44){
            day = days[4];
        }
        return day;
    }

    private String getStudy(Workbook wb,GroupCellDTO groupCellDTO, int i, String numerator){
        String study = "";
        if (numerator.equals("null")){
            if (i % 2 != 0) {
                String s1 = getCellText(wb.getSheetAt(groupCellDTO.sheet).
                        getRow(groupCellDTO.row + i ).getCell(groupCellDTO.cell-1));
                String s2 = getCellText(wb.getSheetAt(groupCellDTO.sheet).
                        getRow(groupCellDTO.row + i+1 ).getCell(groupCellDTO.cell-1));
                study = s1 + " "+ s2;
            } else {

            }

        }else if (numerator.equals("numerator")){
            String s1 = getCellText(wb.getSheetAt(groupCellDTO.sheet).
                    getRow(groupCellDTO.row + i ).getCell(groupCellDTO.cell-1));
            study = s1;

        }else if (numerator.equals("denominator")){
            String s1 = getCellText(wb.getSheetAt(groupCellDTO.sheet).
                    getRow(groupCellDTO.row + i ).getCell(groupCellDTO.cell-1));
            study = s1;
            boolean teachers = ExcelUtils.isMergedRegion(wb, groupCellDTO.sheet, groupCellDTO.row + i, groupCellDTO.cell+1);
            if (teachers){
                String s2 = getCellText(wb.getSheetAt(groupCellDTO.sheet).
                        getRow(groupCellDTO.row + i-1 ).getCell(groupCellDTO.cell-1));
                study = s2 + " "+ s1;
            }
        }




        return study;
    }

    private ArrayList<String> getTeachers(Workbook wb,GroupCellDTO groupCellDTO, int i){
        String teachers = "";
        teachers = getCellText(wb.getSheetAt(groupCellDTO.sheet).
                getRow(groupCellDTO.row + i ).getCell(groupCellDTO.cell+1));
        boolean teachersb = ExcelUtils.isMergedRegion(wb, groupCellDTO.sheet, groupCellDTO.row + i, groupCellDTO.cell+1);
        if (teachersb){
            if (i % 2 == 0) {
                String s2 = getCellText(wb.getSheetAt(groupCellDTO.sheet).
                        getRow(groupCellDTO.row + i-1 ).getCell(groupCellDTO.cell+1));
                teachers = s2 + " "+ teachers;
            }
        }
        String temp = setOneSpace(teachers);
        temp = temp.replaceAll("[,]", "");
        return getArrayTeachers(temp);
    }

    private ArrayList<String> getArrayTeachers(String temp) {

        String delimiter = " ";
        String[] subData = temp.split(delimiter);
        ArrayList<String> teachers = new ArrayList<>();
        if (subData.length==2){
            subData[1] = correctPointsByTeacher(subData[1]);
            String t1 = subData[0]+" "+subData[1];
            teachers.add(t1);
        }else if (subData.length==4){
            subData[1] = correctPointsByTeacher(subData[1]);
            subData[3] = correctPointsByTeacher(subData[3]);
            String t1 = subData[0]+" "+subData[1];
            String t2 = subData[2]+" "+subData[3];
            teachers.add(t1);
            teachers.add(t2);
        }
        return teachers;
    }
    private String correctPointsByTeacher(String str){
        char endChar = str.charAt(str.length() - 1);
        char condition = '.';
        if(endChar != condition)
        {
            str = str+".";
        }
        char startChar = str.charAt(0);
        if(startChar == condition)
        {
            str = str.substring(1);
        }
        return str;
    }

    private String setOneSpace(String teachers){
        teachers = teachers.trim().replaceAll("\\s{2,}", " ").trim();

        return teachers;
    }

    private ArrayList<Lesson> checkOneLessonTwoNumerators(Lesson lesson, Workbook wb, GroupCellDTO groupCellDTO, int i){
        Lesson lesson1 = null;
        ArrayList<Lesson>lessons = new ArrayList<>();
        if (lesson.getNumerator().equals("null")){
            boolean Numteachers = ExcelUtils.isMergedRegion(wb, groupCellDTO.sheet, groupCellDTO.row + i, groupCellDTO.cell+1);
            if (!Numteachers){
                lesson1 = new Lesson();
                lesson1.setName(lesson.getName());
                lesson1.setGroupp(lesson.getGroupp());
                lesson1.setDay(lesson.getDay());
                lesson1.setNumber(lesson.getNumber());
                ArrayList<String> teachers = getTeachers(wb, groupCellDTO, i+1);
                if (!teachers.get(0).isEmpty()) {
                    lesson1.setTeacher(teachers.get(0));
                }
                if (teachers.size() > 1) {
                    lesson1.setTeacher2(teachers.get(1));
                }
                lesson1.setNumerator("denominator");
                if (!ExcelUtils.isMergedRegion(wb, groupCellDTO.sheet, groupCellDTO.row + i, groupCellDTO.cell-1)){
                    lesson1.setStudy(getStudy(wb, groupCellDTO, i,"denominator" ));
                    lesson.setStudy(getStudy(wb, groupCellDTO, i,"numerator" ));
                }else {
                    lesson1.setStudy(lesson.getStudy());
                }
                lesson.setNumerator("numerator");
                System.out.println(lesson.toString());
                System.out.println(lesson1.toString());
                for (int j = 0; j <10; j++) {
                    System.out.println("--");
                }
            }
        }
        if (lesson1==null){
            lessons.add(lesson);
        }else {
            lessons.add(lesson);
            lessons.add(lesson1);
        }
        return lessons;
    }





    public static String getCellText(Cell cell){
        cell.setCellType(CELL_TYPE_STRING);
        return cell.getRichStringCellValue().getString();
    }






}


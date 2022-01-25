package testgroup.service.parseReplacement;

import testgroup.model.Replacement;

import java.io.File;
import java.io.IOException;
import java.util.ArrayList;

public class EnterPoint {
    private Day day;
    private ArrayList<LessonReplacement> lessonReplacements;
    private ArrayList<Replacement> replacements = new ArrayList<>();
    public ArrayList<Replacement> parse (File file) throws IOException {
        ParseExcel parseExcel = new ParseExcel();
        if (parseExcel.parse(file)){
            day = parseExcel.getParseDay().getDay();
            if (day.getDay() != 999){
                lessonReplacements = parseExcel.getLessonReplacements();
                getParseReplacement();
            }
        }
        return replacements;
    }
    private void getParseReplacement(){
        for (LessonReplacement lessonReplacement : lessonReplacements) {
            Replacement replacement = new Replacement();
            replacement.setId(0);
            replacement.setGroupp(lessonReplacement.getGroupp());
            replacement.setYear(day.getYear());
            replacement.setMonth(day.getMonth());
            replacement.setDay(day.getDay());
            replacement.setNumber(Integer.parseInt(lessonReplacement.getCouple()));
            replacement.setOld_name(lessonReplacement.getOldName());
            replacement.setOld_teacher1(lessonReplacement.getOldTeacher1());
            replacement.setOld_teacher2(lessonReplacement.getOldTeacher2());
            replacement.setOld_study("");
            replacement.setNew_name(lessonReplacement.getNewName());
            replacement.setNew_teacher1(lessonReplacement.getNewTeacher1());
            replacement.setNew_teacher2(lessonReplacement.getNewTeacher2());
            replacement.setNew_study(lessonReplacement.getAuditory());
            replacements.add(replacement);
        }
    }
}

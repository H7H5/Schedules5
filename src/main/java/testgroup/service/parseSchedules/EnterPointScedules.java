package testgroup.service.parseSchedules;

import testgroup.model.Lesson;
import testgroup.model.Replacement;
import testgroup.service.parseReplacement.Day;
import testgroup.service.parseReplacement.LessonReplacement;
import testgroup.service.parseReplacement.ParseExcel;

import java.io.File;
import java.io.IOException;
import java.util.ArrayList;

public class EnterPointScedules {
    private ArrayList<Lesson> lessons = new ArrayList<>();
    public ArrayList<Lesson> parse (File file) throws IOException {
        MainSchedules mainSchedules = new MainSchedules();
        lessons = mainSchedules.parse(file);
        return lessons;
    }
}

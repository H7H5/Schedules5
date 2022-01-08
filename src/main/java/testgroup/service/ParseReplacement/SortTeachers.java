package testgroup.service.ParseReplacement;


import java.util.ArrayList;

public class SortTeachers {
    ArrayList<LessonReplacement> lessonReplacements;
    public SortTeachers(ArrayList<LessonReplacement> lessonReplacements) {
        this.lessonReplacements = lessonReplacements;
    }

    public ArrayList<LessonReplacement> parse(){
        FindTwoTeacher();
        DeleteSpace();
        return lessonReplacements;
    }

    private void FindTwoTeacher(){
        String delimiter = "\\s+";
        for (LessonReplacement lessonReplacement : lessonReplacements) {
            String str = lessonReplacement.getOldTeacher1();
            String[] subStr = str.split(delimiter);
            if (subStr.length > 1) {
                if (subStr[1].length() > 2) {
                    lessonReplacement.setOldTeacher1(subStr[0]);
                    lessonReplacement.setOldTeacher2(subStr[1]);
                }
            }
            str = lessonReplacement.getNewTeacher1();
            if (!lessonReplacement.getNewTeacher1().equals("САМ. РОБОТА")) {
                String[] subStr2 = str.split(delimiter);
                if (subStr2.length > 1) {
                    if (subStr2[1].length() > 2) {
                        lessonReplacement.setNewTeacher1(subStr2[0]);
                        lessonReplacement.setNewTeacher2(subStr2[1]);
                    }
                }
            }
        }
    }

    private void DeleteSpace(){
        for (LessonReplacement lessonReplacement : lessonReplacements) {
            lessonReplacement.setOldTeacher1(lessonReplacement.getOldTeacher1().replaceAll("\\s+", " "));
            lessonReplacement.setNewTeacher1(lessonReplacement.getNewTeacher1().replaceAll("\\s+", " "));
            lessonReplacement.setOldTeacher2(lessonReplacement.getOldTeacher2().replaceAll("\\s+", " "));
            lessonReplacement.setNewTeacher2(lessonReplacement.getNewTeacher2().replaceAll("\\s+", " "));
        }
    }
}

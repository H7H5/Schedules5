package testgroup.service.parseReplacement;


import java.util.ArrayList;


public class SortCouple {
    private ArrayList<LessonReplacement> lessonReplacements;
    public SortCouple(ArrayList<LessonReplacement> lessonReplacements) {
        this.lessonReplacements = lessonReplacements;
    }

    public ArrayList<LessonReplacement> sortLessonsByCouple() {
        ArrayList<LessonReplacement> sortByCouple = new ArrayList<LessonReplacement>();
        ArrayList<LessonReplacement> sortByCouple2 = new ArrayList<LessonReplacement>();
        for (int i = 0; i < lessonReplacements.size(); i++) {
            if (lessonReplacements.get(i).getCouple().contains(".")) {
                sortByCouple.add(lessonReplacements.get(i));
                lessonReplacements.remove(i);
                i--;
            }
            else if (lessonReplacements.get(i).getCouple().contains(",")) {
                sortByCouple2.add(lessonReplacements.get(i));
                lessonReplacements.remove(i);
                i--;
            }
        }
        lessonReplacements.addAll(detachMultiCouple(sortByCouple));
        lessonReplacements.addAll(detachMultiCoupleComa(sortByCouple2));
        return new SortTeachers(lessonReplacements).parse();
    }

    private ArrayList<LessonReplacement> detachMultiCouple(ArrayList<LessonReplacement> lessonReplacements) {
        String delimiter = "\\."; // Разделитель
        return delimeterFind(lessonReplacements,delimiter);
    }
    private ArrayList<LessonReplacement> detachMultiCoupleComa(ArrayList<LessonReplacement> lessonReplacements) {
        String delimiter = ","; // Разделитель
        return delimeterFind(lessonReplacements,delimiter);
    }
    private ArrayList<LessonReplacement> delimeterFind(ArrayList<LessonReplacement> lessonReplacements, String delimiter){
        ArrayList<LessonReplacement> sortByMultiCouple = new ArrayList<LessonReplacement>();
        for (int i = 0; i < lessonReplacements.size(); i++) {
            String str = lessonReplacements.get(i).getCouple();
            String[] subStr = str.split(delimiter);
            for (int j = 0; j < subStr.length; j++) {
                LessonReplacement tempLessonReplacement = lessonReplacements.get(i);
                sortByMultiCouple.add(new LessonReplacement(tempLessonReplacement.getNumberRow(), tempLessonReplacement.getGroupp(),subStr[j],
                        tempLessonReplacement.getOldName(), tempLessonReplacement.getOldTeacher1(),"", tempLessonReplacement.getNewName(),
                        tempLessonReplacement.getNewTeacher1(),"", tempLessonReplacement.getAuditory()));
            }
        }
        return sortByMultiCouple;
    }
}


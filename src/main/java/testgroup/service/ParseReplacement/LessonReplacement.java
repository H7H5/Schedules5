package testgroup.service.ParseReplacement;

public class LessonReplacement {
    private int numberRow;
    private String groupp;
    private String couple;
    private String oldName;
    private String oldTeacher1;
    private String oldTeacher2;
    private String newName;
    private String newTeacher1;
    private String newTeacher2;
    private String auditory;

    public LessonReplacement(int numberRow, String groupp, String couple,
                             String oldName, String oldTeacher1, String oldTeacher2,
                             String newName, String newTeacher1, String newTeacher2,
                             String auditory) {
        this.numberRow = numberRow;
        this.groupp = groupp;
        this.couple = couple;
        this.oldName = oldName;
        this.oldTeacher1 = oldTeacher1;
        this.oldTeacher2 = oldTeacher2;
        this.newName = newName;
        this.newTeacher1 = newTeacher1;
        this.newTeacher2 = newTeacher2;
        this.auditory = auditory;
    }

    public int getNumberRow() {
        return numberRow;
    }

    public String getGroupp() {
        return groupp;
    }

    public void setGroupp(String groupp) {
        this.groupp = groupp;
    }

    public String getCouple() {
        return couple;
    }

    public String getOldName() {
        return oldName;
    }

    public String getOldTeacher1() {
        return oldTeacher1;
    }

    public void setOldTeacher1(String oldTeacher1) {
        this.oldTeacher1 = oldTeacher1;
    }

    public String getOldTeacher2() {
        return oldTeacher2;
    }

    public void setOldTeacher2(String oldTeacher2) {
        this.oldTeacher2 = oldTeacher2;
    }

    public String getNewName() {
        return newName;
    }

    public String getNewTeacher1() {
        return newTeacher1;
    }

    public void setNewTeacher1(String newTeacher1) {
        this.newTeacher1 = newTeacher1;
    }

    public String getNewTeacher2() {
        return newTeacher2;
    }

    public void setNewTeacher2(String newTeacher2) {
        this.newTeacher2 = newTeacher2;
    }

    public String getAuditory() {
        return auditory;
    }

    @Override
    public String toString() {
        return "LessonReplacement{" +
                "numberRow=" + numberRow +
                ", groupp='" + groupp + '\'' +
                ", couple='" + couple + '\'' +
                ", oldName='" + oldName + '\'' +
                ", oldTeacher1='" + oldTeacher1 + '\'' +
                ", oldTeacher2='" + oldTeacher2 + '\'' +
                ", newName='" + newName + '\'' +
                ", newTeacher1='" + newTeacher1 + '\'' +
                ", newTeacher2='" + newTeacher2 + '\'' +
                ", auditory='" + auditory + '\'' +
                '}';
    }
}

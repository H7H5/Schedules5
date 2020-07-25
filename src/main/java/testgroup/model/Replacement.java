package testgroup.model;

import javax.persistence.*;

@Entity
@Table(name = "replacement")
public class Replacement {
    @Id
    @Column(name = "id")
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;

    @Column(name = "groupp")
    private String groupp;

    @Column(name = "year")
    private int year;

    @Column(name = "month")
    private int month;

    @Column(name = "day")
    private int day;

    @Column(name = "number")
    private int number;

    @Column(name = "old_name")
    private String old_name;

    @Column(name = "old_teacher1")
    private String old_teacher1;

    @Column(name = "old_teacher2")
    private String old_teacher2;

    @Column(name = "old_study")
    private String old_study;

    @Column(name = "new_name")
    private String new_name;

    @Column(name = "new_teacher1")
    private String new_teacher1;

    @Column(name = "new_teacher2")
    private String new_teacher2;

    @Column(name = "new_study")
    private String new_study;

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getGroupp() {
        return groupp;
    }

    public void setGroupp(String groupp) {
        this.groupp = groupp;
    }

    public int getYear() {
        return year;
    }

    public void setYear(int year) {
        this.year = year;
    }

    public int getMonth() {
        return month;
    }

    public void setMonth(int month) {
        this.month = month;
    }

    public int getDay() {
        return day;
    }

    public void setDay(int day) {
        this.day = day;
    }

    public int getNumber() {
        return number;
    }

    public void setNumber(int number) {
        this.number = number;
    }

    public String getOld_name() {
        return old_name;
    }

    public void setOld_name(String old_name) {
        this.old_name = old_name;
    }

    public String getOld_teacher1() {
        return old_teacher1;
    }

    public void setOld_teacher1(String old_teacher1) {
        this.old_teacher1 = old_teacher1;
    }

    public String getOld_teacher2() {
        return old_teacher2;
    }

    public void setOld_teacher2(String old_teacher2) {
        this.old_teacher2 = old_teacher2;
    }

    public String getOld_study() {
        return old_study;
    }

    public void setOld_study(String old_study) {
        this.old_study = old_study;
    }

    public String getNew_name() {
        return new_name;
    }

    public void setNew_name(String new_name) {
        this.new_name = new_name;
    }

    public String getNew_teacher1() {
        return new_teacher1;
    }

    public void setNew_teacher1(String new_teacher1) {
        this.new_teacher1 = new_teacher1;
    }

    public String getNew_teacher2() {
        return new_teacher2;
    }

    public void setNew_teacher2(String new_teacher2) {
        this.new_teacher2 = new_teacher2;
    }

    public String getNew_study() {
        return new_study;
    }

    public void setNew_study(String new_study) {
        this.new_study = new_study;
    }
}



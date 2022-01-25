package testgroup.controller;


import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.ModelAndView;
import testgroup.model.Lesson;
import testgroup.model.Replacement;
import testgroup.service.LessonService;
import testgroup.service.ReplacementService;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.stream.Collectors;

@Controller
public class ScheduleController {
    private LessonService lessonService;
    private ReplacementService replacementService;

    @Autowired
    public void setLessonService(LessonService lessonService) {
        this.lessonService = lessonService;
    }
    @Autowired
    public void setReplacementService(ReplacementService replacementService) {
        this.replacementService = replacementService;
    }
    @RequestMapping(method = RequestMethod.GET)
    public ModelAndView Select() {
        List<Lesson> lessons = lessonService.allLesson();
        ModelAndView modelAndView = new ModelAndView();
        modelAndView.setViewName("selectSchedules");
        modelAndView.addObject("lessonsList", lessons);
        return modelAndView;
    }
    @RequestMapping(value = "select",method = RequestMethod.GET)
    public ModelAndView selectType(@ModelAttribute("groupp") String groupp){
        String gr = "groups";
        String te = "teachers";
        String gr_json = "groups_json";
        String te_json = "teachers_json";
        String edit_group = "edit_group";
        ModelAndView modelAndView = new ModelAndView();
        if (groupp.equals(gr)){
            List<String> grup = lessonService.allGroup();
            Collections.sort(grup);
            grup = grup.stream().distinct().collect(Collectors.toList());
            modelAndView.setViewName("selectGroup");
            modelAndView.addObject("grup",grup);

        }else if(groupp.equals(te)){
            List<String> teacher = lessonService.allTeacher();
            Collections.sort(teacher);
            teacher = teacher.stream().distinct().collect(Collectors.toList());
            modelAndView.setViewName("selectTeacher");
            modelAndView.addObject("teacher",teacher);
        }else if(groupp.equals(gr_json)){
            ArrayList<String> grup = (ArrayList<String>) lessonService.allGroup();
            Collections.sort(grup);
            grup = (ArrayList<String>) grup.stream().distinct().collect(Collectors.toList());
            //JSONArray jsArray = new JSONArray(grup);
            //String jsonText = jsArray.toString();
            //System.out.print(jsonText);
            String test2 = "";
            test2 = test2.concat("{\"response\":[{");
            for (int i = 0 ;i < grup.size();i++){
                if (i==grup.size()-1){
                    test2 = test2.concat("\""+i+"\":");
                    test2 = test2.concat("\""+grup.get(i)+"\"");
                }else {
                    test2 = test2.concat("\""+i+"\":");
                    test2 = test2.concat("\""+grup.get(i)+"\",");
                }
            }
            test2 = test2.concat("}]}");
            modelAndView.setViewName("test");
            modelAndView.addObject("grup",test2);
        }else if(groupp.equals(te_json)){
            ArrayList<String> teacher = (ArrayList<String>) lessonService.allTeacher();
            Collections.sort(teacher);
            teacher = (ArrayList<String>) teacher.stream().distinct().collect(Collectors.toList());
            String test2 = "";
            test2 = test2.concat("{\"response\":[{");
            String gvxcv = "vdfd";
            for (int i = 0 ;i < teacher.size();i++){
                if (i==teacher.size()-1){
                    test2 = test2.concat("\""+i+"\":");
                    test2 = test2.concat("\""+teacher.get(i)+"\"");
                }else {
                    test2 = test2.concat("\""+i+"\":");
                    test2 = test2.concat("\""+teacher.get(i)+"\",");
                }
            }
            test2 = test2.concat("}]}");
            modelAndView.setViewName("test");
            modelAndView.addObject("grup",test2);
        }else if(groupp.equals(edit_group)){
            List<Lesson> lessons = lessonService.allLesson();
            modelAndView = new ModelAndView();
            modelAndView.setViewName("schedules");
            //modelAndView.setViewName("selectGroup");
            modelAndView.addObject("lessonsList", lessons);

        }
        return modelAndView;
    }
    @RequestMapping(value = "schedules",method = RequestMethod.GET)
    public ModelAndView allLesson() {
        List<Lesson> lessons = lessonService.allLesson();
        ModelAndView modelAndView = new ModelAndView();
        modelAndView.setViewName("schedules");
        modelAndView.addObject("lessonsList", lessons);
        return modelAndView;
    }
    @RequestMapping(value = "/replacements",method = RequestMethod.GET)
    public ModelAndView allReplacement(){
        List<Replacement> replacements = replacementService.allReplacement();
        ModelAndView modelAndView = new ModelAndView();
        modelAndView.setViewName("replacements");
        modelAndView.addObject("replacementsList",replacements);
        return modelAndView;
    }
    @RequestMapping(value = "/schedulesGroup", method = RequestMethod.GET)
    public ModelAndView allLessonGroup(@ModelAttribute("groupp") String groupp){
        List<Lesson> lessons = lessonService.allLessonGroup(groupp);
        ModelAndView modelAndView = new ModelAndView();
        modelAndView.setViewName("schedules");
        modelAndView.addObject("lessonsList", lessons);
        return modelAndView;
    }
    @RequestMapping(value = "/schedulesTeacher", method = RequestMethod.GET)
    public ModelAndView allLessonTeacher(@ModelAttribute("teacher") String teacher){
        List<Lesson> lessons = lessonService.allLessonTeacher(teacher);
        ModelAndView modelAndView = new ModelAndView();
        modelAndView.setViewName("schedules");
        modelAndView.addObject("lessonsList", lessons);
        return modelAndView;
    }
    @RequestMapping(value = "/edit/{id}", method = RequestMethod.GET)
    public ModelAndView editPage(@PathVariable("id") int id) {
        Lesson lesson = lessonService.getById(id);
        ModelAndView modelAndView = new ModelAndView();
        modelAndView.setViewName("editPage");
        modelAndView.addObject("lesson", lesson);
        modelAndView.addObject("lesson", lessonService.getById(id));
        return modelAndView;
    }
    @RequestMapping(value = "/editReplacement/{id}", method = RequestMethod.GET)
    public ModelAndView editPageReplacement(@PathVariable("id") int id) {
        Replacement replacement = replacementService.getById(id);
        ModelAndView modelAndView = new ModelAndView();
        modelAndView.setViewName("editPageReplacement");
        modelAndView.addObject("replacement", replacement);
        modelAndView.addObject("replacement", replacementService.getById(id));
        return modelAndView;
    }
    @RequestMapping(value = "/edit", method = RequestMethod.POST)
    public ModelAndView editLesson(@ModelAttribute("lesson") Lesson lesson) {
        ModelAndView modelAndView = new ModelAndView();
        modelAndView.setViewName("redirect:/schedules");
        lessonService.edit(lesson);
        return modelAndView;
    }
    @RequestMapping(value = "/editReplacement", method = RequestMethod.POST)
    public ModelAndView editReplacement(@ModelAttribute("replacement") Replacement replacement) {
        ModelAndView modelAndView = new ModelAndView();
        modelAndView.setViewName("redirect:/replacements");
        replacementService.edit(replacement);
        return modelAndView;
    }
    @RequestMapping(value = "/add", method = RequestMethod.GET)
    public ModelAndView addPage() {
        ModelAndView modelAndView = new ModelAndView();
        modelAndView.setViewName("editPage");
        return modelAndView;
    }
    @RequestMapping(value = "/addReplacement", method = RequestMethod.GET)
    public ModelAndView addPageReplacement() {
        ModelAndView modelAndView = new ModelAndView();
        modelAndView.setViewName("editPageReplacement");
        return modelAndView;
    }
    @RequestMapping(value = "/add", method = RequestMethod.POST)
    public ModelAndView addLesson(@ModelAttribute("lesson") Lesson lesson) {
        ModelAndView modelAndView = new ModelAndView();
        modelAndView.setViewName("redirect:/schedules");
        lessonService.add(lesson);
        return modelAndView;
    }
    @RequestMapping(value = "/addReplacement", method = RequestMethod.POST)
    public ModelAndView addReplacement(@ModelAttribute("replacement") Replacement replacement) {
        ModelAndView modelAndView = new ModelAndView();
        modelAndView.setViewName("redirect:/replacements");
        replacementService.add(replacement);
        return modelAndView;
    }
    @RequestMapping(value="/delete/{id}", method = RequestMethod.GET)
    public ModelAndView deleteFilm(@PathVariable("id") int id) {
        ModelAndView modelAndView = new ModelAndView();
        modelAndView.setViewName("redirect:/schedules");
        Lesson lesson = lessonService.getById(id);
        lessonService.delete(lesson);
        return modelAndView;
    }
    @RequestMapping(value="/deleteReplacement/{id}", method = RequestMethod.GET)
    public ModelAndView deleteReplacement(@PathVariable("id") int id) {
        ModelAndView modelAndView = new ModelAndView();
        modelAndView.setViewName("redirect:/replacements");
        System.out.println(id);
        Replacement replacement = replacementService.getById(id);
        replacementService.delete(replacement);
        return modelAndView;
    }
    @RequestMapping(value = "/select/{grup}", method = RequestMethod.GET)
    public ModelAndView Select(@PathVariable("grup") String grup) {
        List<Lesson> lessons = lessonService.allLessonGroup(grup);
        ModelAndView modelAndView = new ModelAndView();
        // modelAndView.setViewName("schedules");
        String test2 = "";
        test2 = test2.concat("{\"response\":[");
        for (int i = 0 ;i < lessons.size();i++){
            String k;
            if (i==lessons.size()-1) {
                k = "";
            }else {
                k=",";
            }
            test2 = test2.concat("{\"name\":\"" + lessons.get(i).getName() + "\",");
            test2 = test2.concat("\"group\":\"" + lessons.get(i).getGroupp() + "\",");
            test2 = test2.concat("\"teacher\":\"" + lessons.get(i).getTeacher() + "\",");
            test2 = test2.concat("\"teacher2\":\"" + lessons.get(i).getTeacher2() + "\",");
            test2 = test2.concat("\"day\":\"" + lessons.get(i).getDay() + "\",");
            test2 = test2.concat("\"study\":\"" + lessons.get(i).getStudy() + "\",");
            test2 = test2.concat("\"numerator\":\"" + lessons.get(i).getNumerator() + "\",");
            test2 = test2.concat("\"number\":\"" + lessons.get(i).getNumber() + "\"}"+k);
        }
        test2 = test2.concat("]}");
        modelAndView.setViewName("test");
        modelAndView.addObject("grup",test2);
        return modelAndView;
    }
    @RequestMapping(value = "/JsonReplacement", method = RequestMethod.GET)
    public ModelAndView JsonReplacements( ){
        List<Replacement> replacements1 = replacementService.allParseReplacement();
        List<Replacement> replacements = replacementService.allReplacement();
        ModelAndView modelAndView = new ModelAndView();
        // modelAndView.setViewName("schedules");
        String test2 = "";
        test2 = test2.concat("{\"response\":[");
        for (int i = 0 ;i < replacements.size();i++){
            String k;
            if (i==replacements.size()-1) {
                k = "";
            }else {
                k=",";
            }
            test2 = test2.concat("{\"group\":\"" + replacements.get(i).getGroupp() + "\",");
            test2 = test2.concat("\"year\":\"" + replacements.get(i).getYear() + "\",");
            test2 = test2.concat("\"month\":\"" + replacements.get(i).getMonth() + "\",");
            test2 = test2.concat("\"day\":\"" + replacements.get(i).getDay() + "\",");
            test2 = test2.concat("\"number\":\"" + replacements.get(i).getNumber() + "\",");
            test2 = test2.concat("\"old_name\":\"" + replacements.get(i).getOld_name() + "\",");
            test2 = test2.concat("\"old_teacher1\":\"" + replacements.get(i).getOld_teacher1() + "\",");
            test2 = test2.concat("\"old_teacher2\":\"" + replacements.get(i).getOld_teacher2() + "\",");
            test2 = test2.concat("\"old_study\":\"" + replacements.get(i).getOld_study() + "\",");
            test2 = test2.concat("\"new_name\":\"" + replacements.get(i).getNew_name()+ "\",");
            test2 = test2.concat("\"new_teacher1\":\"" + replacements.get(i).getNew_teacher1() + "\",");
            test2 = test2.concat("\"new_teacher2\":\"" + replacements.get(i).getNew_teacher2() + "\",");
            test2 = test2.concat("\"new_study\":\"" + replacements.get(i).getNew_study() + "\"}"+k);
        }
        test2 = test2.concat("]}");
        modelAndView.setViewName("JsonReplacement");
        modelAndView.addObject("replacements",test2);
        return modelAndView;
    }
    @RequestMapping(value = "/editGroup/{grup}", method = RequestMethod.GET)
    public ModelAndView EditGroup(@PathVariable("grup") String grup) {
        List<Lesson> lessons = lessonService.allLessonGroup(grup);
        ModelAndView modelAndView = new ModelAndView();
        modelAndView.setViewName("schedules");
        //modelAndView.setViewName("selectGroup");
        modelAndView.addObject("lessonsList", lessons);
        return modelAndView;
    }
    @RequestMapping(value = "/editTeacher/{teacher}", method = RequestMethod.GET)
    public ModelAndView EditTeacher (@PathVariable("teacher") String teacher) {

        List<String> teachers = lessonService.allTeacher();
        Collections.sort(teachers);
        teachers = teachers.stream().distinct().collect(Collectors.toList());
        String tempTe = "";
        for (int i = 0 ;i < teachers.size();i++){
            String text = "" + i;
            if (teacher.equals(text)){
                tempTe = teachers.get(i);
                tempTe=tempTe.substring(0, tempTe.length() - 1);
            }
        }
        List<Lesson> lessons = lessonService.allLessonTeacher(tempTe);
        ModelAndView modelAndView = new ModelAndView();
        String test2="";
        test2 = test2.concat("{\"response\":[");
        for (int i = 0 ;i < lessons.size();i++){
            if (i==lessons.size()-1) {
                test2 = test2.concat("{\"name\":\"" + lessons.get(i).getName() + "\",");
                test2 = test2.concat("\"group\":\"" + lessons.get(i).getGroupp() + "\",");
                test2 = test2.concat("\"teacher\":\"" + lessons.get(i).getTeacher() + "\",");
                test2 = test2.concat("\"teacher2\":\"" + lessons.get(i).getTeacher2() + "\",");
                test2 = test2.concat("\"day\":\"" + lessons.get(i).getDay() + "\",");
                test2 = test2.concat("\"study\":\"" + lessons.get(i).getStudy() + "\",");
                test2 = test2.concat("\"numerator\":\"" + lessons.get(i).getNumerator() + "\",");
                test2 = test2.concat("\"number\":\"" + lessons.get(i).getNumber() + "\"}");
            }else {
                test2 = test2.concat("{\"name\":\"" + lessons.get(i).getName() + "\",");
                test2 = test2.concat("\"group\":\"" + lessons.get(i).getGroupp() + "\",");
                test2 = test2.concat("\"teacher\":\"" + lessons.get(i).getTeacher() + "\",");
                test2 = test2.concat("\"teacher2\":\"" + lessons.get(i).getTeacher2() + "\",");
                test2 = test2.concat("\"day\":\"" + lessons.get(i).getDay() + "\",");
                test2 = test2.concat("\"study\":\"" + lessons.get(i).getStudy() + "\",");
                test2 = test2.concat("\"numerator\":\"" + lessons.get(i).getNumerator() + "\",");
                test2 = test2.concat("\"number\":\"" + lessons.get(i).getNumber() + "\"},");
            }
        }
        test2 = test2.concat("]}");
        modelAndView.setViewName("test");
        modelAndView.addObject("grup",test2);
        return modelAndView;
    }
    @RequestMapping(value = "/selectT/{teacher}", method = RequestMethod.GET)
    public ModelAndView SelectT(@PathVariable("teacher") String teacher) {

        List<String> teachers = lessonService.allTeacher();
        Collections.sort(teachers);
        teachers = teachers.stream().distinct().collect(Collectors.toList());
        String tempTe = "";
        for (int i = 0 ;i < teachers.size();i++){
            String text = "" + i;
            if (teacher.equals(text)){
                tempTe = teachers.get(i);
                tempTe=tempTe.substring(0, tempTe.length() - 1);
            }
        }
        List<Lesson> lessons = lessonService.allLessonTeacher(tempTe);
        ModelAndView modelAndView = new ModelAndView();
        String test2="";
        test2 = test2.concat("{\"response\":[");
        for (int i = 0 ;i < lessons.size();i++){
            if (i==lessons.size()-1) {
                test2 = test2.concat("{\"name\":\"" + lessons.get(i).getName() + "\",");
                test2 = test2.concat("\"group\":\"" + lessons.get(i).getGroupp() + "\",");
                test2 = test2.concat("\"teacher\":\"" + lessons.get(i).getTeacher() + "\",");
                test2 = test2.concat("\"teacher2\":\"" + lessons.get(i).getTeacher2() + "\",");
                test2 = test2.concat("\"day\":\"" + lessons.get(i).getDay() + "\",");
                test2 = test2.concat("\"study\":\"" + lessons.get(i).getStudy() + "\",");
                test2 = test2.concat("\"numerator\":\"" + lessons.get(i).getNumerator() + "\",");
                test2 = test2.concat("\"number\":\"" + lessons.get(i).getNumber() + "\"}");
            }else {
                test2 = test2.concat("{\"name\":\"" + lessons.get(i).getName() + "\",");
                test2 = test2.concat("\"group\":\"" + lessons.get(i).getGroupp() + "\",");
                test2 = test2.concat("\"teacher\":\"" + lessons.get(i).getTeacher() + "\",");
                test2 = test2.concat("\"teacher2\":\"" + lessons.get(i).getTeacher2() + "\",");
                test2 = test2.concat("\"day\":\"" + lessons.get(i).getDay() + "\",");
                test2 = test2.concat("\"study\":\"" + lessons.get(i).getStudy() + "\",");
                test2 = test2.concat("\"numerator\":\"" + lessons.get(i).getNumerator() + "\",");
                test2 = test2.concat("\"number\":\"" + lessons.get(i).getNumber() + "\"},");
            }
        }
        test2 = test2.concat("]}");
        modelAndView.setViewName("test");
        modelAndView.addObject("grup",test2);
        return modelAndView;
    }

    @RequestMapping(value = "/parseReplacements",method = RequestMethod.GET)
    public ModelAndView parseReplacement(){
        List<Replacement> replacements = replacementService.allParseReplacement();
        ModelAndView modelAndView = new ModelAndView();
        modelAndView.setViewName("parseReplacement");
        modelAndView.addObject("replacementsList",replacements);
        return modelAndView;
    }
    @RequestMapping(value = "/DeleteAllReplacement", method = RequestMethod.GET)
    public ModelAndView DeleteAllReplacement( ){
        replacementService.deleteAllReplacement();
        List<Replacement> replacements = replacementService.allReplacement();
        ModelAndView modelAndView = new ModelAndView();
        modelAndView.setViewName("replacements");
        modelAndView.addObject("replacementsList",replacements);
        return modelAndView;
    }

    @RequestMapping(value = "/deleteSchedules",method = RequestMethod.GET)
    public ModelAndView deleteSchedules(){
        ModelAndView modelAndView = new ModelAndView();
        modelAndView.setViewName("redirect:/schedules");
        List<Lesson> lessons = lessonService.allLesson();
        for (int i = 0; i <lessons.size() ; i++) {
            Lesson lesson = lessonService.getById(lessons.get(i).getId());
            lessonService.delete(lesson);
        }
        return modelAndView;
    }
    @RequestMapping(value = "/parseSchedules",method = RequestMethod.GET)
    public ModelAndView parseSchedules(){
        lessonService.parseSchedules();
        ModelAndView modelAndView = new ModelAndView();
        modelAndView.setViewName("redirect:/schedules");
        return modelAndView;
    }
}
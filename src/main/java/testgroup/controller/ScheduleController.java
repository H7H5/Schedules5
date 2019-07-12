package testgroup.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.ModelAndView;
import testgroup.model.Lesson;
import testgroup.service.LessonService;

import java.util.Collections;
import java.util.List;
import java.util.stream.Collectors;

@Controller
public class ScheduleController {
    private LessonService lessonService;

    @Autowired
    public void setFilmService(LessonService lessonService) {
        this.lessonService = lessonService;
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
        ModelAndView modelAndView = new ModelAndView();
        if (groupp.equals(gr)){
            List<String> grup = lessonService.allGroup();
            Collections.sort(grup);
            grup = grup.stream().distinct().collect(Collectors.toList());
            modelAndView.setViewName("selectGroup");
            modelAndView.addObject("grup",grup);

        }else{
            List<String> teacher = lessonService.allTeacher();
            Collections.sort(teacher);
            teacher = teacher.stream().distinct().collect(Collectors.toList());
            modelAndView.setViewName("selectTeacher");
            modelAndView.addObject("teacher",teacher);
        }
        return modelAndView;
    }

    @RequestMapping(value = "schedules",method = RequestMethod.GET)
    public ModelAndView allLesson() {
        List<Lesson> lessons = lessonService.allLesson();
        ModelAndView modelAndView = new ModelAndView();
        modelAndView.setViewName("schedules");
        //modelAndView.setViewName("selectGroup");
        modelAndView.addObject("lessonsList", lessons);
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
    @RequestMapping(value = "/edit", method = RequestMethod.POST)
    public ModelAndView editLesson(@ModelAttribute("lesson") Lesson lesson) {
        ModelAndView modelAndView = new ModelAndView();
        modelAndView.setViewName("redirect:/schedules");
        lessonService.edit(lesson);
        return modelAndView;
    }
    @RequestMapping(value = "/add", method = RequestMethod.GET)
    public ModelAndView addPage() {
        ModelAndView modelAndView = new ModelAndView();
        modelAndView.setViewName("editPage");
        return modelAndView;
    }
    @RequestMapping(value = "/add", method = RequestMethod.POST)
    public ModelAndView addLesson(@ModelAttribute("lesson") Lesson lesson) {
        ModelAndView modelAndView = new ModelAndView();
        modelAndView.setViewName("redirect:/schedules");
        lessonService.add(lesson);
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
    @RequestMapping(value = "/select/{grup}", method = RequestMethod.GET)
    public ModelAndView Select(@PathVariable("grup") String grup) {
        List<Lesson> lessons = lessonService.allLessonGroup(grup);
        ModelAndView modelAndView = new ModelAndView();
        modelAndView.setViewName("schedules");
        modelAndView.addObject("lessonsList", lessons);
        return modelAndView;
    }
    @RequestMapping(value = "/selectT/{teacher}", method = RequestMethod.GET)
    public ModelAndView SelectT(@PathVariable("teacher") String teacher) {
        List<Lesson> lessons = lessonService.allLessonTeacher(teacher);
        ModelAndView modelAndView = new ModelAndView();
        modelAndView.setViewName("schedules");
        modelAndView.addObject("lessonsList", lessons);
        return modelAndView;
    }
}

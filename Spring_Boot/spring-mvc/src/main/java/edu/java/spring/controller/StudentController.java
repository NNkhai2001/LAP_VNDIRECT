package edu.java.spring.controller;

import edu.java.spring.dao.StudentDAO;
import edu.java.spring.dao.impl.StudentDAOimpl;
import edu.java.spring.model.Student;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.servlet.ModelAndView;

import javax.servlet.ServletContext;
import javax.servlet.http.HttpServletRequest;
import javax.validation.Valid;
import java.io.ByteArrayOutputStream;
import java.io.File;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.nio.file.StandardOpenOption;

@Controller
@RequestMapping(value = "student")
public class StudentController {
    /*
    @RequestMapping(value = "add", method = RequestMethod.GET)
    public ModelAndView add() {
        ModelAndView mv = new ModelAndView();
        mv.setViewName("student.form");
        return mv;
    }

    @RequestMapping(value = "save", method = RequestMethod.POST)
    public ModelAndView save(@RequestParam(value = "name", required = false) String name
            , @RequestParam(value = "age", required = false) int age) {
        ModelAndView mv = new ModelAndView();
        Student student = new Student(name,age);
        mv.addObject("student", student);
        mv.setViewName("student.view");
        return mv;
    }

     */
    /*
    @RequestMapping(value = "add", method = RequestMethod.GET)
    public ModelAndView add() {
        return new ModelAndView("student.form", "command", new Student());

    }

    @RequestMapping(value = "save", method = RequestMethod.POST)
    public ModelAndView save(@Valid @ModelAttribute("command") Student student, BindingResult result) {
        ModelAndView mv = new ModelAndView();
        if (result.hasErrors()) {
            mv = new ModelAndView("student.form", "command", student);
            mv.addObject("errors", result);
            mv.setViewName("student.form");
            return mv;
        }
        mv.addObject("student", student);
        mv.setViewName("student.view");
        return mv;
    }

     */
    //Program web MVC with JDBC
    @Autowired
    //Add
    private StudentDAO studentDAO;

    @RequestMapping(value = "add", method = RequestMethod.GET)
    public ModelAndView add() {
        return new ModelAndView("student.form", "command", new Student());

    }

    //Save
    @RequestMapping(value = "save", method = {RequestMethod.POST})
    public ModelAndView save(@Valid @ModelAttribute("command") Student student, BindingResult result) {
        if (result.hasErrors()) {
            ModelAndView mv = new ModelAndView();
            mv = new ModelAndView("student.form", "command", student);
            mv.addObject("errors", result);
            mv.setViewName("student.form");
            return mv;
        }
        if (student.getId() > 0) {
            studentDAO.Update(student);
        } else {

            studentDAO.insert(student);
        }
        return new ModelAndView("redirect:/student/list");
    }

    //List All from table
    @RequestMapping(value = "list", method = RequestMethod.GET)
    public ModelAndView listStudent(@RequestParam(value = "q", required = false) String query) {
        ModelAndView mode = new ModelAndView();
        if (query == null) {
            mode.setViewName("student.list");
            mode.addObject("students", studentDAO.list());
            return mode;
        } else {
            mode.setViewName("student.list");
            mode.addObject("students", studentDAO.listStudentByName(query));
            return mode;
        }
    }

    //delete from table
    @RequestMapping(value = "delete/{id}", method = RequestMethod.GET)
    public String delete(@PathVariable String id) {
        int Id = Integer.parseInt(id);
        studentDAO.Delete(Id);
        return "redirect:/student/list";
    }

    //getStudentById
    @RequestMapping(value = "edit/{id}", method = RequestMethod.GET)
    public ModelAndView edit(@PathVariable String id) {
        int Id = Integer.parseInt(id);
        Student student = studentDAO.getStudentById(Id);
        return new ModelAndView("../student.form", "command", student);
    }

    //forward link
    @RequestMapping(value = "edit/save", method = RequestMethod.POST)
    public String saveEdit() {
        return "forward:/student/save";
    }

    //json
    @RequestMapping(value = "json/{id}", method = RequestMethod.GET)
    public @ResponseBody Student viewJson(@PathVariable String id) {
        return studentDAO.getStudentById(Integer.parseInt(id));
    }

    //Upload File
    @RequestMapping(value = "avatar/save", method = RequestMethod.POST)
    public String handleFormUpload(@RequestParam("file") MultipartFile file, HttpServletRequest request, int id) throws IOException {
        if (file.isEmpty()) return "student.error";
        byte[] bytes = file.getBytes();
        System.out.println("found---->" + bytes.length);
        Path avatarFile = getImageFile(request, id);
        Files.write(avatarFile, file.getBytes(), StandardOpenOption.CREATE);
        System.out.println(avatarFile);
        return "redirect:/student/list";
    }

    private Path getImageFile(HttpServletRequest request, int id) {
        ServletContext servletContext = request.getSession().getServletContext();
        String diskPath = servletContext.getRealPath("/");
        File folder = new File(diskPath + File.separator + "avatar" + File.separator);
        folder.mkdirs();
        return Paths.get(String.valueOf(new File(folder, String.valueOf(id) + ".jpg")));
    }

    //View Avatar
    @RequestMapping(value = "avatar/{id}", method = RequestMethod.GET)
    public ResponseEntity<byte[]> getImage(@PathVariable Integer id, HttpServletRequest request) throws IOException {
        ByteArrayOutputStream byteOutput = new ByteArrayOutputStream();
        if (id != null) {
            Path avatarPath = getImageFile(request, id);
            if (Files.exists(avatarPath)) byteOutput.write(Files.readAllBytes(avatarPath));
        }
        HttpHeaders headers = new HttpHeaders();
        headers.setContentType(MediaType.IMAGE_JPEG);
        return new ResponseEntity<byte[]>(byteOutput.toByteArray(), headers, HttpStatus.CREATED);
    }



}

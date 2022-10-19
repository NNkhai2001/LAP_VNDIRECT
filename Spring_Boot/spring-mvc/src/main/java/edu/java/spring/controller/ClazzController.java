package edu.java.spring.controller;

import edu.java.spring.dao.StudentDAO;
import edu.java.spring.model.JavaClazz;
import edu.java.spring.utils.XSLTUtils;
import edu.java.spring.view.ExcelBuilder;
import net.sf.jasperreports.engine.JRDataSource;
import net.sf.jasperreports.engine.data.JRBeanCollectionDataSource;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.servlet.ModelAndView;
import org.xml.sax.SAXException;

import javax.xml.bind.JAXBException;
import javax.xml.parsers.ParserConfigurationException;
import java.io.IOException;

@Controller
@RequestMapping(value = "clazz")
public class ClazzController {
    @Autowired
    private StudentDAO studentDAO;

    @RequestMapping(value = "xml", method = RequestMethod.GET, produces = MediaType.APPLICATION_XML_VALUE)
    public @ResponseBody JavaClazz viewInXML() {
        return new JavaClazz(studentDAO.list());
    }

    @RequestMapping(value = "json", method = RequestMethod.GET, produces = MediaType.APPLICATION_JSON_VALUE)
    public @ResponseBody JavaClazz viewInJSON() {
        return new JavaClazz(studentDAO.list());
    }

    @RequestMapping(value = "xslt",method = RequestMethod.GET)
    public ModelAndView viewXSLT() throws JAXBException, ParserConfigurationException, IOException, SAXException {
        JavaClazz clazz = new JavaClazz(studentDAO.list());
        ModelAndView model = new ModelAndView("ClazzView");
        model.getModelMap().put("data", XSLTUtils.clazzToDomSource(clazz));
        return model;
    }
    @RequestMapping(value = "excel" ,method = RequestMethod.GET)
    public ModelAndView viewExcel() {
        JavaClazz clazz = new JavaClazz(studentDAO.list());
        ModelAndView model = new ModelAndView("excelView");
        model.getModelMap().put("clazzObj",clazz);
        return model;
    }
    @RequestMapping(value = "pdf",method = RequestMethod.GET,produces = "application/pdf")
    public ModelAndView viewPdf() {
        JavaClazz clazz = new JavaClazz(studentDAO.list());
        ModelAndView model = new ModelAndView("pdfView");
        model.getModelMap().put("clazzObj",clazz);
        return model;
    }
    @RequestMapping(value = "report",method = RequestMethod.GET,produces = "application/pdf")
    public ModelAndView viewReport() {
        ModelAndView mv = new ModelAndView("pdfReport");
        JRDataSource dataSource = new JRBeanCollectionDataSource(studentDAO.list());
        mv.addObject("dataSource",dataSource);
        return mv;

    }
}

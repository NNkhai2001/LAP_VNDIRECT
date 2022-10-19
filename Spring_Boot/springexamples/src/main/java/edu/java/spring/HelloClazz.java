package edu.java.spring;

import org.springframework.beans.BeansException;
import org.springframework.beans.factory.DisposableBean;
import org.springframework.beans.factory.config.BeanPostProcessor;
import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;

import java.util.List;

public class HelloClazz implements DisposableBean {
    String message;
    private List<JavaClazz> clazzes;

    public List<JavaClazz> getClazzes() {
        return clazzes;
    }

    public void setClazzes(List<JavaClazz> clazzes) {
        this.clazzes = clazzes;
    }

    public String getMessage() {
        return message;
    }

    public HelloClazz() {
        message = "From constructor:Say hello everyone!";
    }

    public HelloClazz(String message) {
        this.message = message;
    }
    public HelloClazz(int person) {
        message = "From Constructor:Say hello to"+person+" person(s)!";
    }
    public HelloClazz(HelloClazz clazz) {
        message = clazz.message;
    }

    public void setMessage(String message) {

        this.message = "Call From Setter: "+message;
    }
    public void printMessage() {
        System.out.println("Your Message: "+message);
    }

    private void release() {
        message = null;
        System.out.println("Message is "+message);
    }

    @Override
    public void destroy() throws Exception {
        message = null;
        System.out.println("Message null");
    }
    private void initMessage() {
        System.out.println("Calling init method...");
        message = "From init method:Say hello bean!";
    }

}


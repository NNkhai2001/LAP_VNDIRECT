package edu.java.spring;

import org.apache.log4j.Logger;
import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;

/**
 * Hello world!
 */
public class App {
    private final static Logger LOGGER = Logger.getLogger(JavaClazz.class);

    public static void main(String[] args) {
        ClassPathXmlApplicationContext context = new ClassPathXmlApplicationContext("beans.xml");
        //HelloClazz
        //context.registerShutdownHook();
//        HelloClazz obj = (HelloClazz) context.getBean("helloJavaClazz2");
//        obj.printMessage();
        //System.out.println(obj);
//        HelloClazz obj2 = (HelloClazz) context.getBean("helloJavaClazz");
//        obj2.printMessage();
//        System.out.println(obj2 == obj);


        //HELLOWORLD
//         HelloWorld HelloWorld =(HelloWorld) context.getBean("helloWorld");
//         HelloWorld.sayHello();


        //MAP
//        JavaClazz clazz = (JavaClazz) context.getBean("jee01");
//        LOGGER.info("Map Implement is: " + clazz.getStudents().getClass());
//        LOGGER.info("There are " + clazz.getStudents().size() + " in the class");
//        HelloClazz clazzes = (HelloClazz) context.getBean("helloJavaClazz");
//        LOGGER.info("Total classes is: " + clazzes.getClazzes().size());
        HelloWorld helloWorld = (HelloWorld) context.getBean("helloWorld");
        helloWorld.sayHello();

       // context.start();
    }

}

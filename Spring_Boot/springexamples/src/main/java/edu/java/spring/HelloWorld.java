package edu.java.spring;
import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.beans.factory.annotation.Required;

public class HelloWorld {
    @Autowired(required = true)
   // @Qualifier("helloJavaClazz2")
    private HelloClazz clazz;
    private final static Logger LOGGER = Logger.getLogger(HelloWorld.class);
    String message;
    public HelloWorld(){}

    public void setClazz(HelloClazz clazz) {
        this.clazz = clazz;
    }
@Required
    public void setMessage(String message) {
        this.message = message;
    }

    public void sayHello() {
        clazz.printMessage();
        LOGGER.info("From HelloWorld: "+message);
    }

    public HelloWorld(String name, HelloClazz clazz) {
        message = "From HelloWorld constructor: "+name+":"+clazz.getMessage();

    }
    //-----------------------------------------------


}

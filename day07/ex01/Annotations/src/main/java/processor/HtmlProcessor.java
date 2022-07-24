package processor;

import javax.annotation.processing.AbstractProcessor;
import javax.annotation.processing.RoundEnvironment;
import javax.lang.model.element.TypeElement;
import java.util.Set;

public class HtmlProcessor extends AbstractProcessor {
    private Object object;
    private Class c;

    public HtmlProcessor(Object object, Class c) {
        this.object = object;
        this.c = c;
    }

    @Override
    public boolean process(Set<? extends TypeElement> annotations, RoundEnvironment roundEnv) {
        System.out.println("here");
        
        return false;
    }


}


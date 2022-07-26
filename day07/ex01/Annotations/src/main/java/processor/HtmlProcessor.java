package processor;

import com.google.auto.service.AutoService;

import javax.annotation.processing.AbstractProcessor;
import javax.annotation.processing.Processor;
import javax.annotation.processing.RoundEnvironment;
import javax.annotation.processing.SupportedAnnotationTypes;
import javax.lang.model.element.Element;
import javax.lang.model.element.TypeElement;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.Set;

@AutoService(Processor.class)
@SupportedAnnotationTypes({"annotations.HtmlForm", "annotations.HtmlInput"})
public class HtmlProcessor extends AbstractProcessor {
    @Override
    public boolean process(Set<? extends TypeElement> annotations, RoundEnvironment roundEnv) {
        System.out.println("here");
//        Path file = Paths.get();
        for (TypeElement annotation : annotations) {
            Set<? extends Element> annotatedElements
                = roundEnv.getElementsAnnotatedWith(annotation);
            System.out.println(annotatedElements.toString());
        }
        return true;
    }


}


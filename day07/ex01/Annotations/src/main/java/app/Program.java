package app;

import classes.UserForm;
import processor.HtmlProcessor;

public class Program {
    public static void main(String[] args) {
        UserForm userForm = new UserForm();
        HtmlProcessor htmlProcessor = new HtmlProcessor(userForm, userForm.getClass());
        htmlProcessor.process(null, null);
    }
}

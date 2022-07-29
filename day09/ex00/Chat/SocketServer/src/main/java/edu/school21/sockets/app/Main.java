package edu.school21.sockets.app;

import com.beust.jcommander.JCommander;
import com.beust.jcommander.Parameter;
import com.beust.jcommander.Parameters;
import edu.school21.sockets.server.Server;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;

import java.security.SecureRandom;

//@Parameters(separators = "=")
public class Main {
//    @Parameter(names = {"--port", "-p"})
//    int port;
//    public static void main(String[] args) {
//        Main main = new Main();
//
//        JCommander.newBuilder().addObject(main).build().parse(args);
//        main.run();
//    }
//
//    public void run() {
//        Server server = new Server(port);
//    }
    public static void main(String[] args) {
        String pass = "password";
        System.out.println(pass);
        PasswordEncoder passwordEncoder = new BCryptPasswordEncoder(10, new SecureRandom());
        String pass1 = passwordEncoder.encode(pass);
        String pass2 = passwordEncoder.encode("password");
        String pass3 = passwordEncoder.encode("password");
        System.out.println(pass1 + "  " + passwordEncoder.matches(pass, pass1));
        System.out.println(pass2 + "  " + passwordEncoder.matches(pass, pass2));
        System.out.println(pass3 + "  " + passwordEncoder.matches(pass, pass3));
    }
}

package printer;

import render.Renderer;

import java.time.LocalDateTime;

public class PrinterWithDateTimeImpl implements Printer<LocalDateTime>{
    private LocalDateTime dateTime;
    private Renderer renderer;

    public PrinterWithDateTimeImpl(Renderer renderer) {
        this.renderer = renderer;
        this.dateTime = LocalDateTime.now();
    }

    @Override
    public void print(LocalDateTime dateTime) {
        renderer.printConsole(dateTime.toString());
    }
}

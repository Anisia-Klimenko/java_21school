package printer;

import render.Renderer;

public class PrinterWithPrefixImpl implements Printer<String>{
    private Renderer renderer;
    private String prefix;

    public PrinterWithPrefixImpl(Renderer renderer) {
        this.renderer = renderer;
        this.prefix = "";
    }

    public void setPrefix(String prefix) {
        this.prefix = prefix;
    }

    @Override
    public void print(String message) {
        renderer.printConsole(this.prefix + " " + message);
    }
}

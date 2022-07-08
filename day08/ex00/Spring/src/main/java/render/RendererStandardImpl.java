package render;

import preprocessor.PreProcessor;

public class RendererStandardImpl implements Renderer {
    private PreProcessor preProcessor;

    public RendererStandardImpl(PreProcessor preProcessor) {
        this.preProcessor = preProcessor;
    }

    @Override
    public void printConsole(String message) {
        System.out.println(preProcessor.process(message));
    }
}

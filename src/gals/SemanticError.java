package gals;

public class SemanticError extends AnalysisError {

    private int line = 0;

    public SemanticError(String msg, int position) {
        super(msg, position);
    }

    public SemanticError(String msg, Token t) {
        super(msg, t.getPosition());
        line = t.getLine();

    }

    public int getLine() {
        return line;
    }
    
    
}

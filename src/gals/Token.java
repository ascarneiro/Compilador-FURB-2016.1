package gals;

public class Token {
	// Constantes para montagem da declaração
	private final int WIDTHCOLUMN_LINE = 8;
	private final int WIDTHCOLUMN_CLAZZ = 20;
	private final int WIDTHCOLUMN_LEXEME = 12;

	private int id;
	private String lexeme;
	private int position;
	private String clazz;
	private int line;

	public Token(int id, String lexeme, int position, String clazz, int line) {
		this.id = id;
		this.lexeme = lexeme;
		this.position = position;
		this.clazz = clazz;
		this.line = line;
	}

	public final int getId() {
		return id;
	}

	public final String getLexeme() {
		return lexeme;
	}

	public final int getPosition() {
		return position;
	}

	public String getClazz() {
		return clazz;
	}

	public int getLine() {
		return line;
	}

	private String colocaEspaco(int qtdEspaco) {
		String resultado = "";
		for (int i = 0; i < qtdEspaco; i++) {
			resultado += " ";
		}
		return resultado;
	}

	private String montaDeclaracao() {
		String resultado = "";

		resultado += line + colocaEspaco(WIDTHCOLUMN_LINE - String.valueOf(line).length());
		resultado += clazz + colocaEspaco(WIDTHCOLUMN_CLAZZ - String.valueOf(clazz).length());
		resultado += lexeme + colocaEspaco(WIDTHCOLUMN_LEXEME - String.valueOf(lexeme).length());

		return resultado;
	}

	public String toString() {
		return montaDeclaracao();
	}
}
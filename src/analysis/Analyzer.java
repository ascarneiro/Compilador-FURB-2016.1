package analysis;

/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
import gals.Constants;
import gals.LexicalError;
import gals.Lexico;
import gals.SemanticError;
import gals.Semantico;
import gals.Sintatico;
import gals.SyntaticError;
import java.awt.Color;
import java.io.BufferedWriter;
import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import javax.swing.JTextArea;
import javax.swing.text.DefaultHighlighter;

public class Analyzer {

    private static Analyzer analyzerInstance;
    private JTextArea cod = null;

    public static Analyzer getInstance() {
        if (analyzerInstance == null) {
            analyzerInstance = new Analyzer();
        }
        return analyzerInstance;
    }

    private String nomeArquivo, caminhoArquivo;
    private String codigoGerado;

    private String source;
    private final String SUCCESS = "Programa compilado com sucesso\n";

    public Analyzer() {
        source = "";
        codigoGerado = "";
    }

    public void setCod(JTextArea cod) {
        this.cod = cod;
    }

    public void setSource(String source) {
        this.source = source;
    }

    public String compile() throws Exception {
        padrao();
        String resultado = "";
        if (source != null && !source.isEmpty()) {
            Lexico lexico = new Lexico();
            lexico.setInput(source);
            try {
                new Sintatico().parse(lexico, new Semantico());
                resultado = SUCCESS;
            } catch (LexicalError | SyntaticError le) {
                if (le.getMessage().equals("símbolo inválido")) {
                    selecionar(le.getPosition(), getLinha(le.getPosition()), getSimbolo(le.getPosition()));
                    throw new Exception("Erro na linha " + getLinha(le.getPosition()) + " - " + getSimbolo(le.getPosition()) + " " + le.getMessage());

                } else {
                    selecionar(le.getPosition(), getLinha(le.getPosition()), getSimbolo(le.getPosition()));
                    throw new Exception("Erro na linha " + getLinha(le.getPosition()) + " - " + le.getMessage());
                }
            } catch (SemanticError e) {
                selecionar(e.getPosition(), getLinha(e.getPosition()), getSimbolo(e.getPosition()));
                throw new Exception("Erro na linha " + getLinha(e.getPosition()) + " " + e.getMessage());
            } catch (Exception e) {
                e.printStackTrace();
            }
        }
        return resultado;
    }

    public void setArquivo(String nomeArquivo, String caminhoArquivo) {
        this.nomeArquivo = nomeArquivo.replace(".txt", ".il");
        this.caminhoArquivo = caminhoArquivo;
    }

    public String getNomeArquivo() {
        return nomeArquivo;
    }

    public void save() throws IOException {
        File arquivoLido = new File(caminhoArquivo + "/" + nomeArquivo);
        FileWriter fw = new FileWriter(arquivoLido);
        BufferedWriter bw = new BufferedWriter(fw);
        try {
            bw.append(codigoGerado);
        } finally {
            bw.close();
        }
    }

    public String format(String texto, int tamanho) {

        while (texto.length() < tamanho) {
            texto += " ";
        }
        return texto;
    }

    public void limpar() {
        codigoGerado = "";
    }

    public void adicionaCodigo(String codigo) {
        codigoGerado += codigo + "\r\n";
    }

    public int getLinha(int pos) {

        String[] linhas = source.split("\n");

        int posCount = 0;
        int linhaCount = 0;

        for (String linha : linhas) {
            posCount += (linha.length() + 1);
            linhaCount++;

            if (pos < posCount) {
                return linhaCount;
            }
        }

        return linhaCount++;
    }

    public String getSimbolo(int pos) {
        return String.valueOf(source.charAt(pos));
    }

    public String getClasse(int id) {

        switch (id) {
            case Constants.t_int:
            case Constants.t_float:
            case Constants.t_string:
            case Constants.t_bool:
                return "identificador";
            case Constants.t_constint:
                return "constante inteira";
            case Constants.t_constfloat:
                return "constante float";
            case Constants.t_conststring:
                return "constante string";
            case Constants.t_TOKEN_7:
            case Constants.t_TOKEN_8:
            case Constants.t_TOKEN_9:
            case Constants.t_TOKEN_10:
            case Constants.t_TOKEN_11:
            case Constants.t_TOKEN_12:
            case Constants.t_TOKEN_13:
            case Constants.t_TOKEN_14:
            case Constants.t_TOKEN_15:
            case Constants.t_TOKEN_16:
            case Constants.t_TOKEN_17:
            case Constants.t_TOKEN_18:
            case Constants.t_TOKEN_19:
            case Constants.t_TOKEN_20:
            case Constants.t_TOKEN_21:
            case Constants.t_TOKEN_22:
            case Constants.t_TOKEN_23:
            case Constants.t_TOKEN_24:
            case Constants.t_TOKEN_25:
            case Constants.t_TOKEN_26:
                return "símbolo especial";
            case Constants.t_and:
            case Constants.t_false:
            case Constants.t_if:
            case Constants.t_in:
            case Constants.t_isFalseDo:
            case Constants.t_isTrueDo:
            case Constants.t_main:
            case Constants.t_module:
            case Constants.t_not:
            case Constants.t_or:
            case Constants.t_out:
            case Constants.t_true:
            case Constants.t_while:
            case Constants.t_palavra:
                return "palavra reservada";
        }
        return String.valueOf(id);
    }

    private void selecionar(int ix, int ix2, String text) {

        String[] linhaStrings = cod.getText().split("\n");
        int tamanho = 5;
        if (ix2 <= linhaStrings.length) {
            try {
                tamanho = linhaStrings[ix2 - 1].length();
            } catch (Exception e) {
                tamanho = 3;
            }
        }

        DefaultHighlighter highlighter = (DefaultHighlighter) cod.getHighlighter();
        DefaultHighlighter.DefaultHighlightPainter painter = new DefaultHighlighter.DefaultHighlightPainter(Color.RED);
        highlighter.setDrawsLayeredHighlights(false); // this is the key line
        try {
            highlighter.addHighlight(cod.getLineStartOffset(ix2 - 1), cod.getLineStartOffset(ix2 - 1) + tamanho, painter);
        } catch (Exception e) {
            System.out.println(e);
        }
    }

    private void padrao() {
        String text = cod.getText();
        cod.setText("");
        cod.setText(text);

    }
}

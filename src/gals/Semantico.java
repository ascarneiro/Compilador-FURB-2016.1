package gals;

import analysis.Analyzer;
import java.util.ArrayList;
import java.util.HashMap;

public class Semantico implements Constants {

    private final String MSG_EXPRESSAO = " tipos incompatíveis em expressão relacional.";
    private final String MSG_TIPOS_INCOMPATIVEIS = "%s e %s possuem tipos incompatíveis";
    private final String MSG_IDENTIFICADOR_NAO_DECLARADO = " identificador (%s) não declarado.";
    private final String MSG_IDENTIFICADOR_DECLARADO = " identificador (%s) já declarado.";
    private final String MSG_ATRIBUICAO = " tipos incompatíveis em comando de atribuição.";
    private final String MSG_TIPO_NAO_BOOLEANO = " não sao permitidos tipos booleanos.";
    private final String MSG_TIPO_BOOLEANO = " somente sao permitidos tipos booleanos.";

    //Registros semanticos
    private Pilha pilha_tipos = new Pilha();
    private Pilha pilhaIF = new Pilha();
    private Pilha pilhaWhile = new Pilha();
    private Pilha pilha_enderecos = new Pilha();
    private ArrayList<String> lista_identificadores = new ArrayList<String>();
    private HashMap<String, String> tabela_de_simbolos = new HashMap<String, String>();
    private HashMap<String, String> arrays = new HashMap<String, String>();
    private String tamanho = "0";
    private int _nextLabel = 1;
    private String tipo = "", operador = "", valor_inicial = "";
    private String idExpressoes = "";
    private Boolean isANDOuOR = false;

    String tipo1, tipo2, id, idIndex;
    String TAB1 = "\t";
    String TAB2 = "\t\t";

    private Analyzer analyzer;

    public Semantico() {
    }

    public void executeAction(int action, Token token) throws SemanticError {
        this.analyzer = Analyzer.getInstance();
        switch (action) {
            case 1:
                executeAction01(token);
                break;

            case 2:
                executeAction02(token);
                break;

            case 3:
                executeAction03(token);
                break;

            case 4:
                executeAction04(token);
                break;

            case 5:
                executeAction05(token);
                break;

            case 6:
                executeAction06(token);
                break;

            case 7:
                executeAction07(token);
                break;

            case 8:
                executeAction08(token);
                break;

            case 9:
                executeAction09(token);
                break;

            case 10:
                executeAction10(token);
                break;

            case 11:
                executeAction11(token);
                break;

            case 12:
                executeAction12(token);
                break;

            case 13:
                executeAction13(token);
                break;

            case 14:
                executeAction14(token);
                break;

            case 15:
                executeAction15(token);
                break;

            case 16:
                executeAction16(token);
                break;

            case 17:
                executeAction17(token);
                break;

            case 18:
                executeAction18(token);
                break;

            case 19:
                executeAction19(token);
                break;
            case 20:
                executeAction20(token);
                break;
            case 21:
                executeAction21(token);
                break;
            case 22:
                executeAction22(token);
                break;
            case 23:
                executeAction23(token);
                break;
            case 24:
                executeAction24(token);
                break;
            case 25:
                executeAction25(token);
                break;
            case 26:
                executeAction26(token);
                break;
            case 27:
                executeAction27(token);
                break;
            case 28:
                executeAction28(token);
                break;
            case 29:
                executeAction29(token);
                break;
            case 30:
                executeAction30(token);
                break;
            case 31:
                executeAction31(token);
                break;
            case 32:
                executeAction32(token);
                break;
            case 33:
                executeAction33(token);
                break;
            case 34:
                executeAction34(token);
                break;
            case 35:
                executeAction35(token);
                break;
            case 36:
                executeAction36(token);
                break;
            case 37:
                executeAction37(token);
                break;
            default:
                throw new IllegalArgumentException("Ação semântica não implementada: #" + action);
        }

    }

    public void executeAction01(Token token) throws SemanticError {
        tipo1 = pilha_tipos.desempilha();
        tipo2 = pilha_tipos.desempilha();
        if (tipo1.equals("String") || tipo1.equals("Bool")
                || tipo2.equals("String") || tipo2.equals("Bool")) {
            System.out.println("GALS - Acao 01 " + MSG_EXPRESSAO);
            throw new SemanticError(MSG_EXPRESSAO, token);
        }

        if (tipo1.equals("Float64") || tipo2.equals("Float64")) {
            pilha_tipos.empilha("Float64");
        } else {
            pilha_tipos.empilha("Int64");
        }
        analyzer.adicionaCodigo(TAB2 + "add");
    }

    public void executeAction02(Token token) throws SemanticError {
        tipo1 = pilha_tipos.desempilha();
        tipo2 = pilha_tipos.desempilha();
        if (tipo1.equals("String") || tipo1.equals("Bool") || tipo2.equals("String") || tipo2.equals("Bool")) {
            System.out.println("GALS - Acao 02 " + MSG_EXPRESSAO);
            throw new SemanticError(MSG_EXPRESSAO, token);
        }

        if (tipo1.equals("Float64") || tipo2.equals("Float64")) {
            pilha_tipos.empilha("Float64");
        } else {
            pilha_tipos.empilha("Int64");
        }
        analyzer.adicionaCodigo(TAB2 + "sub");
    }

    public void executeAction03(Token token) throws SemanticError {
        tipo1 = pilha_tipos.desempilha();
        tipo2 = pilha_tipos.desempilha();
        if (tipo1.equals("String") || tipo1.equals("Bool") || tipo2.equals("String") || tipo2.equals("Bool")) {
            System.out.println("GALS - Acao 03 " + MSG_EXPRESSAO);
            throw new SemanticError(MSG_EXPRESSAO, token);
        }

        if (tipo1.equals("Float64") || tipo2.equals("Float64")) {
            pilha_tipos.empilha("Float64");
        } else {
            pilha_tipos.empilha("Int64");
        }
        analyzer.adicionaCodigo(TAB2 + "mul");
    }

    public void executeAction04(Token token) throws SemanticError {
        tipo1 = pilha_tipos.desempilha();
        tipo2 = pilha_tipos.desempilha();
        if (tipo1.equals(tipo2)) {
            pilha_tipos.empilha(tipo1);
            if (token.getLexeme().startsWith("]")) {
                analyzer.adicionaCodigo(TAB2 + "ldelem int64");
                analyzer.adicionaCodigo(TAB2 + "conv.r8");
            }

            analyzer.adicionaCodigo(TAB2 + "div");
        } else {
            System.out.println("GALS - Acao 04 Operando invalido divisão");
            throw new SemanticError("Operando invalido divisão", token);
        }

    }

    public void executeAction05(Token token) {
        pilha_tipos.empilha("Int64");
        analyzer.adicionaCodigo(TAB2 + "ldc.i8 " + token.getLexeme());

    }

    public void executeAction06(Token token) {
        pilha_tipos.empilha("Float64");
        analyzer.adicionaCodigo(TAB2 + "ldc.r8 " + token.getLexeme().replace(',', '.'));
    }

    public void executeAction07(Token token) throws SemanticError {
        tipo1 = pilha_tipos.desempilha();
        if (tipo.equals("Int64") || tipo.equals("Float64")) {
            pilha_tipos.empilha(tipo1);
        } else {
            System.out.println("GALS - Acao 07 " + MSG_EXPRESSAO);
            throw new SemanticError(MSG_EXPRESSAO, token);
        }
    }

    public void executeAction08(Token token) throws SemanticError {
        tipo1 = pilha_tipos.desempilha();
        if (tipo.equals("Int64") || tipo.equals("Float64")) {
            pilha_tipos.empilha(tipo1);
        } else {
            System.out.println("GALS - Acao 08 " + MSG_EXPRESSAO);
            throw new SemanticError(MSG_EXPRESSAO, token);
        }
        analyzer.adicionaCodigo(TAB2 + "ldc.i8 -1");
        analyzer.adicionaCodigo(TAB2 + "mul");
    }

    public void executeAction09(Token token) throws SemanticError {

        tipo1 = pilha_tipos.desempilha();
        tipo2 = pilha_tipos.desempilha();
        if (tipo1.equalsIgnoreCase(tipo2)) {
            pilha_tipos.empilha("Bool");
        } else {
            System.out.println("GALS - Acao 09 " + MSG_EXPRESSAO);
            throw new SemanticError(MSG_EXPRESSAO, token);
        }

        switch (operador) {
            case "==":
                analyzer.adicionaCodigo(TAB2 + "ceq ");
                break;
            case "!=":
                analyzer.adicionaCodigo(TAB2 + "ceq ");
                analyzer.adicionaCodigo(TAB2 + "ldc.i4.1 ");
                analyzer.adicionaCodigo(TAB2 + "xor ");
                break;
            case "<":
                analyzer.adicionaCodigo(TAB2 + "clt ");
                break;
            case "<=":
                analyzer.adicionaCodigo(TAB2 + "cgt ");
                analyzer.adicionaCodigo(TAB2 + "ldc.i4.1");
                analyzer.adicionaCodigo(TAB2 + "xor ");
                break;
            case ">":
                analyzer.adicionaCodigo(TAB2 + "cgt ");
                break;
            case ">=":
                analyzer.adicionaCodigo(TAB2 + "clt ");
                analyzer.adicionaCodigo(TAB2 + "ldc.i4.1 ");
                analyzer.adicionaCodigo(TAB2 + "xor ");
                break;
        }

        operador = "";
    }

    public void executeAction10(Token token) throws SemanticError {
        operador = token.getLexeme();
    }

    public void executeAction11(Token token) throws SemanticError {
        tipo = pilha_tipos.desempilha();
        analyzer.adicionaCodigo(TAB2 + "call void [mscorlib]System.Console::Write(" + tipo.toString().toLowerCase() + ") ");

    }

    public void executeAction12(Token token) {
        analyzer.limpar();
        String nomeArquivoAux = analyzer.getNomeArquivo().replace(".il", "");
        analyzer.adicionaCodigo(".assembly extern mscorlib {}");
        analyzer.adicionaCodigo(".assembly " + nomeArquivoAux + "{}");
        nomeArquivoAux = analyzer.getNomeArquivo().replace(".il", ".exe");
        analyzer.adicionaCodigo(".module " + nomeArquivoAux);
        analyzer.adicionaCodigo(".class public _Principal{");
        analyzer.adicionaCodigo(".method static public void _principal() {");
        analyzer.adicionaCodigo(TAB2 + ".entrypoint");
    }

    public void executeAction13(Token token) {
        analyzer.adicionaCodigo(TAB2 + "ret");
        analyzer.adicionaCodigo(TAB1 + "}");
        analyzer.adicionaCodigo("}");
    }

    public void executeAction14(Token token) throws SemanticError {
        tipo = pilha_tipos.desempilha();
        if (!tipo.equals("Bool")) {
            System.out.println("GALS - Acao 14 " + MSG_EXPRESSAO);
            throw new SemanticError(MSG_EXPRESSAO, token);
        }
        pilha_tipos.empilha("Bool");
        analyzer.adicionaCodigo(TAB2 + "and");
        isANDOuOR = true;
    }

    public void executeAction15(Token token) throws SemanticError {
        tipo = pilha_tipos.desempilha();
        if (tipo.equals("Bool")) {
            System.out.println("GALS - Acao 15 " + MSG_EXPRESSAO);
            throw new SemanticError(MSG_EXPRESSAO, token);
        }
        pilha_tipos.empilha("Bool");
        analyzer.adicionaCodigo(TAB2 + "or");
        isANDOuOR = true;
    }

    public void executeAction16(Token token) {
        pilha_tipos.empilha("Bool");
        analyzer.adicionaCodigo(TAB2 + "ldc.i4.1");
    }

    public void executeAction17(Token token) {
        pilha_tipos.empilha("Bool");
        analyzer.adicionaCodigo(TAB2 + "ldc.i4.0");
    }

    public void executeAction18(Token token) throws SemanticError {
        tipo = pilha_tipos.desempilha();
        if (!tipo.equals("Bool")) {
            System.out.println("GALS - Acao 18 " + MSG_EXPRESSAO);
            throw new SemanticError(MSG_EXPRESSAO, token);
        }

        pilha_tipos.empilha(tipo);
        analyzer.adicionaCodigo(TAB2 + "ldc.i4.1 ");
        analyzer.adicionaCodigo(TAB2 + "xor");
    }

    public void executeAction19(Token token) {
        pilha_tipos.empilha("String");
        String val = token.getLexeme();
        switch (val) {
            case "\\space":
                analyzer.adicionaCodigo(TAB2 + "ldstr " + "\" \"");
                break;
            case "\\newline":
                analyzer.adicionaCodigo(TAB2 + "ldstr " + "\"\n\"");
                break;
            case "\\tab":
                analyzer.adicionaCodigo(TAB2 + "ldstr " + "\"\t\"");
                break;
            default:
                analyzer.adicionaCodigo(TAB2 + "ldstr " + token.getLexeme());
                break;
        }

    }

    public void executeAction20(Token token) throws SemanticError {
        idIndex = token.getLexeme();
    }

    public void executeAction21(Token token) {
        if (!lista_identificadores.contains(token.getLexeme())) {
            lista_identificadores.add(token.getLexeme());
            pilha_tipos.empilha(getTipo(token.getLexeme()));
        }

    }

    public void executeAction22(Token token) throws SemanticError {
        String ids = "";
        for (String id1 : lista_identificadores) {
            if (tabela_de_simbolos.containsKey(id1)) {
                System.out.println("GALS - Acao 22 " + String.format(MSG_IDENTIFICADOR_DECLARADO, id1));
                throw new SemanticError(String.format(MSG_IDENTIFICADOR_DECLARADO, id1), token);
            }

            tabela_de_simbolos.put(id1, tamanho);
            if (!tamanho.equalsIgnoreCase("0")) {
                arrays.put(id1, id1);
                analyzer.adicionaCodigo(TAB2 + ".locals(" + getTipo(id1).toLowerCase() + "[] " + id1 + ")");
                analyzer.adicionaCodigo(TAB2 + "ldc.i8 " + tamanho);
                analyzer.adicionaCodigo(TAB2 + "newarr [mscorlib] System." + getTipo(id1));
                analyzer.adicionaCodigo(TAB2 + "stloc " + id1);
                tamanho = "0";
            } else {
                ids += getTipo(id1).toLowerCase() + " " + id1;
                if (!ids.isEmpty()
                        && !arrays.containsKey(id1.trim())) {//Declara em outro ponto os arrays
                    analyzer.adicionaCodigo(TAB2 + ".locals ( " + ids + " )");
                }
            }

        }

        lista_identificadores.clear();
        tipo = "";
    }

    public void executeAction23(Token token) throws SemanticError {
        try {
            for (String ids : lista_identificadores) {
                if (!tabela_de_simbolos.containsKey(ids)) {
                    System.out.println("GALS - Acao 23 " + String.format(MSG_IDENTIFICADOR_NAO_DECLARADO, ids));
                    throw new SemanticError(String.format(MSG_IDENTIFICADOR_NAO_DECLARADO, ids), token);
                }
                String tipo = getTipo(ids);

                if (arrays.containsKey(ids) || !ids.equalsIgnoreCase(idIndex)) {

                    analyzer.adicionaCodigo(TAB2 + "call string [mscorlib]System.Console::ReadLine()");
                    switch (tipo) {
                        case "Int64":
                            analyzer.adicionaCodigo(TAB2 + "call int64 [mscorlib]System.Int64::Parse(string)");
                            break;
                        case "Float64":
                            analyzer.adicionaCodigo(TAB2 + "call float [mscorlib]System.Double::Parse(string)");
                            break;
                        case "String":
                            analyzer.adicionaCodigo(TAB2 + "call string [mscorlib]System.Boolean::Parse(string)");
                            break;
                        case "Bool":
                            analyzer.adicionaCodigo(TAB2 + "call bool [mscorlib]System.Boolean::Parse(string)");
                            break;
                    }
                    if (arrays.containsKey(ids)) {
                        analyzer.adicionaCodigo(TAB2 + "stelem " + getTipo(ids).toLowerCase());
                    } else {
                        analyzer.adicionaCodigo(TAB2 + "stloc " + ids);
                    }
                }

            }
        } finally {
            lista_identificadores.clear();
        }
    }

    public void executeAction24(Token token) throws SemanticError {
        String idLocal = token.getLexeme();
        if (!idLocal.startsWith("]")) {

            if (!idExpressoes.isEmpty()
                    && !tabela_de_simbolos.containsKey(idExpressoes)) {
                System.out.println("GALS - Acao 24 " + String.format(MSG_IDENTIFICADOR_NAO_DECLARADO, idExpressoes));
                throw new SemanticError(String.format(MSG_IDENTIFICADOR_NAO_DECLARADO, idExpressoes), token);
            }

            if (!idExpressoes.isEmpty()
                    && Integer.parseInt(tabela_de_simbolos.get(idExpressoes)) > 0) {

                analyzer.adicionaCodigo(TAB2 + "ldloc " + idLocal);
                analyzer.adicionaCodigo(TAB2 + "ldelem " + getTipo(idExpressoes).toLowerCase());

                pilha_tipos.empilha(getTipo(idExpressoes));
                idExpressoes = "";
            } else {

                pilha_tipos.empilha(getTipo(idLocal));
                analyzer.adicionaCodigo(TAB2 + "ldloc " + idLocal);
            }
        }
    }

    public void executeAction25(Token token) throws SemanticError {
        if (!tabela_de_simbolos.containsKey(id)) {
            System.out.println("GALS - Acao 25 " + String.format(MSG_IDENTIFICADOR_NAO_DECLARADO, id));
            throw new SemanticError(String.format(MSG_IDENTIFICADOR_NAO_DECLARADO, id), token);
        }

        tipo1 = getTipo(id);
        tipo2 = pilha_tipos.desempilha();
        if (!tipo1.equals(tipo2)) {
            System.out.println("GALS - Acao 25 " + MSG_ATRIBUICAO);
            throw new SemanticError(MSG_ATRIBUICAO, token);
        }
        if (!arrays.containsKey(id)) {
            analyzer.adicionaCodigo(TAB2 + "stloc " + id);
        } else {
            analyzer.adicionaCodigo(TAB2 + "stelem " + getTipo(id).toLowerCase());
        }

    }

    private void executeAction26(Token token) {
        tamanho = token.getLexeme();

    }

    private void executeAction27(Token token) {
        analyzer.adicionaCodigo(TAB2 + "ldloc " + token.getLexeme());
    }

    //Fim if
    private void executeAction29(Token token) {
        analyzer.adicionaCodigo(pilhaIF.topoPilha() + ":");
    }

    //Inicio IF
    private void executeAction30(Token token) {
        if (!token.getLexeme().equalsIgnoreCase("isTrueDo") && !token.getLexeme().equalsIgnoreCase("isFalseDo")) {
            idExpressoes = token.getLexeme();
            analyzer.adicionaCodigo(TAB2 + "ldloc " + idExpressoes);
        }

    }

    private void executeAction28(Token token) throws SemanticError {
        String labelName = "";
        String br = "";
        if (token.getLexeme().equalsIgnoreCase("isTrueDo")) {
            br = "brfalse";

            analyzer.adicionaCodigo(br);
            labelName = nextLabel();
            analyzer.adicionaCodigo(labelName);

        } else {
            br = "brtrue";

            labelName = nextLabel();
            pilhaIF.empilha(labelName);

            analyzer.adicionaCodigo(br);
            analyzer.adicionaCodigo(labelName);

            labelName = nextLabel();
            pilhaIF.empilha(labelName);

            analyzer.adicionaCodigo("br");
            analyzer.adicionaCodigo(pilhaIF.desempilha());
            analyzer.adicionaCodigo(pilhaIF.desempilha() + ":");
        }

        pilhaIF.empilha(labelName);

    }

    //Inicio While
    private void executeAction31(Token token) throws SemanticError {
        String labelName = nextLabel();
        pilhaWhile.empilha(labelName);
        analyzer.adicionaCodigo(labelName + ":");
    }

    //condicao while
    private void executeAction32(Token token) throws SemanticError {
        String labelName = nextLabel();
        String br;
        if (token.getLexeme().equalsIgnoreCase("isTrueDo")) {
            br = "brfalse";
        } else {
            br = "brtrue";
        }
        analyzer.adicionaCodigo(TAB2 + br + " " + labelName);
        pilhaWhile.empilha(labelName);
    }

    //Fim while
    private void executeAction33(Token token) throws SemanticError {
        String label = pilhaWhile.desempilha();
        analyzer.adicionaCodigo(TAB2 + "br " + pilhaWhile.desempilha());
        analyzer.adicionaCodigo(label + ":");
    }

    private void executeAction34(Token token) throws SemanticError {
        String tipo = pilha_tipos.desempilha();
        if (!"Bool".equalsIgnoreCase(tipo)) {
            System.out.println("GALS - Acao 34 " + MSG_TIPO_BOOLEANO);
            throw new SemanticError(MSG_TIPO_BOOLEANO, token);
        }
    }

    private void executeAction35(Token token) throws SemanticError {
        String tipo = pilha_tipos.desempilha();
        if ("Bool".equalsIgnoreCase(tipo)) {
            System.out.println("GALS - Acao 35 " + MSG_TIPO_BOOLEANO);
            throw new SemanticError(MSG_TIPO_NAO_BOOLEANO, token);
        }
    }

    private void executeAction36(Token token) throws SemanticError {
        if (isANDOuOR) {
            String tipo1 = pilha_tipos.desempilha().toLowerCase();
            String tipo2 = pilha_tipos.desempilha().toLowerCase();
            if ((tipo1.equalsIgnoreCase("int64") && tipo2.equalsIgnoreCase("int64"))
                    || (tipo1.equalsIgnoreCase("float64") && tipo2.equalsIgnoreCase("float64"))
                    || (tipo1.equalsIgnoreCase("string") && tipo2.equalsIgnoreCase("string"))
                    || (tipo1.equalsIgnoreCase("bool") && tipo2.equalsIgnoreCase("bool"))) {
                pilha_tipos.empilha(tipo1);
                isANDOuOR = false;
            } else {
                System.out.println("GALS - Acao 36 " + String.format(MSG_TIPOS_INCOMPATIVEIS, tipo1, tipo2));
                throw new SemanticError(String.format(MSG_TIPOS_INCOMPATIVEIS, tipo1, tipo2), token);
            }
        }
    }

    private void executeAction37(Token token) {
        id = token.getLexeme();
    }

    private String getTipo(String id1) {
        if (id1.length() > 1) {
            String tipoId = id1.substring(0, 2);
            switch (tipoId) {
                case "b_":
                    return "Bool";
                case "i_":
                    return "Int64";
                case "f_":
                    return "Float64";
                case "s_":
                    return "String";

            }
        }

        return "Int64";
    }

    private String nextLabel() {
        return "l" + String.valueOf(_nextLabel++);
    }

}

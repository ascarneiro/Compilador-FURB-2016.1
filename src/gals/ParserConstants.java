package gals;

public interface ParserConstants
{
    int START_SYMBOL = 43;

    int FIRST_NON_TERMINAL    = 43;
    int FIRST_SEMANTIC_ACTION = 80;

    int[][] PARSER_TABLE =
    {
        { -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1,  0, -1, -1, -1, -1, -1, -1, -1, -1, -1 },
        { -1, -1,  3,  3,  3,  3, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1 },
        { -1, -1, 30, 30, 30, 30, -1, -1, -1, -1, -1, 30, -1, -1, -1, 30, 30, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, 30, -1, -1, -1, -1, -1, -1, 30, -1, -1, 30, -1, 30, 30, 30 },
        { -1, -1, 13, 13, 13, 13, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, 13, 13, -1, -1, -1, -1, -1, -1, 13, -1, 13, -1, -1, -1 },
        { -1, -1, 23, 23, 23, 23, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1 },
        { -1, -1, -1, -1, -1, -1, -1,  2, -1, -1, -1, -1, -1,  1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1 },
        { -1, -1,  4,  4,  4,  4, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1 },
        { -1, -1,  9, 10, 11, 12, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1 },
        { -1, -1, 21, 21, 21, 21, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1 },
        { -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, 22, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1 },
        { -1, -1, 24, 24, 24, 24, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1 },
        { -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, 29, -1, -1, -1, -1, -1 },
        { -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, 33, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1 },
        { -1, -1, 36, 36, 36, 36, -1, -1, -1, -1, -1, -1, -1, -1, 36, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, 36, 36, 37, -1, -1, -1, -1, -1, 36, -1, 36, -1, -1, -1 },
        { -1, -1, 38, 38, 38, 38, -1, -1, -1, -1, -1, -1, -1, -1, 38, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, 38, 38, -1, 39, -1, -1, -1, -1, 38, -1, 38, -1, -1, -1 },
        { -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, 40, -1, -1, -1 },
        { -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, 42, 41, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1 },
        { -1, -1, 14, 14, 14, 14, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, 17, 15, -1, -1, -1, -1, -1, -1, 16, -1, 18, -1, -1, -1 },
        { -1, -1, 43, 43, 43, 43, -1, -1, -1, -1, -1, 43, -1, -1, -1, 43, 43, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, 43, -1, -1, -1, -1, -1, -1, 43, -1, -1, 43, -1, 43, 43, 43 },
        { -1, -1, 47, 47, 47, 47, -1, -1, -1, -1, -1, 47, -1, -1, -1, 47, 47, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, 49, -1, -1, -1, -1, -1, -1, 50, -1, -1, 48, -1, 47, 47, 47 },
        { -1, -1, 51, 51, 51, 51, -1, -1, -1, -1, -1, 51, -1, -1, -1, 51, 51, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, 51, 51, 51 },
        { -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, 54, 55, 56, 57, 58, 59, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1 },
        { -1, -1, 60, 60, 60, 60, -1, -1, -1, -1, -1, 60, -1, -1, -1, 60, 60, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, 60, 60, 60 },
        { -1, -1, -1, -1, -1, -1, 75, -1, 75, 76, 75, -1, 75, -1, -1, 75, 75, 75, 75, -1, 75, 75, 75, 75, 75, 75, 75, -1, -1, -1, -1, -1, -1, -1, -1, 75, -1, -1, -1, -1, -1, -1 },
        { -1, -1, -1, -1, -1, -1, 44, -1, 44, -1, 44, -1, 44, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, 46, -1, -1, -1, -1, -1, -1, -1, -1, 45, -1, -1, -1, -1, -1, -1 },
        { -1, -1, -1, -1, -1, -1, 52, -1, 52, -1, 52, -1, 52, -1, -1, -1, -1, -1, -1, -1, 53, 53, 53, 53, 53, 53, 52, -1, -1, -1, -1, -1, -1, -1, -1, 52, -1, -1, -1, -1, -1, -1 },
        { -1, -1, -1, -1, -1, -1, 72, -1, 72, -1, 72, -1, 72, -1, -1, 73, 74, -1, -1, -1, 72, 72, 72, 72, 72, 72, 72, -1, -1, -1, -1, -1, -1, -1, -1, 72, -1, -1, -1, -1, -1, -1 },
        { -1, -1, 61, 61, 61, 61, -1, -1, -1, -1, -1, 61, -1, -1, -1, 61, 61, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, 61, 61, 61 },
        { -1, -1, -1, -1, -1, -1, 69, -1, 69, -1, 69, -1, 69, -1, -1, 69, 69, 70, 71, -1, 69, 69, 69, 69, 69, 69, 69, -1, -1, -1, -1, -1, -1, -1, -1, 69, -1, -1, -1, -1, -1, -1 },
        { -1, -1, 62, 62, 62, 62, -1, -1, -1, -1, -1, 66, -1, -1, -1, 67, 68, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, 63, 64, 65 },
        { -1, -1, -1, -1, -1, -1,  8, -1,  7, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1 },
        { -1, -1, -1, -1, -1, -1, 32, -1, -1, -1, -1, -1, 31, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1 },
        { -1, -1, 20, 20, 20, 20, -1, -1, -1, -1, -1, -1, -1, -1, 19, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, 20, 20, -1, -1, -1, -1, -1, -1, 20, -1, 20, -1, -1, -1 },
        { -1, -1, -1, -1, -1, -1, 28, -1, -1, -1, -1, -1, 27, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1 },
        { -1, -1, -1, -1, -1, -1,  5, -1,  5,  6, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1 },
        { -1, -1, -1, -1, -1, -1, 25, -1, -1, 26, -1, -1, 25, -1, -1, -1, -1, -1, -1, 25, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1 },
        { -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, 35, 34, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1 }
    };

    int[][] PRODUCTIONS = 
    {
        {  92,  33,  34,  48,  14,  46,  15,  93 },
        {   0 },
        {   8,  44,   9 },
        {  49,  73 },
        {  50, 101,  77, 102 },
        {   0 },
        {  10,  40, 106,  11 },
        {   0 },
        {   7,  44 },
        {   3 },
        {   4 },
        {   5 },
        {   6 },
        {  60,  75 },
        {  51 },
        {  52 },
        {  54 },
        {  55 },
        {  58 },
        {   0 },
        {  46 },
        {  50, 101, 117,  78,  20,  61, 105,   9 },
        {  30,  12,  47,  13,   9 },
        {  53,  76 },
        {  50, 101, 115,  78, 103 },
        {   0 },
        { 107,  10,  61, 100,  11 },
        {   0 },
        {   7,  47 },
        {  37,  12,  45,  13,   9 },
        {  61,  91,  74 },
        {   0 },
        {   7,  45 },
        {  29,  12,  61, 114,  13,  79 },
        {  32, 108,   8,  14,  46,  15,  56, 109 },
        {  31, 108,   8,  14,  46,  15,  57, 109 },
        {   0 },
        {  31, 110,   8,  14,  46,  15 },
        {   0 },
        {  32, 110,   8,  14,  46,  15 },
        { 111,  39,  12,  61, 114,  13,  59, 112,   8,  14,  46,  15, 113 },
        {  32 },
        {  31 },
        {  62,  67, 116 },
        {   0 },
        {  36,  62,  95,  67 },
        {  27,  62,  94,  67 },
        {  63 },
        {  38,  96 },
        {  28,  97 },
        {  35,  62,  98 },
        {  65,  68 },
        {   0 },
        {  64,  90,  65,  89 },
        {  21 },
        {  22 },
        {  23 },
        {  24 },
        {  25 },
        {  26 },
        {  70,  69 },
        {  72,  71 },
        {  50, 101,  66, 104 },
        {  40,  85 },
        {  41,  86 },
        {  42,  99 },
        {  12,  61,  13 },
        {  16,  72,  87 },
        {  17,  72,  88 },
        {   0 },
        {  18,  72,  83,  71 },
        {  19,  72,  84,  71 },
        {   0 },
        {  16,  70,  81,  69 },
        {  17,  70,  82,  69 },
        {   0 },
        { 110,  10,  61,  11 }
    };

         String[] PARSER_ERROR
            = {
                "",
                "esperado fim de programa",//"Era esperado fim de programa",
                "esperado identificador",//"Era esperado palavra",
                "esperado int",//"Era esperado int",
                "esperado float",//"Era esperado float",
                "esperado string",//"Era esperado string",
                "esperado bool",//"Era esperado bool",
                "esperado \",\"",//"Era esperado \",\"",
                "esperado \":\"",//"Era esperado \":\"",
                "esperado ;",//"Era esperado \";\"",
                "esperado \"[\"",//"Era esperado \"[\"",
                "esperadp \"]\"",//"Era esperado \"]\"",
                "esperado \"(\"",//"Era esperado \"(\"",
                "esperado \")\"",//Era esperado \")\"",
                "esperado \"{\"", //"Era esperado \"{\"",
                "esperado \"}\"", //"Era esperado \"}\"",
                "esperado \"+\"",//"Era esperado \"+\"",
                "esperado \"-\"", //"Era esperado \"-\"",
                "esperado \"*\"", //"Era esperado \"*\"",
                "esperado \"/\"", //"Era esperado \"/\"",
                "esperado <-",//"Era esperado \"<-\"",
                "esperado \"=\"",//"Era esperado \"=\"",
                "esperado \"!=\"",//"Era esperado \"!=\"",
                "esperado \"<\"",//"Era esperado \"<\"",
                "esperado \"<=\"",//"Era esperado \"<=\"",
                "esperado \">\"",//"Era esperado \">\"",
                "esperado \">=\"",//"Era esperado \">=\"",
                "esperado and",//"Era esperado and",
                "esperado false",//Era esperado false",
                "esperado if",//Era esperado if",
                "esperado in",//Era esperado in",
                "esperado isFalseDo",//Era esperado isFalseDo",
                "esperado isTrueDo",//Era esperado isTrueDo",
                "esperado main",//Era esperado main",
                "esperado module",//Era esperado module",
                "esperado not",//Era esperado not",
                "esperado or",//Era esperado or",
                "esperado out",//Era esperado out",
                "esperado true",//Era esperado true",
                "esperado while",//Era esperado while",
                "esparado constante int",//Era esperado constint",
                "esperado constante float",//"Era esperado constfloat",
                "esperado constante string",//"Era esperado conststring",                
                "esperado main",//"<declaracao> invalido",
                "esperado identificador",//"esperado int, float, string ou bool",//"<listadevariaveis> invalido",
                "esperado expressão",//"esperado int, float, string, bool, (, +, -, false, not, true, constante int, constante float ou constante string",//"<listaexpressoes> invalido",
                "esperado comando",//"<listacomandos> invalido",
                "esperado identificador",//"<listadeidentificadores> invalido",
                "esperado :  {", //"<variaveis> invalido",
                "esperado identificador",//"<palavravet> invalido",
                "esperado identificador", //<palavra> invalido",
                "esperado identificador",//"<atribuicao> invalido",
                "esperado in",//"<entrada> invalido",
                "esperado identificador",//"<palavraent> invalido",
                "esperado out",//"<saida> invalido",
                "esperado if",//"<selecao> invalido",
                "esperado comando } ifFalseDo",//<alternativatrue> invalido",
                "esperado comando } isTrueDo",//"<alternativafalse> invalido",
                "esperado while", //"<repeticao> invalido",
                "esperado ifFalseDo isTrueDo",//"<opcao> invalido",
                "esperado comando",//"<comando> invalido",
                "esperada expressão",//"<expressao> invalido",
                "esperada expressão",//"<elemento> invalido",
                "esperada expressão",//<relacional> invalido",
                "esperada expressão",//"<operador_relacional> invalido",
                "esperada expressão",//"<aritmetica> invalido",
                "esperado , ; [ ] ) operador binario (aritmetico, lógico, relacional)",//"<vetor_uso> invalido",
                "esperada expressão",//"<expressao_1> invalido",
                "esperada expressão",//"<relacional_1> invalido",
                "esperada expressão",//<aritmetica_1> invalido",
                "esperada expressão", //"<termo> invalido",
                "esperada expressão",//"<termo_1> invalido",		
                "esperada expressão",//"<fator> invalido",
                "esperado , ;", //<listavariaveis1> invalido",
                "esperado , )",//"<listaexpressoes2> invalido",
                "esperado comando }",//"<listacomandos3> invalido",
                "esperado , )",//"<listadeidentificadores4> invalido",
                "esperado , ; [",//"<palavravet4> invalido",
                "esperado , [ ) <-",//"<palavraent5> invalido",
                "esperado ifFalseDo ifTrueDo    ",//"<selecao1> invalido"
            };
}

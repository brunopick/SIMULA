/**
 * Por enquanto manter essa classe dessa maneira para compilar projeto
 * 
 * OBS: cuidar para nunca commitar essa classe pois o SIMULA gera o código por cima da mesma
 * 
 * @author bruno
 */
class Codigos { 
  public static int colunas=25, linhas=25;
  public static tipoAgente[] agentes=new tipoAgente[1000];
  public static Dist[] distribuicao=new Dist[625];
  public static boolean[] vetorBoolean = new boolean[1000];
  public static int[] vetorInt = new int[1000], vetorQtd = new int[1000];
  public static char[] vetorChar = new char[1000];
  public static ItemVariable[] indice = new ItemVariable[1000];

  public static void inicializaTipos() {}
  public static void acoesAgentes(Agente agente) { }
  public static boolean atingiuCondicaoParada() {return false; }
}

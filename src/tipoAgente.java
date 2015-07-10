class tipoAgente {
  public String nome, area, ident, nagentes, carga, energia;
  public int imagem[]=new int[256];

  public tipoAgente(String id, String no, String ar, String en, String cg, String nu, int img[]) {
    ident = id;
    nome = no;
    area = ar;
    energia=en;
    carga=cg;
    nagentes = nu;
    imagem = img; } }

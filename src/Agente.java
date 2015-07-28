
import java.awt.*;

class Agente
{

    public String nome;
    public int numero, sexo, linha, coluna, area, tempovida, sucesso, ncriacao, energia, carga;
    public Image imagem;

    public Agente( String no, int nu, int sx, int col, int li, int ar, int tpvd,
            int sc, int en, int cg, Image img, int ncri )
    {
        nome = no;
        numero = nu;
        sexo = sx;
        coluna = col;
        linha = li;
        area = ar;
        tempovida = tpvd;
        sucesso = sc;
        energia = en;
        carga = cg;
        imagem = img;
        ncriacao = ncri;
    }
}

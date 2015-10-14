import java.awt.*;

public class AreaCanvas extends Canvas
{

    static int[][] agentes = new int[40][40];
    static int[][] pistas = new int[40][40];
    static int deslocx = 4, deslocy = 4, cl, li;
    static Posicao origem = new Posicao( 0, 0 );

    public static void selecionaArea( Posicao orig )
    {
        origem = orig;
    }

    public static void calculaDeslocamento( int col, int lin )
    {
        cl = col;
        li = lin;
        if ( cl < 25 )
        {
            while ( cl > 1 )
            {
                cl = cl / 2;
            }
            if ( cl == 1 )
            {
                deslocx = (((25 - col) / 2)) * 16 + 4;
            }
            else
            {
                deslocx = (((25 - col) / 2)) * 16 + 12;
            }
            cl = col;
        }
        else
        {
            cl = 25;
            deslocx = 4;
        }
        if ( li < 25 )
        {
            while ( li > 1 )
            {
                li = li / 2;
            }
            if ( li == 1 )
            {
                deslocy = (((25 - lin) / 2)) * 16 + 4;
            }
            else
            {
                deslocy = (((25 - lin) / 2)) * 16 + 12;
            }
            li = lin;
        }
        else
        {
            li = 25;
            deslocy = 4;
        }
    }

    @Override
    public void paint( Graphics g )
    {
        int lin, col;
        Posicao limite = new Posicao( 0, 0 );
        int tamx = cl * 16, tamy = li * 16;
        g.setColor( Color.white );
        for ( int i = 0; i < 25; i++ )
        {
            for ( int j = 0; j < 25; j++ )
            {
                col = (i * 16) + deslocx;
                lin = (j * 16) + deslocy;
                if ( agentes[origem.x + i][origem.y + j] != -1 )
                {
                    g.drawImage( Executar.agentes[(agentes[origem.x + i][origem.y + j])].imagem, col, lin, this );
                }
                else if ( pistas[origem.x + i][origem.y + j] != -1 )
                {
                    g.drawImage( Executar.pista, col, lin, this );
                }
                else if ( i < Codigos.colunas && j < Codigos.linhas )
                {
                    g.fillRect( col, lin, 16, 16 );
                }
            }
        }
        g.setColor( Color.black );
        g.draw3DRect( deslocx - 4, deslocy - 4, (tamx + 8), (tamy + 8), true );
    }

    @Override
    public void update( Graphics g )
    {
        paint( g );
    }
}


import java.awt.*;
import java.awt.image.*;

public class GridCanvas extends Canvas
{

    public int pixels[] = new int[256];
    public Image image;

    public void paint( Graphics g )
    {
        int i = 0;
        while ( i < 161 )
        {
            g.drawLine( i, 0, i, 160 );
            g.drawLine( 0, i, 160, i );
            i = i + 10;
        }
        desenhacores( g );
    }

    public void desenhacores( Graphics g )
    {
        for ( int i = 0; i < 16; i++ )
        {
            for ( int j = 0; j < 16; j++ )
            {
                g.setColor( new Color( pixels[i + (j * 16)] ) );
                g.fillRect( (i * 10) + 1, (j * 10) + 1, 9, 9 );
            }
        }
    }

    public void update( Graphics g )
    {
        paint( g );
    }

    public void selecionaCor( Color cor, int cordX, int cordY )
    {
        pixels[(cordX / 10) + ((cordY / 10) * 16)] = cor.getRGB();
        repaint();
    }

    public void inicializaPixels()
    {
        int branco = Color.white.getRGB();
        for ( int i = 0; i < 256; i++ )
        {
            pixels[i] = branco;
        }
        repaint();
    }

    public void setPixels( int pix[] )
    {
        pixels = pix;
        repaint();
    }
}

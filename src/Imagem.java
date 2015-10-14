import java.awt.*;
import java.awt.image.*;
import java.io.*;

public class Imagem extends Canvas
{

    public Image image;
    public MemoryImageSource imagem;

    public void alteraImagem( int pixels[] )
    {
        imagem = new MemoryImageSource( 16, 16, pixels, 0, 16 );
        image = Toolkit.getDefaultToolkit().createImage( imagem );
        repaint();
    }

    public void update( Graphics g )
    {
        paint( g );
    }

    public void paint( Graphics g )
    {
        g.drawRect( 0, 0, 21, 21 );
        g.drawImage( image, 3, 3, this );
    }
}


import java.awt.*;
import java.awt.image.*;

public class LogoCanvas extends Canvas
{

    Image imagem;

    public LogoCanvas()
    {
        imagem = Toolkit.getDefaultToolkit().getImage( "nav12.gif" );
    }

    public LogoCanvas( ImageProducer imageProducer )
    {
        imagem = createImage( imageProducer );
    }

    public void paint( Graphics g )
    {
        g.drawImage( imagem, 100, 72, this );
    }
}

import java.awt.*;
import java.applet.*;

public class DrawImage extends Applet
{

    private Image ufrgsImage;

    public void init()
    {
        ufrgsImage = getImage( getDocumentBase(), "ufrgs.gif" );
    }

    public void paint( Graphics g )
    {
        g.drawImage( ufrgsImage, 30, 30, this );
    }
}

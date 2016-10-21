package components;

import java.io.File;
import javax.swing.JFileChooser;
import javax.swing.filechooser.FileNameExtensionFilter;

/**
 *
 * @author bruno
 */
public class SFileChooser
    extends 
        JFileChooser
{
    public static int FILE_DAT = 1;
    public static int FILE_IMAGE = 2;
    
    /**
     * Cria um JFileChooser com diversas propriedades por default.
     * 
     * @param dialogType JFileChooser.SAVE_DIALOG ou JFileChooser.OPEN_DIALOG
     * @param fileType Deve ser SFileChooser.FILE_DAT ou SFileChooser.FILE_IMAGE.
     * É utilizado para definir o filtro de arquivos.
     */
    public SFileChooser( int dialogType, int fileType )
    {
        super();
        super.setDialogType( dialogType );
        super.setMultiSelectionEnabled( false );
        
        if (fileType == FILE_IMAGE) {
            super.addChoosableFileFilter( new FileNameExtensionFilter( "Imagens", "jpg", "jpeg", "png" ) );
        }
        else {
            super.addChoosableFileFilter( new FileNameExtensionFilter( "Arquivos .dat", "dat", "DAT" ) );
        }
        super.setAcceptAllFileFilterUsed( false );
        super.setFileSelectionMode( JFileChooser.FILES_ONLY );
    }
    
}

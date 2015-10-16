package components;

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
    /**
     * Cria um JFileChooser com diversas propriedades por default.
     * 
     * @param dialogType JFileChooser.SAVE_DIALOG ou JFileChooser.OPEN_DIALOG
     */
    public SFileChooser( int dialogType )
    {
        super();
        super.setDialogType( dialogType );
        super.setAcceptAllFileFilterUsed( false );
        super.setMultiSelectionEnabled( false );
        super.addChoosableFileFilter( new FileNameExtensionFilter( "Arquivos .dat", "dat", "DAT" ) );
        super.setCurrentDirectory( null );
        super.setFileSelectionMode( JFileChooser.FILES_ONLY );
    }
    
}

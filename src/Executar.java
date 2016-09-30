//import java.awt.*;
//import java.awt.event.*;
//import java.awt.image.*;
import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Image;
import java.awt.Toolkit;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.AdjustmentEvent;
import java.awt.event.AdjustmentListener;
import java.awt.event.ItemEvent;
import java.awt.event.ItemListener;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.awt.event.WindowEvent;
import java.awt.event.WindowListener;
import java.awt.image.MemoryImageSource;
import javax.swing.JButton;
import javax.swing.JComboBox;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JMenuItem;
import javax.swing.JPanel;
import javax.swing.JPopupMenu;
import utils.Logger;

public class Executar extends JFrame implements WindowListener, MouseListener,
        ActionListener, Runnable, ItemListener, AdjustmentListener
{

    public static int[] vetorInt = new int[1000], vetorQtd = new int[1000];
    public static int compInt, atribuicao = 3, indiceCritParada = -1, tempo = 0;
    static Thread execThread;
    public static Agente[] agentes = new Agente[1600];
    public static AreaCanvas ambiente;
    public static Image pista/*, tiro*/;
    static JComboBox variaveisComboBox, agentesComboBox;
    static JLabel label8, label5, label7, status;
    JButton button1, button2, button3, button4, button5;
    static int lin, col, amostra;
    public static int[][] ambtmp = new int[40][40];
    public static Codigos codigos = new Codigos();
    public Posicao orig;
    static final int img[] =
    {
        -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1,
        10000, 10000, 10000, 10000, 10000, 10000, 10000, 10000, 10000, 10000, 10000, 10000, 10000, 10000, 10000, 10000, 10000, 10000, 10000, 10000, 10000, 10000, 10000, 10000, 10000,
        -1, -1, -1, -1, -65536, -65536, -1, -65536, -1, -65536, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1,
        -65536, -65536, -65536, -65536, -65536, -65536, -65536, -1, -1, -1, -1, -1, -1, -1, -1, -1,
        -65536, -65536, -65536, -65536, -65536, -65536, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1,
        -65536, -65536, -65536, -65536, -65536, -65536, -65536, -1, -1, -1, -1, -1, -1, -1, -1, -1,
        -65536, -1, -65536, -1, -65536, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -65536, -1, -1, -1,
        -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -65536, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1,
        -1, -1, -1, -1, -65536, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -65536, -1, -1,
        -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -65536, -65536, -65536, -65536, -65536, -65536,
        -1, -1, -1, -1, -1, -1, -1, -1, -1, -65536, -65536, -65536, -65536, -65536, -65536, -65536,
        -65536, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1,
        -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1
    };

    public Executar()
    {
        super( "Execução" );
        JPanel p;
        JLabel label1, label2, label3, label4, label6;
        int i = 0, aux;
        Codigos.inicializaTipos();
        setBackground( Color.lightGray );
        execThread = new Thread( this );
        setTitle( "Execução" );
        setLayout( new BorderLayout() );
        p = new JPanel();
        p.setLayout( null );
        p.setSize( getInsets().left + getInsets().right + 665, getInsets().top + getInsets().bottom + 460 );
        status = new JLabel("");
        status.setBounds(getInsets().left + 15,getInsets().top + 445,409,20);
        p.add(status);
        label1 = new JLabel( "Variável:" );
        label1.setBounds( getInsets().left + 478, getInsets().top + 24, 84, 26 );
        p.add( label1 );
        variaveisComboBox = new JComboBox();
        p.add( variaveisComboBox );
        variaveisComboBox.setBounds( getInsets().left + 478, getInsets().top + 60, 158, 21 );
        variaveisComboBox.addItemListener( this );
        label2 = new JLabel( "Agentes:" );
        label2.setBounds( getInsets().left + 478, getInsets().top + 132, 72, 24 );
        p.add( label2 );
        label3 = new JLabel( "Valor:" );
        label3.setBounds( getInsets().left + 478, getInsets().top + 96, 72, 24 );
        p.add( label3 );
        label4 = new JLabel( "Número total:" );
        label4.setBounds( getInsets().left + 478, getInsets().top + 204, 96, 24 );
        p.add( label4 );
        agentesComboBox = new JComboBox();
        p.add( agentesComboBox );
        agentesComboBox.setBounds( getInsets().left + 478, getInsets().top + 168, 158, 21 );
        agentesComboBox.addItemListener( this );
        label5 = new JLabel();
        label5.setBounds( getInsets().left + 586, getInsets().top + 204, 36, 24 );
        p.add( label5 );
        label6 = new JLabel( "Tempo(ciclos):" );
        label6.setBounds( getInsets().left + 478, getInsets().top + 240, 108, 27 );
        p.add( label6 );
        label7 = new JLabel();
        label7.setBounds( getInsets().left + 586, getInsets().top + 240, 36, 24 );
        p.add( label7 );
        button1 = new JButton( "Iniciar" );
        button1.setBounds( getInsets().left + 478, getInsets().top + 288, 72, 36 );
        p.add( button1 );
        (button1).addActionListener( this );
        (button1).addMouseListener(this);
        button2 = new JButton( "Reiniciar" );
        button2.setBounds( getInsets().left + 478, getInsets().top + 336, 72, 36 );
        p.add( button2 );
        (button2).addActionListener( this );
        (button2).addMouseListener(this);
        button3 = new JButton( "Parar" );
        button3.setBounds( getInsets().left + 562, getInsets().top + 288, 72, 36 );
        p.add( button3 );
        (button3).addActionListener( this );
        (button3).addMouseListener(this);
        button4 = new JButton( "Passo" );
        button4.setBounds( getInsets().left + 562, getInsets().top + 336, 72, 36 );
        p.add( button4 );
        (button4).addActionListener( this );
        (button4).addMouseListener(this);
        button5 = new JButton( "Sair" );
        button5.setBounds( getInsets().left + 495, getInsets().top + 382, 120, 36 );
        p.add( button5 );
        (button5).addActionListener( this );
        (button5).addMouseListener(this);
        label8 = new JLabel();
        label8.setBounds( getInsets().left + 586, getInsets().top + 96, 36, 24 );
        p.add( label8 );
        ambiente = new AreaCanvas();
        ambiente.setBounds( getInsets().left + 15, getInsets().top + 10, 409, 409 );
        (ambiente).addMouseListener( this );
        p.add( ambiente );
        add( "Center", p );
        while ( agentes[i] != null )
        {
            agentes[i] = null;
        }
        variaveisComboBox.addItem( "" );
        agentesComboBox.addItem( "" );
        inicializaAmbiente();
        aux = 1;
        while ( Codigos.indice[aux] != null )
        {
            if ( !Codigos.indice[aux].nome.equals( "" ) )
            {
                variaveisComboBox.addItem( Codigos.indice[aux].nome );
            }
            aux++;
        }
        aux = 1;
        while ( Codigos.agentes[aux] != null )
        {
            if ( !Codigos.agentes[aux].nome.equals( "" ) )
            {
                agentesComboBox.addItem( Codigos.agentes[aux].nome );
            }
            aux++;
        }
        ambiente.calculaDeslocamento( Codigos.colunas, Codigos.linhas );
//        pack();
//        setResizable( false );
setSize(800, 600);
        setVisible( true );
        addWindowListener( this );
        execThread.start();
        execThread.suspend();
    }

    public static void atualizaAmostra()
    {
        int aux = 1, pos = variaveisComboBox.getSelectedIndex();
        if ( pos != 0 )
        {
            if ( Codigos.indice[pos].tipo.equals( "int" ) )
            {
                label8.setText( new Integer( Codigos.vetorInt[Codigos.indice[pos].index] ).toString() );
            }
            else if ( Codigos.indice[pos].tipo.equals( "boolean" ) )
            {
                if ( Codigos.vetorBoolean[Codigos.indice[pos].index] )
                {
                    label8.setText( "true" );
                }
                else
                {
                    label8.setText( "false" );
                }
            }
            else if ( Codigos.indice[pos].tipo.equals( "char" ) )
            {
                label8.setText( new Integer( Codigos.vetorChar[Codigos.indice[pos].index] ).toString() );
            }
        }
        if ( agentesComboBox.getSelectedIndex() > 0 )
        {
            label5.setText( new Integer( vetorQtd[agentesComboBox.getSelectedIndex()] ).toString() );
        }
        label7.setText( new Integer( tempo ).toString() );
    }

    public static void inicializaInformacoes()
    {
        int aux = 1;
        tempo = 0;
        aux = 1;
        while ( Codigos.agentes[aux] != null )
        {
            vetorQtd[aux] = new Integer( Codigos.agentes[aux].nagentes ).intValue();
            aux++;
        }
        atualizaAmostra();
    }

    public static Posicao randPos()
    {
        Posicao pos = new Posicao( 0, 0 );
        boolean achou = false;
        while ( !achou )
        {
            pos.x = (int) (Math.random() * Codigos.colunas);
            pos.y = (int) (Math.random() * Codigos.linhas);
            if ( ambiente.agentes[pos.x][pos.y] == -1 )
            {
                achou = true;
            }
        }
        return pos;
    }

    public static Posicao procuraPos( String nome, String posicao )
    {
        Posicao pos = new Posicao( 0, 0 );
        boolean achou = false;
        int j = 0;
        while ( Codigos.distribuicao[j] != null )
        {
            if ( (Codigos.distribuicao[j].nome.equals( nome )) && (Codigos.distribuicao[j].numero.equals( posicao )) )
            {
                pos.x = Integer.valueOf( Codigos.distribuicao[j].coluna ).intValue();
                pos.y = Integer.valueOf( Codigos.distribuicao[j].linha ).intValue();
                achou = true;
            }
            j++;
        }
        if ( !achou )
        {
            pos = randPos();
        }
        return pos;
    }

    public static void criaAgentes()
    {
        Posicao pos = new Posicao( 0, 0 );
        int num = 1, cont = 1, numag;
        Image image;
        MemoryImageSource imagem;
        imagem = new MemoryImageSource( 16, 16, img, 0, 16 );
        pista = Toolkit.getDefaultToolkit().createImage( imagem );
//    imagem = new MemoryImageSource(16,16,img2,0,16);
//    tiro = Toolkit.getDefaultToolkit().createImage(imagem);
        Codigos.inicializaTipos();
        while ( Codigos.agentes[num] != null )
        {
            imagem = new MemoryImageSource( 16, 16, Codigos.agentes[num].imagem, 0, 16 );
            image = Toolkit.getDefaultToolkit().createImage( imagem );
            numag = Integer.valueOf( Codigos.agentes[num].nagentes ).intValue();
            for ( int i = 0; i < numag; i++ )
            {
                pos = procuraPos( (Codigos.agentes[num].nome), (new Integer( i + 1 ).toString()) );
                ambiente.agentes[pos.x][pos.y] = (cont + i);
                agentes[cont + i] = new Agente( Codigos.agentes[num].nome, (i + 1), ((int) (Math.random() * 2)),
                        pos.x, pos.y, (new Integer( Codigos.agentes[num].area )).intValue(),
                        0, 0, (new Integer( Codigos.agentes[num].energia )).intValue(),
                        (new Integer( Codigos.agentes[num].carga )).intValue(), image, (cont + i) );
            }
            cont = ambiente.agentes[pos.x][pos.y] + 1;
            num++;
        }
    }

    public static void inicializaAmbiente()
    {
        int aux = Codigos.colunas - 25;
        for ( int i = 0; i < 40; i++ )
        {
            for ( int j = 0; j < 40; j++ )
            {
                ambiente.agentes[i][j] = -1;
                ambiente.pistas[i][j] = -1;
            }
        }
        tempo = 0;
        criaAgentes();
        ambiente.repaint();
        inicializaInformacoes();
        atualizaAmostra();
    }

    public void executa()
    {
        int nag = 1;
        tempo++;
        for ( int i = 0; i < 40; i++ )
        {
            for ( int j = 0; j < 40; j++ )
            {
                ambtmp[i][j] = ambiente.agentes[i][j];
            }
        }
        for ( int i = 0; i < 40; i++ )
        {
            for ( int j = 0; j < 40; j++ )
            {
                nag = ambtmp[i][j];
                if ( nag != -1 )
                {
                    if ( agentes[nag] != null )
                    {
                        agentes[nag].tempovida++;
                        Codigos.acoesAgentes( agentes[nag] );
                    }
                }
            }
        }
        ambiente.repaint();
        atualizaAmostra();
        Logger.log("Ciclo " + tempo);
    }

    public void fechaJanela()
    {
        execThread.suspend();
        setVisible( false );
        dispose();
        System.exit( 0 );
    }

    public void run()
    {
        while ( true )
        {
            try
            {
                execThread.sleep( 200 );
            }
            catch ( Exception e )
            {
                e.printStackTrace();
            }
            if ( Codigos.atingiuCondicaoParada() )
            {
                execThread.suspend();
            }
            else
            {
                executa();
            }
        }
    }

    public int selecionaAgente(int posx, int posy) {
        int lin,col,agente;
        if((posx>0) && (posy>0) && (posx<400) && (posy<400)) {
            col = posx/16;
            lin = posy/16;
            agente = ambiente.agentes[col][lin];

            if(agente != -1) {
                return agente;
            }
        }
        return -1;
    }

    public int selecionaPista(int posx, int posy) {
        int lin,col,pst;
        if((posx>0) && (posy>0) && (posx<400) && (posy<400)) {
            col = posx/16;
            lin = posy/16;
            pst = ambiente.pistas[col][lin];

            if(pst!=-1) {
                return pst;
            }
        }
        return -1;
    }

    public static void terminaExecucao()
    {
        execThread.suspend();
        inicializaAmbiente();
    }

    public void actionPerformed( ActionEvent evt )
    {
        String parametro = evt.getActionCommand();
        Object source = evt.getSource();
        if ( parametro.equals( "Parar" ) )
        {
            execThread.suspend();
        }
        else if ( parametro.equals( "Sair" ) )
        {
            fechaJanela();
            Logger.log("Fim da simulação");
            Logger.log("Tempo da simulação: "+label7.getText()+" ciclos.");
        }
        else if ( parametro.equals( "Iniciar" ) )
        {
            execThread.resume();
            if (tempo==0) {
                Logger.log("Início da simulação");
            }
        } 
        else if ( parametro.equals( "Passo" ) )
        {
            if ( !Codigos.atingiuCondicaoParada() )
            {
                executa();
            }
        }
        else if ( parametro.equals( "Reiniciar" ) )
        {
            terminaExecucao();
        }
        else if ( (source == variaveisComboBox) || (source == agentesComboBox) )
        {
            atualizaAmostra();
        }
        else if(parametro.equals("Mover")){
            setCursor(HAND_CURSOR);
        }
        else if(parametro.equals("Excluir")){
            Metodos.morteDeAgente(agentes[amostra]);
            ambiente.repaint();
            atualizaAmostra();
        }
        else if(parametro.equals("Pista")){
            ambiente.pistas[orig.x][orig.y] = 1;
            ambiente.repaint();
            atualizaAmostra();
        }
        else if(parametro.equals("Exclui")){
            ambiente.pistas[orig.x][orig.y] = -1;
            ambiente.repaint();
            atualizaAmostra(); }
    }

    public void itemStateChanged( ItemEvent evt )
    {
        atualizaAmostra();
    }

    public void adjustmentValueChanged( AdjustmentEvent evt )
    {
    }

    public void mouseClicked(MouseEvent e) {
        if (e.getComponent()==ambiente) {
            JPopupMenu popup;
            JMenuItem menuItem;
            popup = new JPopupMenu();
            orig = new Posicao((e.getX()-4)/16,(e.getY()-4)/16);
            if (ambiente.pistas[orig.x][orig.y]==1) {
                menuItem = new JMenuItem("Exclui");
                menuItem.addActionListener(this);
                popup.add(menuItem);
            }
            else if (Metodos.posicaoVaga(orig)) {
                menuItem = new JMenuItem("Pista");
                menuItem.addActionListener(this);
                popup.add(menuItem);
            }
            else {
                menuItem = new JMenuItem("Mover");
                menuItem.addActionListener(this);
                popup.add(menuItem);
                menuItem = new JMenuItem("Excluir");
                menuItem.addActionListener(this);
                popup.add(menuItem);
            }

            if (e.getButton() == MouseEvent.BUTTON3) {
                amostra = selecionaAgente(e.getX()-4,e.getY()-4);
                popup.show(e.getComponent(), e.getX(), e.getY());
            }

            if (getCursorType()==(HAND_CURSOR)){
                Posicao dest = new Posicao((e.getX()-4)/16,(e.getY()-4)/16);
                if (Metodos.posicaoVaga(dest)) {
                    Metodos.atualizaPosicao((agentes[amostra]),(dest));
                    ambiente.repaint();
                    atualizaAmostra();
                    setCursor(DEFAULT_CURSOR);
                }
            }
        }
    }

    public void mouseEntered( MouseEvent e )
    {
        if(e.getComponent()==button1){
            status.setText("Inicia a simulação");
        }
        if(e.getComponent()==button2){
            status.setText("Reinicia a simulação");
        }
        if(e.getComponent()==button3){
            status.setText("Pausa a simulação");
        }
        if(e.getComponent()==button4){
            status.setText("Executa a simulação passo a passo");
        }
        if(e.getComponent()==button5){
            status.setText("Sai da simulação");
        }
    }

    public void mouseExited( MouseEvent e )
    {
        status.setText(null);
    }

    public void mouseReleased( MouseEvent e )
    {
        //TODO: verificar, já estava comentado
//    Posicao pos = new Posicao((e.getX()-4)/16,(e.getY()-4)/16);
//    if (Metodos.posicaoVaga(pos)) {
//      Metodos.atualizaPosicao((agentes[amostra]),(pos));
//      ambiente.repaint();
//      atualizaAmostra();
    }

    public void mousePressed( MouseEvent evt )
    {
        //TODO: verificar, já estava comentado
//    JOptionPane.showMessageDialog(null, String.valueOf("Posição do agente: ("+evt.getX()+","+evt.getY()+")"));
   // amostra = selecionaAgente(evt.getX()-4,evt.getY()-4);
//    JOptionPane.showMessageDialog(null, amostra);
    }

    public void windowActivated( WindowEvent evt )
    {
        ambiente.repaint();
    }

    public void windowDeactivated( WindowEvent evt )
    {
    }

    public void windowOpened( WindowEvent evt )
    {
        ambiente.repaint();
    }

    public void windowClosing( WindowEvent evt )
    {
        fechaJanela();
    }

    public void windowClosed( WindowEvent evt )
    {
    }

    public void windowIconified( WindowEvent evt )
    {
    }

    public void windowDeiconified( WindowEvent evt )
    {
    }

    public static void main( String args[] )
    {
        new Executar();
    }
}

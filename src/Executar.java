import java.awt.*;
import java.awt.event.*;
import java.awt.image.*;

public class Executar extends Frame implements WindowListener, MouseListener,
        ActionListener, Runnable, ItemListener, AdjustmentListener
{

    public static int[] vetorInt = new int[1000], vetorQtd = new int[1000];
    public static int compInt, atribuicao = 3, indiceCritParada = -1, tempo = 0;
    static Thread execThread;
    public static Agente[] agentes = new Agente[1600];
    public static AreaCanvas ambiente;
    public static Image pista/*, tiro*/;
    static Choice choice1, choice2;
    static Label label8, label5, label7;
    Button button1, button2, button3, button4, button5;
    static int lin, col, amostra = 1;
    public static int[][] ambtmp = new int[40][40];
    public static Codigos codigos = new Codigos();
    public static Scrollbar horScroll, verScroll;
    static final int img[] =
    {
        -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1,
        -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1,
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
    /*  static final int img2[] = {
     -1,-1,-1,-1,-1,-1,-1,-1,-1,-1,-1,-1,-1,-1,-1,-1,-1,-1,-1,-1,-1,-1,-1,-1,-1,-1,
     -1,-1,-1,-1,-1,-1,-1,-1,-1,-1,-1,-1,-1,-1,-1,-1,-1,-1,-1,-1,-1,-1,-1,-1,-1,-1,
     -1,-1,-1,-1,-1,-1,-1,-1,-1,-1,-1,-1,-1,-1,-1,-1,-1,-1,-1,-1,-1,-1,-1,-1,-1,-1,
     -1,-1,-1,-1,-1,-1,-1,-1,-1,-1,-1,-1,-1,-1,-1,-1,-1,-1,-1,-1,-1,-1,-16777216,-8355712,
     -8355712,-8355712,-8355712,-8355712,-8355712,-16777216,-1,-1,-1,-1,-1,-1,-1,-1,
     -16777216,-16777216,-16777216,-16777216,-16777216,-16777216,-8355712,-8355712,-16777216,
     -1,-1,-1,-1,-1,-1,-1,-16777216,-16777216,-16777216,-16777216,-16777216,-16777216,-8355712,
     -8355712,-8355712,-16777216,-1,-1,-1,-1,-1,-1,-16777216,-16777216,-16777216,-16777216,
     -16777216,-16777216,-8355712,-8355712,-16777216,-1,-1,-1,-1,-1,-1,-1,-16777216,-8355712,
     -8355712,-8355712,-8355712,-8355712,-8355712,-16777216,-1,-1,-1,-1,-1,-1,-1,-1,-1,
     -1,-1,-1,-1,-1,-1,-1,-1,-1,-1,-1,-1,-1,-1,-1,-1,-1,-1,-1,-1,-1,-1,-1,-1,-1,-1,-1,
     -1,-1,-1,-1,-1,-1,-1,-1,-1,-1,-1,-1,-1,-1,-1,-1,-1,-1,-1,-1,-1,-1,-1,-1,-1,-1,-1,
     -1,-1,-1,-1,-1,-1,-1,-1,-1,-1,-1,-1,-1,-1,-1,-1,-1,-1,-1,-1,-1};
     */

    public Executar()
    {
        super( "Execucao" );
        Panel p;
        Label label1, label2, label3, label4, label6;
        int i = 0, aux;
        Codigos.inicializaTipos();
        setBackground( Color.lightGray );
        execThread = new Thread( this );
        setTitle( "Execucao" );
        setLayout( new BorderLayout() );
        p = new Panel();
        p.setLayout( null );
        p.setSize( getInsets().left + getInsets().right + 665, getInsets().top + getInsets().bottom + 460 );
        label1 = new Label( "Variável:" );
        label1.setBounds( getInsets().left + 478, getInsets().top + 24, 84, 26 );
        p.add( label1 );
        choice1 = new Choice();
        p.add( choice1 );
        choice1.setBounds( getInsets().left + 478, getInsets().top + 60, 158, 21 );
        choice1.addItemListener( this );
        label2 = new Label( "Agentes:" );
        label2.setBounds( getInsets().left + 478, getInsets().top + 132, 72, 24 );
        p.add( label2 );
        label3 = new Label( "Valor:" );
        label3.setBounds( getInsets().left + 478, getInsets().top + 96, 72, 24 );
        p.add( label3 );
        label4 = new Label( "Número total:" );
        label4.setBounds( getInsets().left + 478, getInsets().top + 204, 96, 24 );
        p.add( label4 );
        choice2 = new Choice();
        p.add( choice2 );
        choice2.setBounds( getInsets().left + 478, getInsets().top + 168, 158, 21 );
        choice2.addItemListener( this );
        label5 = new Label();
        label5.setBounds( getInsets().left + 586, getInsets().top + 204, 36, 24 );
        p.add( label5 );
        label6 = new Label( "Tempo(ciclos):" );
        label6.setBounds( getInsets().left + 478, getInsets().top + 240, 108, 27 );
        p.add( label6 );
        label7 = new Label();
        label7.setBounds( getInsets().left + 586, getInsets().top + 240, 36, 24 );
        p.add( label7 );
        button1 = new Button( "Iniciar" );
        button1.setBounds( getInsets().left + 478, getInsets().top + 288, 72, 36 );
        p.add( button1 );
        (button1).addActionListener( this );
        button2 = new Button( "Reiniciar" );
        button2.setBounds( getInsets().left + 478, getInsets().top + 336, 72, 36 );
        p.add( button2 );
        (button2).addActionListener( this );
        button3 = new Button( "Parar" );
        button3.setBounds( getInsets().left + 562, getInsets().top + 288, 72, 36 );
        p.add( button3 );
        (button3).addActionListener( this );
        button4 = new Button( "Passo" );
        button4.setBounds( getInsets().left + 562, getInsets().top + 336, 72, 36 );
        p.add( button4 );
        (button4).addActionListener( this );
        button5 = new Button( "Sair" );
        button5.setBounds( getInsets().left + 495, getInsets().top + 382, 120, 36 );
        p.add( button5 );
        (button5).addActionListener( this );
        label8 = new Label();
        label8.setBounds( getInsets().left + 586, getInsets().top + 96, 36, 24 );
        p.add( label8 );
        ambiente = new AreaCanvas();
        ambiente.setBounds( getInsets().left + 15, getInsets().top + 10, 409, 409 );
        (ambiente).addMouseListener( this );
        p.add( ambiente );
        horScroll = new Scrollbar( Scrollbar.HORIZONTAL );
        horScroll.setBounds( getInsets().left + 15, getInsets().top + 420, 409, 20 );
        horScroll.setMinimum( 0 );
        horScroll.setMaximum( 0 );
        horScroll.setUnitIncrement( 1 );
        horScroll.addAdjustmentListener( this );
        p.add( horScroll );
        verScroll = new Scrollbar( Scrollbar.VERTICAL );
        verScroll.setBounds( getInsets().left + 425, getInsets().top + 10, 20, 409 );
        verScroll.setMinimum( 0 );
        verScroll.setMaximum( 0 );
        verScroll.setUnitIncrement( 1 );
        verScroll.addAdjustmentListener( this );
        p.add( verScroll );
        add( "Center", p );
        while ( agentes[i] != null )
        {
            agentes[i] = null;
        }
        choice1.add( "" );
        choice2.add( "" );
        inicializaAmbiente();
        aux = 1;
        while ( Codigos.indice[aux] != null )
        {
            if ( !Codigos.indice[aux].nome.equals( "" ) )
            {
                choice1.add( Codigos.indice[aux].nome );
            }
            aux++;
        }
        aux = 1;
        while ( Codigos.agentes[aux] != null )
        {
            if ( !Codigos.agentes[aux].nome.equals( "" ) )
            {
                choice2.add( Codigos.agentes[aux].nome );
            }
            aux++;
        }
        ambiente.calculaDeslocamento( Codigos.colunas, Codigos.linhas );
        pack();
        setResizable( false );
        setVisible( true );
        addWindowListener( this );
        execThread.start();
        execThread.suspend();
    }

    public static void atualizaAmostra()
    {
        int aux = 1, pos = choice1.getSelectedIndex();
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
        if ( choice2.getSelectedIndex() != 0 )
        {
            label5.setText( new Integer( vetorQtd[choice2.getSelectedIndex()] ).toString() );
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
        if ( aux >= 0 )
        {
            horScroll.setMaximum( aux + 1 );
        }
        else
        {
            horScroll.setVisible( false );
        }
        aux = Codigos.linhas - 25;
        if ( aux >= 0 )
        {
            verScroll.setMaximum( aux + 1 );
        }
        else
        {
            verScroll.setVisible( false );
        }
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

    /*  public int selecionaAgente(int posx, int posy) {
     int lin,col,agente;
     if((posx>0)&&(posy>0)&&(posx<400)&&(posy<400)) {
     col = posx/16;
     lin = posy/16;
     agente = ambiente.agentes[col][lin];
     if(agente!=-1) {
     return agente; } }
     return amostra; } */
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
        }
        else if ( parametro.equals( "Iniciar" ) )
        {
            execThread.resume();
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
        else if ( (source == choice1) || (source == choice2) )
        {
            atualizaAmostra();
        }
    }

    public void itemStateChanged( ItemEvent evt )
    {
        atualizaAmostra();
    }

    public void adjustmentValueChanged( AdjustmentEvent evt )
    {
        Posicao origem = new Posicao( horScroll.getValue(), verScroll.getValue() );
        ambiente.selecionaArea( origem );
        ambiente.repaint();
    }

    public void mouseClicked( MouseEvent e )
    {
    }

    public void mouseEntered( MouseEvent e )
    {
    }

    public void mouseExited( MouseEvent e )
    {
    }

    public void mouseReleased( MouseEvent e )
    {
    }

    public void mousePressed( MouseEvent evt )
    {
    }
//    amostra = selecionaAgente(evt.getX()-4,evt.getY()-4);
//    atualizaAmostra(); }

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

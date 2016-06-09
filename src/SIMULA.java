


//import java.awt.*;
//import java.awt.event.*;
//import java.io.*;
import javax.swing.JFileChooser;
import components.SFileChooser;
import java.awt.BorderLayout;
import java.awt.Button;
import java.awt.Choice;
import java.awt.Color;
import java.awt.Dialog;
import java.awt.Frame;
import java.awt.Label;
import java.awt.List;
import java.awt.Menu;
import java.awt.MenuBar;
import java.awt.MenuItem;
import java.awt.Panel;
import java.awt.Scrollbar;
import java.awt.TextField;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.AdjustmentEvent;
import java.awt.event.AdjustmentListener;
import java.awt.event.ItemEvent;
import java.awt.event.ItemListener;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.awt.event.WindowEvent;
import java.awt.event.WindowListener;
import java.io.BufferedReader;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.InputStreamReader;
import javax.swing.JOptionPane;

public class SIMULA
    extends
        Frame
    implements
        ActionListener, WindowListener, ItemListener, KeyListener, MouseListener,
        AdjustmentListener
{

    public static ItemVariable[] indice = new ItemVariable[1000];
    public static tipoAgente[] typeAgentList = new tipoAgente[1000];
    public static Behavior[] behaviorList = new Behavior[1000];
    public static Variable[] variaveisList = new Variable[1000];
    public static Dist[] distList = new Dist[1600];
    public static String linhas = "25", colunas = "25", critpar = "", printstr, condicao, regraString, paraString;
    public static File file = null;
    public Imagem canvas1;
    public int ident = 1, identDist = 1, aux, identVar = 1, identComp = 1, indPreCond = 0, choice = 0;
    public static int col = 25, lin = 25;
    public int index = 1, posicao;
    Color cor = Color.white;
    public GridCanvas gridCanvas;
    public TextField colFieldDist, linFieldDist, numFieldDist, condFieldPar, nomeField, numField, areaField, paramFieldDialogComp;
    public TextField nomeFieldVar, valFieldVar, priorFieldComp, colFieldDim, linFieldDim, paramFieldDialogPar;
    public TextField energFieldAgent, cargFieldAgent;
    public Panel baseAgentes, baseComportamentos, baseVariaveis, baseDimensoes, baseDistrib, baseDialogParada;
    public Label idLabel, idenLabel, agenLabel, numLabel, areaLabel, corLabel, imgLabel;
    public Label compLabelDialogComp, agenteLabelDialogComp, paramLabelDialogComp, varLabelDialogComp, operLabelDialogComp;
    public Label paramLabelDialogPar, varLabelDialogPar, operLabelDialogPar;
    public Label idLabelVar, nomeLabelVar, idenLabelVar, tipoLabelVar, valLabelVar;
    public Label regraLabelComp, idLabelComp, priorLabelComp, preLabelComp, ativLabelComp, agenLabelComp;
    public Label regraLabelDialogComp, regra2LabelDialogComp;
    public Label enerLabelAgent, cargLabelAgent;
    public Label regraLabelDialogPar, regra2LabelDialogPar, colLabelDim, linLabelDim;
    public Label conLabelComp, posLabelComp, preLabelComp2, ativLabelComp2, conLabelComp2, posLabelComp2;
    public Label agenLabelDist, numLabelDist, colLabelDist, linLabelDist;
    public Button okButton, proxButton, antButton, excButton, canButton;
    public Button okButtonDialogComp, canButtonDialogComp, okButtonDialogPar, canButtonDialogPar;
    public Button okButtonComp, proxButtonComp, antButtonComp, excButtonComp, canButtonComp;
    public Button preButtonComp, ativButtonComp, conButtonComp, posButtonComp;
    public Button okButtonVar, proxButtonVar, antButtonVar, excButtonVar, canButtonVar;
    public Button okButtonDim, canButtonDim, okDialogButton;
    public Button okButtonDist, proxButtonDist, antButtonDist, excButtonDist, canButtonDist;
    public Button remAntButtonDialogComp, iniBlocoButtonDialogComp, fimBlocoButtonDialogComp;
    public Button compButtonDialogComp, agenteButtonDialogComp, varButtonDialogComp, operButtonDialogComp, paramButtonDialogComp;
    public Button remAntButtonDialogPar, iniBlocoButtonDialogPar, fimBlocoButtonDialogPar;
    public Button varButtonDialogPar, operButtonDialogPar, paramButtonDialogPar;
    public Choice corChoice, agenteChoiceDialogComp, varChoiceDialogComp, compChoiceDialogComp, agenChoiceComp, operChoiceDialogComp;
    public Choice varChoiceDialogPar, operChoiceDialogPar, agenChoiceDist, tipoChoiceVar;
    public Dialog defAgentes, defComportamentos, defVariaveis, defDialogComportamentos;
    public Dialog critParadaDialog, dimensoesDialog, distDialog, defDialogParada, okDialog;
    public Menu FileMenu, SettingsMenu, CompileMenu, HelpMenu;
    public Scrollbar regraScrollDialogComp, regraScrollDialogPar;
    static int tmp[][] = new int[1000][256];
    public static Indaux[] vetorIndex = new Indaux[100];
    public static final String variaveis = "(), <>!=&|+-/*;";
    public static boolean acao = false;

    public SIMULA()
    {
        super( "SIMULA" );
        setTitle( "SIMULA" );
        MenuBar programMenuBar = new MenuBar();
        setMenuBar( programMenuBar );
        FileMenu = new Menu( "Arquivo" );
        FileMenu.add( new MenuItem( "Novo" ) );
        FileMenu.add( new MenuItem( "Abrir" ) );
        FileMenu.add( new MenuItem( "Salvar" ) );
        FileMenu.add( new MenuItem( "Salvar Como" ) );
        FileMenu.addSeparator();
        FileMenu.add( new MenuItem( "Sair" ) );
        programMenuBar.add( FileMenu );
        SettingsMenu = new Menu( "Configurações" );
        SettingsMenu.add( new MenuItem( "Definição de Agentes" ) );
        SettingsMenu.add( new MenuItem( "Definição de Variáveis" ) );
        SettingsMenu.add( new MenuItem( "Regras de Comportamento" ) );
        SettingsMenu.add( new MenuItem( "Critério de Parada" ) );
        SettingsMenu.add( new MenuItem( "Dimensão do Ambiente" ) );
        SettingsMenu.add( new MenuItem( "Distribuição de Agentes" ) );
        programMenuBar.add( SettingsMenu );
        CompileMenu = new Menu( "Executar" );
        CompileMenu.add( new MenuItem( "Gerar Código" ) );
        CompileMenu.add( new MenuItem( "Execução" ) );
        programMenuBar.add( CompileMenu );
        HelpMenu = new Menu( "Ajuda" );
        HelpMenu.add( new MenuItem( "Ajuda" ) );
        HelpMenu.add( new MenuItem( "Sobre" ) );
        programMenuBar.add( HelpMenu );
        (FileMenu).addActionListener( this );
        (SettingsMenu).addActionListener( this );
        (CompileMenu).addActionListener( this );
        (HelpMenu).addActionListener( this );
        setLayout( new BorderLayout() );
        add( "Center", (new LogoCanvas()) );
        setResizable( false );
        setSize( 400, 250 );
        setVisible( true );
        addWindowListener( this );
    }

    @Override
    public void actionPerformed( ActionEvent evt )
    {
        String parametro = evt.getActionCommand();
        Object source = evt.getSource();
        if ( source == FileMenu )
        {
            switch ( parametro )
            {
                case "Abrir":
                    abrirArquivo();
                    break;
                case "Novo":
                    novasVariaveis();
                    file = null;
                    this.setTitle( "SIMULA" );
                    break;
                case "Salvar":
                    SalvaArq(file);
                    break;
                case "Salvar Como":
                    SalvaArq();
                    break;
                case "Sair":
                    dispose();
                    System.exit( 0 );
            }
        }

        else if ( source == CompileMenu )
        {
            switch ( parametro )
            {
                case "Gerar Código":
                    GerarCodigo();
                    break;
                case "Execução":
                    Runtime rt = Runtime.getRuntime();
                    try
                    {
                        Process compila = rt.exec( "java Executar" );
                    }
                    catch ( Exception e )
                    {
                        e.printStackTrace();
                    }
                    break;
            }
        }

        else if ( parametro.equals( "Cancela" ) )
        {
            if ( source == canButtonVar )
            {
                defVariaveis.setVisible( false );
                defVariaveis.dispose();
            }
            else if ( source == canButtonDialogComp )
            {
                defDialogComportamentos.setVisible( false );
                defDialogComportamentos.dispose();
            }
            else if ( source == canButtonDialogPar )
            {
                defDialogParada.setVisible( false );
                defDialogParada.dispose();
            }
            else if ( source == canButtonComp )
            {
                defComportamentos.setVisible( false );
                agenChoiceComp.removeAll();
                defComportamentos.dispose();
            }
            else if ( source == canButtonDist )
            {
                agenChoiceDist.removeAll();
                distDialog.setVisible( false );
                distDialog.dispose();
            }
            else if ( source == canButtonDim )
            {
                dimensoesDialog.setVisible( false );
                dimensoesDialog.dispose();
            }
            else if ( source == canButton )
            {
                defAgentes.setVisible( false );
                defAgentes.dispose();
            }
        }

        else if ( parametro.equals( "OK" ) )
        {
            if ( source == okButtonVar )
            {
                if ( !(nomeFieldVar.getText().equals( "" )) && !(nomeFieldVar.getText().equals( "sucesso" ))
                        && !(nomeFieldVar.getText().equals( "energia" )) && !(nomeFieldVar.getText().equals( "carga" )) )
                {
                    salvaVariaveis();
                    defVariaveis.setVisible( false );
                    defVariaveis.dispose();
                }
            }
            else if ( source == okDialogButton )
            {
                okDialog.setVisible( false );
                okDialog.dispose();
            }
            else if ( source == okButtonComp )
            {
                if ( !(agenChoiceComp.getSelectedItem().equals( "" )) && !(preLabelComp2.getText().equals( "" ))
                        && !(ativLabelComp2.getText().equals( "" )) )
                {
                    salvaComportamentos();
                    defComportamentos.setVisible( false );
                    agenChoiceComp.removeAll();
                }
            }
            else if ( source == okButtonDialogPar )
            {
                if ( testaEntrada( paraString, false ) || paraString.equals( "" ) )
                {
                    critpar = paraString;
                    defDialogParada.setVisible( false );
                    defDialogParada.dispose();
                }
                else
                {
                    criaOkDialog( "Erro na instrucao." );
                }
            }
            else if ( source == okButtonDialogComp )
            {
                boolean aceita = false;
                if ( (choice == 1) && (testaEntrada( regraString, false ) || regraString.equals( "" )) )
                {
                    aceita = true;
                    preLabelComp2.setText( regraString );
                }
                else if ( (choice == 2) && (testaEntrada( regraString, true ) || regraString.equals( "" )) )
                {
                    aceita = true;
                    ativLabelComp2.setText( regraString );
                }
                else if ( (choice == 3) && (testaEntrada( regraString, false ) || regraString.equals( "" )) )
                {
                    aceita = true;
                    conLabelComp2.setText( regraString );
                }
                else if ( (choice == 4) && (testaEntrada( regraString, true ) || regraString.equals( "" )) )
                {
                    aceita = true;
                    posLabelComp2.setText( regraString );
                }
                if ( aceita )
                {
                    defDialogComportamentos.setVisible( false );
                    defDialogComportamentos.dispose();
                }
                else
                {
                    criaOkDialog( "Erro na instrucao." );
                }
            }
            else if ( source == okButtonDist )
            {
                if ( !(agenChoiceDist.getSelectedItem().equals( "" )) )
                {
                    salvaDistribuicao();
                }
                distDialog.setVisible( false );
                agenChoiceDist.removeAll();
            }
            else if ( source == okButtonDim )
            {
                int cl, li;
                cl = Integer.parseInt( colFieldDim.getText() );
                li = Integer.parseInt( linFieldDim.getText() );
                if ( !(colFieldDim.getText().equals( "" )) && !(linFieldDim.getText().equals( "" )) )
                {
                    if ( (li < 41) && (li > 0) && (cl < 41) && (cl > 0) )
                    {
                        colunas = colFieldDim.getText();
                        col = cl;
                        linhas = linFieldDim.getText();
                        lin = li;
                        dimensoesDialog.setVisible( false );
                    }
                }
            }
            else if ( source == okButton )
            {
                if ( !(nomeField.getText().equals( "" )) && !(nomeField.getText().equals( "sucesso" ))
                        && !(nomeField.getText().equals( "energia" )) && !(nomeField.getText().equals( "carga" )) )
                {
                    if ( typeAgentList[ident] == null )
                    {
                        int[] img = new int[256];
                        typeAgentList[ident] = new tipoAgente( "", "", "0", "0", "0", "0", img );
                    }
                    salvaAgentes();
                    defAgentes.setVisible( false );
                }
            }
        }

        else if ( parametro.equals( "Proximo" ) )
        {
            if ( source == proxButtonVar )
            {
                if ( !(nomeFieldVar.getText().equals( "" )) && !(nomeFieldVar.getText().equals( "sucesso" ))
                        && !(nomeFieldVar.getText().equals( "energia" )) && !(nomeFieldVar.getText().equals( "carga" )) )
                {
                    salvaVariaveis();
                    identVar++;
                    if ( variaveisList[identVar] == null )
                    {
                        variaveisList[identVar] = new Variable( "", "-1", "0" );
                    }
                    atualizaVariaveis();
                }
            }
            else if ( source == proxButtonComp )
            {
                if ( !(agenChoiceComp.getSelectedItem().equals( "" )) && !(preLabelComp2.getText().equals( "" ))
                        && !(ativLabelComp2.getText().equals( "" )) )
                {
                    salvaComportamentos();
                    identComp++;
                    if ( behaviorList[identComp] == null )
                    {
                        behaviorList[identComp] = new Behavior( "", (Integer.toString( identComp )),
                                "", "", "", "", "0" );
                    }
                    atualizaComportamentos();
                }
            }
            else if ( source == proxButtonDist )
            {
                if ( !(agenChoiceDist.getSelectedItem().equals( "" )) )
                {
                    salvaDistribuicao();
                    identDist++;
                    if ( distList[identDist] == null )
                    {
                        distList[identDist] = new Dist( "", "0", "0", "0" );
                    }
                    atualizaDistribuicao();
                }
            }
            else if ( source == proxButton )
            {
                if ( !(nomeField.getText().equals( "" )) && !(nomeField.getText().equals( "sucesso" ))
                        && !(nomeField.getText().equals( "energia" )) && !(nomeField.getText().equals( "carga" )) )
                {
                    salvaAgentes();
                    ident++;
                    if ( typeAgentList[ident] == null )
                    {
                        for ( int i = 0; i < 256; i++ )
                        {
                            tmp[ident][i] = -1;
                        }
                        typeAgentList[ident]
                                = new tipoAgente( Integer.toString(ident), "", "0", "0", "0", "0", tmp[ident] );
                    }
                    atualizaAgentes();
                }
            }
        }

        else if ( parametro.equals( "Anterior" ) )
        {
            if ( source == antButton )
            {
                if ( ident > 1 )
                {
                    salvaAgentes();
                    ident--;
                    atualizaAgentes();
                }
            }
            else if ( source == antButtonComp )
            {
                if ( identComp > 1 )
                {
                    salvaComportamentos();
                    identComp--;
                    atualizaComportamentos();
                }
            }
            else if ( source == antButtonDist )
            {
                if ( identDist > 1 )
                {
                    salvaDistribuicao();
                    identDist--;
                    atualizaDistribuicao();
                }
            }
            else if ( source == antButtonVar )
            {
                if ( identVar > 1 )
                {
                    salvaVariaveis();
                    identVar--;
                    atualizaVariaveis();
                }
            }
        }

        else if ( parametro.equals( "Exclui" ) )
        {
            if ( source == excButton )
            {
                if ( typeAgentList[ident + 1] != null )
                {
                    aux = ident;
                    while ( typeAgentList[aux] != null )
                    {
                        typeAgentList[aux] = typeAgentList[aux + 1];
                        aux++;
                    }
                    atualizaAgentes();
                }
                else
                {
                    nomeField.setText( "" );
                    numField.setText( "0" );
                    areaField.setText( "0" );
                    gridCanvas.inicializaPixels();
                    gridCanvas.repaint();
                }
            }
            else if ( source == excButtonComp )
            {
                if ( behaviorList[identComp + 1] != null )
                {
                    aux = identComp;
                    while ( behaviorList[aux] != null )
                    {
                        behaviorList[aux] = behaviorList[aux + 1];
                        aux++;
                    }
                    atualizaComportamentos();
                }
                else
                {
                    idLabelComp.setText( (new Integer( identComp )).toString() );
                    preLabelComp2.setText( "" );
                    ativLabelComp2.setText( "" );
                    conLabelComp2.setText( "" );
                    posLabelComp2.setText( "" );
                    priorFieldComp.setText( "0" );
                }
            }
            else if ( source == excButtonDist )
            {
                if ( distList[identDist + 1] != null )
                {
                    aux = identDist;
                    while ( (distList[aux] != null) )
                    {
                        distList[aux] = distList[aux + 1];
                        aux++;
                    }
                    atualizaDistribuicao();
                }
                else
                {
                    agenChoiceDist.select( -1 );
                    numFieldDist.setText( Integer.toString(identDist) );
                    linFieldDist.setText( "0" );
                    colFieldDist.setText( "0" );
                }
            }
            else if ( source == excButtonVar )
            {
                if ( variaveisList[identVar + 1] != null )
                {
                    aux = identVar;
                    while ( variaveisList[aux] != null )
                    {
                        variaveisList[aux] = variaveisList[aux + 1];
                        aux++;
                    }
                    atualizaVariaveis();
                }
                else
                {
                    nomeFieldVar.setText( "" );
                    tipoChoiceVar.select( 0 );
                    valFieldVar.setText( "0" );
                }
            }
        }

        else if ( parametro.equals( "Alterar" ) )
        {
            if ( source == preButtonComp )
            {
                criaDialogComportamentos( 1, preLabelComp2.getText() );
            }
            else if ( source == ativButtonComp )
            {
                criaDialogComportamentos( 2, ativLabelComp2.getText() );
            }
            else if ( source == conButtonComp )
            {
                criaDialogComportamentos( 3, conLabelComp2.getText() );
            }
            else if ( source == posButtonComp )
            {
                criaDialogComportamentos( 4, posLabelComp2.getText() );
            }
        }

        else if ( parametro.equals( "Adicionar" ) )
        {
            int compScroll;
            if ( source == compButtonDialogComp )
            {
                regraString = regraString + compChoiceDialogComp.getSelectedItem() + "(";
                atualizaRegra( true );
            }
            else if ( source == paramButtonDialogComp )
            {
                regraString = regraString + paramFieldDialogComp.getText();
                atualizaRegra( true );
            }
            else if ( source == agenteButtonDialogComp )
            {
                regraString = regraString + agenteChoiceDialogComp.getSelectedItem();
                atualizaRegra( true );
            }
            else if ( source == operButtonDialogComp )
            {
                regraString = regraString + operChoiceDialogComp.getSelectedItem();
                atualizaRegra( true );
            }
            else if ( source == varButtonDialogComp )
            {
                regraString = regraString + varChoiceDialogComp.getSelectedItem();
                atualizaRegra( true );
            }
            else if ( source == operButtonDialogPar )
            {
                paraString = paraString + operChoiceDialogPar.getSelectedItem();
                atualizaRegra( false );
            }
            else if ( source == paramButtonDialogPar )
            {
                paraString = paraString + paramFieldDialogPar.getText();
                atualizaRegra( false );
            }
            else if ( source == varButtonDialogPar )
            {
                paraString = paraString + varChoiceDialogPar.getSelectedItem();
                atualizaRegra( false );
            }
        }
        else if ( parametro.equals( "Remover Anterior" ) )
        {
            int compScroll;
            if ( source == remAntButtonDialogPar )
            {
                if ( paraString.length() > 0 )
                {
                    int compUltPalavra = retornaCompUltPalavra( paraString );
                    paraString = paraString.substring( 0, (paraString.length() - compUltPalavra) );
                    atualizaRegra( false );
                }
            }
            else if ( source == remAntButtonDialogComp )
            {
                if ( regraString.length() > 0 )
                {
                    int compUltPalavra = retornaCompUltPalavra( regraString );
                    regraString = regraString.substring( 0, (regraString.length() - compUltPalavra) );
                    atualizaRegra( true );
                }
            }
        }
        else if ( source == iniBlocoButtonDialogComp )
        {
            regraString = regraString + "(";
            atualizaRegra( true );
        }
        else if ( source == fimBlocoButtonDialogComp )
        {
            regraString = regraString + ")";
            atualizaRegra( true );
        }
        else if ( source == iniBlocoButtonDialogPar )
        {
            paraString = paraString + "(";
            atualizaRegra( false );
        }
        else if ( source == fimBlocoButtonDialogPar )
        {
            paraString = paraString + ")";
            atualizaRegra( false );
        }

        else if ( source == SettingsMenu )
        {
            if ( "Definição de Agentes".equals( parametro ) )
            {
                criaDefAgentes();
            }
            else if ( ("Definição de Variáveis").equals( parametro ) )
            {
                criaDefVariaveis();
            }
            else if ( ("Regras de Comportamento").equals( parametro ) )
            {
                criaDefComportamentos();
            }
            else if ( ("Critério de Parada").equals( parametro ) )
            {
                criaDialogParada();
            }
            else if ( ("Dimensão do Ambiente").equals( parametro ) )
            {
                criaDimensoesDialog();
            }
            else if ( ("Distribuição de Agentes").equals( parametro ) )
            {
                criaDistribAgentes();
            }
        }
        else if ( source == HelpMenu )
        {
        }
    }

    /**
     * *************************************************************************
     */
    public void criaDefComportamentos()
    {
        int j = 1;
        identComp = 1;
        if ( behaviorList[identComp] == null )
        {
            behaviorList[identComp] = new Behavior( "", Integer.toString(identComp),
                    "", "", "", "", "0" );
        }
        if ( defComportamentos == null )
        {
            defComportamentos = new Dialog( this, true );
            defComportamentos.setLayout( new BorderLayout() );
            baseComportamentos = new Panel();
            baseComportamentos.setSize( getInsets().left + getInsets().right + 540, getInsets().top + getInsets().bottom + 250 );
            baseComportamentos.setLayout( null );
            okButtonComp = new Button( "OK" );
            okButtonComp.setBounds( 30, 250, 80, 25 );
            baseComportamentos.add( okButtonComp );
            okButtonComp.addActionListener( this );
            proxButtonComp = new Button( "Proximo" );
            proxButtonComp.setBounds( 130, 250, 80, 25 );
            baseComportamentos.add( proxButtonComp );
            proxButtonComp.addActionListener( this );
            antButtonComp = new Button( "Anterior" );
            antButtonComp.setBounds( 230, 250, 80, 25 );
            baseComportamentos.add( antButtonComp );
            antButtonComp.addActionListener( this );
            excButtonComp = new Button( "Exclui" );
            excButtonComp.setBounds( 330, 250, 80, 25 );
            baseComportamentos.add( excButtonComp );
            excButtonComp.addActionListener( this );
            canButtonComp = new Button( "Cancela" );
            canButtonComp.setBounds( 430, 250, 80, 25 );
            baseComportamentos.add( canButtonComp );
            canButtonComp.addActionListener( this );
            agenChoiceComp = new Choice();
            baseComportamentos.add( agenChoiceComp );
            agenChoiceComp.setBounds( 110, 50, 200, 21 );
            agenLabelComp = new Label( "Agente:" );
            agenLabelComp.setBounds( 20, 50, 70, 20 );
            baseComportamentos.add( agenLabelComp );
            regraLabelComp = new Label( "Regra:" );
            regraLabelComp.setBounds( 20, 20, 70, 20 );
            baseComportamentos.add( regraLabelComp );
            idLabelComp = new Label( "1" );
            idLabelComp.setBounds( 110, 20, 50, 20 );
            baseComportamentos.add( idLabelComp );
            priorLabelComp = new Label( "Prioridade:" );
            priorLabelComp.setBounds( 20, 80, 75, 20 );
            baseComportamentos.add( priorLabelComp );
            preLabelComp = new Label( "Pré-Condição:" );
            preLabelComp.setBounds( 20, 120, 100, 20 );
            baseComportamentos.add( preLabelComp );
            ativLabelComp = new Label( "Ação Ativada:" );
            ativLabelComp.setBounds( 20, 150, 100, 20 );
            baseComportamentos.add( ativLabelComp );
            conLabelComp = new Label( "Ação Condicional:" );
            conLabelComp.setBounds( 20, 180, 115, 20 );
            baseComportamentos.add( conLabelComp );
            posLabelComp = new Label( "Pós-Condição:" );
            posLabelComp.setBounds( 20, 210, 100, 20 );
            baseComportamentos.add( posLabelComp );
            priorFieldComp = new TextField();
            baseComportamentos.add( priorFieldComp );
            priorFieldComp.setBounds( 110, 80, 50, 20 );
            (priorFieldComp).addKeyListener( this );
            preLabelComp2 = new Label();
            preLabelComp2.setBounds( 140, 120, 280, 20 );
            baseComportamentos.add( preLabelComp2 );
            ativLabelComp2 = new Label();
            ativLabelComp2.setBounds( 140, 150, 280, 20 );
            baseComportamentos.add( ativLabelComp2 );
            conLabelComp2 = new Label();
            conLabelComp2.setBounds( 140, 180, 280, 20 );
            baseComportamentos.add( conLabelComp2 );
            posLabelComp2 = new Label();
            posLabelComp2.setBounds( 140, 210, 280, 20 );
            baseComportamentos.add( posLabelComp2 );
            preButtonComp = new Button( "Alterar" );
            preButtonComp.setBounds( 440, 120, 80, 20 );
            baseComportamentos.add( preButtonComp );
            preButtonComp.addActionListener( this );
            ativButtonComp = new Button( "Alterar" );
            ativButtonComp.setBounds( 440, 150, 80, 20 );
            baseComportamentos.add( ativButtonComp );
            ativButtonComp.addActionListener( this );
            conButtonComp = new Button( "Alterar" );
            conButtonComp.setBounds( 440, 180, 80, 20 );
            baseComportamentos.add( conButtonComp );
            conButtonComp.addActionListener( this );
            posButtonComp = new Button( "Alterar" );
            posButtonComp.setBounds( 440, 210, 80, 20 );
            baseComportamentos.add( posButtonComp );
            posButtonComp.addActionListener( this );
            defComportamentos.add( "Center", baseComportamentos );
            defComportamentos.setTitle( "Definição de Comportamentos" );
            defComportamentos.pack();
            defComportamentos.setResizable( false );
        }
        agenChoiceComp.add( "" );
        while ( (typeAgentList[j] != null) && (!(typeAgentList[j].nome.equals( "" ))) )
        {
            agenChoiceComp.add( typeAgentList[j].nome );
            j++;
        }
        atualizaComportamentos();
        defComportamentos.setVisible( true );
    }

    public void criaDialogComportamentos( int ch, String strtmp )
    {
        Panel baseDialogComportamentos;
        int j = 0;
        if ( defDialogComportamentos == null )
        {
            defDialogComportamentos = new Dialog( this, true );
            defDialogComportamentos.setLayout( new BorderLayout() );
            baseDialogComportamentos = new Panel();
            baseDialogComportamentos.setLayout( null );
            baseDialogComportamentos.setSize( getInsets().left + getInsets().right + 500, getInsets().top + getInsets().bottom + 370 );
            compLabelDialogComp = new Label( "Comportamento:" );
            compLabelDialogComp.setBounds( 24, 10, 120, 20 );
            baseDialogComportamentos.add( compLabelDialogComp );
            agenteLabelDialogComp = new Label( "Agente Objetivo:" );
            agenteLabelDialogComp.setBounds( 24, 35, 120, 20 );
            baseDialogComportamentos.add( agenteLabelDialogComp );
            varLabelDialogComp = new Label( "Variável:" );
            varLabelDialogComp.setBounds( 24, 60, 120, 20 );
            baseDialogComportamentos.add( varLabelDialogComp );
            operLabelDialogComp = new Label( "Operador:" );
            operLabelDialogComp.setBounds( 24, 85, 120, 20 );
            baseDialogComportamentos.add( operLabelDialogComp );
            paramLabelDialogComp = new Label( "Parâmetro Comparação:" );
            paramLabelDialogComp.setBounds( 24, 110, 150, 20 );
            baseDialogComportamentos.add( paramLabelDialogComp );
            regraLabelDialogComp = new Label( "Regra:" );
            regraLabelDialogComp.setBounds( 24, 144, 120, 20 );
            baseDialogComportamentos.add( regraLabelDialogComp );
            regra2LabelDialogComp = new Label();
            regra2LabelDialogComp.setBounds( 12, 180, 468, 20 );
            baseDialogComportamentos.add( regra2LabelDialogComp );
            regraScrollDialogComp = new Scrollbar( Scrollbar.HORIZONTAL );
            regraScrollDialogComp.setBounds( 12, 204, 468, 17 );
            regraScrollDialogComp.setMinimum( 0 );
            regraScrollDialogComp.setMaximum( 0 );
            regraScrollDialogComp.setUnitIncrement( 1 );
            regraScrollDialogComp.addAdjustmentListener( this );
            baseDialogComportamentos.add( regraScrollDialogComp );
            compButtonDialogComp = new Button( "Adicionar" );
            compButtonDialogComp.setBounds( 396, 10, 90, 20 );
            baseDialogComportamentos.add( compButtonDialogComp );
            (compButtonDialogComp).addActionListener( this );
            agenteButtonDialogComp = new Button( "Adicionar" );
            agenteButtonDialogComp.setBounds( 396, 35, 90, 20 );
            baseDialogComportamentos.add( agenteButtonDialogComp );
            (agenteButtonDialogComp).addActionListener( this );
            varButtonDialogComp = new Button( "Adicionar" );
            varButtonDialogComp.setBounds( 396, 60, 90, 20 );
            baseDialogComportamentos.add( varButtonDialogComp );
            (varButtonDialogComp).addActionListener( this );
            operButtonDialogComp = new Button( "Adicionar" );
            operButtonDialogComp.setBounds( 396, 85, 90, 20 );
            baseDialogComportamentos.add( operButtonDialogComp );
            (operButtonDialogComp).addActionListener( this );
            paramButtonDialogComp = new Button( "Adicionar" );
            paramButtonDialogComp.setBounds( 396, 110, 90, 20 );
            baseDialogComportamentos.add( paramButtonDialogComp );
            (paramButtonDialogComp).addActionListener( this );
            compChoiceDialogComp = new Choice();
            baseDialogComportamentos.add( compChoiceDialogComp );
            compChoiceDialogComp.setBounds( 156, 10, 200, 19 );
            agenteChoiceDialogComp = new Choice();
            baseDialogComportamentos.add( agenteChoiceDialogComp );
            agenteChoiceDialogComp.setBounds( 156, 35, 200, 19 );
            varChoiceDialogComp = new Choice();
            baseDialogComportamentos.add( varChoiceDialogComp );
            varChoiceDialogComp.setBounds( 156, 60, 200, 19 );
            operChoiceDialogComp = new Choice();
            operChoiceDialogComp.add( "&&" );
            operChoiceDialogComp.add( "||" );
            operChoiceDialogComp.add( "!" );
            operChoiceDialogComp.add( "<=" );
            operChoiceDialogComp.add( ">=" );
            operChoiceDialogComp.add( "!=" );
            operChoiceDialogComp.add( "=" );
            operChoiceDialogComp.add( "<" );
            operChoiceDialogComp.add( ">" );
            operChoiceDialogComp.add( "," );
            operChoiceDialogComp.add( "-" );
            operChoiceDialogComp.add( "+" );
            operChoiceDialogComp.add( "/" );
            operChoiceDialogComp.add( "*" );
            baseDialogComportamentos.add( operChoiceDialogComp );
            operChoiceDialogComp.setBounds( 156, 85, 50, 19 );
            paramFieldDialogComp = new TextField();
            paramFieldDialogComp.setBounds( 180, 110, 180, 20 );
            baseDialogComportamentos.add( paramFieldDialogComp );
            iniBlocoButtonDialogComp = new Button( "Início de Bloco" );
            iniBlocoButtonDialogComp.setBounds( 36, 240, 123, 26 );
            baseDialogComportamentos.add( iniBlocoButtonDialogComp );
            (iniBlocoButtonDialogComp).addActionListener( this );
            fimBlocoButtonDialogComp = new Button( "Fim de Bloco" );
            fimBlocoButtonDialogComp.setBounds( 180, 240, 123, 26 );
            baseDialogComportamentos.add( fimBlocoButtonDialogComp );
            (fimBlocoButtonDialogComp).addActionListener( this );
            remAntButtonDialogComp = new Button( "Remover Anterior" );
            remAntButtonDialogComp.setBounds( 324, 240, 123, 26 );
            baseDialogComportamentos.add( remAntButtonDialogComp );
            (remAntButtonDialogComp).addActionListener( this );
            okButtonDialogComp = new Button( "OK" );
            okButtonDialogComp.setBounds( 350, 300, 123, 26 );
            baseDialogComportamentos.add( okButtonDialogComp );
            (okButtonDialogComp).addActionListener( this );
            canButtonDialogComp = new Button( "Cancela" );
            canButtonDialogComp.setBounds( 350, 340, 123, 26 );
            baseDialogComportamentos.add( canButtonDialogComp );
            (canButtonDialogComp).addActionListener( this );
            List listComp = new List( 8, false );
            listComp.setBounds( 20, 290, 310, 100 );
            listComp.add( "movimento_randomico()" );
            listComp.add( "movimento_direcionado(direcao(N,S,L,O,NO,NE,SO,SE), n. posicoes)" );
            listComp.add( "deixa_pista()" );
            listComp.add( "remove_pista()" );
            listComp.add( "segue_pista()" );
            listComp.add( "segue_maior_gradiente(agente que emite)" );
            listComp.add( "segue_menor_gradiente(agente que emite)" );
            listComp.add( "segue_agente(agente seguindo)" );
            listComp.add( "foge_de_agente(agente que persegue)" );
            listComp.add( "mata_agente(agente a matar)" );
            listComp.add( "morte()" );
            listComp.add( "reproduz(quantidade, tipo de agente)" );
            listComp.add( "transforma(novo agente)" );
            listComp.add( "evita_obstaculo()" );
            listComp.add( "percebe_agente(agente a ser percebido)" );
            listComp.add( "atinge_agente(agente a ser atingido)" );
            listComp.add( "percebe_pista()" );
            listComp.add( "atinge_pista(agente que deixou a pista)" );
            listComp.add( "atinge_tempo_de_vida(tempo máximo de vida)" );
            listComp.add( "tempo_de_fertilidade(tempo de vida fértil)" );
            listComp.add( "atinge_vida_adulta(tempo de vida para tornar-se adulto)" );
            listComp.add( "tipo_sexo(sexo,taxa de nascimento)" );
            listComp.add( "taxa_de_sucesso(taxa)" );
            baseDialogComportamentos.add( listComp );
            defDialogComportamentos.add( "Center", baseDialogComportamentos );
            defDialogComportamentos.pack();
            defDialogComportamentos.setTitle( "Definição de Comportamentos" );
            defDialogComportamentos.setResizable( false );
        }
        if ( choice != ch )
        {
            choice = ch;
            compChoiceDialogComp.removeAll();
            if ( (choice == 1) || (choice == 3) )
            {
                compChoiceDialogComp.add( "percebe_agente" );
                compChoiceDialogComp.add( "percebe_pista" );
                compChoiceDialogComp.add( "atinge_agente" );
                compChoiceDialogComp.add( "atinge_pista" );
                compChoiceDialogComp.add( "atinge_tempo_de_vida" );
                compChoiceDialogComp.add( "atinge_vida_adulta" );
                compChoiceDialogComp.add( "tipo_sexo" );
                compChoiceDialogComp.add( "taxa_sucesso" );
            }
            else if ( choice == 2 )
            {
                compChoiceDialogComp.add( "movimento_randomico" );
                compChoiceDialogComp.add( "movimento_direcionado" );
                compChoiceDialogComp.add( "deixa_pista" );
                compChoiceDialogComp.add( "segue_pista" );
                compChoiceDialogComp.add( "remove_pista" );
                compChoiceDialogComp.add( "segue_maior_gradiente" );
                compChoiceDialogComp.add( "segue_menor_gradiente" );
                compChoiceDialogComp.add( "segue_agente" );
                compChoiceDialogComp.add( "foge_de_agente" );
                compChoiceDialogComp.add( "mata_agente" );
                compChoiceDialogComp.add( "morte" );
                compChoiceDialogComp.add( "reproduz" );
                compChoiceDialogComp.add( "transforma" );
            }
        }
        varChoiceDialogComp.removeAll();
        varChoiceDialogComp.add( "" );
        j = 1;
        while ( (variaveisList[j] != null) && (!(variaveisList[j].nome.equals( "" ))) )
        {
            varChoiceDialogComp.add( variaveisList[j].nome );
            j++;
        }
        varChoiceDialogComp.add( "sucesso" );
        varChoiceDialogComp.add( "energia" );
        varChoiceDialogComp.add( "carga" );
        j = 1;
        agenteChoiceDialogComp.removeAll();
        agenteChoiceDialogComp.add( "" );
        while ( (typeAgentList[j] != null) && (!(typeAgentList[j].nome.equals( "" ))) )
        {
            agenteChoiceDialogComp.add( typeAgentList[j].nome );
            j++;
        }
        regraString = strtmp;
        atualizaRegra( true );
        defDialogComportamentos.setVisible( true );
    }

    public void atualizaComportamentos()
    {
        idLabelComp.setText( (new Integer( identComp )).toString() );
        agenChoiceComp.select( behaviorList[identComp].agente );
        preLabelComp2.setText( behaviorList[identComp].precond );
        ativLabelComp2.setText( behaviorList[identComp].acativ );
        conLabelComp2.setText( behaviorList[identComp].accond );
        posLabelComp2.setText( behaviorList[identComp].poscond );
        priorFieldComp.setText( behaviorList[identComp].priorid );
    }

    public void salvaComportamentos()
    {
        behaviorList[identComp].ident = idLabelComp.getText();
        behaviorList[identComp].agente = agenChoiceComp.getSelectedItem();
        behaviorList[identComp].precond = preLabelComp2.getText();
        behaviorList[identComp].acativ = ativLabelComp2.getText();
        behaviorList[identComp].accond = conLabelComp2.getText();
        behaviorList[identComp].poscond = posLabelComp2.getText();
        behaviorList[identComp].priorid = priorFieldComp.getText();
    }

    /**
     * *************************************************************************
     * @param comp
     */
    public void atualizaRegra( boolean comp )
    {
        if ( comp )
        {
            int valor = regraString.length(), aux = regraScrollDialogComp.getValue();

            // Alterado 02/10/2005 para corrigir um bug no valor da
            // variavel "aux"
            if ( aux < 0 )
            {
                aux = 0;
            }

            valor = valor - 70;
            if ( valor < 0 )
            {
                valor = 0;
            }

            regraScrollDialogComp.setMaximum( valor );

            if ( aux > valor )
            {
                aux = 0;
                regraScrollDialogComp.setValue( 0 );
            }
            valor = aux;

            regra2LabelDialogComp.setText( regraString.substring( valor, regraString.length() ) );
        }
        else
        {
            int valor = paraString.length(), aux = regraScrollDialogPar.getValue();

            // Alterado 16/10/2005 para corrigir um bug no valor da
            // variavel "aux" no menu "Critério de parada"
            if ( aux < 0 )
            {
                aux = 0;
            }

            valor = valor - 70;

            if ( valor < 0 )
            {
                valor = 0;
            }

            regraScrollDialogPar.setMaximum( valor );
            if ( aux > valor )
            {
                aux = 0;
                regraScrollDialogPar.setValue( 0 );
            }
            valor = aux;
            regra2LabelDialogPar.setText( paraString.substring( valor, paraString.length() ) );
        }
    }

    /**
     * *************************************************************************
     */
    public void criaDistribAgentes()
    {
        int j = 1;
        if ( distList[1] == null )
        {
            distList[1] = new Dist( "", "0", "0", "0" );
        }
        if ( distDialog == null )
        {
            distDialog = new Dialog( this, true );
            distDialog.setLayout( new BorderLayout() );
            baseDistrib = new Panel();
            baseDistrib.setLayout( null );
            baseDistrib.setSize( getInsets().left + getInsets().right + 480, getInsets().top + getInsets().bottom + 150 );
            agenLabelDist = new Label( "Nome do Agente:" );
            agenLabelDist.setBounds( 20, 20, 120, 20 );
            baseDistrib.add( agenLabelDist );
            agenChoiceDist = new Choice();
            baseDistrib.add( agenChoiceDist );
            agenChoiceDist.setBounds( 190, 20, 200, 20 );
            numLabelDist = new Label( "Número do Agente:" );
            numLabelDist.setBounds( 20, 50, 130, 20 );
            baseDistrib.add( numLabelDist );
            numFieldDist = new TextField();
            baseDistrib.add( numFieldDist );
            numFieldDist.setBounds( 190, 50, 50, 20 );
            numFieldDist.addKeyListener( this );
            colLabelDist = new Label( "Coluna do Agente:" );
            colLabelDist.setBounds( 20, 80, 130, 20 );
            baseDistrib.add( colLabelDist );
            colFieldDist = new TextField();
            baseDistrib.add( colFieldDist );
            colFieldDist.setBounds( 190, 80, 50, 20 );
            colFieldDist.addKeyListener( this );
            linLabelDist = new Label( "Linha do Agente:" );
            linLabelDist.setBounds( 20, 110, 120, 20 );
            baseDistrib.add( linLabelDist );
            linFieldDist = new TextField();
            baseDistrib.add( linFieldDist );
            linFieldDist.setBounds( 190, 110, 50, 20 );
            linFieldDist.addKeyListener( this );
            okButtonDist = new Button( "OK" );
            okButtonDist.setBounds( 20, 150, 80, 25 );
            baseDistrib.add( okButtonDist );
            okButtonDist.addActionListener( this );
            proxButtonDist = new Button( "Proximo" );
            proxButtonDist.setBounds( 110, 150, 80, 25 );
            baseDistrib.add( proxButtonDist );
            proxButtonDist.addActionListener( this );
            antButtonDist = new Button( "Anterior" );
            antButtonDist.setBounds( 200, 150, 80, 25 );
            baseDistrib.add( antButtonDist );
            antButtonDist.addActionListener( this );
            excButtonDist = new Button( "Exclui" );
            excButtonDist.setBounds( 290, 150, 80, 25 );
            baseDistrib.add( excButtonDist );
            excButtonDist.addActionListener( this );
            canButtonDist = new Button( "Cancela" );
            canButtonDist.setBounds( 380, 150, 80, 25 );
            baseDistrib.add( canButtonDist );
            canButtonDist.addActionListener( this );
            distDialog.add( "Center", baseDistrib );
            distDialog.pack();
            distDialog.setTitle( "Distribuição de Agentes" );
            distDialog.setResizable( false );
        }
        agenChoiceDist.removeAll();
        agenChoiceDist.add( "" );
        while ( (typeAgentList[j] != null) && (!(typeAgentList[j].nome.equals( "" ))) )
        {
            agenChoiceDist.add( typeAgentList[j].nome );
            j++;
        }
        atualizaDistribuicao();
        distDialog.setVisible( true );
    }

    public void salvaDistribuicao()
    {
        distList[identDist].nome = agenChoiceDist.getSelectedItem();
        distList[identDist].numero = numFieldDist.getText();
        distList[identDist].linha = linFieldDist.getText();
        distList[identDist].coluna = colFieldDist.getText();
    }

    public void atualizaDistribuicao()
    {
        agenChoiceDist.select( distList[identDist].nome );
        numFieldDist.setText( distList[identDist].numero );
        linFieldDist.setText( distList[identDist].linha );
        colFieldDist.setText( distList[identDist].coluna );
    }

    /**
     * *************************************************************************
     */
    public void criaDefVariaveis()
    {
        identVar = 1;
        if ( variaveisList[1] == null )
        {
            variaveisList[1] = new Variable( "", "0", "0" );
        }
        if ( defVariaveis == null )
        {
            defVariaveis = new Dialog( this, true );
            defVariaveis.setLayout( new BorderLayout() );
            baseVariaveis = new Panel();
            baseVariaveis.setBounds( getInsets().left + getInsets().right + 12, getInsets().top + getInsets().bottom + +12, 520, 200 );
            baseVariaveis.setLayout( null );
            idenLabelVar = new Label( "Identificador:" );
            idenLabelVar.setBounds( 40, 20, 100, 20 );
            baseVariaveis.add( idenLabelVar );
            idLabelVar = new Label();
            idLabelVar.setBounds( 180, 20, 50, 20 );
            baseVariaveis.add( idLabelVar );
            nomeLabelVar = new Label( "Nome da Variável:" );
            nomeLabelVar.setBounds( 40, 50, 120, 20 );
            baseVariaveis.add( nomeLabelVar );
            nomeFieldVar = new TextField( "" );
            nomeFieldVar.setBounds( 180, 50, 280, 20 );
            baseVariaveis.add( nomeFieldVar );
            tipoLabelVar = new Label( "Tipo da Variável:" );
            tipoLabelVar.setBounds( 40, 80, 120, 20 );
            baseVariaveis.add( tipoLabelVar );
            tipoChoiceVar = new Choice();
            tipoChoiceVar.add( "int" );
            tipoChoiceVar.add( "string" );
            tipoChoiceVar.add( "char" );
            baseVariaveis.add( tipoChoiceVar );
            tipoChoiceVar.setBounds( 180, 80, 100, 19 );
            valLabelVar = new Label( "Valor Inicial:" );
            valLabelVar.setBounds( 40, 110, 100, 20 );
            baseVariaveis.add( valLabelVar );
            valFieldVar = new TextField( "" );
            valFieldVar.setBounds( 180, 110, 50, 20 );
            baseVariaveis.add( valFieldVar );
            valFieldVar.addKeyListener( this );
            canButtonVar = new Button( "Cancela" );
            canButtonVar.setBounds( 420, 155, 80, 25 );
            baseVariaveis.add( canButtonVar );
            canButtonVar.addActionListener( this );
            excButtonVar = new Button( "Exclui" );
            excButtonVar.setBounds( 320, 155, 80, 25 );
            baseVariaveis.add( excButtonVar );
            excButtonVar.addActionListener( this );
            antButtonVar = new Button( "Anterior" );
            antButtonVar.setBounds( 220, 155, 80, 25 );
            baseVariaveis.add( antButtonVar );
            antButtonVar.addActionListener( this );
            proxButtonVar = new Button( "Proximo" );
            proxButtonVar.setBounds( 120, 155, 80, 25 );
            baseVariaveis.add( proxButtonVar );
            proxButtonVar.addActionListener( this );
            okButtonVar = new Button( "OK" );
            okButtonVar.setBounds( 20, 155, 80, 25 );
            baseVariaveis.add( okButtonVar );
            okButtonVar.addActionListener( this );
            defVariaveis.add( "Center", baseVariaveis );
            defVariaveis.pack();
            defVariaveis.setResizable( false );
            defVariaveis.addWindowListener( this );
            defVariaveis.setTitle( "Definição de Variáveis" );
        }
        atualizaVariaveis();
        defVariaveis.setVisible( true );
    }

    public void salvaVariaveis()
    {
        variaveisList[identVar].nome = nomeFieldVar.getText();
        variaveisList[identVar].tipo = tipoChoiceVar.getSelectedItem();
        variaveisList[identVar].valor = valFieldVar.getText();
    }

    public void atualizaVariaveis()
    {
        idLabelVar.setText( (new Integer( identVar )).toString() );
        nomeFieldVar.setText( variaveisList[identVar].nome );
        tipoChoiceVar.select( variaveisList[identVar].tipo );
        valFieldVar.setText( (new Integer( variaveisList[identVar].valor )).toString() );
    }

    /**
     * *************************************************************************
     */
    public void novasVariaveis()
    {
        int aux = 1;
        while ( typeAgentList[aux] != null )
        {
            typeAgentList[aux] = null;
            aux++;
        }
        aux = 1;
        while ( behaviorList[aux] != null )
        {
            behaviorList[aux] = null;
            aux++;
        }
        aux = 1;
        while ( variaveisList[aux] != null )
        {
            variaveisList[aux] = null;
            aux++;
        }
        aux = 1;
        while ( distList[aux] != null )
        {
            distList[aux] = null;
            aux++;
        }
        critpar = "";
        linhas = "25";
        colunas = "25";
        col = 25;
        lin = 25;
        ident = 1;
        identVar = 1;
        identDist = 1;
        identComp = 1;
    }

    public void abrirArquivo()
    {
        int tmp[][] = new int[1000][256];
        int k = 1;
        String id, no, ar, nu, en, cg;
        int contA = 1, contV = 1, contC = 1, contD = 1;
        for ( int j = 0; j < 256; j++ )
        {
            tmp[0][j] = -1;
        }
        SFileChooser fc = new SFileChooser( JFileChooser.OPEN_DIALOG, file );
        try
        {
            if ( fc.showOpenDialog( this ) == JFileChooser.APPROVE_OPTION )
            {
                novasVariaveis();
                String thisLine;
                try
                {
                    FileInputStream fin = new FileInputStream( fc.getSelectedFile() );
                    try
                    {
                        BufferedReader input = new BufferedReader( new InputStreamReader( fin ) );
                        try
                        {
                            while ( (thisLine = input.readLine()) != null )
                            {
                                if ( (thisLine.equals( ".A." )) )
                                {
                                    id = input.readLine();
                                    no = input.readLine();
                                    ar = input.readLine();
                                    nu = input.readLine();
                                    en = input.readLine();
                                    cg = input.readLine();
                                    for ( int i = 0; i < 256; i++ )
                                    {
                                        tmp[contA][i] = Integer.parseInt(input.readLine());
                                    }
                                    typeAgentList[contA] = new tipoAgente( id, no, ar, en, cg, nu, tmp[contA] );
                                    contA++;
                                    input.readLine();
                                }
                                else if ( (thisLine.equals( ".V." )) )
                                {
                                    variaveisList[contV] = new Variable( input.readLine(),
                                            input.readLine(), input.readLine() );
                                    contV++;
                                    input.readLine();
                                }
                                else if ( (thisLine.equals( ".P." )) )
                                {
                                    critpar = input.readLine();
                                    input.readLine();
                                }
                                else if ( (thisLine.equals( ".C." )) )
                                {
                                    behaviorList[contC] = new Behavior( input.readLine(),
                                            input.readLine(), input.readLine(),
                                            input.readLine(), input.readLine(),
                                            input.readLine(), input.readLine() );
                                    contC++;
                                    input.readLine();
                                }
                                else if ( (thisLine.equals( ".D." )) )
                                {
                                    distList[contD] = new Dist( input.readLine(), input.readLine(),
                                            input.readLine(), input.readLine() );
                                    contD++;
                                    input.readLine();
                                }
                                else if ( (thisLine.equals( ".M." )) )
                                {
                                    colunas = input.readLine();
                                    if ( colunas.equals( "" ) )
                                    {
                                        colunas = "25";
                                    }
                                    linhas = input.readLine();
                                    if ( linhas.equals( "" ) )
                                    {
                                        linhas = "25";
                                    }
                                    col = Integer.parseInt(colunas);
                                    lin = Integer.parseInt(linhas);
                                }
                            }
                            file = fc.getSelectedFile();
                            this.setTitle( "SIMULA - " + file );
                        }
                        catch ( Exception e )
                        {
                            e.printStackTrace();
                        }
                    }
                    catch ( Exception e )
                    {
                        e.printStackTrace();
                    }
                }
                catch ( Exception e )
                {
                    e.printStackTrace();
                }
            }
        }
        catch ( Exception e )
        {
            e.printStackTrace();
        }
    }

    public void SalvaArq()
    {
        SalvaArq( null );
    }

    public void SalvaArq( File fileToSave )
    {
        try
        {
            if ( fileToSave == null )
            {
                SFileChooser fc = new SFileChooser( JFileChooser.SAVE_DIALOG, file );

                if ( fc.showSaveDialog( this ) == JFileChooser.APPROVE_OPTION )
                {
                    fileToSave = fc.getSelectedFile();
                    String file_name = fileToSave.toString();
                    if ( !(file_name.endsWith( ".dat" ) || file_name.endsWith(".DAT") ) )
                    {
                        fileToSave = new File( file_name + ".dat" );
                    }
                    
                }
            }

            if ( fileToSave != null )
            {
                FileOutputStream f = new FileOutputStream( fileToSave );
                int j = 1;
                while ( typeAgentList[j] != null )
                {
                    f.write( ".A.\r\n".getBytes() );
                    f.write( (typeAgentList[j].ident + "\r\n").getBytes() );
                    f.write( (typeAgentList[j].nome + "\r\n").getBytes() );
                    f.write( (typeAgentList[j].area + "\r\n").getBytes() );
                    f.write( (typeAgentList[j].nagentes + "\r\n").getBytes() );
                    f.write( (typeAgentList[j].energia + "\r\n").getBytes() );
                    f.write( (typeAgentList[j].carga + "\r\n").getBytes() );
                    for ( int l = 0; l < 256; l++ )
                    {
                        f.write( ((new Integer( typeAgentList[j].imagem[l] )).toString() + "\r\n").getBytes() );
                    }
                    f.write( "\r\n".getBytes() );
                    j++;
                }
                j = 1;
                while ( variaveisList[j] != null )
                {
                    f.write( ".V.\r\n".getBytes() );
                    f.write( (variaveisList[j].nome + "\r\n").getBytes() );
                    f.write( (variaveisList[j].tipo + "\r\n").getBytes() );
                    f.write( (variaveisList[j].valor + "\r\n\r\n").getBytes() );
                    j++;
                }
                f.write( ".P.\r\n".getBytes() );
                f.write( (critpar + "\r\n\r\n").getBytes() );
                j = 1;
                while ( behaviorList[j] != null )
                {
                    if ( !behaviorList[j].agente.equals( "" ) )
                    {
                        f.write( ".C.\r\n".getBytes() );
                        f.write( (behaviorList[j].agente + "\r\n").getBytes() );
                        f.write( (behaviorList[j].ident + "\r\n").getBytes() );
                        f.write( (behaviorList[j].precond + "\r\n").getBytes() );
                        f.write( (behaviorList[j].acativ + "\r\n").getBytes() );
                        f.write( (behaviorList[j].accond + "\r\n").getBytes() );
                        f.write( (behaviorList[j].poscond + "\r\n").getBytes() );
                        f.write( (behaviorList[j].priorid + "\r\n\r\n").getBytes() );
                    }
                    j++;
                }
                j = 1;
                while ( distList[j] != null )
                {
                    f.write( ".D.\r\n".getBytes() );
                    f.write( (distList[j].nome + "\r\n").getBytes() );
                    f.write( (distList[j].numero + "\r\n").getBytes() );
                    f.write( (distList[j].coluna + "\r\n").getBytes() );
                    f.write( (distList[j].linha + "\r\n\r\n").getBytes() );
                    j++;
                }
                f.write( ".M.\r\n".getBytes() );
                f.write( (colunas + "\r\n").getBytes() );
                f.write( (linhas + "\r\n").getBytes() );
                f.close();
                
                file = fileToSave;
                this.setTitle( "SIMULA - " + file );
            }
        }
        catch ( Exception e )
        {
            e.printStackTrace();
        }

    }

    /**
     * *************************************************************************
     */
    public void criaDimensoesDialog()
    {
        if ( dimensoesDialog == null )
        {
            dimensoesDialog = new Dialog( this, true );
            dimensoesDialog.setLayout( new BorderLayout() );
            baseDimensoes = new Panel();
            baseDimensoes.setLayout( null );
            baseDimensoes.setSize( getInsets().left + getInsets().right + 230, getInsets().top + getInsets().bottom + 90 );
            colLabelDim = new Label( "Número de Colunas:" );
            colLabelDim.setBounds( 20, 20, 130, 20 );
            baseDimensoes.add( colLabelDim );
            colFieldDim = new TextField();
            baseDimensoes.add( colFieldDim );
            colFieldDim.setBounds( 150, 20, 50, 20 );
            (colFieldDim).addKeyListener( this );
            colFieldDim.setEditable( false );
            colFieldDim.setEnabled( false );
            linLabelDim = new Label( "Número de Linhas:" );
            linLabelDim.setBounds( 20, 50, 130, 20 );
            baseDimensoes.add( linLabelDim );
            linFieldDim = new TextField();
            baseDimensoes.add( linFieldDim );
            linFieldDim.setBounds( 150, 50, 50, 20 );
            (linFieldDim).addKeyListener( this );
            linFieldDim.setEditable( false );
            linFieldDim.setEnabled( false );
            okButtonDim = new Button( "OK" );
            okButtonDim.setBounds( 30, 90, 80, 25 );
            baseDimensoes.add( okButtonDim );
            okButtonDim.addActionListener( this );
            canButtonDim = new Button( "Cancela" );
            canButtonDim.setBounds( 130, 90, 80, 25 );
            baseDimensoes.add( canButtonDim );
            canButtonDim.addActionListener( this );
            dimensoesDialog.add( "Center", baseDimensoes );
            dimensoesDialog.pack();
            dimensoesDialog.addWindowListener( this );
            dimensoesDialog.setResizable( false );
            dimensoesDialog.setTitle( "Dimensoes do Ambiente" );
        }
        colFieldDim.setText( colunas );
        linFieldDim.setText( linhas );
        dimensoesDialog.setVisible( true );
    }

    /**
     * *************************************************************************
     */
    public void criaDefAgentes()
    {
        if ( typeAgentList[1] == null )
        {
            for ( int i = 0; i < 256; i++ )
            {
                tmp[ident][i] = -1;
            }
            typeAgentList[ident] = new tipoAgente( "1", "", "0", "0", "0", "0", tmp[ident] );
        }
        defAgentes = new Dialog( this, true );
        baseAgentes = new Panel();
        defAgentes.setTitle( "Definição de Agentes" );
        defAgentes.setLayout( new BorderLayout() );
        baseAgentes.setLayout( null );
        baseAgentes.setBounds( getInsets().left + 12, getInsets().top + 12, 480, 350 );
        defAgentes.add( "Center", baseAgentes );
        idenLabel = new Label( "Identificador:" );
        idenLabel.setBounds( 10, 10, 100, 20 );
        baseAgentes.add( idenLabel );
        idLabel = new Label( "n" );
        idLabel.setBounds( 160, 10, 50, 20 );
        baseAgentes.add( idLabel );
        agenLabel = new Label( "Nome do Agente:" );
        agenLabel.setBounds( 10, 40, 120, 20 );
        baseAgentes.add( agenLabel );
        numLabel = new Label( "Número de Agentes:" );
        numLabel.setBounds( 10, 70, 130, 20 );
        baseAgentes.add( numLabel );
        areaLabel = new Label( "Área de Percepção:" );
        areaLabel.setBounds( 10, 100, 130, 20 );
        baseAgentes.add( areaLabel );
        enerLabelAgent = new Label( "Energia:" );
        enerLabelAgent.setBounds( 10, 130, 130, 20 );
//        baseAgentes.add( enerLabelAgent );
        cargLabelAgent = new Label( "Carga:" );
        cargLabelAgent.setBounds( 10, 160, 130, 20 );
//        baseAgentes.add( cargLabelAgent );
        corLabel = new Label( "Cor Selecionada:" );
        corLabel.setBounds( 10, 200, 115, 20 );
        baseAgentes.add( corLabel );
        imgLabel = new Label( "Imagem:" );
        imgLabel.setBounds( 10, 230, 70, 20 );
        baseAgentes.add( imgLabel );
        nomeField = new TextField();
        nomeField.setBounds( 160, 40, 300, 20 );
        baseAgentes.add( nomeField );
        numField = new TextField();
        numField.setBounds( 160, 70, 50, 20 );
        baseAgentes.add( numField );
        numField.addKeyListener( this );
        areaField = new TextField();
        areaField.setBounds( 160, 100, 50, 20 );
        baseAgentes.add( areaField );
        areaField.addKeyListener( this );
        energFieldAgent = new TextField();
        energFieldAgent.setBounds( 160, 130, 50, 20 );
//        baseAgentes.add( energFieldAgent );
        energFieldAgent.addKeyListener( this );
        cargFieldAgent = new TextField();
        cargFieldAgent.setBounds( 160, 160, 50, 20 );
//        baseAgentes.add( cargFieldAgent );
        cargFieldAgent.addKeyListener( this );
        corChoice = new Choice();
        corChoice.add( "Branco" );
        corChoice.add( "Amarelo" );
        corChoice.add( "Azul" );
        corChoice.add( "Verde" );
        corChoice.add( "Laranja" );
        corChoice.add( "Rosa" );
        corChoice.add( "Vermelho" );
        corChoice.add( "Magenta" );
        corChoice.add( "Ciano" );
        corChoice.add( "Cinza" );
        corChoice.add( "Preto" );
        baseAgentes.add( corChoice );
        corChoice.setBounds( 160, 200, 80, 21 );
        corChoice.addItemListener( this );
        okButton = new Button( "OK" );
        okButton.setBounds( 10, 305, 80, 25 );
        baseAgentes.add( okButton );
        okButton.addActionListener( this );
        proxButton = new Button( "Proximo" );
        proxButton.setBounds( 105, 305, 80, 25 );
        baseAgentes.add( proxButton );
        proxButton.addActionListener( this );
        antButton = new Button( "Anterior" );
        antButton.setBounds( 200, 305, 80, 25 );
        baseAgentes.add( antButton );
        antButton.addActionListener( this );
        excButton = new Button( "Exclui" );
        excButton.setBounds( 295, 305, 80, 25 );
        baseAgentes.add( excButton );
        excButton.addActionListener( this );
        canButton = new Button( "Cancela" );
        canButton.setBounds( 385, 305, 80, 25 );
        baseAgentes.add( canButton );
        canButton.addActionListener( this );
        canvas1 = new Imagem();
        canvas1.setBounds( 100, 230, 23, 23 );
        baseAgentes.add( canvas1 );
        gridCanvas = new GridCanvas();
        gridCanvas.setBounds( 260, 105, 161, 161 );
        (gridCanvas).addMouseListener( this );
        baseAgentes.add( gridCanvas );
        defAgentes.add( "Center", baseAgentes );
        defAgentes.pack();
        defAgentes.addWindowListener( this );
        defAgentes.setResizable( false );
        atualizaAgentes();
        defAgentes.setVisible( true );
    }

    public void salvaAgentes()
    {
        typeAgentList[ident].ident = new Integer( ident ).toString();
        typeAgentList[ident].nome = nomeField.getText();
        typeAgentList[ident].nagentes = numField.getText();
        typeAgentList[ident].area = areaField.getText();
        typeAgentList[ident].energia = energFieldAgent.getText();
        typeAgentList[ident].carga = cargFieldAgent.getText();
        typeAgentList[ident].imagem = gridCanvas.pixels;
    }

    public void atualizaAgentes()
    {
        idLabel.setText( (new Integer( ident )).toString() );
        nomeField.setText( typeAgentList[ident].nome );
        numField.setText( typeAgentList[ident].nagentes );
        areaField.setText( typeAgentList[ident].area );
        energFieldAgent.setText( typeAgentList[ident].energia );
        cargFieldAgent.setText( typeAgentList[ident].carga );
        gridCanvas.setPixels( typeAgentList[ident].imagem );
        canvas1.alteraImagem( gridCanvas.pixels );
    }

    /**
     * *************************************************************************
     */
    public void criaOkDialog( String msg )
    {
        Panel baseOkDialog;
        Label mensagem = new Label();
        if ( okDialog == null )
        {
            baseOkDialog = new Panel();
            okDialog = new Dialog( this, true );
            okDialog.setTitle( "Atencao" );
            okDialog.setLayout( new BorderLayout() );
            baseOkDialog.setBounds( getInsets().left + 10, getInsets().top + 10, 240, 110 );
            okDialogButton = new Button( "OK" );
            baseOkDialog.setLayout( null );
            baseOkDialog.add( mensagem );
            mensagem.setBounds( 35, 20, 200, 40 );
            baseOkDialog.add( okDialogButton );
            okDialogButton.setBounds( 70, 70, 80, 25 );
            okDialogButton.addActionListener( this );
            okDialog.add( "Center", baseOkDialog );
            okDialog.addWindowListener( this );
            okDialog.pack();
        }
        mensagem.setText( msg );
        okDialog.setVisible( true );
    }

    /**
     * *************************************************************************
     */
    public void criaDialogParada()
    {
        int j = 1;
        if ( defDialogParada == null )
        {
            defDialogParada = new Dialog( this, true );
            defDialogParada.setLayout( new BorderLayout() );
            baseDialogParada = new Panel();
            baseDialogParada.setLayout( null );
            baseDialogParada.setSize( getInsets().left + getInsets().right + 500, getInsets().top + getInsets().bottom + 265 );
            varLabelDialogPar = new Label( "Variável:" );
            varLabelDialogPar.setBounds( 24, 20, 120, 20 );
            baseDialogParada.add( varLabelDialogPar );
            operLabelDialogPar = new Label( "Operador:" );
            operLabelDialogPar.setBounds( 24, 50, 120, 20 );
            baseDialogParada.add( operLabelDialogPar );
            paramLabelDialogPar = new Label( "Parâmetro Comparação:" );
            paramLabelDialogPar.setBounds( 24, 80, 150, 20 );
            baseDialogParada.add( paramLabelDialogPar );
            regraLabelDialogPar = new Label( "Critério de Parada:" );
            regraLabelDialogPar.setBounds( 24, 120, 120, 20 );
            baseDialogParada.add( regraLabelDialogPar );
            regra2LabelDialogPar = new Label();
            regra2LabelDialogPar.setBounds( 12, 150, 468, 20 );
            baseDialogParada.add( regra2LabelDialogPar );
            regraScrollDialogPar = new Scrollbar( Scrollbar.HORIZONTAL );
            regraScrollDialogPar.setBounds( 12, 180, 468, 17 );
            regraScrollDialogPar.setMinimum( 0 );
            regraScrollDialogPar.setMaximum( 0 );
            regraScrollDialogPar.setUnitIncrement( 1 );
            regraScrollDialogPar.addAdjustmentListener( this );
            baseDialogParada.add( regraScrollDialogPar );
            varButtonDialogPar = new Button( "Adicionar" );
            varButtonDialogPar.setBounds( 396, 20, 90, 20 );
            baseDialogParada.add( varButtonDialogPar );
            (varButtonDialogPar).addActionListener( this );
            operButtonDialogPar = new Button( "Adicionar" );
            operButtonDialogPar.setBounds( 396, 50, 90, 20 );
            baseDialogParada.add( operButtonDialogPar );
            (operButtonDialogPar).addActionListener( this );
            paramButtonDialogPar = new Button( "Adicionar" );
            paramButtonDialogPar.setBounds( 396, 80, 90, 20 );
            baseDialogParada.add( paramButtonDialogPar );
            (paramButtonDialogPar).addActionListener( this );
            varChoiceDialogPar = new Choice();
            baseDialogParada.add( varChoiceDialogPar );
            varChoiceDialogPar.setBounds( 156, 20, 200, 21 );
            operChoiceDialogPar = new Choice();
            operChoiceDialogPar.add( "&&" );
            operChoiceDialogPar.add( "||" );
            operChoiceDialogPar.add( "!" );
            operChoiceDialogPar.add( "<=" );
            operChoiceDialogPar.add( ">=" );
            operChoiceDialogPar.add( "!=" );
            operChoiceDialogPar.add( "=" );
            operChoiceDialogPar.add( "<" );
            operChoiceDialogPar.add( ">" );
            baseDialogParada.add( operChoiceDialogPar );
            operChoiceDialogPar.setBounds( 156, 50, 50, 21 );
            paramFieldDialogPar = new TextField();
            paramFieldDialogPar.setBounds( 180, 80, 180, 20 );
            baseDialogParada.add( paramFieldDialogPar );
            remAntButtonDialogPar = new Button( "Remover Anterior" );
            remAntButtonDialogPar.setBounds( 324, 210, 123, 26 );
            baseDialogParada.add( remAntButtonDialogPar );
            (remAntButtonDialogPar).addActionListener( this );
            iniBlocoButtonDialogPar = new Button( "Início de Bloco" );
            iniBlocoButtonDialogPar.setBounds( 36, 210, 123, 26 );
            baseDialogParada.add( iniBlocoButtonDialogPar );
            (iniBlocoButtonDialogPar).addActionListener( this );
            fimBlocoButtonDialogPar = new Button( "Fim de Bloco" );
            fimBlocoButtonDialogPar.setBounds( 180, 210, 123, 26 );
            baseDialogParada.add( fimBlocoButtonDialogPar );
            (fimBlocoButtonDialogPar).addActionListener( this );
            okButtonDialogPar = new Button( "OK" );
            okButtonDialogPar.setBounds( 100, 250, 123, 26 );
            baseDialogParada.add( okButtonDialogPar );
            (okButtonDialogPar).addActionListener( this );
            canButtonDialogPar = new Button( "Cancela" );
            canButtonDialogPar.setBounds( 280, 250, 123, 26 );
            baseDialogParada.add( canButtonDialogPar );
            (canButtonDialogPar).addActionListener( this );
            defDialogParada.add( "Center", baseDialogParada );
            defDialogParada.pack();
            defDialogParada.setTitle( "Definição do Critério de Parada" );
            defDialogParada.setResizable( false );
        }
        varChoiceDialogPar.removeAll();
        varChoiceDialogPar.add( "" );
        while ( (variaveisList[j] != null) && (!(variaveisList[j].nome.equals( "" ))) )
        {
            varChoiceDialogPar.add( variaveisList[j].nome );
            j++;
        }
        j = 1;
        paraString = critpar;
        atualizaRegra( false );
        defDialogParada.setVisible( true );
    }

    /**
     * *************************************************************************
     */
    public void atualizaJanela( Object source )
    {
        if ( source == defAgentes )
        {
            atualizaAgentes();
        }
        else if ( source == defComportamentos )
        {
            atualizaComportamentos();
        }
        else if ( source == defComportamentos )
        {
            atualizaDistribuicao();
        }
        else if ( source == defComportamentos )
        {
            atualizaVariaveis();
        }
    }

    /**
     * *************************************************************************
     */
    public void GerarCodigo()
    {
        String agente;
        boolean accond = false, primitem = true, trocou = true, acabou = false;
        int num = 1, j = 1, l, inicio, fim;
        Runtime rt = Runtime.getRuntime();
        tipoOrdenador agentes[] = new tipoOrdenador[1000];
        tipoOrdenador agentmp;
        while ( behaviorList[num] != null )
        {
            if ( !behaviorList[num].agente.equals( "" ) )
            {
                agentes[num] = new tipoOrdenador( behaviorList[num].agente,
                        new Integer( behaviorList[num].priorid ).intValue(),
                        num );
            }
            num++;
        }
        fim = 1;
        num = 2;
        while ( agentes[num] != null )
        {
            while ( agentes[num] != null )
            {
                if ( agentes[fim].nome.equals( agentes[num].nome ) )
                {
                    fim++;
                    if ( (num - fim) != 0 )
                    {
                        agentmp = agentes[num];
                        agentes[num] = agentes[fim];
                        agentes[fim] = agentmp;
                    }
                }
                num++;
            }
            fim++;
            num = fim + 1;
        }
        num = 1;
        inicio = 1;
        while ( agentes[inicio] != null )
        {
            while ( (agentes[num] != null)
                    && (agentes[num].nome.equals( agentes[inicio].nome )) )
            {
                num++;
            }
            fim = num - 1;
            trocou = true;
            while ( trocou )
            {
                trocou = false;
                for ( int i = inicio; i < fim; i++ )
                {
                    if ( agentes[i].prior > agentes[i + 1].prior )
                    {
                        agentmp = agentes[i];
                        agentes[i] = agentes[i + 1];
                        agentes[i + 1] = agentmp;
                        trocou = true;
                    }
                }
            }
            inicio = fim + 1;
        }
        try
        {
            int aux = 1, maxagt = 1, maxdst = 1, vb = 0, vi = 0, vc = 0;
            j = 1;
            FileOutputStream f = new FileOutputStream( "Codigos.java" );
//            FileOutputStream f = new FileOutputStream( "./src/Codigos.java" );
//            f.write( ("import src.*; \r\n").getBytes() );
            f.write( ("import java.awt.*; \r\n").getBytes() );
            f.write( ("import java.awt.image.*; \r\n\r\n").getBytes() );
            f.write( ("class Codigos { \r\n").getBytes() );

            f.write( ("  public static int colunas=25, linhas=25;\r\n").getBytes() );
            while ( SIMULA.typeAgentList[aux] != null )
            {
                aux++;
            }
            maxagt = aux - 1;
            aux = 1;
            while ( SIMULA.distList[aux] != null )
            {
                aux++;
            }
            maxdst = aux - 1;
            f.write( ("  public static tipoAgente[] agentes=new tipoAgente[1000];\r\n").getBytes() );
            f.write( ("  public static Dist[] distribuicao=new Dist[625];\r\n").getBytes() );
            f.write( ("  public static boolean[] vetorBoolean = new boolean[1000];\r\n").getBytes() );
            f.write( ("  public static int[] vetorInt = new int[1000], vetorQtd = new int[1000];\r\n").getBytes() );
            f.write( ("  public static char[] vetorChar = new char[1000];\r\n").getBytes() );
            f.write( ("  public static ItemVariable[] indice = new ItemVariable[1000];\r\n").getBytes() );
            aux = 1;
            f.write( ("\r\n  public static void inicializaTipos() {\r\n").getBytes() );
            for ( int i = 0; i < maxagt; i++ )
            {
                if ( !typeAgentList[i + 1].nome.equals( "" ) )
                {
                    f.write( ("    int[] img" + (i + 1) + "={").getBytes() );
                    for ( int k = 0; k < 255; k++ )
                    {
                        f.write( ((new Integer( typeAgentList[i + 1].imagem[k] )).toString() + ",").getBytes() );
                    }
                    f.write( (Integer.toString(SIMULA.typeAgentList[i + 1].imagem[255]) + "};\r\n").getBytes() );
                }
            }
            for ( int i = 0; i < maxagt; i++ )
            {
                if ( !typeAgentList[i + 1].nome.equals( "" ) )
                {
                    f.write( ("    agentes[" + (i + 1) + "]=new tipoAgente(\"" + i + "\", \"" + SIMULA.typeAgentList[i + 1].nome + "\", \"" + SIMULA.typeAgentList[i + 1].area
                            + "\", \"" + SIMULA.typeAgentList[i + 1].energia + "\", \"" + SIMULA.typeAgentList[i + 1].carga + "\", \"" + SIMULA.typeAgentList[i + 1].nagentes + "\", img" + (i + 1) + ");\r\n").getBytes() );
                }
            }
            for ( int i = 0; i < maxdst; i++ )
            {
                if ( SIMULA.distList[(i + 1)] != null )
                {
                    if ( !SIMULA.distList[(i + 1)].nome.equals( "" ) )
                    {
                        f.write( ("    distribuicao[" + i + "]=new Dist(\"" + distList[i + 1].nome + "\", \""
                                + distList[i + 1].numero + "\", \"" + ((new Integer( distList[i + 1].coluna )) - 1) + "\", \"" + ((new Integer( SIMULA.distList[i + 1].linha )) - 1) + "\");\r\n").getBytes() );
                    }
                }
            }
            while ( SIMULA.variaveisList[j] != null )
            {
                if ( !SIMULA.variaveisList[j].nome.equals( "" ) )
                {
                    if ( SIMULA.variaveisList[j].tipo.equals( "boolean" ) )
                    {
                        f.write( ("    indice[" + j + "] = new ItemVariable(\"" + SIMULA.variaveisList[j].nome + "\", \""
                                + "\"boolean\", " + vb + ");\r\n").getBytes() );
                        if ( !SIMULA.variaveisList[j].valor.equals( "" ) )
                        {
                            if ( SIMULA.variaveisList[j].valor.equals( "true" ) )
                            {
                                f.write( ("    vetorBoolean[" + vb + "]=true;\r\n").getBytes() );
                            }
                            else
                            {
                                f.write( ("    vetorBoolean[" + vb + "]=false;\r\n").getBytes() );
                            }
                        }
                        vb++;
                    }
                    else if ( SIMULA.variaveisList[j].tipo.equals( "int" ) )
                    {
                        f.write( ("    indice[" + j + "] = new ItemVariable(\"" + SIMULA.variaveisList[j].nome + "\", \"int\", "
                                + vi + ");\r\n").getBytes() );
                        if ( !SIMULA.variaveisList[j].valor.equals( "" ) )
                        {
                            f.write( ("    vetorInt[" + vi + "]=" + SIMULA.variaveisList[j].valor + ";\r\n").getBytes() );
                        }
                        vi++;
                    }
                    else if ( SIMULA.variaveisList[j].tipo.equals( "char" ) )
                    {
                        f.write( ("    indice[" + j + "] = new ItemVariable(\"" + SIMULA.variaveisList[j].nome + "\", \""
                                + "\"char\", " + vc + ");\r\n").getBytes() );
                        if ( !SIMULA.variaveisList[j].valor.equals( "" ) )
                        {
                            f.write( ("    vetorChar[" + vc + "]=" + SIMULA.variaveisList[j].valor.charAt( 0 ) + ";\r\n").getBytes() );
                        }
                        vc++;
                    }
                }
                j++;
            }
            f.write( ("    colunas=" + colunas + ";\r\n").getBytes() );
            f.write( ("    linhas=" + linhas + "; }\r\n\r\n").getBytes() );
            f.write( ("  public static void acoesAgentes(Agente agente) { \r\n").getBytes() );
            j = 1;
            while ( agentes[j] != null )
            {
                agente = agentes[j].nome;
                if ( !(agente.equals( "" )) && !(behaviorList[(agentes[j].pos)].precond.equals( "" )) )
                {
                    if ( primitem )
                    {
                        f.write( ("    ").getBytes() );
                        primitem = false;
                    }
                    else
                    {
                        f.write( ("\r\n    else ").getBytes() );
                    }
                    f.write( ("if(agente.nome.equals(\"" + agente + "\")) { \r\n").getBytes() );
                    f.write( ("      if(" + montaInstrucao( behaviorList[(agentes[j].pos)].precond, false ) + ") { \r\n").getBytes() );
                    if ( !(behaviorList[j].accond.equals( "" )) )
                    {
                        f.write( ("\r\n        if(" + montaInstrucao( behaviorList[(agentes[j].pos)].accond, false ) + ") { \r\n").getBytes() );
                        accond = true;
                    }
                    if ( !(behaviorList[(agentes[j].pos)].acativ.equals( "" )) )
                    {
                        f.write( ("        " + montaInstrucao( behaviorList[(agentes[j].pos)].acativ, true ) + ";").getBytes() );
                    }
                    if ( !(behaviorList[(agentes[j].pos)].poscond.equals( "" )) )
                    {
                        f.write( ("\r\n        " + montaInstrucao( behaviorList[(agentes[j].pos)].poscond, true ) + ";").getBytes() );
                    }
                    if ( accond )
                    {
                        f.write( (" }").getBytes() );
                        accond = false;
                    }
                    f.write( (" }").getBytes() );
                    j++;
                    acabou = false;
                    while ( (agentes[j] != null) && (!acabou) )
                    {
                        if ( agentes[j].nome.equals( agente ) && !(behaviorList[(agentes[j].pos)].precond.equals( "" )) )
                        {
                            f.write( ("\r\n      else if(" + montaInstrucao( behaviorList[(agentes[j].pos)].precond, false ) + ") { \r\n").getBytes() );
                            if ( !(behaviorList[(agentes[j].pos)].accond.equals( "" )) )
                            {
                                f.write( ("        if(" + montaInstrucao( behaviorList[(agentes[j].pos)].accond, false ) + ") { \r\n").getBytes() );
                                accond = true;
                            }
                            f.write( ("        " + montaInstrucao( behaviorList[(agentes[j].pos)].acativ, true ) + ";").getBytes() );
                            if ( !(behaviorList[(agentes[j].pos)].poscond.equals( "" )) )
                            {
                                f.write( ("\r\n        " + montaInstrucao( behaviorList[(agentes[j].pos)].poscond, true ) + ";").getBytes() );
                            }
                            if ( accond )
                            {
                                f.write( (" }").getBytes() );
                                accond = false;
                            }
                            f.write( (" }").getBytes() );
                            j++;
                        }
                        else
                        {
                            acabou = true;
                        }
                    }
                }
                f.write( (" }").getBytes() );
            }
            f.write( (" } \r\n\r\n").getBytes() );
            f.write( ("  public static boolean atingiuCondicaoParada() {\r\n").getBytes() );
            if ( !(critpar.equals( "" )) )
            {
                f.write( ("    if(" + montaInstrucao( critpar, false ) + ") { \r\n").getBytes() );
                f.write( ("      return true; }\r\n").getBytes() );
                f.write( ("    else { \r\n").getBytes() );
                f.write( ("      return false; } }").getBytes() );
            }
            else
            {
                f.write( ("    return false; }").getBytes() );
            }
            f.write( (" }\r\n").getBytes() );
            f.close();
            
            Process compila = rt.exec( "javac Codigos.java" );
            compila.waitFor();
            int exitValue = compila.exitValue();
            
            if ( exitValue == 0 ) 
            {
                JOptionPane.showMessageDialog( this, "Código gerado!", "Sucesso", JOptionPane.INFORMATION_MESSAGE);
            } 
            else
            {
                JOptionPane.showMessageDialog( this, "Ocorreu um erro na criação do código, verifique os dados.", "Erro!", JOptionPane.ERROR_MESSAGE);
            }
        }
        catch ( Exception e )
        {
            e.printStackTrace();
        }
    }

    public void adicionaComponente( int cod, String param )
    {
        vetorIndex[index] = new Indaux( cod, param );
        index++;
    }

    public void adicionaOperador( int op, int pos )
    {
        if ( !printstr.equals( "" ) )
        {
            adicionaComponente( -1, printstr );
            printstr = "";
        }
        if ( op == 0 )
        {
            adicionaComponente( 0, "(" );
        }
        else if ( op == 1 )
        {
            adicionaComponente( 1, ")" );
        }
        else if ( op == 3 )
        {
        }
        else if ( condicao.charAt( pos ) == variaveis.charAt( 7 ) )
        {
            if ( op == 4 )
            {
                adicionaComponente( 5, "<=" );
                posicao++;
            }
            else if ( op == 5 )
            {
                adicionaComponente( 5, ">=" );
                posicao++;
            }
            else if ( op == 6 )
            {
                adicionaComponente( 5, "!=" );
                posicao++;
            }
            else
            {
                adicionaComponente( 5, "=" );
            }
        }
        else if ( condicao.charAt( pos ) == variaveis.charAt( 8 ) )
        {
            if ( acao )
            {
                adicionaComponente( 5, ";" );
            }
            else
            {
                adicionaComponente( 5, "&&" );
            }
            posicao++;
        }
        else if ( condicao.charAt( pos ) == variaveis.charAt( 9 ) )
        {
            adicionaComponente( 5, "||" );
            posicao++;
        }
        else
        {
            adicionaComponente( 5, (new Character( variaveis.charAt( op ) )).toString() );
        }
    }

    public int retornaCompUltPalavra( String entrada )
    {
        String palavra;
        int aux = 1;
        criaVetorIndex( entrada );
        while ( vetorIndex[aux] != null )
        {
            aux++;
        }
        palavra = vetorIndex[(aux - 1)].param;
        return palavra.length();
    }

    public boolean testaEntrada( String entrada, boolean ac )
    {
        boolean aceita = true;
        int num = 1, parenteses = 0;
        entrada = montaInstrucao( entrada, ac );
        if ( vetorIndex[1] != null )
        {
            if ( vetorIndex[1].cod == -1 )
            {
                return false;
            }
        }
        else
        {
            return false;
        }
        if ( ac )
        {
            if ( vetorIndex[1].cod == 0 )
            {
                return false;
            }
            while ( vetorIndex[num] != null )
            {
                if ( vetorIndex[num].cod == 5 && vetorIndex[num].param.equals( "||" ) )
                {
                    return false;
                }
                num++;
            }
            num = 1;
        }
        while ( vetorIndex[num] != null )
        {
            if ( vetorIndex[num].cod == 0 )
            {
                parenteses++;
            }
            else if ( (vetorIndex[num].cod == 5) && (vetorIndex[num].param.equals( "||" )) && (ac) )
            {
                return false;
            }
            else if ( vetorIndex[num].cod == 1 )
            {
                parenteses--;
            }
            else if ( vetorIndex[num].cod == 2 )
            {
                if ( vetorIndex[num + 1] != null )
                {
                    if ( (vetorIndex[num + 1].cod > 1) && (vetorIndex[num + 1].cod != 3) )
                    {
                        return false;
                    }
                }
            }
            num++;
        }
        num--;
        if ( vetorIndex[num].cod == 5 )
        {
            return false;
        }
        if ( parenteses == 0 )
        {
            return aceita;
        }
        else
        {
            return false;
        }
    }

    public void criaVetorIndex( String cond )
    {
        int aux;
        boolean achou = false;
        condicao = cond;
        posicao = 0;
        index = 1;
        while ( vetorIndex[index] != null )
        {
            vetorIndex[index] = null;
            index++;
        }
        index = 1;
        printstr = "";
        aux = condicao.length() - 1;
        if ( !condicao.equals( "" ) )
        {
            while ( posicao <= aux )
            {
                achou = false;
                for ( int i = 0; i < 15; i++ )
                {
                    if ( condicao.charAt( posicao ) == variaveis.charAt( i ) )
                    {
                        adicionaOperador( i, posicao );
                        achou = true;
                        break;
                    }
                }
                if ( !achou )
                {
                    printstr = printstr + condicao.charAt( posicao );
                }
                posicao++;
            }
            if ( !achou )
            {
                adicionaComponente( -1, printstr );
            }
        }
    }

    public String montaInstrucao( String inst, boolean ac )
    {
        int num;
        String retorno = "";
        acao = ac;
        if ( inst.equals( "" ) )
        {
            return retorno;
        }
        criaVetorIndex( inst );
        adicionaCodigos( ac );
        defineVariaveis();
        num = 1;
        while ( vetorIndex[num] != null )
        {
            if ( vetorIndex[num].cod == 0 )
            {
                retorno = retorno + vetorIndex[num].param;
            }
            else if ( vetorIndex[num].cod == -1 )
            {
                retorno = retorno + vetorIndex[num].param;
            }
            else if ( vetorIndex[num].cod == 1 )
            {
                retorno = retorno + vetorIndex[num].param;
            }
            else if ( vetorIndex[num].cod == 2 )
            {
                retorno = retorno + "Metodos." + vetorIndex[num].param;
                num++;
            }
            else if ( vetorIndex[num].cod == 3 )
            {
                retorno = retorno + "\"" + vetorIndex[num].param + "\"";
            }
            else if ( vetorIndex[num].cod == 4 )
            {
                retorno = retorno + calculaLocalVar( vetorIndex[num].param );
            }
            else if ( vetorIndex[num].cod == 5 )
            {
                if ( vetorIndex[num].param.equals( "," ) )
                {
                    retorno = retorno + ", ";
                }
                else if ( vetorIndex[num].param.equals( ";" ) )
                {
                    retorno = retorno + ";\r\n        ";
                }
                else if ( vetorIndex[num].param.equals( "=" ) && !ac && !vetorIndex[num - 1].param.equals( "!" ) && !vetorIndex[num + 1].param.equals( "=" ) )
                {
                    retorno = retorno + "==";
                }
                else
                {
                    retorno = retorno + vetorIndex[num].param;
                }
            }
            else if ( vetorIndex[num].cod == 6 )
            {
                retorno = retorno + vetorIndex[num].param;
            }
            else if ( vetorIndex[num].cod == 7 )
            {
                retorno = retorno + vetorIndex[num].param;
            }
            else if ( vetorIndex[num].cod == 8 )
            {
                retorno = retorno + "\"" + vetorIndex[num].param + "\"";
            }
            num++;
        }
        return retorno;
    }

    public void adicionaCodigos( boolean ac )
    {
        int num = 1, aux;
        boolean achou;
        while ( vetorIndex[num] != null )
        {
            achou = false;
            if ( (vetorIndex[num].cod == -1) )
            {
                aux = 1;
                while ( (variaveisList[aux] != null) && (!achou) )
                {
                    if ( variaveisList[aux].nome.equals( vetorIndex[num].param ) )
                    {
                        vetorIndex[num].cod = 4;
                        achou = true;
                    }
                    aux++;
                }
                if ( !achou )
                {
                    aux = 1;
                    while ( (typeAgentList[aux] != null) && (!achou) )
                    {
                        if ( vetorIndex[num].param.equals( typeAgentList[aux].nome ) )
                        {
                            vetorIndex[num].cod = 3;
                            achou = true;
                        }
                        aux++;
                    }
                }
                if ( !achou )
                {
                    String retorno = retornaComportamento( vetorIndex[num].param );
                    if ( !retorno.equals( "" ) )
                    {
                        vetorIndex[num].cod = 2;
                        vetorIndex[num].param = retorno;
                        achou = true;
                    }
                }
                if ( !achou )
                {
                    if ( vetorIndex[num].param.equals( "sucesso" ) )
                    {
                        if ( !ac )
                        {
                            vetorIndex[num].param = "agente.sucesso";
                        }
                        else
                        {
                            vetorIndex[num].param = "Executar.agentes[agente.ncriacao].sucesso";
                        }
                        vetorIndex[num].cod = 6;
                        achou = true;
                    }
                }
                if ( !achou )
                {
                    if ( vetorIndex[num].param.equals( "energia" ) )
                    {
                        if ( !ac )
                        {
                            vetorIndex[num].param = "agente.energia";
                        }
                        else
                        {
                            vetorIndex[num].param = "Executar.agentes[agente.ncriacao].energia";
                        }
                        vetorIndex[num].cod = 6;
                        achou = true;
                    }
                }
                if ( !achou )
                {
                    if ( vetorIndex[num].param.equals( "carga" ) )
                    {
                        if ( !ac )
                        {
                            vetorIndex[num].param = "agente.carga";
                        }
                        else
                        {
                            vetorIndex[num].param = "Executar.agentes[agente.ncriacao].carga";
                        }
                        vetorIndex[num].cod = 6;
                        achou = true;
                    }
                }
                if ( !achou )
                {
                    if ( vetorIndex[num - 2] != null )
                    {
                        if ( vetorIndex[num - 2].cod == 4 )
                        {
                            aux = 1;
                            while ( (variaveisList[aux] != null) && (!achou) )
                            {
                                if ( variaveisList[aux].nome.equals( vetorIndex[num - 2].param ) )
                                {
                                    if ( variaveisList[aux].tipo.equals( "int" ) )
                                    {
                                        vetorIndex[num].cod = 7;
                                    }
                                    else if ( variaveisList[aux].tipo.equals( "string" ) )
                                    {
                                        vetorIndex[num].cod = 8;
                                    }
                                    achou = true;
                                }
                                aux++;
                            }
                        }
                    }
                    if ( (!achou) && (vetorIndex[num - 2].param.equals( "movimentaPara(agente, " )) )
                    {
                        if ( vetorIndex[num].param.equals( "N" ) )
                        {
                            vetorIndex[num].param = "1";
                        }
                        else if ( vetorIndex[num].param.equals( "S" ) )
                        {
                            vetorIndex[num].param = "2";
                        }
                        else if ( vetorIndex[num].param.equals( "L" ) )
                        {
                            vetorIndex[num].param = "4";
                        }
                        else if ( vetorIndex[num].param.equals( "O" ) )
                        {
                            vetorIndex[num].param = "3";
                        }
                        else if ( vetorIndex[num].param.equals( "NO" ) )
                        {
                            vetorIndex[num].param = "5";
                        }
                        else if ( vetorIndex[num].param.equals( "NE" ) )
                        {
                            vetorIndex[num].param = "6";
                        }
                        else if ( vetorIndex[num].param.equals( "SO" ) )
                        {
                            vetorIndex[num].param = "7";
                        }
                        else
                        {
                            vetorIndex[num].param = "8";
                        }
                        vetorIndex[num].cod = 7;
                        achou = true;
                    }
                }
                if ( !achou )
                {
                    vetorIndex[num].cod = 6;
                    achou = true;
                }
            }
            num++;
        }
    }

    public String retornaComportamento( String nome )
    {
        if ( nome.equals( "movimento_randomico" ) )
        {
            return ("movimentoRandomico(agente");
        }
        else if ( nome.equals( "movimento_direcionado" ) )
        {
            return ("movimentaPara(agente, ");
        }
        else if ( nome.equals( "deixa_pista" ) )
        {
            return ("deixaPista(agente");
        }
        else if ( nome.equals( "segue_pista" ) )
        {
            return ("seguePista(agente, agente.nome");
        }
        else if ( nome.equals( "segue_maior_gradiente" ) )
        {
            return ("segueMaiorGradiente(agente, ");
        }
        else if ( nome.equals( "segue_menor_gradiente" ) )
        {
            return ("segueMenorGradiente(agente, ");
        }
        else if ( nome.equals( "segue_agente" ) )
        {
            return ("segueAgente(agente, ");
        }
        else if ( nome.equals( "foge_de_agente" ) )
        {
            return ("fogeDeAgente(agente, ");
        }
        else if ( nome.equals( "morte" ) )
        {
            return ("morteDeAgente(agente");
        }
        else if ( nome.equals( "mata_agente" ) )
        {
            return ("mataAgente(agente, ");
        }
        else if ( nome.equals( "reproduz" ) )
        {
            return ("reproduzAgente(agente, ");
        }
        else if ( nome.equals( "transforma" ) )
        {
            return ("transformaAgente(agente, ");
        }
        else if ( nome.equals( "evita_obstaculo" ) )
        {
            return ("evitaObstaculo(agente");
        }
        else if ( nome.equals( "percebe_agente" ) )
        {
            return ("percebeAgente(agente, ");
        }
        else if ( nome.equals( "percebe_pista" ) )
        {
            return ("percebePista(agente, agente.nome");
        }
        else if ( nome.equals( "atinge_agente" ) )
        {
            return ("atingeAgente(agente, ");
        }
        else if ( nome.equals( "atinge_pista" ) )
        {
            return ("atingePista(agente");
        }
        else if ( nome.equals( "remove_pista" ) )
        {
            return ("removePista(agente");
        }
        else if ( nome.equals( "atinge_tempo_de_vida" ) )
        {
            return ("atingeTempoVida(agente, ");
        }
        else if ( nome.equals( "tempo_de_fertilidade" ) )
        {
            return ("tempoFertilidade(agente, ");
        }
        else if ( nome.equals( "atinge_vida_adulta" ) )
        {
            return ("atingeVidaAdulta(agente, ");
        }
        else if ( nome.equals( "tipo_sexo" ) )
        {
            return ("tipoSexo(agente, ");
        }
        else if ( nome.equals( "taxa_de_sucesso" ) )
        {
            return ("taxaSucesso(agente, ");
        }
        return ("");
    }

    public String calculaLocalVar( String var )
    {
        int num = 1;
        while ( indice[num] != null )
        {
            if ( indice[num].nome.equals( var ) )
            {
                if ( indice[num].tipo.equals( "int" ) )
                {
                    return ("vetorInt[" + (num - 1) + "]");
                }
                if ( indice[num].tipo.equals( "boolean" ) )
                {
                    return ("vetorBoolean[" + (num - 1) + "]");
                }
                else
                {
                    return ("vetorChar[" + (num - 1) + "]");
                }
            }
            num++;
        }
        return ("");
    }

    public void defineVariaveis()
    {
        int j = 1, vb = 0, vi = 0, vc = 0;
        while ( SIMULA.variaveisList[j] != null )
        {
            if ( !SIMULA.variaveisList[j].nome.equals( "" ) )
            {
                if ( SIMULA.variaveisList[j].tipo.equals( "boolean" ) )
                {
                    indice[j] = new ItemVariable( SIMULA.variaveisList[j].nome, "boolean", vb );
                    vb++;
                }
                else if ( SIMULA.variaveisList[j].tipo.equals( "int" ) )
                {
                    indice[j] = new ItemVariable( SIMULA.variaveisList[j].nome, "int", vi );
                    vi++;
                }
                else if ( SIMULA.variaveisList[j].tipo.equals( "char" ) )
                {
                    indice[j] = new ItemVariable( SIMULA.variaveisList[j].nome, "char", vc );
                    vc++;
                }
            }
            j++;
        }
    }

    /**
     * *************************************************************************
     * @param source
     */
    public void salvaJanela( Object source )
    {
        if ( source == defAgentes )
        {
            salvaAgentes();
        }
        else if ( source == defComportamentos )
        {
            salvaComportamentos();
        }
        else if ( source == defComportamentos )
        {
            salvaDistribuicao();
        }
        else if ( source == defComportamentos )
        {
            salvaVariaveis();
        }
    }

    public void fechaJanela( Object source )
    {
        if ( source == this )
        {
            dispose();
            System.exit( 0 );
        }
    }

    @Override
    public void windowActivated( WindowEvent evt )
    {
        atualizaJanela( evt.getSource() );
    }

    @Override
    public void windowDeactivated( WindowEvent evt )
    {
        salvaJanela( evt.getSource() );
    }

    @Override
    public void windowOpened( WindowEvent evt )
    {
        atualizaJanela( evt.getSource() );
    }

    @Override
    public void windowClosing( WindowEvent evt )
    {
        fechaJanela( evt.getSource() );
    }

    @Override
    public void windowClosed( WindowEvent evt )
    {
    }

    @Override
    public void windowIconified( WindowEvent evt )
    {
    }

    @Override
    public void windowDeiconified( WindowEvent evt )
    {
    }

    @Override
    public void mouseClicked( MouseEvent e )
    {
    }

    @Override
   public void mouseEntered( MouseEvent e )
    {
    }

    @Override
    public void mouseExited( MouseEvent e )
    {
    }

    @Override
    public void mouseReleased( MouseEvent e )
    {
    }

    @Override
    public void mousePressed( MouseEvent evt )
    {
        gridCanvas.selecionaCor( cor, evt.getX(), evt.getY() );
        canvas1.alteraImagem( gridCanvas.pixels );
    }

    @Override
    public void keyPressed( KeyEvent evt )
    {
    }

    @Override
    public void keyReleased( KeyEvent evt )
    {
    }

    @Override
    public void keyTyped( KeyEvent evt )
    {
        char c = evt.getKeyChar();
        if ( !Character.isDigit( c ) )
        {
            evt.consume();
        }
    }

    @Override
    public void adjustmentValueChanged( AdjustmentEvent evt )
    {
        Object source = evt.getSource();
        if ( source == regraScrollDialogComp )
        {
            atualizaRegra( true );
        }
        else
        {
            atualizaRegra( false );
        }
    }

    @Override
    public void itemStateChanged( ItemEvent evt )
    {
        int item = corChoice.getSelectedIndex();
        if ( item == 0 )
        {
            cor = Color.white;
        }
        else if ( item == 1 )
        {
            cor = Color.yellow;
        }
        else if ( item == 2 )
        {
            cor = Color.blue;
        }
        else if ( item == 3 )
        {
            cor = Color.green;
        }
        else if ( item == 4 )
        {
            cor = Color.orange;
        }
        else if ( item == 5 )
        {
            cor = Color.magenta;
        }
        else if ( item == 6 )
        {
            cor = Color.red;
        }
        else if ( item == 7 )
        {
            cor = Color.pink;
        }
        else if ( item == 8 )
        {
            cor = Color.cyan;
        }
        else if ( item == 9 )
        {
            cor = Color.gray;
        }
        else if ( item == 10 )
        {
            cor = Color.black;
        }
    }

    public static void main( String args[] )
    {
        SIMULA simula = new SIMULA();
    }
}

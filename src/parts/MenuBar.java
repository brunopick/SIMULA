
package parts;

import java.awt.event.ActionListener;
import javax.swing.JMenu;
import javax.swing.JMenuBar;
import javax.swing.JMenuItem;


public class MenuBar extends JMenuBar {
    
    private ActionListener listener;

    public static final String AC_ARQUIVO_NOVO = "ARQUIVO_NOVO";
    public static final String AC_ARQUIVO_ABRIR = "ARQUIVO_ABRIR";
    public static final String AC_ARQUIVO_SALVAR = "ARQUIVO_SALVAR";
    public static final String AC_ARQUIVO_SALVAR_COMO = "ARQUIVO_SALVAR_COMO";
    public static final String AC_ARQUIVO_SAIR = "ARQUIVO_SAIR";
    
    public static final String AC_CONFIGURACOES_DEFINICAO_AGENTES = "CONFIGURACOES_DEFINICAO_AGENTES";
    public static final String AC_CONFIGURACOES_DEFINICAO_VARIAVEIS = "CONFIGURACOES_DEFINICAO_VARIAVEIS";
    public static final String AC_CONFIGURACOES_REGRAS_COMPORTAMENTO = "CONFIGURACOES_REGRAS_COMPORTAMENTO";
    public static final String AC_CONFIGURACOES_CRITERIO_PARADA = "CONFIGURACOES_CRITERIO_PARADA";
    public static final String AC_CONFIGURACOES_DISTRIBUICAO_AGENTES = "CONFIGURACOES_DISTRIBUICAO_AGENTES";
    
    public static final String AC_EXECUTAR_GERAR_CODIGO = "EXECUTAR_GERAR_CODIGO";
    public static final String AC_EXECUTAR_EXECUCAO = "EXECUTAR_EXECUCAO";
    
    public static final String AC_AJUDA_AJUDA = "AJUDA_AJUDA";
    public static final String AC_AJUDA_SOBRE = "AJUDA_SOBRE";
        
    public MenuBar( ActionListener listener )
    {
        this.listener = listener;
        initComponents();
    }

    public void refreshContent()
    {
    }

    private void initComponents()
    {
        arquivoMenu = new JMenu( "Arquivo" );
        arquivoNovo.setText("Novo");
        arquivoNovo.setActionCommand( AC_ARQUIVO_NOVO );
        arquivoAbrir.setText("Abrir");
        arquivoAbrir.setActionCommand( AC_ARQUIVO_ABRIR );
        arquivoSalvar.setText("Salvar");
        arquivoSalvar.setActionCommand( AC_ARQUIVO_SALVAR );
        arquivoSalvarComo.setText("Salvar Como");
        arquivoSalvarComo.setActionCommand( AC_ARQUIVO_SALVAR_COMO );
        arquivoSair.setText("Sair");
        arquivoSair.setActionCommand( AC_ARQUIVO_SAIR );
        arquivoMenu.add( arquivoNovo ).addActionListener(listener);
        arquivoMenu.add( arquivoAbrir ).addActionListener(listener);
        arquivoMenu.add( arquivoSalvar ).addActionListener(listener);
        arquivoMenu.add( arquivoSalvarComo ).addActionListener(listener);
        arquivoMenu.addSeparator();
        arquivoMenu.add( arquivoSair ).addActionListener(listener);
        this.add(arquivoMenu );
    
        configuracoesMenu = new JMenu( "Configurações" );
        configuracoesDefinicaoAgentes.setText( "Definição de Agentes" );
        configuracoesDefinicaoAgentes.setActionCommand( AC_CONFIGURACOES_DEFINICAO_AGENTES );
        configuracoesDefinicaoVariaveis.setText( "Definição de Variáveis" );
        configuracoesDefinicaoVariaveis.setActionCommand( AC_CONFIGURACOES_DEFINICAO_VARIAVEIS );
        configuracoesRegrasComportamento.setText( "Regras de Comportamento" );
        configuracoesRegrasComportamento.setActionCommand( AC_CONFIGURACOES_REGRAS_COMPORTAMENTO );
        configuracoesCriterioParada.setText( "Critério de Parada" );
        configuracoesCriterioParada.setActionCommand( AC_CONFIGURACOES_CRITERIO_PARADA );
        configuracoesDistribuicaoAgentes.setText( "Distribuição de Agentes" );
        configuracoesDistribuicaoAgentes.setActionCommand( AC_CONFIGURACOES_DISTRIBUICAO_AGENTES );
        configuracoesMenu.add( configuracoesDefinicaoAgentes ).addActionListener(listener);
        configuracoesMenu.add( configuracoesDefinicaoVariaveis ).addActionListener(listener);
        configuracoesMenu.add( configuracoesRegrasComportamento ).addActionListener(listener);
        configuracoesMenu.add( configuracoesCriterioParada ).addActionListener(listener);
        configuracoesMenu.add( configuracoesDistribuicaoAgentes ).addActionListener(listener);
        this.add(configuracoesMenu );
        
        executarMenu = new JMenu( "Executar" );
        executarGerarCodigo.setText( "Gerar Código" );
        executarExecucao.setText( "Execução" );
        executarMenu.add( executarGerarCodigo ).addActionListener(listener);
        executarMenu.setActionCommand( AC_EXECUTAR_GERAR_CODIGO );
        executarMenu.add( executarExecucao ).addActionListener(listener);
        executarMenu.setActionCommand( AC_EXECUTAR_EXECUCAO );
        this.add( executarMenu );
        
        ajudaMenu = new JMenu( "Ajuda" );
        ajudaAjuda.setText( "Ajuda" );
        ajudaAjuda.setActionCommand( AC_AJUDA_AJUDA );
        ajudaSobre.setText( "Sobre" );
        ajudaSobre.setActionCommand( AC_AJUDA_SOBRE );
        ajudaMenu.add( ajudaAjuda ).addActionListener(listener);
        ajudaMenu.add( ajudaSobre ).addActionListener(listener);
        this.add( ajudaMenu );
    }

    private JMenu arquivoMenu = new JMenu();
    private JMenu configuracoesMenu = new JMenu();
    private JMenu executarMenu = new JMenu();
    private JMenu ajudaMenu = new JMenu();
    
    private JMenuItem arquivoNovo = new JMenuItem(); 
    private JMenuItem arquivoAbrir = new JMenuItem(); 
    private JMenuItem arquivoSalvar = new JMenuItem(); 
    private JMenuItem arquivoSalvarComo = new JMenuItem(); 
    private JMenuItem arquivoSair= new JMenuItem();

    private JMenuItem configuracoesDefinicaoAgentes = new JMenuItem(); 
    private JMenuItem configuracoesDefinicaoVariaveis = new JMenuItem(); 
    private JMenuItem configuracoesRegrasComportamento = new JMenuItem();
    private JMenuItem configuracoesCriterioParada = new JMenuItem(); 
    private JMenuItem configuracoesDistribuicaoAgentes = new JMenuItem();

    private JMenuItem executarGerarCodigo = new JMenuItem(); 
    private JMenuItem executarExecucao = new JMenuItem(); 
    
    private JMenuItem ajudaAjuda = new JMenuItem();
    private JMenuItem ajudaSobre = new JMenuItem();
}
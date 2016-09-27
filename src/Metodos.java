import java.awt.*;
import java.awt.image.*;
import utils.Logger;

public class Metodos
{

    public static final Posicao indeferido = new Posicao( -1, -1 );
    public static final int direcoes[] =
    {
        1, 5, 6, 3, 4, 7, 8, 2, 2, 7, 8, 3, 4, 5, 6, 1,
        3, 5, 7, 1, 2, 6, 8, 4, 4, 6, 8, 1, 2, 5, 7, 3, 5, 1, 3, 6, 7, 4, 2, 8,
        6, 1, 4, 5, 8, 3, 2, 7, 7, 3, 2, 5, 8, 1, 4, 6, 8, 2, 4, 7, 6, 3, 1, 5
    };

    public static Posicao movimenta( Agente agente, int direcao )
    {
        Posicao pos = new Posicao( agente.coluna, agente.linha );
        pos = retornaPosicao( pos, direcao );
        if ( pos != indeferido )
        {
            if ( posicaoVaga( pos ) )
            {
                return pos;
            }
        }
        return indeferido;
    }

    public static boolean posicaoVaga( Posicao pos )
    {
        if( (Executar.ambiente.agentes[pos.x][pos.y] == -1) && (Executar.ambiente.pistas[pos.x][pos.y] == -1) ) {
            return true;
        }
        return false;
    }

    public static void atualizaPosicao( Agente agente, Posicao pos )
    {
        Executar.ambiente.agentes[agente.coluna][agente.linha] = -1;
        Executar.agentes[agente.ncriacao].coluna = pos.x;
        Executar.agentes[agente.ncriacao].linha = pos.y;
        Executar.ambiente.agentes[pos.x][pos.y] = agente.ncriacao;
    }

    public static void movimentoRandomico( Agente agente )
    {
        int direcao, mem=0;
        do {
            direcao = (int)(Math.random() * 10); 
        } while( (direcao<1) || (direcao>8) );
        
        if(mem==direcao){
            do {
                direcao = (int)(Math.random() * 10); 
            } while( (direcao<1) || (direcao>8) ); 
        }
        mem=direcao;  
        Logger.log(agente.nome+" executa movimento randômico");
        movimentaPara( agente, direcao );
    }

    public static void movimentaPara( Agente agente, int direcao, int posicoes )
    {
        for ( int i = 0; i < posicoes; i++ )
        {
            movimentaPara( agente, direcao );
        }
    }

    public static void movimentaPara( Agente agente, int direcao )
    {
        Posicao posicao;
        int inicio = (direcao - 1) * 8;
        for ( int i = 0; i < 8; i++ )
        {
            posicao = movimenta( agente, direcoes[inicio + i] );
            if ( posicao != indeferido )
            {
                atualizaPosicao( agente, posicao );
                break;
            }
        }
    }

    public static void movimentaPara( Agente agente, Posicao pos )
    {
        int direcao = calculaDirecao( agente, pos );
        movimentaPara( agente, direcao );
    }

    public static void deixaPista( Agente agente )
    {
        int num = 1;
        while ( !(Codigos.agentes[num].nome.equals( agente.nome )) )
        {
            num++;
        }
        Executar.ambiente.pistas[agente.coluna][agente.linha] = num;
        Logger.log(agente.nome+" deixa pista");
    }

    public static void removePista( Agente agente )
    {
        Executar.ambiente.pistas[agente.coluna][agente.linha] = -1;
        Logger.log(agente.nome+" remove pista");
    }

    public static void mataAgente( Agente agente, String alvo )
    {
        Posicao pos = percebeAgente( 1, new Posicao( agente.coluna, agente.linha ), alvo );
        if ( pos != indeferido )
        {
            int aux = retornaNumTipoAgente( alvo );
            Executar.ambtmp[pos.x][pos.y] = -1;
            Executar.agentes[Executar.ambiente.agentes[pos.x][pos.y]] = null;
            Executar.ambiente.agentes[pos.x][pos.y] = -1;
            Executar.vetorQtd[aux]--;
        }
        Logger.log(agente.nome+" matou "+alvo);
    }

    public static void morteDeAgente( Agente agente )
    {
        int aux = retornaNumTipoAgente( agente.nome );
        Executar.ambiente.agentes[agente.coluna][agente.linha] = -1;
        Executar.agentes[agente.ncriacao] = null;
        Executar.vetorQtd[aux]--;
        Logger.log(agente.nome+" morreu");
    }

    public static void mataAgenteDir( Agente agente, int direcao )
    {
        Posicao pos = atingeAgenteDir( agente, direcao );
        int aux = retornaNumTipoAgente( Executar.agentes[Executar.ambiente.agentes[pos.x][pos.y]].nome );
        if ( pos != indeferido )
        {
            Executar.agentes[Executar.ambiente.agentes[pos.x][pos.y]] = null;
            Executar.ambtmp[pos.x][pos.y] = -1;
            Executar.ambiente.agentes[pos.x][pos.y] = -1;
            Executar.vetorQtd[aux]--;
        }
    }

    public static Posicao percebeAgente( int raio, Posicao origem, String alvo )
    {
        Posicao pos = new Posicao( 0, 0 );
        int max = 2 * raio + 1, numag;
        pos.y = origem.y - raio;
        if ( (pos.y >= 0) && (pos.y < Codigos.linhas) )
        {
            pos.x = origem.x - raio;
            for ( int i = 0; i < max; i++ )
            {
                if ( ((pos.x + i) >= 0) && ((pos.x + i) < Codigos.colunas) )
                {
                    numag = Executar.ambiente.agentes[(pos.x + i)][pos.y];
                    if ( numag != -1 )
                    {
                        if ( alvo.equals( Executar.agentes[numag].nome ) )
                        {
                            pos.x = pos.x + i;
                            return pos;
                        }
                    }
                }
            }
        }
        pos.x = origem.x + raio;
        if ( (pos.x >= 0) && (pos.x < Codigos.colunas) )
        {
            pos.y = origem.y - raio;
            for ( int i = 1; i < max; i++ )
            {
                if ( ((pos.y + i) >= 0) && ((pos.y + i) < Codigos.linhas) )
                {
                    numag = Executar.ambiente.agentes[pos.x][(pos.y + i)];
                    if ( numag != -1 )
                    {
                        if ( alvo.equals( Executar.agentes[numag].nome ) )
                        {
                            pos.y = pos.y + i;
                            return pos;
                        }
                    }
                }
            }
        }
        pos.y = origem.y + raio;
        if ( (pos.y >= 0) && (pos.y < Codigos.linhas) )
        {
            pos.x = origem.x + raio;
            for ( int i = 1; i < max; i++ )
            {
                if ( ((pos.x - i) >= 0) && ((pos.x - i) < Codigos.colunas) )
                {
                    numag = Executar.ambiente.agentes[(pos.x - i)][pos.y];
                    if ( numag != -1 )
                    {
                        if ( alvo.equals( Executar.agentes[numag].nome ) )
                        {
                            pos.x = pos.x - i;
                            return pos;
                        }
                    }
                }
            }
        }
        max--;
        pos.x = origem.x - raio;
        if ( (pos.x >= 0) && (pos.x < Codigos.colunas) )
        {
            pos.y = origem.y + raio;
            for ( int i = 1; i < max; i++ )
            {
                if ( ((pos.y - i) >= 0) && ((pos.y - i) < Codigos.linhas) )
                {
                    numag = Executar.ambiente.agentes[pos.x][(pos.y - i)];
                    if ( numag != -1 )
                    {
                        if ( alvo.equals( Executar.agentes[numag].nome ) )
                        {
                            pos.y = pos.y - i;
                            return pos;
                        }
                    }
                }
            }
        }
        return indeferido;
    }

    public static boolean percebePista( Agente agente, String alvo )
    {
        Posicao pos = percebePistaNoRaio( agente, alvo );
        if ( pos != indeferido )
        {
            Logger.log(agente.nome+" percebe pista de "+alvo);
            return true;
        }
        return false;
    }

    public static Posicao percebePistaNoRaio( Agente agente, String alvo )
    {
        Posicao pos, origem = new Posicao( agente.coluna, agente.linha );
        if ( agente.area > 0 )
        {
            for ( int i = 1; i <= agente.area; i++ )
            {
                pos = percebePista( i, origem, alvo );
                if ( pos != indeferido )
                {
                    return pos;
                }
            }
        }
        return indeferido;
    }

    public static Posicao percebeAgenteNoRaio( Agente agente, String alvo )
    {
        Posicao pos, origem = new Posicao( agente.coluna, agente.linha );
        if ( agente.area > 0 )
        {
            for ( int i = 1; i <= agente.area; i++ )
            {
                pos = percebeAgente( i, origem, alvo );
                if ( pos != indeferido )
                {
                    return pos;
                }
            }
        }
        return indeferido;
    }

    public static void seguePista( Agente agente, String alvo )
    {
        Posicao pos = percebePistaNoRaio( agente, alvo );
        if ( pos != indeferido )
        {
            Logger.log(agente.nome+" segue pista");
            movimentaPara( agente, pos );
        }
    }

    public static void segueAgente( Agente agente, String alvo )
    {
        Posicao pos = percebeAgenteNoRaio( agente, alvo );
        if ( pos != indeferido )
        {
            Logger.log(agente.nome+" segue "+alvo);
            movimentaPara( agente, pos );
        }
    }

    public static boolean percebeAgente( Agente agente, String alvo )
    {
        Posicao pos = percebeAgenteNoRaio( agente, alvo );
        if ( pos != indeferido )
        {
            Logger.log(agente.nome+" percebe "+alvo);
            return true;
        }
        return false;
    }

    public static int calculaDirecao( Agente agente, Posicao pos )
    {
        if ( agente.coluna > pos.x )
        {
            if ( agente.linha > pos.y )
            {
                return 5;
            }
            else if ( agente.linha == pos.y )
            {
                return 3;
            }
            else
            {
                return 7;
            }
        }
        else
        {
            if ( agente.coluna == pos.x )
            {
                if ( agente.linha > pos.y )
                {
                    return 1;
                }
                else
                {
                    return 2;
                }
            }
            else
            {
                if ( agente.linha > pos.y )
                {
                    return 6;
                }
                else if ( agente.linha == pos.y )
                {
                    return 4;
                }
                else
                {
                    return 8;
                }
            }
        }
    }

    public static Posicao percebePista( int raio, Posicao origem, String alvo )
    {
        Posicao pos = new Posicao( 0, 0 );
        int max = 2 * raio + 1, numpis;
        pos.y = origem.y - raio;
        if ( (pos.y >= 0) && (pos.y < Codigos.linhas) )
        {
            pos.x = origem.x - raio;
            for ( int i = 0; i < max; i++ )
            {
                if ( ((pos.x + i) > 0) && ((pos.x + i) < Codigos.colunas) )
                {
                    numpis = Executar.ambiente.pistas[(pos.x + i)][pos.y];
                    if ( numpis != -1 )
                    {
                        pos.x = pos.x + i;
                        return pos;
                    }
                }
            }
        }
        pos.x = origem.x + raio;
        if ( (pos.x >= 0) && (pos.x < Codigos.colunas) )
        {
            pos.y = origem.y - raio;
            for ( int i = 1; i < max; i++ )
            {
                if ( ((pos.y + i) >= 0) && ((pos.y + i) < Codigos.linhas) )
                {
                    numpis = Executar.ambiente.pistas[pos.x][(pos.y + i)];
                    if ( numpis != -1 )
                    {
                        pos.y = pos.y + i;
                        return pos;
                    }
                }
            }
        }
        pos.y = origem.y + raio;
        if ( (pos.y >= 0) && (pos.y < Codigos.linhas) )
        {
            pos.x = origem.x + raio;
            for ( int i = 1; i < max; i++ )
            {
                if ( ((pos.x - i) >= 0) && ((pos.x - i) < Codigos.colunas) )
                {
                    numpis = Executar.ambiente.pistas[(pos.x - i)][pos.y];
                    if ( numpis != -1 )
                    {
                        pos.x = pos.x - i;
                        return pos;
                    }
                }
            }
        }
        max--;
        pos.x = origem.x - raio;
        if ( (pos.x >= 0) && (pos.x < Codigos.colunas) )
        {
            pos.y = origem.y + raio;
            for ( int i = 1; i < max; i++ )
            {
                if ( ((pos.y - i) >= 0) && ((pos.y - i) < Codigos.linhas) )
                {
                    numpis = Executar.ambiente.pistas[pos.x][(pos.y - i)];
                    if ( numpis != -1 )
                    {
                        pos.y = pos.y - i;
                        return pos;
                    }
                }
            }
        }
        return indeferido;
    }

    public static int inverteDirecao( int direcao )
    {
        if ( direcao == 1 )
        {
            return 2;
        }
        else if ( direcao == 2 )
        {
            return 1;
        }
        else if ( direcao == 3 )
        {
            return 4;
        }
        else if ( direcao == 4 )
        {
            return 3;
        }
        else if ( direcao == 5 )
        {
            return 8;
        }
        else if ( direcao == 6 )
        {
            return 7;
        }
        else if ( direcao == 7 )
        {
            return 6;
        }
        else if ( direcao == 8 )
        {
            return 5;
        }
        return 9;
    }

    public static void fogeDeAgente( Agente agente, String seguidor )
    {
        int direcao;
        Posicao pos = percebeAgenteNoRaio( agente, seguidor );
        if ( pos != indeferido )
        {
            Logger.log(agente.nome+" foge de "+seguidor);
            direcao = calculaDirecao( agente, pos );
            direcao = inverteDirecao( direcao );
            movimentaPara( agente, direcao );
        }
    }

    public static boolean atingeAgenteDirecao( Agente agente, String alvo, int direcao )
    {
        if ( atingeAgenteDir( agente, direcao ) != indeferido )
        {
            return true;
        }
        else
        {
            return false;
        }
    }

    public static Posicao retornaPosicao( Posicao pos, int direcao )
    {
        if ( (direcao == 1) && (pos.y > 0) )
        {
            pos.y--;
            return pos;
        }
        else if ( (direcao == 2) && (pos.y < (Codigos.linhas - 1)) )
        {
            pos.y++;
            return pos;
        }
        else if ( (direcao == 3) && (pos.x > 0) )
        {
            pos.x--;
            return pos;
        }
        else if ( (direcao == 4) && (pos.x < (Codigos.colunas - 1)) )
        {
            pos.x++;
            return pos;
        }
        else if ( (direcao == 5) && (pos.x > 0) && (pos.y > 0) )
        {
            pos.x--;
            pos.y--;
            return pos;
        }
        else if ( (direcao == 6) && (pos.x < (Codigos.colunas - 1)) && (pos.y > 0) )
        {
            pos.x++;
            pos.y--;
            return pos;
        }
        else if ( (direcao == 7) && (pos.x > 0) && (pos.y < (Codigos.linhas - 1)) )
        {
            pos.x--;
            pos.y++;
            return pos;
        }
        else if ( (direcao == 8) && (pos.x < (Codigos.colunas - 1)) && (pos.y < (Codigos.linhas - 1)) )
        {
            pos.x++;
            pos.y++;
            return pos;
        }
        return indeferido;
    }

    public static Posicao atingeAgenteDir( Agente agente, int direcao )
    {
        Posicao pos = new Posicao( agente.coluna, agente.linha );
        pos = retornaPosicao( pos, direcao );
        if ( Executar.ambiente.agentes[pos.x][pos.y] != -1 )
        {
            return pos;
        }
        return indeferido;
    }

    public static boolean atingeAgente( Agente agente, String alvo )
    {
        Posicao pos = (new Posicao( agente.coluna, agente.linha ));
        if ( percebeAgente( 1, pos, alvo ) != indeferido )
        {
            Logger.log(agente.nome+" atingiu "+alvo);
            return true;
        }
        else
        {
            return false;
        }
    }

    public static boolean atingePista( Agente agente )
    {
        Posicao pos = new Posicao( agente.coluna, agente.linha );
        if ( Executar.ambiente.pistas[pos.x][pos.y] != -1 )
        {
            Logger.log(agente.nome+" atingiu pista");
            return true;
        }
        else
        {
            return false;
        }
    }

    /* alterar tipoSexo(agente) */
    public static int tipoSexo( Agente agente, String sexo, int taxa )
    {
        return agente.sexo;
    }

    public static boolean taxaSucesso( Agente agente, int taxa )
    {
        if ( agente.sucesso >= taxa )
        {
            return true;
        }
        else
        {
            return false;
        }
    }

    public static boolean atingeVidaAdulta( Agente agente, int tempo )
    {
        if ( agente.tempovida >= tempo )
        {
            return true;
        }
        else
        {
            return false;
        }
    }

    public static Posicao achaMaisProximo( Agente agente, String alvo )
    {
        Posicao pos, origem = new Posicao( agente.coluna, agente.linha );
        int max;
        if ( Codigos.colunas > Codigos.linhas )
        {
            max = Codigos.colunas;
        }
        else
        {
            max = Codigos.linhas;
        }
        for ( int i = 1; i <= max; i++ )
        {
            pos = percebeAgente( i, origem, alvo );
            if ( pos != indeferido )
            {
                return pos;
            }
        }
        return indeferido;
    }

    public static void segueMaiorGradiente( Agente agente, String alvo )
    {
        Posicao pos = achaMaisProximo( agente, alvo );
        if ( pos != indeferido )
        {
            movimentaPara( agente, pos );
        }
    }

    public static void segueMenorGradiente( Agente agente, String alvo )
    {
        Posicao pos = achaMaisProximo( agente, alvo );
        int direcao;
        if ( pos != indeferido )
        {
            direcao = calculaDirecao( agente, pos );
            direcao = inverteDirecao( direcao );
            movimentaPara( agente, direcao );
        }
    }

    public static boolean tempoFertilidade( Agente agente, int tempofertil )
    {
        if ( agente.tempovida <= tempofertil )
        {
            return true;
        }
        else
        {
            return false;
        }
    }

    public static int retornaNumTipoAgente( String tipo )
    {
        int aux = 1;
        while ( Codigos.agentes[aux] != null )
        {
            if ( Codigos.agentes[aux].nome.equals( tipo ) )
            {
                return aux;
            }
            else
            {
                aux++;
            }
        }
        return aux;
    }

    public static void reproduzAgente( Agente agente, int prole, String tipo )
    {
        Posicao pos;
        for ( int i = 0; i < prole; i++ )
        {
            pos = encontraPosicaoVaga( agente );
            if ( pos != indeferido )
            {
                Logger.log(agente.nome+" reproduziu "+tipo);
                criaAgente( tipo, pos );
            }
        }
    }

    public static Posicao encontraPosicaoVaga( Agente agente )
    {
        Posicao pos = new Posicao( 0, 0 );
        int raio = 1, distancia;
        if ( Codigos.colunas < Codigos.linhas )
        {
            distancia = Codigos.linhas;
        }
        else
        {
            distancia = Codigos.colunas;
        }
        while ( raio < distancia )
        {
            int max = 2 * raio + 1;
            pos.y = agente.linha - raio;
            if ( (pos.y >= 0) && (pos.y < Codigos.linhas) )
            {
                pos.x = agente.coluna - raio;
                for ( int i = 0; i < max; i++ )
                {
                    if ( ((pos.x + i) >= 0) && ((pos.x + i) < Codigos.colunas) )
                    {
                        if ( Executar.ambiente.agentes[(pos.x + i)][pos.y] == -1 )
                        {
                            pos.x = pos.x + i;
                            return pos;
                        }
                    }
                }
            }
            pos.x = agente.coluna + raio;
            if ( (pos.x >= 0) && (pos.x < Codigos.colunas) )
            {
                for ( int i = 1; i < max; i++ )
                {
                    pos.y = agente.linha - raio;
                    if ( ((pos.y + i) >= 0) && ((pos.y + i) < Codigos.linhas) )
                    {
                        if ( Executar.ambiente.agentes[(pos.x)][pos.y + i] == -1 )
                        {
                            pos.y = pos.y + i;
                            return pos;
                        }
                    }
                }
            }
            pos.y = agente.linha + raio;
            if ( (pos.y >= 0) && (pos.y < Codigos.linhas) )
            {
                for ( int i = 1; i < max; i++ )
                {
                    pos.x = agente.coluna + raio;
                    if ( ((pos.x - i) >= 0) && ((pos.x - i) < Codigos.colunas) )
                    {
                        if ( Executar.ambiente.agentes[pos.x - i][pos.y] == -1 )
                        {
                            pos.x = pos.x - i;
                            return pos;
                        }
                    }
                }
            }
            max--;
            pos.x = agente.coluna - raio;
            if ( (pos.x >= 0) && (pos.x < Codigos.colunas) )
            {
                for ( int i = 1; i < max; i++ )
                {
                    pos.y = agente.linha + raio;
                    if ( ((pos.y - i) >= 0) && ((pos.y - i) < Codigos.linhas) )
                    {
                        if ( Executar.ambiente.agentes[(pos.x)][pos.y - i] == -1 )
                        {
                            pos.y = pos.y - i;
                            return pos;
                        }
                    }
                }
            }
            raio++;
        }
        return indeferido;
    }

    public static Agente retornaUltimoAgente( String tipoagente )
    {
        int agnum = 0, id = 0, aux = retornaNumTipoAgente( tipoagente );
        Posicao pos;
        for ( int i = 0; i < 1600; i++ )
        {
            if ( Executar.agentes[i] != null )
            {
                if ( Executar.agentes[i].nome.equals( tipoagente ) )
                {
                    if ( agnum < Executar.agentes[i].numero )
                    {
                        agnum = Executar.agentes[i].numero;
                        id = i;
                    }
                }
            }
        }
        if ( Executar.agentes[id] != null )
        {
            return Executar.agentes[id];
        }
        else
        {
            MemoryImageSource imagem = new MemoryImageSource( 16, 16, Codigos.agentes[aux].imagem, 0, 16 );
            Image image = Toolkit.getDefaultToolkit().createImage( imagem );
            pos = Executar.randPos();
            return new Agente( Codigos.agentes[aux].nome, 0, ((int) (Math.random() * 2)),
                    pos.x, pos.y, (new Integer( Codigos.agentes[aux].area )).intValue(),
                    0, 0, (new Integer( Codigos.agentes[aux].energia )).intValue(),
                    (new Integer( Codigos.agentes[aux].carga )).intValue(), image, 0 );
        }
    }

    public static void criaAgente( String tipoagente, Posicao pos )
    {
        int aux = retornaNumTipoAgente( tipoagente ), num = 1;
        Agente tipo = retornaUltimoAgente( tipoagente );
        while ( num < 1600 )
        {
            if ( Executar.agentes[num] == null )
            {
                break;
            }
            else
            {
                num++;
            }
        }
        if ( num < 1600 )
        {
            Executar.agentes[num] = new Agente( tipoagente, tipo.numero + 1, ((int) (Math.random() * 2)),
                    pos.x, pos.y, tipo.area, 0, 0, (new Integer( Codigos.agentes[aux].energia )).intValue(),
                    tipo.carga, tipo.imagem, num );
            Executar.ambiente.agentes[pos.x][pos.y] = num;
            Executar.vetorQtd[aux]++;
        }
    }

    public static void transformaAgente( Agente agente, String novotipo )
    {
        int aux = retornaNumTipoAgente( agente.nome );
        String partialLog = agente.nome+" transformou-se em ";
        Agente tipo = retornaUltimoAgente( novotipo );
        Logger.log(partialLog+novotipo);
        Executar.agentes[agente.ncriacao].nome = novotipo;
        Executar.agentes[agente.ncriacao].imagem = tipo.imagem;
        Executar.agentes[agente.ncriacao].numero = tipo.numero + 1;
        Executar.agentes[agente.ncriacao].area = tipo.area;
        Executar.vetorQtd[aux]--;
        aux = retornaNumTipoAgente( novotipo );
        Executar.vetorQtd[aux]++;
    }

    public static boolean atingeTempoVida( Agente agente, int tempomax )
    {
        if ( agente.tempovida < tempomax )
        {
            return false;
        }
        else
        {
            return true;
        }
    }
}

package src;

import java.awt.*;

class Behavior
{

    public String agente, precond, acativ, accond, poscond, ident, priorid;

    public Behavior( String ag, String id, String pre, String aca, String acc, String pos, String prio )
    {
        agente = ag;
        ident = id;
        precond = pre;
        acativ = aca;
        accond = acc;
        poscond = pos;
        priorid = prio;
    }
}

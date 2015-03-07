
package Model;

import javax.media.j3d.BranchGroup;

class Escena extends BranchGroup {  
    public Escena () { 
        Sol sol = new Sol(10f, 0l, 5400l, 0f, "imgs/sol.jpg");
        Astro mercurio =  new Astro(0.49f, 17600l, 11740l, 7f, "imgs/mercurio.jpg");
        Astro venus =  new Astro(1.21f, 44949l, 48600l, 9.5f, "imgs/venus.jpg");
        Astro tierra = new Astro(1.27f, 73050l, 200l, 12.5f, "imgs/tierra.jpg");
        Astro luna = new Astro(0.34f, 5460l, 5460l, 1f, "imgs/luna.jpg");
        Astro marte = new Astro(0.68f, 137396l, 4924l, 15f, "imgs/marte.jpg");
        Astro fobos = new Astro(0.02f, 62l, 62l, 0.45f, "imgs/fobos.jpg");
        Astro deimos = new Astro(0.01f, 252l, 252l, 0.57f, "imgs/deimos.jpg");
        Astro jupiter = new Astro(5f, 865780l, 82l, 23f, "imgs/jupiter.jpg");
        Astro io = new Astro(0.36f, 350l, 350l, 2.8f, "imgs/io.jpg");
        Astro europa = new Astro(0.31f, 710l, 710l, 3.3f, "imgs/europa.jpg");
        Astro ganimedes = new Astro(0.53f, 1403l, 1403l, 3.9f, "imgs/ganimedes.jpg");
        Astro calisto = new Astro(0.48f, 3338l, 3338l, 4.6f, "imgs/calisto.jpg");
        Astro saturno = new Astro(4f, 2150580l, 85l, 35f, "imgs/saturno.jpg");
        Astro urano = new Astro(2.6f, 6132730l, 149l, 45f, "imgs/urano.jpg");
        Astro miranda = new Astro(0.04f, 283l, 283l, 1.4f, "imgs/miranda.jpg");
        Astro ariel = new Astro(0.1f, 504l, 504l, 1.55f, "imgs/ariel.jpg");
        Astro umbriel = new Astro(0.12f, 829l, 829l, 1.9f, "imgs/umbriel.jpg");
        Astro titania = new Astro(0.16f, 1741l,  1741l, 2.3f, "imgs/titania.jpg");
        Astro oberon = new Astro(0.15f, 2692l, 2692l, 2.76f, "imgs/oberon.jpg");
        Astro neptuno = new Astro(2.5f, 12030400l, 134l, 51f, "imgs/neptuno.jpg");
        Astro triton = new Astro(0.27f, 1175l, 1175, 1.6f, "imgs/triton.jpg");
        tierra.addSatelite(luna);
        marte.addSatelite(fobos);
        marte.addSatelite(deimos);
        jupiter.addSatelite(io);
        jupiter.addSatelite(europa);
        jupiter.addSatelite(ganimedes);
        jupiter.addSatelite(calisto);
        urano.addSatelite(miranda);
        urano.addSatelite(ariel);
        urano.addSatelite(umbriel);
        urano.addSatelite(titania);
        urano.addSatelite(oberon);
        neptuno.addSatelite(triton);
        sol.addSatelite(mercurio);
        sol.addSatelite(venus);
        sol.addSatelite(tierra);
        sol.addSatelite(marte);
        sol.addSatelite(jupiter);
        sol.addSatelite(saturno);
        sol.addSatelite(urano);
        sol.addSatelite(neptuno);
        
        Nave nave = new Nave();
        this.addChild(nave.dibujar());
        
        this.addChild(sol.dibujar());
    }
}
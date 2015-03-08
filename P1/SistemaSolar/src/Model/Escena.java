
package Model;

import javax.media.j3d.BranchGroup;

class Escena extends BranchGroup {  
    public Escena () { 
        // Creación nave
        Nave nave = new Nave();
        
        // Creación planetas
        Sol sol = new Sol(10f, 5000l, "imgs/sol.jpg");
        Astro mercurio =  new Astro(0.49f, 18000l, 10000l, 7f, "imgs/mercurio.jpg");
        Astro venus =  new Astro(1.21f, 25000l, 30000l, 9.5f, "imgs/venus.jpg");
        Astro tierra = new Astro(1.27f, 60000l, 3000l, 12.5f, "imgs/tierra.jpg");
        Astro luna = new Astro(0.34f, 9000l, 9000l, 1f, "imgs/luna.jpg");
        Astro marte = new Astro(0.68f, 75000l, 5000l, 15f, "imgs/marte.jpg");
        Astro fobos = new Astro(0.11f, 3100l, 3100l, 0.5f, "imgs/fobos.jpg");
        Astro deimos = new Astro(0.1f, 3200l, 3200l, 0.7f, "imgs/deimos.jpg");
        Astro jupiter = new Astro(5f, 90000l, 2900l, 23f, "imgs/jupiter.jpg");
        Astro io = new Astro(0.36f, 3300l, 3300l, 2.8f, "imgs/io.jpg");
        Astro europa = new Astro(0.31f, 3500l, 3500l, 3.3f, "imgs/europa.jpg");
        Astro ganimedes = new Astro(0.53f, 3750l, 3750l, 3.9f, "imgs/ganimedes.jpg");
        Astro calisto = new Astro(0.48f, 6000l, 6000l, 4.6f, "imgs/calisto.jpg");
        Astro saturno = new Astro(4f, 120000l, 2900l, 35f, "imgs/saturno.jpg");
        Astro urano = new Astro(2.6f, 150000l, 2900l, 45f, "imgs/urano.jpg");
        Astro miranda = new Astro(0.12f, 3250l, 3250l, 1.4f, "imgs/miranda.jpg");
        Astro ariel = new Astro(0.13f, 3400l, 3400l, 1.6f, "imgs/ariel.jpg");
        Astro umbriel = new Astro(0.14f, 3550l, 3550l, 2f, "imgs/umbriel.jpg");
        Astro titania = new Astro(0.16f, 4000l,  4000l, 2.3f, "imgs/titania.jpg");
        Astro oberon = new Astro(0.15f, 5000l, 5000l, 2.76f, "imgs/oberon.jpg");
        Astro neptuno = new Astro(2.5f, 200000l, 2900l, 51f, "imgs/neptuno.jpg");
        Astro triton = new Astro(0.27f, 3600l, 3600l, 1.6f, "imgs/triton.jpg");
        
        // Asignación satélites
        tierra.addSatelite(luna);
        marte.addSatelite(fobos); marte.addSatelite(deimos);
        jupiter.addSatelite(io); jupiter.addSatelite(europa); jupiter.addSatelite(ganimedes); jupiter.addSatelite(calisto);
        urano.addSatelite(miranda); urano.addSatelite(ariel); urano.addSatelite(umbriel); urano.addSatelite(titania); urano.addSatelite(oberon);
        neptuno.addSatelite(triton);
        sol.addSatelite(mercurio); sol.addSatelite(venus); sol.addSatelite(tierra); sol.addSatelite(marte); sol.addSatelite(jupiter); sol.addSatelite(saturno); sol.addSatelite(urano); sol.addSatelite(neptuno);
        
        // Colgamos del BranchGroup escena la nave y los planetas
        this.addChild(nave.dibujar());
        this.addChild(sol.dibujar()); // recursivamente añade el resto de planetas
    }
}
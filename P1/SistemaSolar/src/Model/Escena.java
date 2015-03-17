
package Model;

import javax.media.j3d.BranchGroup;
import javax.vecmath.Color3f;

class Escena extends BranchGroup {  
    public Escena () { 
        // Creación nave
        Nave nave = new Nave("models/E-TIE-I/E-TIE-I.obj");
        
        // Creación astros
        Sol sol = new Sol(
                10f,                            // diametro
                5000l,                          // rotación sobre sí mismo
                "imgs/sol.jpg"                  // path textura
        );
        Astro mercurio =  new Astro(
                0.49f,                          // diametro
                18000l,                         // rotación sobre astro 
                10000l,                         // rotación sobre sí mismo
                7f,                             // distancia sobre astro
                "imgs/mercurio.jpg",            // path textura
                new Color3f (0.6f, 0.6f, 0.6f), // componente ambiental
                new Color3f (0.6f, 0.6f, 0.6f), // componente difusa
                new Color3f (0.6f, 0.6f, 0.6f), // componente emisiva
                new Color3f (0.2f, 0.2f, 0.2f), // componente especular
                10f                             // brillo
        );
        Astro venus =  new Astro(
                1.21f, 
                25000l, 
                -100000l, 
                9.5f, 
                "imgs/venus.jpg", 
                new Color3f (0.6f, 0.6f, 0.6f), 
                new Color3f (0.6f, 0.6f, 0.6f), 
                new Color3f (0.6f, 0.6f, 0.6f), 
                new Color3f (0.2f, 0.2f, 0.2f), 
                10f
        );
        Astro tierra = new Astro(
                1.27f, 
                60000l, 
                3000l, 
                12.5f, 
                "imgs/tierra.jpg", 
                new Color3f (0.6f, 0.6f, 0.6f), 
                new Color3f (0.6f, 0.6f, 0.6f), 
                new Color3f (0.6f, 0.6f, 0.6f), 
                new Color3f (0.2f, 0.2f, 0.2f), 
                10f
        );
        Astro luna = new Astro(
                0.34f, 
                9000l, 
                9000l, 
                1f, 
                "imgs/luna.jpg", 
                new Color3f (0.6f, 0.6f, 0.6f), 
                new Color3f (0.6f, 0.6f, 0.6f), 
                new Color3f (0.6f, 0.6f, 0.6f), 
                new Color3f (0.2f, 0.2f, 0.2f), 
                10f
        );
        Astro marte = new Astro(
                0.68f, 
                75000l, 
                5000l, 
                15f, 
                "imgs/marte.jpg", 
                new Color3f (0.6f, 0.6f, 0.6f), 
                new Color3f (0.6f, 0.6f, 0.6f), 
                new Color3f (0.6f, 0.6f, 0.6f), 
                new Color3f (0.2f, 0.2f, 0.2f), 
                10f
        );
        Astro fobos = new Astro(
                0.11f, 
                3100l, 
                3100l, 
                0.5f, 
                "imgs/fobos.jpg", 
                new Color3f (0.6f, 0.6f, 0.6f), 
                new Color3f (0.6f, 0.6f, 0.6f), 
                new Color3f (0.6f, 0.6f, 0.6f), 
                new Color3f (0.2f, 0.2f, 0.2f), 
                10f
        );
        Astro deimos = new Astro(
                0.1f, 
                3200l, 
                3200l, 
                0.7f, 
                "imgs/deimos.jpg", 
                new Color3f (0.6f, 0.6f, 0.6f), 
                new Color3f (0.6f, 0.6f, 0.6f), 
                new Color3f (0.6f, 0.6f, 0.6f), 
                new Color3f (0.2f, 0.2f, 0.2f), 
                10f
        );
        Astro jupiter = new Astro(
                5f, 
                90000l, 
                2900l, 
                23f, 
                "imgs/jupiter.jpg", 
                new Color3f (0.6f, 0.6f, 0.6f), 
                new Color3f (0.6f, 0.6f, 0.6f), 
                new Color3f (0.6f, 0.6f, 0.6f), 
                new Color3f (0.2f, 0.2f, 0.2f), 
                10f
        );
        Astro io = new Astro(
                0.36f, 
                3300l, 
                3300l, 
                2.8f, 
                "imgs/io.jpg", 
                new Color3f (0.6f, 0.6f, 0.6f), 
                new Color3f (0.6f, 0.6f, 0.6f), 
                new Color3f (0.6f, 0.6f, 0.6f), 
                new Color3f (0.2f, 0.2f, 0.2f), 
                10f
        );
        Astro europa = new Astro(
                0.31f, 
                3500l, 
                3500l, 
                3.3f, 
                "imgs/europa.jpg", 
                new Color3f (0.6f, 0.6f, 0.6f), 
                new Color3f (0.6f, 0.6f, 0.6f), 
                new Color3f (0.6f, 0.6f, 0.6f), 
                new Color3f (0.2f, 0.2f, 0.2f), 
                10f
        );
        Astro ganimedes = new Astro(
                0.53f, 
                3750l, 
                3750l, 
                3.9f, 
                "imgs/ganimedes.jpg", 
                new Color3f (0.6f, 0.6f, 0.6f), 
                new Color3f (0.6f, 0.6f, 0.6f), 
                new Color3f (0.6f, 0.6f, 0.6f), 
                new Color3f (0.2f, 0.2f, 0.2f), 
                10f
        );
        Astro calisto = new Astro(
                0.48f, 
                6000l, 
                6000l, 
                4.6f, 
                "imgs/calisto.jpg", 
                new Color3f (0.6f, 0.6f, 0.6f), 
                new Color3f (0.6f, 0.6f, 0.6f), 
                new Color3f (0.6f, 0.6f, 0.6f), 
                new Color3f (0.2f, 0.2f, 0.2f), 
                10f
        );
        Astro saturno = new Astro(
                4f, 
                120000l, 
                2900l, 
                35f, 
                "imgs/saturno.jpg", 
                new Color3f (0.6f, 0.6f, 0.6f), 
                new Color3f (0.6f, 0.6f, 0.6f), 
                new Color3f (0.6f, 0.6f, 0.6f), 
                new Color3f (0.2f, 0.2f, 0.2f), 
                10f
        );
        Astro urano = new Astro(
                2.6f, 
                150000l, 
                2900l, 
                45f, 
                "imgs/urano.jpg", 
                new Color3f (0.6f, 0.6f, 0.6f), 
                new Color3f (0.6f, 0.6f, 0.6f), 
                new Color3f (0.6f, 0.6f, 0.6f), 
                new Color3f (0.2f, 0.2f, 0.2f), 
                10f
        );
        Astro miranda = new Astro(
                0.12f, 
                3250l, 
                3250l, 
                1.4f, 
                "imgs/miranda.jpg", 
                new Color3f (0.6f, 0.6f, 0.6f), 
                new Color3f (0.6f, 0.6f, 0.6f), 
                new Color3f (0.6f, 0.6f, 0.6f), 
                new Color3f (0.2f, 0.2f, 0.2f), 
                10f
        );
        Astro ariel = new Astro(
                0.13f, 
                3400l, 
                3400l, 
                1.6f, 
                "imgs/ariel.jpg", 
                new Color3f (0.6f, 0.6f, 0.6f), 
                new Color3f (0.6f, 0.6f, 0.6f), 
                new Color3f (0.6f, 0.6f, 0.6f), 
                new Color3f (0.2f, 0.2f, 0.2f), 
                10f
        );
        Astro umbriel = new Astro(
                0.14f, 
                3550l, 
                3550l, 
                2f, 
                "imgs/umbriel.jpg", 
                new Color3f (0.6f, 0.6f, 0.6f), 
                new Color3f (0.6f, 0.6f, 0.6f), 
                new Color3f (0.6f, 0.6f, 0.6f), 
                new Color3f (0.2f, 0.2f, 0.2f), 
                10f
        );
        Astro titania = new Astro(
                0.16f, 
                4000l,  
                4000l, 
                2.3f, 
                "imgs/titania.jpg", 
                new Color3f (0.6f, 0.6f, 0.6f), 
                new Color3f (0.6f, 0.6f, 0.6f), 
                new Color3f (0.6f, 0.6f, 0.6f), 
                new Color3f (0.2f, 0.2f, 0.2f), 
                10f
        );
        Astro oberon = new Astro(
                0.15f, 
                5000l, 
                5000l, 
                2.76f, 
                "imgs/oberon.jpg", 
                new Color3f (0.6f, 0.6f, 0.6f), 
                new Color3f (0.6f, 0.6f, 0.6f), 
                new Color3f (0.6f, 0.6f, 0.6f), 
                new Color3f (0.2f, 0.2f, 0.2f), 
                10f
        );
        Astro neptuno = new Astro(
                2.5f, 
                200000l, 
                2900l, 
                51f, 
                "imgs/neptuno.jpg", 
                new Color3f (0.6f, 0.6f, 0.6f), 
                new Color3f (0.6f, 0.6f, 0.6f), 
                new Color3f (0.6f, 0.6f, 0.6f), 
                new Color3f (0.2f, 0.2f, 0.2f), 
                10f
        );
        Astro triton = new Astro(
                0.27f, 
                -36000l, 
                -36000l, 
                1.6f, 
                "imgs/triton.jpg", 
                new Color3f (0.6f, 0.6f, 0.6f), 
                new Color3f (0.6f, 0.6f, 0.6f), 
                new Color3f (0.6f, 0.6f, 0.6f), 
                new Color3f (0.2f, 0.2f, 0.2f), 
                10f
        );
        
        // Creación anillos
        Anillo a = new Anillo(
                3.55f,                          // radio interno
                4f,                             // radio externo
                50000l,                         // rotación
                "imgs/anillo.jpg",              // path textura
                new Color3f (0.8f, 0.8f, 0.8f), // componente ambiental
                new Color3f (0.7f, 0.7f, 0.7f), // componente difusa
                new Color3f (0.6f, 0.6f, 0.6f), // componente emisiva 
                new Color3f (0.2f, 0.2f, 0.2f), // componente especualar
                10f                             // brillo
        );
        Anillo b = new Anillo(
                2.7f, 
                3.5f, 
                50000l, 
                "imgs/anillo.jpg", 
                new Color3f (0.8f, 0.8f, 0.8f), 
                new Color3f (0.7f, 0.7f, 0.7f), 
                new Color3f (0.6f, 0.6f, 0.6f), 
                new Color3f (0.2f, 0.2f, 0.2f), 
                10f
        );
        Anillo c = new Anillo(
                2.2f, 
                2.65f, 
                50000l, 
                "imgs/anillo.jpg", 
                new Color3f (0.8f, 0.8f, 0.8f), 
                new Color3f (0.7f, 0.7f, 0.7f), 
                new Color3f (0.6f, 0.6f, 0.6f), 
                new Color3f (0.2f, 0.2f, 0.2f), 
                10f
        );
        
        // Asignación satélites
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
        
        // Asignación anillos
        saturno.addAnillo(a); 
        saturno.addAnillo(b); 
        saturno.addAnillo(c);
        
        // Colgamos del BranchGroup escena la nave y los planetas
        this.addChild(nave.dibujar());
        this.addChild(sol.dibujar()); // recursivamente añade el resto de planetas
    }
}
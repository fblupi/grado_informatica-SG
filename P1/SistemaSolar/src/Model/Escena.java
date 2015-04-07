
package Model;

import javax.media.j3d.BranchGroup;
import javax.media.j3d.Material;
import javax.vecmath.AxisAngle4f;
import javax.vecmath.Color3f;
import javax.vecmath.Point3f;

class Escena extends BranchGroup {  
    
    public Escena(Camara camaraLuna, Camara camaraNave) {
        //this.setPickable(true);
        // Creación nave
        Nave nave = new Nave(
            "models/Arc170/Arc170.obj",     // archivo del obj con el modelo
            10000,                          // tiempo de ciclo de animación
            new Point3f[] {                 // puntos
                new Point3f(10f,10f,-10f), new Point3f(10f,15f,-5f),
                new Point3f(10f,20f,0f), new Point3f(10f,15f,5f),
                new Point3f(10f,10f,10f), new Point3f(-10f,10f,10f),
                new Point3f(-10f,10f,-10f), new Point3f(10f,10f,-10f)
            },
            new AxisAngle4f[] {             // angulos
                new AxisAngle4f(0.0f, 1.0f, 0.0f, (float) Math.toRadians(0)),
                new AxisAngle4f(1.0f, 0.0f, 0.0f, (float) Math.toRadians(315)),
                new AxisAngle4f(0.0f, 1.0f, 0.0f, (float) Math.toRadians(0)),
                new AxisAngle4f(1.0f, 0.0f, 0.0f, (float) Math.toRadians(45)),
                new AxisAngle4f(0.0f, 1.0f, 0.0f, (float) Math.toRadians(270)),
                new AxisAngle4f(0.0f, 1.0f, 0.0f, (float) Math.toRadians(180)),
                new AxisAngle4f(0.0f, 1.0f, 0.0f, (float) Math.toRadians(90)),
                new AxisAngle4f(0.0f, 1.0f, 0.0f, (float) Math.toRadians(360))
            },
            new float[] {                   // knots
                0f, 0.14f, 0.28f, 0.42f, 0.56f, 0.7f, 0.84f, 1f
            }
        );
        
        // Creación de materiales
        Material materialAstros = new Material (
            new Color3f (0.6f, 0.6f, 0.6f), // componente ambiental
            new Color3f (0.6f, 0.6f, 0.6f), // componente difusa
            new Color3f (0.6f, 0.6f, 0.6f), // componente emisiva
            new Color3f (0.2f, 0.2f, 0.2f), // componente especular
            10f                             // brillo
        );
        Material materialAnillos = new Material (
            new Color3f (0.8f, 0.8f, 0.8f), // componente ambiental
            new Color3f (0.7f, 0.7f, 0.7f), // componente difusa
            new Color3f (0.6f, 0.6f, 0.6f), // componente emisiva 
            new Color3f (0.2f, 0.2f, 0.2f), // componente especualar
            10f                             // brillo
        );
        
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
                materialAstros                  // material
                
        );
        Astro venus =  new Astro(
                1.21f, 
                25000l, 
                -100000l, 
                9.5f, 
                "imgs/venus.jpg", 
                materialAstros
        );
        Astro tierra = new Astro(
                1.27f, 
                60000l, 
                3000l, 
                12.5f, 
                "imgs/tierra.jpg", 
                materialAstros
        );
        Astro luna = new Astro(
                0.34f, 
                9000l, 
                9000l, 
                1f, 
                "imgs/luna.jpg", 
                materialAstros
        );
        Astro marte = new Astro(
                0.68f, 
                75000l, 
                5000l, 
                15f, 
                "imgs/marte.jpg", 
                materialAstros
        );
        Astro fobos = new Astro(
                0.11f, 
                3100l, 
                3100l, 
                0.5f, 
                "imgs/fobos.jpg", 
                materialAstros
        );
        Astro deimos = new Astro(
                0.1f, 
                3200l, 
                3200l, 
                0.7f, 
                "imgs/deimos.jpg", 
                materialAstros
        );
        Astro jupiter = new Astro(
                5f, 
                90000l, 
                2900l, 
                23f, 
                "imgs/jupiter.jpg", 
                materialAstros
        );
        Astro io = new Astro(
                0.36f, 
                3300l, 
                3300l, 
                2.8f, 
                "imgs/io.jpg", 
                materialAstros
        );
        Astro europa = new Astro(
                0.31f, 
                3500l, 
                3500l, 
                3.3f, 
                "imgs/europa.jpg", 
                materialAstros
        );
        Astro ganimedes = new Astro(
                0.53f, 
                3750l, 
                3750l, 
                3.9f, 
                "imgs/ganimedes.jpg", 
                materialAstros
        );
        Astro calisto = new Astro(
                0.48f, 
                6000l, 
                6000l, 
                4.6f, 
                "imgs/calisto.jpg", 
                materialAstros
        );
        Astro saturno = new Astro(
                4f, 
                120000l, 
                2900l, 
                35f, 
                "imgs/saturno.jpg", 
                materialAstros
        );
        Astro urano = new Astro(
                2.6f, 
                150000l, 
                2900l, 
                45f, 
                "imgs/urano.jpg", 
                materialAstros
        );
        Astro miranda = new Astro(
                0.12f, 
                3250l, 
                3250l, 
                1.4f, 
                "imgs/miranda.jpg", 
                materialAstros
        );
        Astro ariel = new Astro(
                0.13f, 
                3400l, 
                3400l, 
                1.6f, 
                "imgs/ariel.jpg",  
                materialAstros
        );
        Astro umbriel = new Astro(
                0.14f, 
                3550l, 
                3550l, 
                2f, 
                "imgs/umbriel.jpg", 
                materialAstros
        );
        Astro titania = new Astro(
                0.16f, 
                4000l,  
                4000l, 
                2.3f, 
                "imgs/titania.jpg", 
                materialAstros
        );
        Astro oberon = new Astro(
                0.15f, 
                5000l, 
                5000l, 
                2.76f, 
                "imgs/oberon.jpg", 
                materialAstros
        );
        Astro neptuno = new Astro(
                2.5f, 
                200000l, 
                2900l, 
                51f, 
                "imgs/neptuno.jpg", 
                materialAstros
        );
        Astro triton = new Astro(
                0.27f, 
                -36000l, 
                -36000l, 
                1.6f, 
                "imgs/triton.jpg", 
                materialAstros
        );
        
        // Creación anillos
        Anillo a = new Anillo(
                3.76f,                          // radio interno
                0.24f,                          // radio externo
                50000l,                         // rotación
                "imgs/anilloa.jpg",             // path textura
                materialAnillos                 // material
        );
        Anillo b = new Anillo(
                3.11f, 
                0.39f, 
                50000l, 
                "imgs/anillob.jpg", 
                materialAnillos
        );
        Anillo c = new Anillo(
                2.375f, 
                0.325f, 
                50000l, 
                "imgs/anilloc.jpg", 
                materialAnillos
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
        this.addChild(sol);
        this.addChild(nave);
        
        luna.addCamara(camaraLuna);
        nave.addCamara(camaraNave);
    }
    
}
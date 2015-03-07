
package GUI;

import javax.media.j3d.Canvas3D;
import Model.Universo;
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;
import javax.swing.JFrame;

public class ControlWindow extends JFrame {
  private Universo universe;

  public ControlWindow(Canvas3D canvas, Universo anUniverse) {
    super();
    universe = anUniverse;
    initComponents();
    setLocation (920, 100);
    addWindowListener(new WindowAdapter() {
      @Override
      public void windowClosing(WindowEvent e) {
        closeApp(0);
      }
    });
    Visualization visualization = new Visualization (this, false, canvas);
    visualization.setVisible(true);
    pack();
  }

  /**
   * This method is called from within the constructor to initialize the form.
   * WARNING: Do NOT modify this code. The content of this method is always
   * regenerated by the Form Editor.
   */
  @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        primitiveGroup = new javax.swing.ButtonGroup();
        appearanceGroup = new javax.swing.ButtonGroup();
        commandsPanel = new javax.swing.JPanel();
        exitApp = new javax.swing.JButton();

        setDefaultCloseOperation(javax.swing.WindowConstants.DISPOSE_ON_CLOSE);
        setTitle("Control");
        setMinimumSize(new java.awt.Dimension(300, 345));
        getContentPane().setLayout(new java.awt.GridLayout(5, 0));

        commandsPanel.setLayout(new java.awt.FlowLayout(java.awt.FlowLayout.CENTER, 5, 20));
        getContentPane().add(commandsPanel);

        exitApp.setText("Salir");
        exitApp.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                exitAppActionPerformed(evt);
            }
        });
        getContentPane().add(exitApp);

        pack();
    }// </editor-fold>//GEN-END:initComponents

  private void exitAppActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_exitAppActionPerformed
    // TODO add your handling code here:
    closeApp(0);
  }//GEN-LAST:event_exitAppActionPerformed

  public void showWindow () {
    setVisible (true);
  }

  void closeApp (int code) {
    universe.closeApp(code);
  }

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.ButtonGroup appearanceGroup;
    private javax.swing.JPanel commandsPanel;
    private javax.swing.JButton exitApp;
    private javax.swing.ButtonGroup primitiveGroup;
    // End of variables declaration//GEN-END:variables
}
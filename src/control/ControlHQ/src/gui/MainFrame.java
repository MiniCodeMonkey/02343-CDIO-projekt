/*
 * MainFrame.java
 *
 * Created on 05-04-2011, 17:44:32
 */

package gui;

import gui.comm.CommFrame;
import gui.info.MiniInfoFrame;
import gui.info.MiniInfoPanel;
import gui.manualControl.ControlFrame;
import gui.path.PathToleranceFrame;
import gui.processing.ProcessingFrame;
import gui.speed.SpeedFrame;

import java.awt.Image;
import javax.swing.ImageIcon;
import javax.swing.JOptionPane;

import controller.MainController;

/**
 *
 * @author Morten Hulvej
 */
public class MainFrame extends javax.swing.JFrame {

    /** Creates new form MainFrame */
    public MainFrame() {
        initComponents();
        
        makeMiniInfoFrame();
        makeCommFrame();
        
        
        FramePlaceHolder.setMainFrame(this);
    }

    /** This method is called from within the constructor to
     * initialize the form.
     * WARNING: Do NOT modify this code. The content of this method is
     * always regenerated by the Form Editor.
     */
    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        dashboard = new javax.swing.JDesktopPane();
        mainMenuBar = new javax.swing.JMenuBar();
        fileMenu = new javax.swing.JMenu();
        importMenuItem = new javax.swing.JMenuItem();
        exportMenuItem = new javax.swing.JMenuItem();
        settingsMenu = new javax.swing.JMenu();
        speedMenuItem = new javax.swing.JMenuItem();
        toleranceMenuItem = new javax.swing.JMenuItem();
        updateMenuItem = new javax.swing.JMenuItem();
        aboutMenu = new javax.swing.JMenu();
        aboutMenuItem = new javax.swing.JMenuItem();

        setDefaultCloseOperation(javax.swing.WindowConstants.EXIT_ON_CLOSE);
        setLocationByPlatform(true);

        dashboard.setBackground(new java.awt.Color(204, 204, 204));

        fileMenu.setText("File");

        importMenuItem.setText("Import config..");
        fileMenu.add(importMenuItem);

        exportMenuItem.setText("Export config..");
        fileMenu.add(exportMenuItem);

        mainMenuBar.add(fileMenu);

        settingsMenu.setText("Instillinger");

        speedMenuItem.setText("Hastigheder");
        speedMenuItem.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                speedMenuItemActionPerformed(evt);
            }
        });
        settingsMenu.add(speedMenuItem);

        toleranceMenuItem.setText("Tolerancer");
        toleranceMenuItem.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                toleranceMenuItemActionPerformed(evt);
            }
        });
        settingsMenu.add(toleranceMenuItem);

        updateMenuItem.setText("Update info");
        updateMenuItem.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                updateMenuItemActionPerformed(evt);
            }
        });
        settingsMenu.add(updateMenuItem);

        mainMenuBar.add(settingsMenu);

        aboutMenu.setText("?");

        aboutMenuItem.setText("About");
        aboutMenuItem.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                aboutMenuItemActionPerformed(evt);
            }
        });
        aboutMenu.add(aboutMenuItem);

        mainMenuBar.add(aboutMenu);

        setJMenuBar(mainMenuBar);

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(dashboard, javax.swing.GroupLayout.DEFAULT_SIZE, 932, Short.MAX_VALUE)
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(dashboard, javax.swing.GroupLayout.DEFAULT_SIZE, 530, Short.MAX_VALUE)
        );

        pack();
    }// </editor-fold>//GEN-END:initComponents

    private void aboutMenuItemActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_aboutMenuItemActionPerformed
        Image icon = new ImageIcon(getClass().getResource("/icons/BERTA.png")).getImage().getScaledInstance(-1, 480, Image.SCALE_FAST);
		JOptionPane.showMessageDialog(null,
                "Orn'lig syg about box til basis kontrol-HQ-2theMaX!\n"
                + "Brugt til test af orn'lig syg B.E.R.T.A!"
                + "\n"
                + "For�r 2011 (c) Gruppe 4 - CDIO Projekt - Morten Hulvej", "Orn'lig syg",
			JOptionPane.WARNING_MESSAGE,new ImageIcon(icon));
    }//GEN-LAST:event_aboutMenuItemActionPerformed

    private void speedMenuItemActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_speedMenuItemActionPerformed
        makeSpeedSettingsFrame();
    }//GEN-LAST:event_speedMenuItemActionPerformed

    private void toleranceMenuItemActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_toleranceMenuItemActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_toleranceMenuItemActionPerformed

    private void updateMenuItemActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_updateMenuItemActionPerformed
        FramePlaceHolder.getMinInfoFrame().UpdateBothRobots();
    }//GEN-LAST:event_updateMenuItemActionPerformed


    /**
     * Opretter og arrangerer alle vinduer
     * <br><br>(ImageProcessing,Comm,Path,manualControl)
     */
    public void makeAndArrangeFrames() {
    	// TODO
	}
    public void makeControlFrame() {
		// TODO
    	
    	ControlFrame controlFrame = new ControlFrame();
    	
    	controlFrame.setVisible(true);
    	dashboard.add(controlFrame);
	}
    /**
     * Opretter panel med funktioner vedr. al image-processing
     */
    public void makeProcessingFrame() {
    	ProcessingFrame processFrame = new ProcessingFrame();
    	
        processFrame.setLocation(dashboard.getSize().width - processFrame.getSize().width, processFrame.getLocation().y);

    	processFrame.setVisible(true);
    	dashboard.add(processFrame);
	}
    public void makeToleranceSettingsFrame() {
		// TODO
    	
    	PathToleranceFrame pathframe = new PathToleranceFrame();
    	
    	pathframe.setVisible(true);
    	dashboard.add(pathframe);
	}
    public void makeCommFrame() {
		// TODO
    	CommFrame commFrame = new CommFrame();
    	commFrame.setVisible(true);
    	
    	dashboard.add(commFrame);
	}
    public void arrangeFrames() {
		// TODO
	}
    public javax.swing.JDesktopPane getDashboard(){
    	return dashboard;
    }
    public void makeSpeedSettingsFrame() {
		// TODO
    	
    	SpeedFrame speedFrame = new SpeedFrame();
    	speedFrame.setVisible(true);
    	dashboard.add(speedFrame);
    	
	}
    public void makeMiniInfoFrame() {
		MiniInfoFrame miniFrame = new MiniInfoFrame();
		miniFrame.setVisible(true);
		dashboard.add(miniFrame);
	}

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JMenu aboutMenu;
    private javax.swing.JMenuItem aboutMenuItem;
    private javax.swing.JDesktopPane dashboard;
    private javax.swing.JMenuItem exportMenuItem;
    private javax.swing.JMenu fileMenu;
    private javax.swing.JMenuItem importMenuItem;
    private javax.swing.JMenuBar mainMenuBar;
    private javax.swing.JMenu settingsMenu;
    private javax.swing.JMenuItem speedMenuItem;
    private javax.swing.JMenuItem toleranceMenuItem;
    private javax.swing.JMenuItem updateMenuItem;
    // End of variables declaration//GEN-END:variables

}

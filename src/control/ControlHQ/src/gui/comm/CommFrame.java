/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

/*
 * CommFrame.java
 *
 * Created on 30-05-2011, 12:10:18
 */

package gui.comm;

import gui.FramePlaceHolder;

import java.awt.Robot;

import lejos.pc.comm.NXTCommException;
import command.BertaCommando;
import command.PropCommando;

import controller.MainController;
import controller.RobotThread;
import controller.RobotThread.RobotType;


/**
 *
 * @author Morten Hulvej
 */
public class CommFrame extends javax.swing.JInternalFrame {

    /** Creates new form CommFrame */
    public CommFrame() {
        initComponents();
    }

    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        connectBtn = new javax.swing.JButton();
        disconnectBtn = new javax.swing.JButton();
        statusPtyLabel = new javax.swing.JLabel();

        setIconifiable(true);
        setTitle("Kommunikation");

        connectBtn.setText("Connect");
        connectBtn.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                connectBtnActionPerformed(evt);
            }
        });

        disconnectBtn.setText("Disconnect");
        disconnectBtn.setEnabled(false);

        statusPtyLabel.setText("--------------------------------------------");

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(layout.createSequentialGroup()
                        .addComponent(connectBtn)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(disconnectBtn))
                    .addComponent(statusPtyLabel))
                .addContainerGap(252, Short.MAX_VALUE))
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(connectBtn)
                    .addComponent(disconnectBtn))
                .addGap(18, 18, 18)
                .addComponent(statusPtyLabel)
                .addContainerGap(30, Short.MAX_VALUE))
        );

        pack();
    }// </editor-fold>//GEN-END:initComponents

    /**
     * @param evt
     */
    private void connectBtnActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_connectBtnActionPerformed
    		
    	// TODO
    	
//    	MainController.getInstance();
    	
    	/*
    	 * hvis begge robotter virker?
    	 * 		-> makeProcessingFrame();
    	 * 		-> makeControlFrame    x 2 (TODO understøttelse af 2 robotter)
    	 */
    	
    	FramePlaceHolder.getMainFrame().makeProcessingFrame();
    	FramePlaceHolder.getMainFrame().makeControlFrame();
			
    }//GEN-LAST:event_connectBtnActionPerformed


    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JButton connectBtn;
    private javax.swing.JButton disconnectBtn;
    private javax.swing.JLabel statusPtyLabel;
    // End of variables declaration//GEN-END:variables

    /**
     * connects or disconnects (if connected) to/from B.E.R.T.A.
     */
    public void connect() {

	}
    
}

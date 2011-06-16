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


import controller.MainController;



/**
 *
 * @author Morten Hulvej
 */
public class CommFrame extends javax.swing.JInternalFrame {

    /** Creates new form CommFrame */
    public CommFrame() {
        initComponents();
        FramePlaceHolder.setCommFrame(this);
    }

    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        robotRadioBtnGroup = new javax.swing.ButtonGroup();
        connectBtn = new javax.swing.JButton();
        disconnectBtn = new javax.swing.JButton();
        BertaRadioBtn = new javax.swing.JRadioButton();
        PropRadioBtn = new javax.swing.JRadioButton();
        BothRadioBtn = new javax.swing.JRadioButton();
        jSeparator1 = new javax.swing.JSeparator();

        setIconifiable(true);
        setTitle("Kommunikation");
        setNormalBounds(new java.awt.Rectangle(450, 290, 234, 133));

        connectBtn.setText("Connect");
        connectBtn.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                connectBtnActionPerformed(evt);
            }
        });

        disconnectBtn.setText("Disconnect");
        disconnectBtn.setEnabled(false);
        disconnectBtn.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                disconnectBtnActionPerformed(evt);
            }
        });

        robotRadioBtnGroup.add(BertaRadioBtn);
        BertaRadioBtn.setSelected(true);
        BertaRadioBtn.setText("B.E.R.T.A.");
        BertaRadioBtn.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                BertaRadioBtnActionPerformed(evt);
            }
        });

        robotRadioBtnGroup.add(PropRadioBtn);
        PropRadioBtn.setText("P.R.O.P.");
        PropRadioBtn.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                PropRadioBtnActionPerformed(evt);
            }
        });

        robotRadioBtnGroup.add(BothRadioBtn);
        BothRadioBtn.setText("Begge Robotter");

        jSeparator1.setOrientation(javax.swing.SwingConstants.VERTICAL);

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(connectBtn)
                    .addComponent(disconnectBtn))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addComponent(jSeparator1, javax.swing.GroupLayout.PREFERRED_SIZE, 13, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(BertaRadioBtn)
                    .addComponent(PropRadioBtn)
                    .addComponent(BothRadioBtn))
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );

        layout.linkSize(javax.swing.SwingConstants.HORIZONTAL, new java.awt.Component[] {connectBtn, disconnectBtn});

        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                    .addGroup(layout.createSequentialGroup()
                        .addComponent(BertaRadioBtn)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(PropRadioBtn)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(BothRadioBtn))
                    .addGroup(layout.createSequentialGroup()
                        .addComponent(connectBtn)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(disconnectBtn))
                    .addComponent(jSeparator1))
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );

        pack();
    }// </editor-fold>//GEN-END:initComponents

    /**
     * @param evt
     */
    private void connectBtnActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_connectBtnActionPerformed
    		 connectBtn.setEnabled(false);
                 disconnectBtn.setEnabled(true);
           if(BertaRadioBtn.isSelected()){
                    PropRadioBtn.setEnabled(false);
                     BothRadioBtn.setEnabled(false);
                     connect(0);
                 }
           if(PropRadioBtn.isSelected()){
                BertaRadioBtn.setEnabled(false);
                 BothRadioBtn.setEnabled(false);
                 connect(1);
           }
           if(BothRadioBtn.isSelected()){
               BertaRadioBtn.setEnabled(false);
               PropRadioBtn.setEnabled(false);
               connect();
           }
        //connect();
    }//GEN-LAST:event_connectBtnActionPerformed

    private void PropRadioBtnActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_PropRadioBtnActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_PropRadioBtnActionPerformed

    private void BertaRadioBtnActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_BertaRadioBtnActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_BertaRadioBtnActionPerformed

    private void disconnectBtnActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_disconnectBtnActionPerformed
        connectBtn.setEnabled(true);
        disconnectBtn.setEnabled(false);
        
        BertaRadioBtn.setEnabled(true);
        PropRadioBtn.setEnabled(true);
        BothRadioBtn.setEnabled(true);
        
    }//GEN-LAST:event_disconnectBtnActionPerformed


    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JRadioButton BertaRadioBtn;
    private javax.swing.JRadioButton BothRadioBtn;
    private javax.swing.JRadioButton PropRadioBtn;
    private javax.swing.JButton connectBtn;
    private javax.swing.JButton disconnectBtn;
    private javax.swing.JSeparator jSeparator1;
    private javax.swing.ButtonGroup robotRadioBtnGroup;
    // End of variables declaration//GEN-END:variables

  
    /**
     * connects or disconnects (if connected) to/from B.E.R.T.A.
     */
    public void connect() {
    	
    	// initializing control unit
    	MainController.getInstance().initialize();
    	
    	FramePlaceHolder.getMainFrame().makeProcessingFrame();
			
	}    
}

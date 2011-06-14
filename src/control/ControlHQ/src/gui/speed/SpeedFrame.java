package gui.speed;

import controller.Thresholds;
import gui.FramePlaceHolder;

/**
 *
 * @author Morten Hulvej
 */
public class SpeedFrame extends javax.swing.JInternalFrame {

    /** Creates new form SpeedFrame */
    public SpeedFrame() {
        initComponents(); 
        
        initListenerAndValues();
        FramePlaceHolder.setSpeedFrame(this);
    }


    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {
        java.awt.GridBagConstraints gridBagConstraints;
        bindingGroup = new org.jdesktop.beansbinding.BindingGroup();

        speedPanel = new javax.swing.JPanel();
        jLabel1 = new javax.swing.JLabel();
        slowSpeedSlider = new javax.swing.JSlider();
        jLabel2 = new javax.swing.JLabel();
        mediumSpeedSlider = new javax.swing.JSlider();
        jLabel3 = new javax.swing.JLabel();
        highSpeedSlider = new javax.swing.JSlider();
        slowSpeedLabel = new javax.swing.JLabel();
        mediumSpeedLabel = new javax.swing.JLabel();
        highSpeedLabel = new javax.swing.JLabel();

        setClosable(true);
        setTitle("Hastighed");

        speedPanel.setLayout(new java.awt.GridBagLayout());

        jLabel1.setText("Lav Hastighed . . . . . .");
        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.gridx = 0;
        gridBagConstraints.gridy = 0;
        gridBagConstraints.anchor = java.awt.GridBagConstraints.NORTHWEST;
        gridBagConstraints.insets = new java.awt.Insets(8, 0, 0, 0);
        speedPanel.add(jLabel1, gridBagConstraints);

        slowSpeedSlider.setMajorTickSpacing(10);
        slowSpeedSlider.setPaintTicks(true);
        slowSpeedSlider.setFocusable(false);
        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.gridx = 1;
        gridBagConstraints.gridy = 0;
        gridBagConstraints.anchor = java.awt.GridBagConstraints.NORTHWEST;
        gridBagConstraints.insets = new java.awt.Insets(8, 0, 0, 0);
        speedPanel.add(slowSpeedSlider, gridBagConstraints);

        jLabel2.setText("Mellem Hastighed . . . .");
        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.gridx = 0;
        gridBagConstraints.gridy = 1;
        gridBagConstraints.anchor = java.awt.GridBagConstraints.NORTHWEST;
        speedPanel.add(jLabel2, gridBagConstraints);

        mediumSpeedSlider.setMajorTickSpacing(10);
        mediumSpeedSlider.setPaintTicks(true);
        mediumSpeedSlider.setFocusable(false);
        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.gridx = 1;
        gridBagConstraints.gridy = 1;
        gridBagConstraints.anchor = java.awt.GridBagConstraints.NORTHWEST;
        speedPanel.add(mediumSpeedSlider, gridBagConstraints);

        jLabel3.setText("Høj Hastighed . . . . . .");
        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.gridx = 0;
        gridBagConstraints.gridy = 2;
        gridBagConstraints.anchor = java.awt.GridBagConstraints.NORTHWEST;
        speedPanel.add(jLabel3, gridBagConstraints);

        highSpeedSlider.setMajorTickSpacing(10);
        highSpeedSlider.setMaximum(50);
        highSpeedSlider.setPaintTicks(true);
        highSpeedSlider.setValue(25);
        highSpeedSlider.setFocusable(false);
        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.gridx = 1;
        gridBagConstraints.gridy = 2;
        gridBagConstraints.anchor = java.awt.GridBagConstraints.NORTHWEST;
        speedPanel.add(highSpeedSlider, gridBagConstraints);

        slowSpeedLabel.setFont(new java.awt.Font("Tahoma", 1, 11));

        org.jdesktop.beansbinding.Binding binding = org.jdesktop.beansbinding.Bindings.createAutoBinding(org.jdesktop.beansbinding.AutoBinding.UpdateStrategy.READ_WRITE, slowSpeedSlider, org.jdesktop.beansbinding.ELProperty.create("${value}"), slowSpeedLabel, org.jdesktop.beansbinding.BeanProperty.create("text"));
        bindingGroup.addBinding(binding);

        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.gridx = 2;
        gridBagConstraints.gridy = 0;
        gridBagConstraints.anchor = java.awt.GridBagConstraints.WEST;
        gridBagConstraints.insets = new java.awt.Insets(8, 0, 0, 0);
        speedPanel.add(slowSpeedLabel, gridBagConstraints);

        mediumSpeedLabel.setFont(new java.awt.Font("Tahoma", 1, 11));

        binding = org.jdesktop.beansbinding.Bindings.createAutoBinding(org.jdesktop.beansbinding.AutoBinding.UpdateStrategy.READ_WRITE, mediumSpeedSlider, org.jdesktop.beansbinding.ELProperty.create("${value}"), mediumSpeedLabel, org.jdesktop.beansbinding.BeanProperty.create("text"));
        bindingGroup.addBinding(binding);

        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.gridx = 2;
        gridBagConstraints.gridy = 1;
        gridBagConstraints.anchor = java.awt.GridBagConstraints.WEST;
        speedPanel.add(mediumSpeedLabel, gridBagConstraints);

        highSpeedLabel.setFont(new java.awt.Font("Tahoma", 1, 11));

        binding = org.jdesktop.beansbinding.Bindings.createAutoBinding(org.jdesktop.beansbinding.AutoBinding.UpdateStrategy.READ_WRITE, highSpeedSlider, org.jdesktop.beansbinding.ELProperty.create("${value}"), highSpeedLabel, org.jdesktop.beansbinding.BeanProperty.create("text"));
        bindingGroup.addBinding(binding);

        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.gridx = 2;
        gridBagConstraints.gridy = 2;
        gridBagConstraints.anchor = java.awt.GridBagConstraints.WEST;
        speedPanel.add(highSpeedLabel, gridBagConstraints);

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(speedPanel, javax.swing.GroupLayout.DEFAULT_SIZE, 346, Short.MAX_VALUE)
                .addContainerGap())
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(speedPanel, javax.swing.GroupLayout.DEFAULT_SIZE, 109, Short.MAX_VALUE)
                .addContainerGap())
        );

        bindingGroup.bind();

        pack();
    }// </editor-fold>//GEN-END:initComponents

    private void initListenerAndValues() {
    	// TODO  virker ikke
    	
    	SpeedChangeListener sl = new SpeedChangeListener();
    	slowSpeedSlider.addChangeListener(sl);
    	mediumSpeedSlider.addChangeListener(sl);
    	highSpeedSlider.addChangeListener(sl);
    	
    	Thresholds t = Thresholds.getInstance();
		slowSpeedSlider.setValue(t.getSlowSpeed());
		mediumSpeedSlider.setValue(t.getMediumSpeed());
		highSpeedSlider.setValue(t.getHighSpeed());
	}

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JLabel highSpeedLabel;
    private javax.swing.JLabel jLabel1;
    private javax.swing.JLabel jLabel2;
    private javax.swing.JLabel jLabel3;
    private javax.swing.JLabel slowSpeedLabel;
    javax.swing.JSlider highSpeedSlider;
    javax.swing.JSlider mediumSpeedSlider;
    private javax.swing.JLabel mediumSpeedLabel;
    javax.swing.JSlider slowSpeedSlider;
    private javax.swing.JPanel speedPanel;
    private org.jdesktop.beansbinding.BindingGroup bindingGroup;
    // End of variables declaration//GEN-END:variables

}

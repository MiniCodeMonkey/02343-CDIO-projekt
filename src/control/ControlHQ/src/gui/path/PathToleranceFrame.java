package gui.path;

import gui.FramePlaceHolder;

import javax.swing.JSlider;
import javax.swing.JSpinner;

import controller.Thresholds;

/**
 *
 * @author Morten Hulvej
 */
public class PathToleranceFrame extends javax.swing.JInternalFrame {

    /** Creates new form PathFinder */
    public PathToleranceFrame() {
        initComponents();
        initValues();
        
        FramePlaceHolder.setPathFrame(this);
    }
    
    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {
        java.awt.GridBagConstraints gridBagConstraints;
        bindingGroup = new org.jdesktop.beansbinding.BindingGroup();

        angleTolPanel = new javax.swing.JPanel();
        jLabel1 = new javax.swing.JLabel();
        jLabel2 = new javax.swing.JLabel();
        roughAngleToleranceSpinner = new javax.swing.JSpinner();
        fineAngleToleranceSpinner = new javax.swing.JSpinner();
        distanceTolPanel = new javax.swing.JPanel();
        jLabel3 = new javax.swing.JLabel();
        cakePickupToleranceSlider = new javax.swing.JSlider();
        jLabel4 = new javax.swing.JLabel();
        cakeDeliveryToleranceSlider = new javax.swing.JSlider();
        cakePickupTolLabel = new javax.swing.JLabel();
        cakeDeliveryTolLabel = new javax.swing.JLabel();

        setClosable(true);
        setTitle("Tolerancer");

        angleTolPanel.setBorder(javax.swing.BorderFactory.createTitledBorder("Vinkel tolerance for robot"));
        angleTolPanel.setLayout(new java.awt.GridBagLayout());

        jLabel1.setText("Grov");
        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.gridx = 0;
        gridBagConstraints.gridy = 0;
        gridBagConstraints.anchor = java.awt.GridBagConstraints.WEST;
        gridBagConstraints.insets = new java.awt.Insets(3, 0, 3, 0);
        angleTolPanel.add(jLabel1, gridBagConstraints);

        jLabel2.setText("Fin");
        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.gridx = 0;
        gridBagConstraints.gridy = 1;
        gridBagConstraints.anchor = java.awt.GridBagConstraints.WEST;
        gridBagConstraints.insets = new java.awt.Insets(3, 0, 3, 0);
        angleTolPanel.add(jLabel2, gridBagConstraints);

        roughAngleToleranceSpinner.setModel(new javax.swing.SpinnerNumberModel(3.0d, 3.0d, 30.0d, 1.0d));
        roughAngleToleranceSpinner.setPreferredSize(new java.awt.Dimension(70, 20));
        roughAngleToleranceSpinner.addChangeListener(new javax.swing.event.ChangeListener() {
            public void stateChanged(javax.swing.event.ChangeEvent evt) {
                roughAngleToleranceSpinnerStateChanged(evt);
            }
        });
        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.gridx = 1;
        gridBagConstraints.gridy = 0;
        gridBagConstraints.insets = new java.awt.Insets(0, 19, 0, 19);
        angleTolPanel.add(roughAngleToleranceSpinner, gridBagConstraints);

        fineAngleToleranceSpinner.setModel(new javax.swing.SpinnerNumberModel(1.0d, 1.0d, 10.0d, 0.5d));
        fineAngleToleranceSpinner.setPreferredSize(new java.awt.Dimension(70, 20));
        fineAngleToleranceSpinner.addChangeListener(new javax.swing.event.ChangeListener() {
            public void stateChanged(javax.swing.event.ChangeEvent evt) {
                fineAngleToleranceSpinnerStateChanged(evt);
            }
        });
        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.gridx = 1;
        gridBagConstraints.gridy = 1;
        angleTolPanel.add(fineAngleToleranceSpinner, gridBagConstraints);

        distanceTolPanel.setBorder(javax.swing.BorderFactory.createTitledBorder("Afstand tolerance for robot"));
        distanceTolPanel.setLayout(new java.awt.GridBagLayout());

        jLabel3.setText("Kage opsamling");
        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.gridx = 0;
        gridBagConstraints.gridy = 0;
        gridBagConstraints.anchor = java.awt.GridBagConstraints.WEST;
        distanceTolPanel.add(jLabel3, gridBagConstraints);

        cakePickupToleranceSlider.setMajorTickSpacing(10);
        cakePickupToleranceSlider.setMaximum(60);
        cakePickupToleranceSlider.setMinorTickSpacing(1);
        cakePickupToleranceSlider.setPaintTicks(true);

        org.jdesktop.beansbinding.Binding binding = org.jdesktop.beansbinding.Bindings.createAutoBinding(org.jdesktop.beansbinding.AutoBinding.UpdateStrategy.READ_WRITE, cakePickupToleranceSlider, org.jdesktop.beansbinding.ELProperty.create("${value}"), cakePickupToleranceSlider, org.jdesktop.beansbinding.BeanProperty.create("toolTipText"));
        bindingGroup.addBinding(binding);

        cakePickupToleranceSlider.addChangeListener(new javax.swing.event.ChangeListener() {
            public void stateChanged(javax.swing.event.ChangeEvent evt) {
                cakePickupToleranceSliderStateChanged(evt);
            }
        });
        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.gridx = 0;
        gridBagConstraints.gridy = 1;
        gridBagConstraints.gridwidth = java.awt.GridBagConstraints.REMAINDER;
        gridBagConstraints.anchor = java.awt.GridBagConstraints.WEST;
        distanceTolPanel.add(cakePickupToleranceSlider, gridBagConstraints);

        jLabel4.setText("Kage aflevering");
        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.gridx = 0;
        gridBagConstraints.gridy = 2;
        gridBagConstraints.anchor = java.awt.GridBagConstraints.WEST;
        distanceTolPanel.add(jLabel4, gridBagConstraints);

        cakeDeliveryToleranceSlider.setMajorTickSpacing(10);
        cakeDeliveryToleranceSlider.setMaximum(60);
        cakeDeliveryToleranceSlider.setMinorTickSpacing(1);
        cakeDeliveryToleranceSlider.setPaintTicks(true);

        binding = org.jdesktop.beansbinding.Bindings.createAutoBinding(org.jdesktop.beansbinding.AutoBinding.UpdateStrategy.READ_WRITE, cakeDeliveryToleranceSlider, org.jdesktop.beansbinding.ELProperty.create("${value}"), cakeDeliveryToleranceSlider, org.jdesktop.beansbinding.BeanProperty.create("toolTipText"));
        bindingGroup.addBinding(binding);

        cakeDeliveryToleranceSlider.addChangeListener(new javax.swing.event.ChangeListener() {
            public void stateChanged(javax.swing.event.ChangeEvent evt) {
                cakeDeliveryToleranceSliderStateChanged(evt);
            }
        });
        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.gridx = 0;
        gridBagConstraints.gridy = 3;
        gridBagConstraints.gridwidth = java.awt.GridBagConstraints.REMAINDER;
        gridBagConstraints.anchor = java.awt.GridBagConstraints.WEST;
        distanceTolPanel.add(cakeDeliveryToleranceSlider, gridBagConstraints);

        cakePickupTolLabel.setFont(new java.awt.Font("Tahoma", 1, 11));

        binding = org.jdesktop.beansbinding.Bindings.createAutoBinding(org.jdesktop.beansbinding.AutoBinding.UpdateStrategy.READ_WRITE, cakePickupToleranceSlider, org.jdesktop.beansbinding.ELProperty.create("${value}"), cakePickupTolLabel, org.jdesktop.beansbinding.BeanProperty.create("text"));
        bindingGroup.addBinding(binding);

        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.gridx = 2;
        gridBagConstraints.gridy = 0;
        distanceTolPanel.add(cakePickupTolLabel, gridBagConstraints);

        cakeDeliveryTolLabel.setFont(new java.awt.Font("Tahoma", 1, 11));

        binding = org.jdesktop.beansbinding.Bindings.createAutoBinding(org.jdesktop.beansbinding.AutoBinding.UpdateStrategy.READ_WRITE, cakeDeliveryToleranceSlider, org.jdesktop.beansbinding.ELProperty.create("${value}"), cakeDeliveryTolLabel, org.jdesktop.beansbinding.BeanProperty.create("text"));
        bindingGroup.addBinding(binding);

        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.gridx = 2;
        gridBagConstraints.gridy = 2;
        distanceTolPanel.add(cakeDeliveryTolLabel, gridBagConstraints);

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                    .addComponent(distanceTolPanel, javax.swing.GroupLayout.Alignment.LEADING, javax.swing.GroupLayout.DEFAULT_SIZE, 240, Short.MAX_VALUE)
                    .addComponent(angleTolPanel, javax.swing.GroupLayout.Alignment.LEADING, javax.swing.GroupLayout.DEFAULT_SIZE, 240, Short.MAX_VALUE))
                .addContainerGap())
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(angleTolPanel, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(distanceTolPanel, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );

        getAccessibleContext().setAccessibleName("");

        bindingGroup.bind();

        pack();
    }// </editor-fold>//GEN-END:initComponents

        private void initValues() {
		Thresholds values = Thresholds.getInstance();
		
		System.out.println(values.getRotationClose());
		
		roughAngleToleranceSpinner.setValue(values.getRotationFairlyClose());
		fineAngleToleranceSpinner.setValue(values.getRotationClose());
		
		cakePickupToleranceSlider.setValue(values.getCloseEnoughToCake());
		cakeDeliveryToleranceSlider.setValue(values.getCloseEnoughToDelivery());
		
	}

        private void roughAngleToleranceSpinnerStateChanged(javax.swing.event.ChangeEvent evt) {//GEN-FIRST:event_roughAngleToleranceSpinnerStateChanged
		    if (evt.getSource() instanceof JSpinner) {
		    	JSpinner spinner = (JSpinner) evt.getSource();
				
		    	Thresholds.getInstance().setRotationFairlyClose((Double) spinner.getValue());
			}
		}//GEN-LAST:event_roughAngleToleranceSpinnerStateChanged

		private void fineAngleToleranceSpinnerStateChanged(javax.swing.event.ChangeEvent evt) {//GEN-FIRST:event_fineAngleToleranceSpinnerStateChanged
			if (evt.getSource() instanceof JSpinner) {
		    	JSpinner spinner = (JSpinner) evt.getSource();
				
		    	Thresholds.getInstance().setRotationClose((Double) spinner.getValue());
			}
		}//GEN-LAST:event_fineAngleToleranceSpinnerStateChanged

		private void cakePickupToleranceSliderStateChanged(javax.swing.event.ChangeEvent evt) {//GEN-FIRST:event_cakePickupToleranceSliderStateChanged
        	if (evt.getSource() instanceof JSlider) {
				JSlider slider = (JSlider) evt.getSource();
				
				Thresholds.getInstance().setCloseEnoughToCake(slider.getValue());
				
			}
        }//GEN-LAST:event_cakePickupToleranceSliderStateChanged

        private void cakeDeliveryToleranceSliderStateChanged(javax.swing.event.ChangeEvent evt) {//GEN-FIRST:event_cakeDeliveryToleranceSliderStateChanged
        	if (evt.getSource() instanceof JSlider) {
				JSlider slider = (JSlider) evt.getSource();
				
				Thresholds.getInstance().setCloseEnoughToDelivery(slider.getValue());
				
			}
        }//GEN-LAST:event_cakeDeliveryToleranceSliderStateChanged

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JPanel angleTolPanel;
    private javax.swing.JLabel cakeDeliveryTolLabel;
    private javax.swing.JSlider cakeDeliveryToleranceSlider;
    private javax.swing.JLabel cakePickupTolLabel;
    private javax.swing.JSlider cakePickupToleranceSlider;
    private javax.swing.JPanel distanceTolPanel;
    private javax.swing.JSpinner fineAngleToleranceSpinner;
    private javax.swing.JLabel jLabel1;
    private javax.swing.JLabel jLabel2;
    private javax.swing.JLabel jLabel3;
    private javax.swing.JLabel jLabel4;
    private javax.swing.JSpinner roughAngleToleranceSpinner;
    private org.jdesktop.beansbinding.BindingGroup bindingGroup;
    // End of variables declaration//GEN-END:variables

}

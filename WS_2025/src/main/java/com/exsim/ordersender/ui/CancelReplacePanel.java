package com.exsim.ordersender.ui;

import javax.swing.*;
import java.awt.*;
import java.awt.event.*;

import com.exsim.ordersender.OrderSenderApplication;
import com.exsim.ordersender.DoubleNumberTextField;
import com.exsim.ordersender.IntegerNumberTextField;
import com.exsim.ordersender.Order;


public class CancelReplacePanel extends JPanel {
    private final JLabel quantityLabel = new JLabel("Quantity");
    private final JLabel limitPriceLabel = new JLabel("Limit");
    private final IntegerNumberTextField quantityTextField = new IntegerNumberTextField();
    private final DoubleNumberTextField limitPriceTextField = new DoubleNumberTextField();
    private final JButton cancelButton = new JButton("Cancel");
    private final JButton replaceButton = new JButton("Replace");
    private Order order = null;

    private final GridBagConstraints constraints = new GridBagConstraints();

    private final OrderSenderApplication application;

    public CancelReplacePanel(final OrderSenderApplication application) {
        this.application = application;
        cancelButton.addActionListener(new CancelListener());
        replaceButton.addActionListener(new ReplaceListener());

        setLayout(new GridBagLayout());
        createComponents();
    }

    public void addActionListener(ActionListener listener) {
        cancelButton.addActionListener(listener);
        replaceButton.addActionListener(listener);
    }

    private void createComponents() {
        constraints.fill = GridBagConstraints.BOTH;
        constraints.weightx = 1;

        int x = 0;
        int y = 0;

        constraints.insets = new Insets(0, 0, 5, 5);
        add(cancelButton, x, y);
        add(replaceButton, ++x, y);
        constraints.weightx = 0;
        add(quantityLabel, ++x, y);
        constraints.weightx = 5;
        add(quantityTextField, ++x, y);
        constraints.weightx = 0;
        add(limitPriceLabel, ++x, y);
        constraints.weightx = 5;
        add(limitPriceTextField, ++x, y);
    }

    public void setEnabled(boolean enabled) {
        cancelButton.setEnabled(enabled);
        replaceButton.setEnabled(enabled);
        quantityTextField.setEnabled(enabled);
        limitPriceTextField.setEnabled(enabled);

        Color labelColor = enabled ? Color.black : Color.gray;
        Color bgColor = enabled ? Color.white : Color.gray;
        quantityTextField.setBackground(bgColor);
        limitPriceTextField.setBackground(bgColor);
        quantityLabel.setForeground(labelColor);
        limitPriceLabel.setForeground(labelColor);
    }

    public void update() {
        setOrder(this.order);
    }

    public void setOrder(Order order) {
        if (order == null)
            return;
        this.order = order;
        quantityTextField.setText
        (Integer.toString(order.getOpen()));

        Double limit = order.getLimit();
        if (limit != null)
            limitPriceTextField.setText(order.getLimit().toString());
        setEnabled(order.getOpen() > 0);
    }

    private JComponent add(JComponent component, int x, int y) {
        constraints.gridx = x;
        constraints.gridy = y;
        add(component, constraints);
        return component;
    }

    private class CancelListener implements ActionListener {
        public void actionPerformed(ActionEvent e) {
            application.cancel(order);
        }
    }

    private class ReplaceListener implements ActionListener {
        public void actionPerformed(ActionEvent e) {
            if(order == null){
                JOptionPane.showMessageDialog(null, "No Order Selected To Amend");
            }

            Order newOrder = (Order) order.clone();
            //Take The Latest values from the field
            int newQuantity = Integer.parseInt(quantityTextField.getText());
            double newLimitPrice = Double.parseDouble(limitPriceTextField.getText());

            newOrder.setQuantity(newQuantity);
            newOrder.setLimit(newLimitPrice);
            newOrder.setRejected(false);
            newOrder.setCanceled(false);
            newOrder.setOpen(0);
            newOrder.setExecuted(0);
            application.replace(order, newOrder);
        }
    }
}

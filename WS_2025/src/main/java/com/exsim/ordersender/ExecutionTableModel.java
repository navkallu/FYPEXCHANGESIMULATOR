package com.exsim.ordersender;

import java.util.HashMap;

import javax.swing.table.AbstractTableModel;

public class ExecutionTableModel extends AbstractTableModel {

    private final static int SYMBOL = 0;
    private final static int QUANTITY = 1;
    private final static int SIDE = 2;
    private final static int PRICE = 3;

    private final HashMap<Integer, Execution> rowToExecution;
    private final HashMap<String, Integer> idToRow;
    private final HashMap<String, Execution> idToExecution;
    private final HashMap<String, Execution> exchangeIdToExecution;

    private final String[] headers;

    public ExecutionTableModel() {
        rowToExecution = new HashMap<Integer, Execution>();
        idToRow = new HashMap<String, Integer>();
        idToExecution = new HashMap<String, Execution>();
        exchangeIdToExecution = new HashMap<String, Execution>();

        headers = new String[] {"Symbol", "Quantity", "Side", "Price"};
    }

    public boolean isCellEditable(int rowIndex, int columnIndex) {
        return false;
    }

    public void addExecution(Execution execution) {
        int row = rowToExecution.size();

        if (exchangeIdToExecution.get(execution.getExchangeID()) != null)
            return;

        rowToExecution.put(row, execution);
        idToRow.put(execution.getID(), row);
        idToExecution.put(execution.getID(), execution);
        exchangeIdToExecution.put(execution.getExchangeID(), execution);

        fireTableRowsInserted(row, row);
    }

    public Execution getExchangeExecution(String exchangeID) {
        return exchangeIdToExecution.get(exchangeID);
    }

    public Execution getExecution(int row) {
        return rowToExecution.get(Integer.valueOf(row));
    }

    public void setValueAt(Object value, int rowIndex, int columnIndex) { }

    public Class<String> getColumnClass(int columnIndex) {
        return String.class;
    }

    public int getRowCount() {
        return rowToExecution.size();
    }

    public int getColumnCount() {
        return headers.length;
    }

    public String getColumnName(int columnIndex) {
        return headers[columnIndex];
    }

    public Object getValueAt(int rowIndex, int columnIndex) {
        Execution execution = rowToExecution.get(Integer.valueOf(rowIndex));

        switch (columnIndex) {
        case SYMBOL:
            return execution.getSymbol();
        case QUANTITY:
            return execution.getQuantity();
        case SIDE:
            return execution.getSide();
        case PRICE:
            return execution.getPrice();
        }
        return "";
    }
}

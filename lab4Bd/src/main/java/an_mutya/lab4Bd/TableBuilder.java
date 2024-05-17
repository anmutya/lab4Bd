/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package an_mutya.lab4Bd;

import java.util.Map;
import javax.swing.table.DefaultTableModel;

/**
 *
 * @author annamutovkina
 */
public class TableBuilder {
    private DefaultTableModel createRows(DefaultTableModel model ,Map<String, Map<Integer, Double>> result ){
        for (Map.Entry<String, Map<Integer, Double>> entry : result.entrySet()) {
            String country = entry.getKey();
            Map<Integer, Double> dataByYear = entry.getValue();
            for (Map.Entry<Integer, Double> yearData : dataByYear.entrySet()) {
                Object[] rowData = {country, yearData.getKey(), yearData.getValue()};
                model.addRow(rowData);
            }
        }
        return model;
    }

    public DefaultTableModel createTableForCounry(Map<String, Map<Integer, Double>> result) {
        DefaultTableModel tableModel = new DefaultTableModel();
        tableModel.addColumn("Страна");
        tableModel.addColumn("Год");
        tableModel.addColumn("Объем ежегодного потребления, т.");

        return createRows(tableModel,result);
    }
        public DefaultTableModel createTableForRegion(Map<String, Map<Integer, Double>> result) {
        DefaultTableModel tableModel = new DefaultTableModel();
        tableModel.addColumn("Регион");
        tableModel.addColumn("Год");
        tableModel.addColumn("Объем ежегодного потребления, т.");
        return createRows(tableModel,result);
    }
    public DefaultTableModel createTableForOperator(Map<String, Map<Integer, Double>> result) {
        DefaultTableModel tableModel = new DefaultTableModel();
        tableModel.addColumn("Оператор");
        tableModel.addColumn("Год");
        tableModel.addColumn("Объем ежегодного потребления, т.");

        return createRows(tableModel,result);
    }
    public DefaultTableModel createTableForOwner(Map<String, Map<Integer, Double>> result) {
        DefaultTableModel tableModel = new DefaultTableModel();
        tableModel.addColumn("Владелец");
        tableModel.addColumn("Год");
        tableModel.addColumn("Объем ежегодного потребления, т.");

        return createRows(tableModel,result);
    }

}

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

    public DefaultTableModel createTableFromCounry(Map<String, Map<Integer, Double>> result) {
        DefaultTableModel tableModel = new DefaultTableModel();
        tableModel.addColumn("Страна");
        tableModel.addColumn("Год");
        tableModel.addColumn("Объем ежегодного потребления, т.");

        for (Map.Entry<String, Map<Integer, Double>> entry : result.entrySet()) {
            String country = entry.getKey();
            Map<Integer, Double> dataByYear = entry.getValue();
            for (Map.Entry<Integer, Double> yearData : dataByYear.entrySet()) {
                Object[] rowData = {country, yearData.getKey(), yearData.getValue()};
                tableModel.addRow(rowData);
            }
        }
        return tableModel;
    }
        public DefaultTableModel createTableFromRegion(Map<String, Map<Integer, Double>> result) {
        DefaultTableModel tableModel = new DefaultTableModel();
        tableModel.addColumn("Регион");
        tableModel.addColumn("Год");
        tableModel.addColumn("Объем ежегодного потребления, т.");

        for (Map.Entry<String, Map<Integer, Double>> entry : result.entrySet()) {
            String country = entry.getKey();
            Map<Integer, Double> dataByYear = entry.getValue();
            for (Map.Entry<Integer, Double> yearData : dataByYear.entrySet()) {
                Object[] rowData = {country, yearData.getKey(), yearData.getValue()};
                tableModel.addRow(rowData);
            }
        }
        return tableModel;
    }
        
    public DefaultTableModel createTableFromOwnersAndOperators( Map<String, Map<String, Map<Integer, Double>>> result) {
        DefaultTableModel tableModel = new DefaultTableModel();
        tableModel.addColumn("Владелец");
        tableModel.addColumn("Оператор");
        tableModel.addColumn("Год");
        tableModel.addColumn("Объем ежегодного потребления, т.");
    for (Map.Entry<String, Map<String, Map<Integer, Double>>> ownerEntry : result.entrySet()) {
        String owner = ownerEntry.getKey();
        Map<String, Map<Integer, Double>> operatorMap = ownerEntry.getValue();
        for (Map.Entry<String, Map<Integer, Double>> operatorEntry : operatorMap.entrySet()) {
            String operator = operatorEntry.getKey();
            Map<Integer, Double> dataByYear = operatorEntry.getValue();
            for (Map.Entry<Integer, Double> yearData : dataByYear.entrySet()) {
                Object[] rowData = {owner, operator, yearData.getKey(), yearData.getValue()};
                tableModel.addRow(rowData);
            }
        }
    }
        return tableModel;
    }
}

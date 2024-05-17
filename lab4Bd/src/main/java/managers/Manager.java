/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package managers;


import agregationAndCalculation.ReactorRepository;
import agregationAndCalculation.ReactorService;
import an_mutya.lab4Bd.TableBuilder;
import an_mutya.lab4Bd.TreeBuilder;
import entityForDataBase.ReadEntitiesForDB;
import entityForDataBase.ExportEntitiesToDB;

import javax.swing.tree.DefaultMutableTreeNode;

import handlers.JSONhandler;
import handlers.XMLhandler;
import handlers.YAMLhandler;
import reactor.ReactorStorage;
import reader.JsonReader;
import reader.XmlReader;
import reader.YamlReader;

import java.io.IOException;
import java.sql.SQLException;
import java.util.Map;
import javax.swing.table.DefaultTableModel;

/**
 * @author annamutovkina
 */
public class Manager {

    private ReadEntitiesForDB entities = new ReadEntitiesForDB();
    private SessionManager sessionManager = new SessionManager();

    public void closeSession() {
        sessionManager.closeSession();
    }

    public DefaultTableModel createTableForCounry() {
        TableBuilder builder = new TableBuilder();
        return builder.createTableForCounry(agregationByCountry());
    }

    public DefaultTableModel createTableForRegion() {
        TableBuilder builder = new TableBuilder();
        return builder.createTableForRegion(agregationByRegion());
    }

    public DefaultTableModel createTableForOperator() {
        TableBuilder builder = new TableBuilder();
        return builder.createTableForOperator(agregationByOperators());
    }
    public DefaultTableModel createTableForOwner() {
        TableBuilder builder = new TableBuilder();
        return builder.createTableForOwner(agregationByOwner());
    }

    private Map<String, Map<Integer, Double>> agregationByOwner() {
        ReactorRepository reactorRepository = new ReactorRepository(sessionManager.getSession());
        ReactorService reactorService = new ReactorService(reactorRepository);
        Map<String, Map<Integer, Double>> result = reactorService.calculateReactorEnergyByOwner();
        return result;
    }

    private Map<String, Map<Integer, Double>> agregationByCountry() {
        ReactorRepository reactorRepository = new ReactorRepository(sessionManager.getSession());
        ReactorService reactorService = new ReactorService(reactorRepository);
        Map<String, Map<Integer, Double>> result = reactorService.calculateReactorEnergyByCountryAndYear();
        return result;
    }

    private Map<String, Map<Integer, Double>> agregationByOperators() {
        ReactorRepository reactorRepository = new ReactorRepository(sessionManager.getSession());
        ReactorService reactorService = new ReactorService(reactorRepository);
        Map<String, Map<Integer, Double>> result = reactorService.calculateReactorEnergyByOperator();
        return result;
    }

//    public Map<String, Map<String, Map<Integer, Double>>> agregationByOwnerAndOperator() {
//        ReactorRepository reactorRepository = new ReactorRepository(sessionManager.getSession());
//        ReactorService reactorService = new ReactorService(reactorRepository);
//        Map<String, Map<String, Map<Integer, Double>>> result = reactorService.calculateReactorEnergyByOwnerAndOperator();
//        return result;
//    }

    public Map<String, Map<Integer, Double>> agregationByRegion() {
        ReactorRepository reactorRepository = new ReactorRepository(sessionManager.getSession());
        ReactorService reactorService = new ReactorService(reactorRepository);
        Map<String, Map<Integer, Double>> result = reactorService.calculateReactorEnergyByRegionAndYear();
        return result;
    }

    public void exportToDB() throws SQLException, IOException {
        ExportEntitiesToDB exportEntitiesToDB = new ExportEntitiesToDB();
//        exportEntitiesToDB.extortStatusToDB(sessionManager.getSession(), entities.readStatusForExcel());
//        exportEntitiesToDB.extortRegionsToDB(sessionManager.getSession(), entities.readRegionsForExcel());
//        exportEntitiesToDB.extortCountriesToDB(sessionManager.getSession(), entities.readCountriesForExcel());
//        exportEntitiesToDB.extortOperatorsToDB(sessionManager.getSession(), entities.readOperatorsForExcel());
//        exportEntitiesToDB.extortOwnersToDB(sessionManager.getSession(), entities.readOwnersForExcel());
//        exportEntitiesToDB.extortReactorsToDB(sessionManager.getSession(), entities.readReactorsForExcel());
        exportEntitiesToDB.extortKiumsToDB(sessionManager.getSession(), entities.readKiumForExcel());
        exportEntitiesToDB.extortOwnersAndReactorsToDB(sessionManager.getSession(), entities.readOwnersAndReactorsForExcel());
    }

    public void loadFile(String path) {
        YAMLhandler yAMLhandler = new YAMLhandler(new YamlReader());
        JSONhandler jSONhandler = new JSONhandler(new JsonReader());
        XMLhandler xMLhandler = new XMLhandler(new XmlReader());
        yAMLhandler.setNext(jSONhandler);
        jSONhandler.setNext(xMLhandler);
        yAMLhandler.handleRequest(path);

    }

    public DefaultMutableTreeNode buildTree() {
        TreeBuilder treeBuilder = new TreeBuilder();
        return treeBuilder.buildTreeData();
    }

    public void deleteFiles() {
        ReactorStorage.INSTANCE.clearReactors();
    }
}

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
import org.hibernate.Session;
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

    public DefaultTableModel createTableFromCounry(){  
        TableBuilder builder = new TableBuilder();
        return builder.createTableFromCounry(agregationByCountry());
    }
    public DefaultTableModel createTableFromRegion() {  
        TableBuilder builder = new TableBuilder();
        return builder.createTableFromRegion(agregationByRegion());
    }
    public DefaultTableModel createTableFromOwnerAndOperator() {  
        TableBuilder builder = new TableBuilder();
        return builder.createTableFromOwnersAndOperators(agregationByOwnerAndOperator());
    }
    private Map<String, Map<Integer, Double>> agregationByCountry() {
        ReactorRepository reactorRepository = new ReactorRepository(sessionManager.getSession());
        ReactorService reactorService = new ReactorService(reactorRepository);
        Map<String, Map<Integer, Double>> result = reactorService.calculateReactorEnergyByCountryAndYear();
        return result;
    }

    public Map<String, Map<String, Map<Integer, Double>>> agregationByOwnerAndOperator() {
        ReactorRepository reactorRepository = new ReactorRepository(sessionManager.getSession());
        ReactorService reactorService = new ReactorService(reactorRepository);
        Map<String, Map<String, Map<Integer, Double>>> result = reactorService.calculateReactorEnergyByOwnerAndOperator();
        return result;
    }

    public Map<String, Map<Integer, Double>> agregationByRegion() {
        ReactorRepository reactorRepository = new ReactorRepository( sessionManager.getSession());
        ReactorService reactorService = new ReactorService(reactorRepository);
        Map<String, Map<Integer, Double>> result = reactorService.calculateReactorEnergyByRegionAndYear();
        return result;
    }

    public void exportToDB() {
        ExportEntitiesToDB exportEntitiesToDB = new ExportEntitiesToDB();
        try {
            exportEntitiesToDB.extortStatusToDB(sessionManager.getSession(), entities.readStatusForExcel());
            exportEntitiesToDB.extortRegionsToDB(sessionManager.getSession(), entities.readRegionsForExcel());
            exportEntitiesToDB.extortCountriesToDB(sessionManager.getSession(), entities.readCountriesForExcel());
            exportEntitiesToDB.extortOperatorsToDB(sessionManager.getSession(), entities.readOperatorsForExcel());
            exportEntitiesToDB.extortOwnersToDB(sessionManager.getSession(), entities.readOwnersForExcel());
            exportEntitiesToDB.extortReactorsToDB(sessionManager.getSession(), entities.readReactorsForExcel());
            exportEntitiesToDB.extortKiumsToDB(sessionManager.getSession(), entities.readKiumForExcel());
        } catch (SQLException e) {
            throw new RuntimeException(e);
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
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

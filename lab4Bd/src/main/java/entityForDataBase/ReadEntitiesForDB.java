package entityForDataBase;

import entities.*;

import java.io.IOException;
import java.util.ArrayList;
import java.util.Objects;
import java.util.logging.Level;
import java.util.logging.Logger;
import org.apache.poi.ss.usermodel.DataFormatter;
import org.apache.poi.ss.usermodel.Row;
import org.apache.poi.xssf.usermodel.XSSFSheet;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;

/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
/**
 *
 * @author annamutovkina
 */
public class ReadEntitiesForDB {

    private String path = Objects.requireNonNull(getClass().getClassLoader().getResource("reactors2.xlsx")).getPath();

    public ArrayList<Reactors> readReactorsFromExcel() throws IOException {
        ArrayList<Reactors> reactors = new ArrayList<>();
        String nameSheet = "reactor";
        DataFormatter formatter = new DataFormatter();
        try (XSSFWorkbook myExcelFWorkbook = new XSSFWorkbook(path)) {
            XSSFSheet myExcelSheet = myExcelFWorkbook.getSheet(nameSheet);
            for (int i = 1; i <= myExcelSheet.getLastRowNum(); i++) {
                Reactors reactor = new Reactors();
                Row row = myExcelSheet.getRow(i);
                String cellValue = formatter.formatCellValue(row.getCell(0));
                reactor.setId(Integer.valueOf(cellValue));
                reactor.setName(row.getCell(1).getStringCellValue());
                reactor.setType(row.getCell(2).getStringCellValue());
                reactor.setModel(row.getCell(3).getStringCellValue());
                int statusId = (int) (row.getCell(4).getNumericCellValue());
                if (statusId != 0) {
                    Status status = new Status();
                    status.setId(Integer.valueOf(statusId));
                    reactor.setStatus(status);
                }
                int operatorId = (int) (row.getCell(5).getNumericCellValue());
                if (operatorId != 0) {
                    Operators operator = new Operators();
                    operator.setId(Integer.valueOf(operatorId));
                    reactor.setOperator(operator);
                }
                cellValue = formatter.formatCellValue(row.getCell(6));
                reactor.setThermalCapacity(Integer.valueOf(cellValue));
                reactor.setFirstGridConnection(row.getCell(7).getStringCellValue());
                reactor.setShutdownDate(row.getCell(8).getStringCellValue());

                int countryId = (int) (row.getCell(9).getNumericCellValue());
                if (countryId != 0) {
                    Countries country = new Countries();
                    country.setId(Integer.valueOf(countryId));
                    reactor.setCountry(country);
                }
                reactors.add(reactor);
            }
            myExcelFWorkbook.close();
        }
        return reactors;

    }

    public ArrayList<Operators> readOperatorsFromExcel() {
        ArrayList<Operators> operatorses = new ArrayList();
        String nameSheet = "operators";
        try (XSSFWorkbook myExcelFWorkbook = new XSSFWorkbook(path)) {
            DataFormatter formatter = new DataFormatter();
            XSSFSheet myExcelSheet = myExcelFWorkbook.getSheet(nameSheet);
            for (int i = 1; i <= myExcelSheet.getLastRowNum(); i++) {
                Operators op = new Operators();
                Row row = myExcelSheet.getRow(i);
                String cellValue = formatter.formatCellValue(row.getCell(0));
                op.setId(Integer.valueOf(cellValue));
                op.setName(row.getCell(1).getStringCellValue());
                operatorses.add(op);
            }
            myExcelFWorkbook.close();
        } catch (IOException ex) {
            Logger.getLogger(ReadEntitiesForDB.class.getName()).log(Level.SEVERE, null, ex);
        }
        return operatorses;
    }

    public ArrayList<Owners> readOwnersFromExcel() {
        ArrayList<Owners> owners = new ArrayList();
        String nameSheet = "owners";
        try (XSSFWorkbook myExcelFWorkbook = new XSSFWorkbook(path)) {
            DataFormatter formatter = new DataFormatter();
            XSSFSheet myExcelSheet = myExcelFWorkbook.getSheet(nameSheet);
            for (int i = 1; i <= myExcelSheet.getLastRowNum(); i++) {
                Owners owner = new Owners();
                Row row = myExcelSheet.getRow(i);
                String cellValue = formatter.formatCellValue(row.getCell(0));
                owner.setId(Integer.valueOf(cellValue));
                owner.setName(row.getCell(1).getStringCellValue());
                owners.add(owner);
            }
            myExcelFWorkbook.close();
        } catch (IOException ex) {
            Logger.getLogger(ReadEntitiesForDB.class.getName()).log(Level.SEVERE, null, ex);
        }
        return owners;
    }

    public ArrayList<Regions> readRegionsFromExcel() {
        ArrayList<Regions> regions = new ArrayList();
        String nameSheet = "regions";
        try (XSSFWorkbook myExcelFWorkbook = new XSSFWorkbook(path)) {
            DataFormatter formatter = new DataFormatter();
            XSSFSheet myExcelSheet = myExcelFWorkbook.getSheet(nameSheet);
            for (int i = 1; i <= myExcelSheet.getLastRowNum(); i++) {
                Regions region = new Regions();
                Row row = myExcelSheet.getRow(i);
                String cellValue = formatter.formatCellValue(row.getCell(0));
                region.setId(Integer.valueOf(cellValue));
                region.setName(row.getCell(1).getStringCellValue());
                regions.add(region);
            }
            myExcelFWorkbook.close();
        } catch (IOException ex) {
            Logger.getLogger(ReadEntitiesForDB.class.getName()).log(Level.SEVERE, null, ex);
        }
        return regions;
    }
public ArrayList<OwnersAndReactors> readOwnersAndReactorsFromExcel() {
        ArrayList<OwnersAndReactors> owners = new ArrayList();
        String nameSheet = "ownersAndReactors";
    try (XSSFWorkbook myExcelFWorkbook = new XSSFWorkbook(path)) {
        DataFormatter formatter = new DataFormatter();
        XSSFSheet myExcelSheet = myExcelFWorkbook.getSheet(nameSheet);
        for (int i = 1; i <= myExcelSheet.getLastRowNum(); i++) {
            OwnersAndReactors ownersAndReactors = new OwnersAndReactors();
            Row row = myExcelSheet.getRow(i);
            int reactorId = (int) (row.getCell(0).getNumericCellValue());
            if (reactorId != 0) {
                Reactors reactors = new Reactors();
                reactors.setId(Integer.valueOf(reactorId));
                ownersAndReactors.setReactor(reactors);
            }
            int ownerId = (int) (row.getCell(1).getNumericCellValue());
            if (ownerId != 0) {
                Owners owner = new Owners();
                owner.setId(Integer.valueOf(ownerId));
                ownersAndReactors.setOwner(owner);
            }
            owners.add(ownersAndReactors);
        }
        myExcelFWorkbook.close();
    } catch (IOException ex) {
        Logger.getLogger(ReadEntitiesForDB.class.getName()).log(Level.SEVERE, null, ex);
    }
    return owners;
}
    public ArrayList<Countries> readCountriesFromExcel() {
        ArrayList<Countries> countries = new ArrayList();
        String nameSheet = "countries";
        try (XSSFWorkbook myExcelFWorkbook = new XSSFWorkbook(path)) {
            DataFormatter formatter = new DataFormatter();
            XSSFSheet myExcelSheet = myExcelFWorkbook.getSheet(nameSheet);
            for (int i = 1; i <= myExcelSheet.getLastRowNum(); i++) {
                Countries country = new Countries();
                Row row = myExcelSheet.getRow(i);
                String cellValue = formatter.formatCellValue(row.getCell(0));
                country.setId(Integer.valueOf(cellValue));
                int regionId = (int) (row.getCell(1).getNumericCellValue());
                if (regionId != 0) {
                    Regions region = new Regions();
                    region.setId(Integer.valueOf(regionId));
                    country.setRegion(region);
                }
                country.setName(row.getCell(2).getStringCellValue());
                countries.add(country);
            }
            myExcelFWorkbook.close();
        } catch (IOException ex) {
            Logger.getLogger(ReadEntitiesForDB.class.getName()).log(Level.SEVERE, null, ex);
        }
        return countries;
    }

    public ArrayList<Status> readStatusFromExcel() {
        ArrayList<Status> statuses = new ArrayList();
        String nameSheet = "status";
        try (XSSFWorkbook myExcelFWorkbook = new XSSFWorkbook(path)) {
            DataFormatter formatter = new DataFormatter();
            XSSFSheet myExcelSheet = myExcelFWorkbook.getSheet(nameSheet);
            for (int i = 1; i <= myExcelSheet.getLastRowNum(); i++) {
                Status status = new Status();
                Row row = myExcelSheet.getRow(i);
                String cellValue = formatter.formatCellValue(row.getCell(0));
                status.setId(Integer.valueOf(cellValue));
                status.setName(row.getCell(1).getStringCellValue());
                statuses.add(status);
            }
            myExcelFWorkbook.close();
        } catch (IOException ex) {
            Logger.getLogger(ReadEntitiesForDB.class.getName()).log(Level.SEVERE, null, ex);
        }
        return statuses;
    }

    public ArrayList<Kium> readKiumFromExcel() {
        ArrayList<Kium> kiums = new ArrayList();
        String nameSheet = "kium";
        try (XSSFWorkbook myExcelFWorkbook = new XSSFWorkbook(path)) {
            DataFormatter formatter = new DataFormatter();
            XSSFSheet myExcelSheet = myExcelFWorkbook.getSheet(nameSheet);
            for (int i = 1; i <= myExcelSheet.getLastRowNum(); i++) {
                Kium kium = new Kium();
                Row row = myExcelSheet.getRow(i);
                int reactorId = (int) (row.getCell(0).getNumericCellValue());
                if (reactorId != 0) {
                    Reactors reactor = new Reactors();
                    reactor.setId(Integer.valueOf(reactorId));
                    kium.setReactor(reactor);
                }
                String cellValue = formatter.formatCellValue(row.getCell(1));
                kium.setYear(Integer.valueOf(cellValue));
                if ((int)row.getCell(2).getNumericCellValue() != 0) {
                    kium.setLoadFactor(Double.valueOf(row.getCell(2).getNumericCellValue()));
                }
                kiums.add(kium);
            }
            myExcelFWorkbook.close();
        } catch (IOException ex) {
            Logger.getLogger(ReadEntitiesForDB.class.getName()).log(Level.SEVERE, null, ex);
        }
        return kiums;
    }
}

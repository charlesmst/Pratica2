/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package br.com.empresa.rh.util;

import java.util.Collection;
import java.util.HashMap;
import java.util.Map;
import net.sf.jasperreports.engine.JRException;
import net.sf.jasperreports.engine.JasperCompileManager;
import net.sf.jasperreports.engine.JasperExportManager;
import net.sf.jasperreports.engine.JasperFillManager;
import net.sf.jasperreports.engine.JasperPrint;
import net.sf.jasperreports.engine.JasperReport;
import net.sf.jasperreports.engine.data.JRBeanCollectionDataSource;
import org.springframework.stereotype.Service;
import org.apache.commons.collections.map.ReferenceMap;
/**
 *
 * @author charles
 */
@Service
public class Relatorios {

    public JasperReport generateJasperFile(String jrxml) throws JRException {
        return JasperCompileManager.compileReport(jrxml);
    }

    public byte[] generateReport(String jrxml, Collection dataSource, Map parameters) throws JRException {
        JRBeanCollectionDataSource jrs = new JRBeanCollectionDataSource(dataSource);
        JasperPrint jp = JasperFillManager.fillReport(generateJasperFile(jrxml), parameters, jrs);
        return JasperExportManager.exportReportToPdf(jp);
    }
}

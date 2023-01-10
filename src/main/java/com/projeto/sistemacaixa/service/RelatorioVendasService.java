package com.projeto.sistemacaixa.service;

import java.io.IOException;
import java.sql.Connection;
import java.sql.SQLException;
import java.sql.Timestamp;
import java.util.Date;
import java.util.HashMap;
import java.util.Map;

import javax.annotation.Resource;
import javax.sql.DataSource;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;
import org.springframework.util.StringUtils;

import net.sf.jasperreports.engine.JRException;
import net.sf.jasperreports.engine.JasperCompileManager;
import net.sf.jasperreports.engine.JasperExportManager;
import net.sf.jasperreports.engine.JasperFillManager;
import net.sf.jasperreports.engine.JasperPrint;
import net.sf.jasperreports.engine.JasperReport;
import net.sf.jasperreports.engine.JasperRunManager;

@Service
public class RelatorioVendasService {
	
	@Value("classpath:reports/relatorio-vendas.jrxml")
	private org.springframework.core.io.Resource relatorioVendasSource;
	
	@Value("classpath:reports/relatorio-vendas.jasper")
	private org.springframework.core.io.Resource relatorioVendasCompilado;
	
	@Autowired
	private DataSource dataSource;
	
public byte[] gerarRelatorio(Long idCliente, Timestamp dataInicio, Timestamp dataFim) {
		
		// try with resources
		
		try (
			Connection connection = dataSource.getConnection();
		) {
		
		Map<String, Object> parametros = new HashMap<>();
		parametros.put("ID_CLIENTE", idCliente);
		parametros.put("DATA_INICIO", dataInicio);
		parametros.put("DATA_FIM", dataFim);
		
		System.out.println(dataInicio);
		System.out.println(dataFim);
		
		return JasperRunManager.runReportToPdf(relatorioVendasCompilado.getInputStream(), parametros, connection);
		
		} catch (SQLException e) {
			
			e.printStackTrace();
		} catch (JRException e) {
			e.printStackTrace();
		} catch (IOException e) {
			e.printStackTrace();
		}
		
		return null;
	}
	
public byte[] gerarRelatorioCompilado() {
		
		// try with resources
		
		try (
			Connection connection = dataSource.getConnection();
		) {
		
		Map<String, Object> parametros = new HashMap<>();
		JasperPrint print = JasperFillManager.fillReport(relatorioVendasCompilado.getInputStream(), parametros, connection);
		
		return JasperExportManager.exportReportToPdf(print);
		
		} catch (SQLException e) {
			
			e.printStackTrace();
		} catch (JRException e) {
			e.printStackTrace();
		} catch (IOException e) {
			e.printStackTrace();
		}
		
		return null;
	}

public byte[] gerarRelatorioCompilando() {
	//try with resources
	try (
		Connection connection = dataSource.getConnection();	
	) {
		
		JasperReport compiledReport = JasperCompileManager
								.compileReport(relatorioVendasSource.getInputStream());
		Map<String, Object> paramentros = new HashMap<>();
		
		JasperPrint print = JasperFillManager
								.fillReport(compiledReport, paramentros, connection);
		
		return JasperExportManager.exportReportToPdf(print);
		
	} catch (SQLException e) {
		e.printStackTrace();
	} catch (JRException e) {
		e.printStackTrace();
	} catch (IOException e) {
		e.printStackTrace();
	}
	
	return null;
}


	
}

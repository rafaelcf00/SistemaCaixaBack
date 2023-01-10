package com.projeto.sistemacaixa.util;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.time.Instant;
import java.time.LocalDate;
import java.sql.Timestamp;
import java.time.LocalDateTime;
import java.time.LocalTime;
import java.time.ZoneId;
import java.time.format.DateTimeFormatter;
import java.util.Date;

import javax.swing.JLabel;

import org.springframework.util.StringUtils;

public class DateUtils {
	
	private static final String PADRAO_FORMATACAO_DATA = "dd/MM/yyyy";
	public static final Timestamp DATA_INICIO_PADRAO;
	static {
		DATA_INICIO_PADRAO = DateUtils.fromString("01/01/1970");
	}

	public static Timestamp fromString(String dataString) {
		return fromString(dataString, false);
	}
	
	public static Timestamp fromString(String dataString, boolean atEndOfDay) {
		
		if (!StringUtils.hasText(dataString)) {
			return null;
		}
		
		LocalDate data = LocalDate.parse(dataString, DateTimeFormatter.ofPattern(PADRAO_FORMATACAO_DATA));
		
		LocalDateTime dataHora;
		
		if (atEndOfDay) {
			dataHora = data.atTime(23,59);
			
		} else {
			dataHora = data.atStartOfDay();
			
		}
		Instant instant = dataHora.atZone(ZoneId.systemDefault()).toInstant();
		return Timestamp.from(instant);
	}

	public static Timestamp hoje(boolean atEndOfDay) {
		String dataHojeFormatada = LocalDate.now().format(DateTimeFormatter.ofPattern(PADRAO_FORMATACAO_DATA));
		return fromString(dataHojeFormatada, atEndOfDay);
	}
}

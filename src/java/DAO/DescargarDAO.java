/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package DAO;

import Clases.ConexionPostgreSQL;
import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.text.ParseException;
import org.apache.poi.ss.usermodel.Cell;
import org.apache.poi.ss.usermodel.CellStyle;
import org.apache.poi.ss.usermodel.CreationHelper;
import org.apache.poi.ss.usermodel.FillPatternType;
import org.apache.poi.ss.usermodel.Font;
import org.apache.poi.ss.usermodel.IndexedColors;
import org.apache.poi.ss.usermodel.Row;
import org.apache.poi.ss.usermodel.Sheet;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;
import org.apache.poi.ss.usermodel.Workbook;

/**
 *
 * @author DHL-SIS-ING
 */
public class DescargarDAO {
    
    private ConexionPostgreSQL connecPostgresql;
    
    public File descargarDatosAlumnos()throws ParseException, SQLException{
        File file = null;
        try {
            connecPostgresql = new ConexionPostgreSQL();
            ResultSet consulta;
            connecPostgresql.callableStatement = connecPostgresql.connection.prepareCall("{call mostrar_estudiantes_todo()}");
            consulta = connecPostgresql.callableStatement.executeQuery();
            try (Workbook workbook = new XSSFWorkbook();) {
                Sheet sh = workbook.createSheet("Datos Alumnos");
                String[] columnHeadings = {"N","curso", "identificacion", "nacionalidad","apellidos", "nombres", "fecha de nacimiento", "dirección", "numero de hermanos",
                    "lugar que ocupa","correo personal", "celular", "tipo de discapacidad", "discapacidad","carnet de discapacidad","historia clinica",
                    "convivencia","padre identificacion", "padre nacionalidad","padre apellidos","padre nombres","codigo único electrónico",
                    "padre celular","padre ocupacion","lugar de trabajo", "padre correo","madre identificacion","madre nacionalidad","madre apellidos",
                    "madre nombres", "codigo único electrónico","madre celular","madre ocupacion", "lugar de trabajo",
                    "madre correo", "referencia identificacion", "referencia apelldios","referencias nombres",
                    "referencia celular","referencia telefono"};
                Font headerFont = workbook.createFont();
                headerFont.setBold(true);
                headerFont.setFontHeightInPoints((short) 12);
                headerFont.setColor(IndexedColors.BLACK.index);
                //Create a CellStyle with the font
                CellStyle headerStyle = workbook.createCellStyle();
                headerStyle.setFont(headerFont);
                headerStyle.setFillPattern(FillPatternType.SOLID_FOREGROUND);
                headerStyle.setFillForegroundColor(IndexedColors.GREY_25_PERCENT.index);
                //Create the header row
                Row headerRow = sh.createRow(0);
                //Iterate over the column headings to create columns
                for (int i = 0; i < columnHeadings.length; i++) {
                    Cell cell = headerRow.createCell(i);
                    cell.setCellValue(columnHeadings[i]);
                    cell.setCellStyle(headerStyle);
                }
                CreationHelper creationHelper = workbook.getCreationHelper();
                CellStyle dateStyle = workbook.createCellStyle();
                dateStyle.setDataFormat(creationHelper.createDataFormat().getFormat("dd/MM/yyyy"));
                int rownum = 1;
                while (consulta.next()) {
                    Row row = sh.createRow(rownum++);
                    row.createCell(0).setCellValue(rownum-1);
                    row.createCell(1).setCellValue(consulta.getString("curso"));
                    row.createCell(2).setCellValue(consulta.getString("identificacion"));
                    row.createCell(3).setCellValue(consulta.getString("nacionalidad"));
                    row.createCell(4).setCellValue(consulta.getString("apellidos"));
                    row.createCell(5).setCellValue(consulta.getString("nombres"));
                    Cell dateCell = row.createCell(6);
                    dateCell.setCellValue(consulta.getDate("fecha_nacimiento"));
                    dateCell.setCellStyle(dateStyle);
                    row.createCell(7).setCellValue(consulta.getString("direccion"));
                    row.createCell(8).setCellValue(consulta.getInt("numero_hermanos"));
                    row.createCell(9).setCellValue(consulta.getInt("lugar_ocupa"));
                    row.createCell(10).setCellValue(consulta.getString("correo_personal"));
                    row.createCell(11).setCellValue(consulta.getString("celular"));
                    row.createCell(12).setCellValue(consulta.getString("tipo_discapacidad"));
                    row.createCell(13).setCellValue(consulta.getString("discapacidad"));
                    row.createCell(14).setCellValue(consulta.getString("carnet_discapacidad"));
                    row.createCell(15).setCellValue(consulta.getString("historia_clinica"));
                    row.createCell(16).setCellValue(consulta.getString("convivencia"));
                    row.createCell(17).setCellValue(consulta.getString("padre_identificacion"));
                    row.createCell(18).setCellValue(consulta.getString("padre_nacionalidad"));
                    row.createCell(19).setCellValue(consulta.getString("padre_apellidos"));
                    row.createCell(20).setCellValue(consulta.getString("padre_nombres"));
                    row.createCell(21).setCellValue(consulta.getString("padre_codigo_unico_electronico"));
                    row.createCell(22).setCellValue(consulta.getString("padre_celular"));
                    row.createCell(23).setCellValue(consulta.getString("padre_ocupacion"));
                    row.createCell(24).setCellValue(consulta.getString("padre_lugar_trabajo"));
                    row.createCell(25).setCellValue(consulta.getString("padre_correo"));
                    row.createCell(26).setCellValue(consulta.getString("madre_identificacion"));
                    row.createCell(27).setCellValue(consulta.getString("madre_nacionalidad"));
                    row.createCell(28).setCellValue(consulta.getString("madre_apellidos"));
                    row.createCell(29).setCellValue(consulta.getString("madre_nombres"));
                    row.createCell(30).setCellValue(consulta.getString("madre_codigo_unico_electronico"));
                    row.createCell(31).setCellValue(consulta.getString("madre_celular"));
                    row.createCell(32).setCellValue(consulta.getString("madre_ocupacion"));
                    row.createCell(33).setCellValue(consulta.getString("madre_lugar_trabajo"));
                    row.createCell(34).setCellValue(consulta.getString("madre_correo"));
                    row.createCell(35).setCellValue(consulta.getString("ref_identificacion"));
                    row.createCell(36).setCellValue(consulta.getString("ref_apelldios"));
                    row.createCell(37).setCellValue(consulta.getString("ref_nombres"));
                    row.createCell(38).setCellValue(consulta.getString("ref_celular"));
                    row.createCell(39).setCellValue(consulta.getString("ref_telefono"));
                }
                //Autosize columns
                for(int i=0;i<columnHeadings.length;i++) {
                    sh.autoSizeColumn(i);
                }
                file = File.createTempFile("Alumnos", ".xlsx");
                FileOutputStream report = new FileOutputStream(file.getPath());
                workbook.write(report);
                //Cerramos el fichero excel
                workbook.close();
                //Cerramos el InputStream
                report.flush();
                report.close();
            }catch(IOException e){
                connecPostgresql.getConnection().close();
                System.out.println(e.getMessage());
                return file;
            }catch(Exception e){
                connecPostgresql.getConnection().close();
                System.out.println(e.getMessage());
                return file;
            }
            System.out.println("Completed");
        } catch (SQLException ex) {
            connecPostgresql.getConnection().close();
            System.out.println(ex.getMessage());
            return file;
        }
        connecPostgresql.getConnection().close();
        return file;
    }
    
    public File descargarDatosEmpleados()throws ParseException, SQLException{
        File file = null;
        try {
            connecPostgresql = new ConexionPostgreSQL();
            ResultSet consulta;
            connecPostgresql.callableStatement = connecPostgresql.connection.prepareCall("{call mostrar_empleados_todo()}");
            consulta = connecPostgresql.callableStatement.executeQuery();
            try (Workbook workbook = new XSSFWorkbook();) {
                Sheet sh = workbook.createSheet("Datos Empleados");
                String[] columnHeadings = {"N", "cargo","identificacion", "nacionalidad", "apellidos", "nombres", "estado civil",
                                    "género", "direccion", "cantón", "telefono", "celular", "fecha de nacimiento",
                                    "correo personal", "correo institucional", "contrato", "escalafón"};
                Font headerFont = workbook.createFont();
                headerFont.setBold(true);
                headerFont.setFontHeightInPoints((short) 12);
                headerFont.setColor(IndexedColors.BLACK.index);
                //Create a CellStyle with the font
                CellStyle headerStyle = workbook.createCellStyle();
                headerStyle.setFont(headerFont);
                headerStyle.setFillPattern(FillPatternType.SOLID_FOREGROUND);
                headerStyle.setFillForegroundColor(IndexedColors.GREY_25_PERCENT.index);
                //Create the header row
                Row headerRow = sh.createRow(0);
                //Iterate over the column headings to create columns
                for (int i = 0; i < columnHeadings.length; i++) {
                    Cell cell = headerRow.createCell(i);
                    cell.setCellValue(columnHeadings[i]);
                    cell.setCellStyle(headerStyle);
                }
                CreationHelper creationHelper = workbook.getCreationHelper();
                CellStyle dateStyle = workbook.createCellStyle();
                dateStyle.setDataFormat(creationHelper.createDataFormat().getFormat("dd/MM/yyyy"));
                int rownum = 1;
                while (consulta.next()) {
                    Row row = sh.createRow(rownum++);
                    row.createCell(0).setCellValue(rownum-1);
                    row.createCell(1).setCellValue(consulta.getString("nombre_cargo"));
                    row.createCell(2).setCellValue(consulta.getString("identificacion"));
                    row.createCell(3).setCellValue(consulta.getString("nacionalidad"));
                    row.createCell(4).setCellValue(consulta.getString("apellidos"));
                    row.createCell(5).setCellValue(consulta.getString("nombres"));
                    row.createCell(6).setCellValue(consulta.getString("estado_civil"));
                    String genero = consulta.getString("genero");
                    if(genero != null){
                        genero = ("F".equals(genero))? "Femenino": "Masculino";
                    }else{ genero = ""; }
                    row.createCell(7).setCellValue(genero);
                    row.createCell(8).setCellValue(consulta.getString("direccion"));
                    row.createCell(9).setCellValue(consulta.getString("nombre_canton"));
                    row.createCell(10).setCellValue(consulta.getString("telefono"));
                    row.createCell(11).setCellValue(consulta.getString("celular"));
                    Cell dateCell = row.createCell(12);
                    dateCell.setCellValue(consulta.getDate("fecha_nacimiento"));
                    dateCell.setCellStyle(dateStyle);
                    row.createCell(13).setCellValue(consulta.getString("correo_personal"));
                    row.createCell(14).setCellValue(consulta.getString("correo_institucional"));
                    row.createCell(15).setCellValue(consulta.getString("contrato"));
                    row.createCell(16).setCellValue(consulta.getString("escalafon"));
                }
                //Autosize columns
                for(int i=0;i<columnHeadings.length;i++) {
                    sh.autoSizeColumn(i);
                }
                file = File.createTempFile("Empleados", ".xlsx");
                FileOutputStream report = new FileOutputStream(file.getPath());
                workbook.write(report);
                //Cerramos el fichero excel
                workbook.close();
                //Cerramos el InputStream
                report.flush();
                report.close();
            }catch(IOException e){
                connecPostgresql.getConnection().close();
                System.out.println(e.getMessage());
                return file;
            }catch(Exception e){
                connecPostgresql.getConnection().close();
                System.out.println(e.getMessage());
                return file;
            }
            System.out.println("Completed");
        }catch (SQLException ex) {
            connecPostgresql.getConnection().close();
            System.out.println(ex.getMessage());
            return file;
        }
        connecPostgresql.getConnection().close();
        return file;
    }
}

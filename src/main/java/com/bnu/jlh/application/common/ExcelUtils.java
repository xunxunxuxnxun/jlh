package com.bnu.jlh.application.common;

import org.apache.poi.hssf.usermodel.*;
import org.apache.poi.hssf.util.HSSFColor;
import org.apache.poi.ss.usermodel.*;

import java.io.InputStream;
import java.io.OutputStream;
import java.math.BigDecimal;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

public class ExcelUtils {
    private String getValue(Cell cell) {
        String value = null;

        if (null == cell) {
            return value;
        }

        switch (cell.getCellType()) {
            //数值型
            case Cell.CELL_TYPE_NUMERIC:
                if (HSSFDateUtil.isCellDateFormatted(cell)) {
                    //如果是date类型则 ，获取该cell的date值
                    Date date = HSSFDateUtil.getJavaDate(cell.getNumericCellValue());
                    SimpleDateFormat format = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
                    value = format.format(date);
                } else {// 纯数字
                    BigDecimal big = new BigDecimal(cell.getNumericCellValue());
                    value = big.toString();
                    //解决1234.0  去掉后面的.0
                    if (null != value && !"".equals(value.trim())) {
                        String[] item = value.split("[.]");
                        if (1 < item.length && "0".equals(item[1])) {
                            value = item[0];
                        }
                    }
                }
                break;
            case Cell.CELL_TYPE_STRING:
                value = cell.getStringCellValue().toString();
                break;
            case Cell.CELL_TYPE_FORMULA:
                value = cell.getCellFormula().toString();
                break;
            case Cell.CELL_TYPE_BOOLEAN:
                value = " " + cell.getBooleanCellValue();
                break;
            case Cell.CELL_TYPE_BLANK:
                value = "";
                break;
            case Cell.CELL_TYPE_ERROR:
                value = "";
                break;
            default:
                value = cell.getStringCellValue().toString();
        }

        if ("null".endsWith(value.trim())) {
            value = "";
        }

        return value;
    }

    public List<ExcelSheet> readExcel(InputStream inputStream) throws Exception {
        Workbook workbook = WorkbookFactory.create(inputStream);
        List<ExcelSheet> list = new ArrayList<>();

        for (int numSheet = 0; numSheet < workbook.getNumberOfSheets(); numSheet++) {
            Sheet sheet = workbook.getSheetAt(numSheet);

            if (sheet == null) {
                continue;
            }

            ExcelSheet excelSheet = new ExcelSheet();
            excelSheet.setName(sheet.getSheetName());

            for (int rowNum = 0; rowNum <= sheet.getLastRowNum(); rowNum++) {
                Row row = sheet.getRow(rowNum);

                if (row != null) {
                    ExcelRow excelRow = new ExcelRow();

                    for (int cellNum = 0; cellNum < row.getLastCellNum(); cellNum++) {
                        excelRow.add(getValue(row.getCell(cellNum)));
                    }

                    excelSheet.add(excelRow);
                }else{
                    excelSheet.add(new ExcelRow());
                }
            }

            list.add(excelSheet);
        }

        return list;
    }

    public void writeExcel(OutputStream outputStream, List<ExcelSheet> list) throws Exception {
        HSSFWorkbook wb = new HSSFWorkbook();

        HSSFCellStyle style = wb.createCellStyle();
        style.setBorderBottom(HSSFCellStyle.BORDER_THIN);
        style.setBorderLeft(HSSFCellStyle.BORDER_THIN);
        style.setBorderTop(HSSFCellStyle.BORDER_THIN);
        style.setBorderRight(HSSFCellStyle.BORDER_THIN);

        HSSFCellStyle firstStyle = wb.createCellStyle();
        firstStyle.cloneStyleFrom(style);
        firstStyle.setFillForegroundColor(HSSFColor.GREY_40_PERCENT.index);
        firstStyle.setFillPattern(HSSFCellStyle.SOLID_FOREGROUND);
        firstStyle.setAlignment(HSSFCellStyle.ALIGN_CENTER);

        HSSFRow row;
        HSSFCell cell;

        if(list != null && list.size() > 0) {
            for (ExcelSheet excelSheet : list) {
                HSSFSheet sheet = wb.createSheet(excelSheet.getName());
                sheet.setDisplayGridlines(false);
                sheet.createFreezePane( 0, 1, 0, 1 );
                int colCount = 0;

                for (int i = 0; i < excelSheet.size(); i++) {
                    ExcelRow excelRow = excelSheet.get(i);
                    row = sheet.createRow(i);

                    for (int j = 0; j < excelRow.size(); j++) {
                        cell = row.createCell(j);
                        cell.setCellStyle(style);
                        String content = excelRow.get(j);
                        if(/*StringUtils.startsWith(content, "number::")  */content.startsWith("number::")) {
                            cell.setCellValue(Double.valueOf(content.replace("number::", "")));
                        } else {
                            cell.setCellValue(content);
                        }
                        if(colCount<j){
                            colCount = j;
                        }

                        if (i == 0) {
                            cell.setCellStyle(firstStyle);
                        }
                    }
                }

                for(int i=0;i<=colCount;i++){
                    sheet.autoSizeColumn(i);
                }
            }
        }

        wb.write(outputStream);
        outputStream.close();
    }
}

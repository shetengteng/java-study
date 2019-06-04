poi

pom

```xml
<dependency>
    <groupId>org.apache.poi</groupId>
    <artifactId>poi-ooxml</artifactId>
    <version>3.17</version>
</dependency>
```



```java
package io.renren.common.utils;

import io.renren.common.exception.RRException;
import org.apache.poi.hssf.usermodel.*;
import org.apache.poi.xssf.streaming.SXSSFSheet;
import org.apache.poi.xssf.streaming.SXSSFWorkbook;
import org.apache.poi.ss.usermodel.*;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.util.StringUtils;

import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.net.URLEncoder;
import java.util.List;

/**
 * @author stt
 * @create 2017/11/6 21:54
 */
public class ExcelUtil {

    private static final String HSSF = "hssf";
    private static final String XSSF = "xssf";


    /**
     * 导出HSSF格式 excel97
     *
     * @param titles
     * @param sheetName
     * @param createInfoRows
     * @return
     */
    public static Workbook getExcelEntity(String[] titles, String sheetName, CreateInfoRows createInfoRows) {
        return getExcelEntity(titles, sheetName, createInfoRows, HSSF);
    }

    /**
     * excel 2007
     *
     * @param titles
     * @param sheetName
     * @param createInfoRows
     * @returnwb
     */
    public static Workbook getExcelXEntity(String[] titles, String sheetName, CreateInfoRows createInfoRows) {
        return getExcelEntity(titles, sheetName, createInfoRows, XSSF);
    }

    private static Workbook getExcelEntity(String[] titles, String sheetName, CreateInfoRows createInfoRows, String type) {
        // 解析成excel表格输出
        Workbook wb = null;
        if (HSSF.equals(type)) {
            HSSFWorkbook hssfWb = new HSSFWorkbook();
            hssfWb.createInformationProperties();
            hssfWb.getDocumentSummaryInformation().setCompany("科大讯飞");
            wb = hssfWb;
        } else {
            SXSSFWorkbook sxssWb = new SXSSFWorkbook(1000);//缓存
            sxssWb.setCompressTempFiles(true);
            wb = sxssWb;
        }
        //1.创建用户的excel的sheet表
        Sheet sheet = wb.createSheet(sheetName);
        //2.填充标题行
        createTileRow(sheet, titles);
        //3.填充信息
        createInfoRows.invoke(sheet);
        //4.设置样式，包括行高，列宽，边框
        setCellStyle(wb, sheetName, titles, type);
        return wb;
    }

    /**
     * 填充标题行
     *
     * @param sheet
     */
    private static void createTileRow(Sheet sheet, String[] titles) {
        Row row = sheet.createRow(0);
        for (int i = 0; i < titles.length; i++) {
            row.createCell(i).setCellValue(titles[i]);
        }
    }

    /**
     * 设置表格的风格 hssf
     *
     * @param wb
     */
    private static void setCellStyle(Workbook wb, String sheetName, String[] titles, String type) {

        if (XSSF.equals(type)) {
            SXSSFSheet sheet = (SXSSFSheet) wb.getSheet(sheetName);
            sheet.trackAllColumnsForAutoSizing();
        }
        Sheet sheet = wb.getSheet(sheetName);
        for (int i = 0; i <= sheet.getLastRowNum(); i++) {
            Row row = sheet.getRow(i);
            //获得每一行，然后给每一行设置高度
            row.setHeightInPoints(25);
            for (int j = 0; j < titles.length; j++) {
                Cell cell = row.getCell(j);
                cell.setCellStyle(getMyCellStyle(wb));
            }
        }

        for (int i = 0; i < titles.length; i++) {
            //设置每一列的大小，自动设置
            sheet.autoSizeColumn(i);
            //设置宽度
            sheet.setColumnWidth(i, (int) (sheet.getColumnWidth(i) * 1.5));
        }
    }

    /**
     * 设置自定义格式并返回
     *
     * @param wb
     * @return
     */
    private static CellStyle getMyCellStyle(Workbook wb) {

        //设置4个边的样式
        CellStyle cellStyle = wb.createCellStyle();
        cellStyle.setBorderBottom(BorderStyle.DASHED);
        cellStyle.setBottomBorderColor(IndexedColors.BLACK.getIndex());
        cellStyle.setBorderLeft(BorderStyle.DASHED);
        cellStyle.setLeftBorderColor(IndexedColors.BLACK.getIndex());
        cellStyle.setBorderRight(BorderStyle.DASHED);
        cellStyle.setRightBorderColor(IndexedColors.BLACK.getIndex());
        cellStyle.setBorderTop(BorderStyle.DASHED);
        cellStyle.setTopBorderColor(IndexedColors.BLACK.getIndex());
        return cellStyle;
    }

    public interface CreateInfoRows {
        Sheet invoke(Sheet sheet);
    }

    public static ResponseEntity<byte[]> getResponseEntity(Workbook wb, String fileName) throws IOException {
        ByteArrayOutputStream outputStream = null;
        try {
            outputStream = new ByteArrayOutputStream();
            wb.write(outputStream);
            byte[] data = outputStream.toByteArray();
            HttpHeaders headers = new HttpHeaders();
            fileName = URLEncoder.encode(fileName, "UTF-8");
            headers.add("Content-Disposition", "attachment;filename=" + fileName);
            headers.setContentType(MediaType.APPLICATION_OCTET_STREAM);
            HttpStatus statusCode = HttpStatus.OK;
            ResponseEntity<byte[]> entity = new ResponseEntity<byte[]>(data, headers, statusCode);
            return entity;
        } catch (Exception e) {
            throw new RRException(e.getMessage());
        } finally {
            if (outputStream != null) {
                outputStream.close();
            }
        }
    }


    public static Workbook createExcel(List<String> tbody, String[][] data) throws Exception {

        // 第一步，创建一个webbook，对应一个Excel文件
        SXSSFWorkbook sxssfWorkbook = new SXSSFWorkbook(1000);
        sxssfWorkbook.setCompressTempFiles(true);
        Workbook wb = sxssfWorkbook;

        // 第二步，在webbook中添加一个sheet,对应Excel文件中的sheet
        Sheet sheet = wb.createSheet("Sheet");

        // 第三步，在sheet中添加表头
        Row row = sheet.createRow(0);

        //设置表头
        for (int i=0, length=tbody.size(); i< length; i++) {
            Cell cell = row.createCell(i);
            cell.setCellValue(tbody.get(i));
            sheet.setColumnWidth(i, tbody.get(i).getBytes().length*350);
        }

        //填充数据
        for (int k = 0, klength = data.length; k < klength; k++) {
            row = sheet.createRow(k + 1);
            for (int b = 0, blength = data[k].length; b < blength; b++) {
                if (StringUtils.isEmpty(data[k][b])) {
                    continue;
                }
                Cell cell = row.createCell(b);
                cell.setCellValue(data[k][b]);

                int columnWidth = sheet.getColumnWidth(k);
                sheet.setColumnWidth(b, ((data[k][b].getBytes().length*350) < columnWidth? columnWidth : (data[k][b].getBytes().length*350)));
            }
        }
        return wb;
    }
}
```



```java
package io.renren.modules.business.service.impl;

import io.renren.common.utils.ExcelUtil;
import io.renren.modules.business.bean.UserBaseInfoBean;
import org.apache.poi.hssf.usermodel.HSSFWorkbook;
import org.apache.poi.ss.formula.functions.T;
import org.apache.poi.ss.usermodel.*;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * Created by stt on 2017/10/12.
 * @author stt
 */

@Service
public class UserInfo2ExcelService {

	public Workbook getExcelEntity(List<UserBaseInfoBean> list) {
		// 标题
	  	String [] titles = new String[]{"序号","姓名","手机","邮箱","籍贯","职业","擅长语言","等级","最近登录时间","状态"};

		return ExcelUtil.getExcelXEntity(titles, "userInfo", new ExcelUtil.CreateInfoRows() {
			@Override
			public Sheet invoke(Sheet sheet) {
				for(int i = 0;i<list.size();i++){
					UserBaseInfoBean e = list.get(i);
					Row row = sheet.createRow(i+1);
					Cell cell = row.createCell(0);
					cell.setCellValue(i+1+"");
					cell = row.createCell(1);
					cell.setCellValue(e.getRealname());
					cell = row.createCell(2);
					cell.setCellValue(e.getPhone());
					cell = row.createCell(3);
					cell.setCellValue(e.getEmail());
					cell=row.createCell(4);
					cell.setCellValue(e.getNativeplace());
					cell = row.createCell(5);
					cell.setCellValue(e.getProfession());
					cell = row.createCell(6);
					cell.setCellValue(e.getLanguageDetail());
					cell = row.createCell(7);
					cell.setCellValue(e.getLevel());
					cell = row.createCell(8);
					cell.setCellValue(e.getLoginTime());
					cell = row.createCell(9);
					cell.setCellValue(e.getLoginStatus());
				}
				return sheet;
			}
		});
	}
}
```


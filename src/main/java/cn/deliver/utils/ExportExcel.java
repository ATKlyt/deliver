package cn.deliver.utils;

import org.apache.poi.hssf.usermodel.HSSFCell;
import org.apache.poi.hssf.usermodel.HSSFRow;
import org.apache.poi.hssf.usermodel.HSSFSheet;
import org.apache.poi.hssf.usermodel.HSSFWorkbook;

import java.io.IOException;
import java.io.OutputStream;
import java.util.List;

/**
 * @author Ming
 * @date 2019/8/2 - 16:58
 */
public class ExportExcel {

    /**
     * 文件导出
     * @param resource List<String []> 集合类型，要导出的具体数据集合
     * @param outputStream 输出流，输出的excel文件保存的位置
     * */
    public void exportExcel(List<String[]> resource, OutputStream outputStream) throws IOException {

        //创建一个内存的Excel对象
        HSSFWorkbook workbook = new HSSFWorkbook();

        //创建一个sheet页
        HSSFSheet sheet = workbook.createSheet("用户Excel表");

        //创建表头
        String[] headerStr = resource.get(0);
        HSSFRow hssfRow= (HSSFRow)sheet.createRow(0);

        //设置长宽
        for (int i =0;i < headerStr.length;i++){
            sheet.setColumnWidth(i,5000);
        }

        //定义表头的内容
        for(int i = 0;i < headerStr.length;i++){
            HSSFCell hssfCell = hssfRow.createCell(i);
            hssfCell.setCellValue(headerStr[i]);
        }

        //标体内容
        for (int row = 1;row < resource.size();row++){
            //输出行数据
            String[] temp = resource.get(row);
            //创建列
            HSSFRow bodyRow = sheet.createRow(row);
            //循环创建列
            for (int cell = 0;cell<temp.length;cell++){
                HSSFCell bodyCell = bodyRow.createCell(cell);
                bodyCell.setCellValue(temp[cell]);
            }
        }
        //将内存创建的excel对象输出到文件中
        workbook.write(outputStream);
    }

}
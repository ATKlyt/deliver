package cn.deliver.utils;

import org.apache.poi.hssf.usermodel.HSSFCell;
import org.apache.poi.hssf.usermodel.HSSFDateUtil;
import org.apache.poi.xssf.usermodel.XSSFCell;

import java.text.SimpleDateFormat;
import java.util.Date;

/**
 * @author Ming
 * @date 2019/8/2 - 21:40
 */
public class CheckFormat {

    public static String formatCell(HSSFCell hssfCell){
        if(hssfCell == null){
            return "";
        }else {
            if(hssfCell.getCellType() == HSSFCell.CELL_TYPE_BOOLEAN){
                return String.valueOf(hssfCell.getBooleanCellValue());
            }else if(hssfCell.getCellType()==HSSFCell.CELL_TYPE_NUMERIC){
                if (HSSFDateUtil.isCellDateFormatted(hssfCell)) {
                    //Excel Date类型处理

                    Date date = HSSFDateUtil.getJavaDate(hssfCell.getNumericCellValue());
                    SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd HH:MM:SS");
                    return sdf.format(date);
                }
                return String.valueOf(hssfCell.getNumericCellValue());
            }else{
                return String.valueOf(hssfCell.getStringCellValue());
            }
        }
    }



}
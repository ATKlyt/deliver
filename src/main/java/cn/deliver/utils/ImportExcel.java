package cn.deliver.utils;

import cn.deliver.domain.User;
import cn.deliver.domain.UserDriverInfo;
import cn.deliver.domain.UserInfo;
import org.apache.poi.hssf.usermodel.HSSFRow;
import org.apache.poi.hssf.usermodel.HSSFSheet;
import org.apache.poi.hssf.usermodel.HSSFWorkbook;
import org.apache.poi.xssf.usermodel.XSSFRow;
import org.apache.poi.xssf.usermodel.XSSFSheet;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;

import javax.sound.midi.Soundbank;
import java.io.IOException;
import java.io.InputStream;
import java.sql.Timestamp;
import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.sql.Date;
import java.util.List;

/**
 * @author Ming
 * @date 2019/8/2 - 21:36
 */
public class ImportExcel {

    public static List<UserDriverInfo> creatList(InputStream inputStream, String fileName) throws IOException, ParseException {
        //获取返回对象
        List<UserDriverInfo> userList = new ArrayList<UserDriverInfo>();

        UserDriverInfo index = null;
        User user = null;
        UserInfo userInfo = null;

//        if (true) {
        HSSFWorkbook workbook = new HSSFWorkbook(inputStream);

        //获取第一个sheet页
        HSSFSheet hssfSheet = workbook.getSheetAt(0);

        if (hssfSheet != null) {
            for (int rowNum = 1; rowNum <= hssfSheet.getLastRowNum(); rowNum++) {
                HSSFRow hssfRow = hssfSheet.getRow(rowNum);
                if (hssfRow == null) {
                    continue;
                }

                String time = CheckFormat.formatCell(hssfRow.getCell(4));
                String birthdayTime = CheckFormat.formatCell(hssfRow.getCell(9));
                Timestamp timestamp = Timestamp.valueOf(time);
                Date birthDay = Date.valueOf(birthdayTime);

                user = new User(null,CheckFormat.formatCell(hssfRow.getCell(0)),CheckFormat.formatCell(hssfRow.getCell(2)),CheckFormat.formatCell(hssfRow.getCell(1)),"1",CheckFormat.formatCell(hssfRow.getCell(3)),
                        timestamp,CheckFormat.formatCell(hssfRow.getCell(5)),CheckFormat.formatCell(hssfRow.getCell(3)),CheckFormat.formatCell(hssfRow.getCell(6)));

                userInfo = new UserInfo(null,null,CheckFormat.formatCell(hssfRow.getCell(7)),CheckFormat.formatCell(hssfRow.getCell(8)), birthDay,null,null,"D:\\picture\\nopicture.jpg");

                index = new UserDriverInfo(user,userInfo);
                System.out.println(user);
                System.out.println(userInfo);
                userList.add(index);

            }
        }
        return userList;
    }

}
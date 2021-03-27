package com.jiudian.manage.until;

import com.jiudian.manage.model.Log;
import org.apache.poi.hssf.usermodel.HSSFCell;
import org.apache.poi.hssf.usermodel.HSSFRow;
import org.apache.poi.hssf.usermodel.HSSFSheet;
import org.apache.poi.hssf.usermodel.HSSFWorkbook;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;

import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.io.UnsupportedEncodingException;
import java.text.SimpleDateFormat;
import java.util.List;

public class POIUtil {
    public static ResponseEntity<byte[]> ExportExcel(List<Log> list)
    {
        //0.创建date转string的格式对象
        SimpleDateFormat sf = new SimpleDateFormat("yyyy-MM-dd");
        //1.创建一个空的Excel表
        HSSFWorkbook workbook = new HSSFWorkbook();

        //3.开始创建sheet页
        HSSFSheet sheet = workbook.createSheet("订单日志表1");

        //4.创建行
        HSSFRow row = sheet.createRow(0);

        //5.根据需要创建列
        HSSFCell c0 = row.createCell(0);
        c0.setCellValue("顾客姓名");
        HSSFCell c1 = row.createCell(1);
        c1.setCellValue("电话号码");
        HSSFCell c2 = row.createCell(2);
        c2.setCellValue("订单开始时间");
        HSSFCell c3 = row.createCell(3);
        c3.setCellValue("订单结束时间");
        HSSFCell c4 = row.createCell(4);
        c4.setCellValue("价格");
        HSSFCell c5 = row.createCell(5);
        c5.setCellValue("房间编号");
        HSSFCell c6 = row.createCell(6);
        c6.setCellValue("操作员编号");


        //6.循环遍历插入数据
        for (int i = 0; i < list.size(); i++)
        {
            Log log = list.get(i);//这里大家可以打断点调试，每个循环进来的数据都放到book对象里
            //因为我这里i=0,而第0行已经被标题给占了，所以我下面的遍历都是从1开始
            //我们可以看到，这里的逻辑可以说相当简单，我们循环创建一行，然后在那一行创建列，给每一列
            //设置对应的数据
            HSSFRow row1 = sheet.createRow(i + 1);
            row1.createCell(0).setCellValue(log.getHouseholdname());
            row1.createCell(1).setCellValue(log.getHoldphone());
            row1.createCell(2).setCellValue(sf.format(log.getStarttime()));
            row1.createCell(3).setCellValue(sf.format(log.getEndtime()));
            row1.createCell(4).setCellValue(log.getMoney());
            row1.createCell(5).setCellValue(log.getRoomid());
            row1.createCell(6).setCellValue(log.getUserid());
        }

        //7.敲重点咯！这里可以说是最烦的地方。
        //首先，我们知道我们这里返回的数据类型是ResponseEntity<byte[]>，所以我们要在返回的时候实例化他
        //我们可以点击他的源码看，发现它里面有很多构造函数
        /**
         public ResponseEntity(@Nullable T body, @Nullable MultiValueMap<String, String> headers,
         HttpStatus status) {
         super(body, headers);
         Assert.notNull(status, "HttpStatus must not be null");
         this.status = status;
         }
         */
        //我们需要一个不为空的主体对象、一个头、一个状态位
        ByteArrayOutputStream baos = new ByteArrayOutputStream();
        HttpHeaders headers = new HttpHeaders();
        try
        {
            /**
             * 防止文件名乱码
             headers.setContentDispositionFormData("attachment",
             new String(exportFile.getName().getBytes("utf-8"), "ISO8859-1"));
             */
            headers.setContentDispositionFormData("attachment", new String("log.xls"
                    .getBytes("UTF-8"), "ISO-8859-1"));
            //response.setContentType(MIME)的作用是使客户端浏览器，区分不同种类的数据，
            // 并根据不同的MIME调用浏览器内不同的程序嵌入模块来处理相应的数据。
            headers.setContentType(MediaType.APPLICATION_OCTET_STREAM);
            //将workbook转成byte数组
            workbook.write(baos);
        }
        catch (UnsupportedEncodingException e)
        {
            e.printStackTrace();
        }
        catch (IOException e)
        {
            e.printStackTrace();
        }

        return new ResponseEntity<byte[]>(baos.toByteArray(), headers, HttpStatus.OK);
    }

}

package com.zsc.edu.bill;

import com.zsc.edu.bill.modules.bills.entity.Bill;
import com.zsc.edu.bill.modules.bills.service.impl.BillServiceImpl;
import org.apache.poi.hssf.usermodel.HSSFWorkbook;
import org.apache.poi.ss.usermodel.Workbook;
import org.jeecgframework.poi.excel.ExcelExportUtil;
import org.jeecgframework.poi.excel.entity.ExportParams;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;

@SpringBootTest
class BillApplicationTests {
    @Autowired
    private BillServiceImpl service;



    @Test
    void contextLoads() {
        //将数据导出到excel保存到本地
        Workbook workbook = ExcelExportUtil.exportExcel(new ExportParams("票据列表", "票据"), Bill.class, service.list());
        try {
            workbook.write(new FileOutputStream(new File("F:/Desktop/工业/bill.xls")));
        } catch (IOException e) {
            e.printStackTrace();


        }
    }


}

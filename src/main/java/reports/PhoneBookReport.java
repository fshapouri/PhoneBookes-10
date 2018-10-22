package reports;

import static net.sf.dynamicreports.report.builder.DynamicReports.*;
import java.math.BigDecimal;
//import net.sf.dynamicreports.report.datasource.DRDataSource;
import dao.PhoneBookDao;
import net.sf.dynamicreports.report.exception.DRException;
import net.sf.jasperreports.engine.JRDataSource;

public class PhoneBookReport {

   public PhoneBookReport(){
       build();
   }

   private void build(){
       try {
           report().columns(col.column("PhoneBookName", "phoneBookName", type.stringType()))
                   .title(cmp.text("PhoneBookes"))
                   .pageFooter(cmp.pageXofY())
//                   .setDataSource()
                   .show();
       } catch (DRException e) {
           e.printStackTrace();
       }
   }
//    private JRDataSource createDataSource() {
//
//       DRDataSource dataSource=new DRDataSource();
//
//       return dataSource;
//    }

}



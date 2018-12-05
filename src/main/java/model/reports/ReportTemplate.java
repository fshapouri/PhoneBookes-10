package model.reports;

import net.sf.dynamicreports.jasper.builder.JasperReportBuilder;
import net.sf.dynamicreports.report.builder.DynamicReports;;
import net.sf.dynamicreports.report.builder.style.StyleBuilder;
import net.sf.dynamicreports.report.constant.HorizontalAlignment;
import net.sf.dynamicreports.report.constant.VerticalAlignment;
import net.sf.dynamicreports.report.exception.DRException;

import java.awt.*;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

import static net.sf.dynamicreports.report.builder.DynamicReports.cmp;
import static net.sf.dynamicreports.report.builder.DynamicReports.stl;

abstract class ReportTemplate {
    Connection cn = null;
    JasperReportBuilder reportBuilder=null;

    ReportTemplate(){
        reportBuilder=DynamicReports.report();
    }

    public final void generatReport(){
        createConnection();
        setReportDesign();
        setData();
        saveReport();
    }

    private void createConnection(){
        try {
            Class.forName("org.apache.derby.jdbc.ClientDriver").newInstance();
            cn=DriverManager.getConnection("jdbc:derby://localhost:1527/c:/derbyDB;create=true");
        } catch (InstantiationException e) {
            e.printStackTrace();
        } catch (IllegalAccessException e) {
            e.printStackTrace();
        } catch (ClassNotFoundException e) {
            e.printStackTrace();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    private void setReportDesign(){
        StyleBuilder boldStyle         = stl.style().bold();
        StyleBuilder boldCenteredStyle = stl.style(boldStyle).setHorizontalAlignment(HorizontalAlignment.CENTER);
        StyleBuilder columnTitleStyle  = stl.style(boldCenteredStyle).
                                             setVerticalAlignment(VerticalAlignment.MIDDLE).
                                             setFontSize(15).
                                             setBorder(stl.pen1Point()).
                                             setBackgroundColor(Color.lightGray);
        reportBuilder   //create new report design
                .setColumnTitleStyle(columnTitleStyle)
                .highlightDetailEvenRows()
                .title(cmp.text("Phone Books List").setStyle(boldCenteredStyle))  //shows report title
                .pageFooter(cmp.pageXofY().setStyle(boldCenteredStyle)); //shows number of page at page footer
    }

    public abstract void setData();


    private void saveReport(){

      try{
          reportBuilder.show();
          reportBuilder.toPdf(new FileOutputStream("d:/report.pdf"));
          reportBuilder.toXlsx(new FileOutputStream("d:/report.xlsx"));

      } catch (FileNotFoundException e) {
          e.printStackTrace();
      } catch (DRException e) {
          e.printStackTrace();
      }
    }





























}

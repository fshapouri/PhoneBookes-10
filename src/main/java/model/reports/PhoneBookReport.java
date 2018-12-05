package model.reports;

import static net.sf.dynamicreports.report.builder.DynamicReports.*;

import java.awt.*;

import net.sf.dynamicreports.report.builder.datatype.DataTypes;
import net.sf.dynamicreports.report.builder.column.TextColumnBuilder;
import net.sf.dynamicreports.report.builder.style.StyleBuilder;
import net.sf.dynamicreports.report.constant.HorizontalAlignment;
import net.sf.dynamicreports.report.constant.VerticalAlignment;

public class PhoneBookReport extends ReportTemplate{

    public PhoneBookReport() {
        super();
    }

    public void setData() {
        StyleBuilder boldStyle= stl.style().bold();
        StyleBuilder boldCenteredStyle= stl.style(boldStyle).setHorizontalAlignment(HorizontalAlignment.CENTER);
        StyleBuilder columnTitleStyle=stl.style(boldCenteredStyle).setVerticalAlignment(VerticalAlignment.MIDDLE).
                setFontSize(15).
                setBorder(stl.pen1Point()).
                setBackgroundColor(Color.lightGray);

        TextColumnBuilder<String> phoneBookColumn = col.column("PhoneBookName" , "PHONBOOKNAME" , DataTypes.stringType()).
                setStyle(boldStyle).setHorizontalAlignment(HorizontalAlignment.LEFT);
        TextColumnBuilder<String> firstNameColumn= col.column("FirstName" , "FIRSTNAME" ,DataTypes.stringType()).
                setHorizontalAlignment(HorizontalAlignment.CENTER);
        TextColumnBuilder<String> lastNamecolumn = col.column("LastName" , "LASTNAME" ,DataTypes.stringType()).
                setHorizontalAlignment(HorizontalAlignment.CENTER);
        TextColumnBuilder<String> NumberColumn = col.column("Numbers", "NUMBERS" , DataTypes.stringType()).
                setHorizontalAlignment(HorizontalAlignment.CENTER);
        TextColumnBuilder<Integer> TypeColumn = col.column("Type","TYPES" , DataTypes.integerType()).
                setHorizontalAlignment(HorizontalAlignment.CENTER);

        reportBuilder.setColumnTitleStyle(columnTitleStyle)
                .columns(phoneBookColumn ,
                        firstNameColumn,
                        lastNamecolumn,
                        NumberColumn,
                        TypeColumn)
                .groupBy(phoneBookColumn)
//                .setColumnTitleStyle(columnTitleStyle)
                .setDataSource("SELECT PHONEBOOK.PHONBOOKNAME , CONTACT.FIRSTNAME , CONTACT.LASTNAME , PHONENUMBER.NUMBERS ,PHONENUMBER.TYPES " +
                        "FROM  CONTACT INNER JOIN PHONEBOOK ON  PHONEBOOK.PHONEBOOK_ID= CONTACT.PHONEBOOK_ID" +
                        "INNER JOIN PHONENUMBER ON CONTACT.CONTACT_ID = PHONENUMBER.CONTACT_ID ", cn);
    }
}



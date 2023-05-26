package com.nielsen.discover.demo.service;

import org.springframework.http.HttpHeaders;
import org.springframework.stereotype.Component;

import com.aspose.cells.Cells;
import com.aspose.cells.Chart;
import com.aspose.cells.ChartCollection;
import com.aspose.cells.Series;
import com.aspose.cells.SeriesCollection;
import com.aspose.cells.Workbook;
import com.aspose.cells.Worksheet;
import com.aspose.cells.WorksheetCollection;
//import com.nielsen.discover.util.DiscoverExportConstants;

import jakarta.servlet.http.HttpServletResponse;

@Component
public class ExportService {

  public void getExport(HttpServletResponse response) {
    Workbook wb = new Workbook();
    WorksheetCollection sheets = wb.getWorksheets();
    // init sheets
    int i = sheets.add();
    Worksheet chartSheet = sheets.get(i);
    ChartCollection charts = chartSheet.getCharts();
    Worksheet dataSheet = sheets.get(0);
    Cells dataSheetCells = dataSheet.getCells();

    // init chart
    Chart chart = charts.get(charts.add(com.aspose.cells.ChartType.BAR_STACKED, 0, 0, 20, 10));
    SeriesCollection nSeries = chart.getNSeries();

    // write data
    dataSheetCells.get(0, 1).setValue("Jan 22");
    dataSheetCells.get(0, 2).setValue("Feb 22");
    dataSheetCells.get(1, 0).setValue("Costco");
    dataSheetCells.get(1, 1).setValue(5543);
    dataSheetCells.get(1, 2).setValue(6543);
    dataSheetCells.get(2, 0).setValue("Walmart");
    dataSheetCells.get(2, 1).setValue(3456);
    dataSheetCells.get(2, 2).setValue(5643);
    dataSheetCells.createRange(1, 0, 2, 1).setName("Retailer");
    dataSheetCells.createRange(1, 1, 2, 1).setName("Jan");
    dataSheetCells.createRange(1, 2, 2, 1).setName("Feb");
    Series janSeries = nSeries.get(nSeries.add("Jan", true));
    janSeries.setName("Jan sales");
    Series febSeries = nSeries.get(nSeries.add("Feb", true));
    febSeries.setName("Feb Sales");
    nSeries.setCategoryData("Retailer");


    try {
      final String fileName = "test.xlsx";
      response.addHeader(HttpHeaders.CONTENT_DISPOSITION,
          "attachment; filename=\"" + fileName + "\"");
      wb.save(response.getOutputStream(), com.aspose.cells.SaveFormat.XLSX);
//      wb.save(
//          "C:\\Users\\prabth01\\Desktop\\Desktop files\\Excels\\StackedTo100PercentStacked.xlsx");
    } catch (Exception e) {
      // TODO Auto-generated catch block
      e.printStackTrace();
    }

  }

}

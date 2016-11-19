/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.tsguild.tropicalfishwebapp.controller;

import com.google.visualization.datasource.datatable.ColumnDescription;
import com.google.visualization.datasource.datatable.DataTable;
import com.google.visualization.datasource.datatable.TableRow;
import com.google.visualization.datasource.datatable.value.ValueType;
import com.google.visualization.datasource.render.JsonRenderer;
import com.tsguild.tropicalfishwebapp.dao.FishDao;
import com.tsguild.tropicalfishwebapp.model.WaterTypeCount;
import java.util.List;
import javax.inject.Inject;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

/**
 *
 * @author Jimmy Cook
 */
@Controller
public class StatsController {
    
    private FishDao dao;

    // annotation-driven constructor injection
    @Inject
    public StatsController(FishDao dao) {
        this.dao = dao;
    }

    @RequestMapping(value = "/stats", method = RequestMethod.GET)
    public String displayStatsPage() {
        return "stats";
    }

    @RequestMapping(value = "/stats/chart", method = RequestMethod.GET)
    @ResponseBody
    public String getDataTable() {
        try {
            List<WaterTypeCount> counts = dao.getWaterTypeCounts();

            DataTable t = new DataTable();
            t.addColumn(new ColumnDescription("Company_Name",
                    ValueType.TEXT,
                    "Company"));
            t.addColumn(new ColumnDescription("Number_Contacts",
                    ValueType.NUMBER,
                    "# Contacts"));
            // Convert each CompanyContactCount object into a table row
            for (WaterTypeCount currentCount : counts) {
                TableRow tr = new TableRow();
                tr.addCell(currentCount.getWaterType());
                tr.addCell(currentCount.getNumberFish());
                t.addRow(tr);
            }
            // Use the JsonRenderer to convert the DataTable to a JSON string
            // the default Jackson converter doesn't convert the DataTable object
            // to JSON properly so we have to do it here.
            return JsonRenderer.renderDataTable(t, true, false, false).toString();
        } catch (Exception e) {
            // just return an error string if we encounter an issue
            return "Invalid Data";
        }
    }
}

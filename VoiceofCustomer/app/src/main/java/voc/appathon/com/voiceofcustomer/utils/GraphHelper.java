package voc.appathon.com.voiceofcustomer.utils;

import android.content.Context;
import android.graphics.Color;

import org.achartengine.model.CategorySeries;
import org.achartengine.renderer.DefaultRenderer;
import org.achartengine.renderer.SimpleSeriesRenderer;

import voc.appathon.com.voiceofcustomer.R;

/**
 * Created by tanu.rawal on 1/21/2017.
 */

public class GraphHelper {

    // Color of each Pie Chart Sections



    public DefaultRenderer openPieChart(String[] ansOptionsArray, double[] responseArray, String question, Context mContext){
        // Pie Chart Section Names


        int[] colors = { mContext.getResources().getColor(R.color.chartcolor1), mContext.getResources().getColor(R.color.chartcolor2), mContext.getResources().getColor(R.color.chartcolor3), mContext.getResources().getColor(R.color.chartcolor4),
                Color.GRAY };




        // Instantiating a renderer for the Pie Chart
        DefaultRenderer defaultRenderer = new DefaultRenderer();
        for (int i = 0; i < responseArray.length; i++) {
            SimpleSeriesRenderer seriesRenderer = new SimpleSeriesRenderer();
            seriesRenderer.setColor(colors[i]);
            seriesRenderer.setDisplayChartValues(true);
//Adding colors to the chart
            defaultRenderer.setBackgroundColor(Color.WHITE);
            defaultRenderer.setApplyBackgroundColor(true);
            // Adding a renderer for a slice
            defaultRenderer.addSeriesRenderer(seriesRenderer);
        }

        defaultRenderer.setChartTitle("    "+question);
        defaultRenderer.setChartTitleTextSize(28);
        defaultRenderer.setLabelsTextSize(28);
        defaultRenderer.setLabelsColor(mContext.getResources().getColor(R.color.colorPrimaryDark));
        defaultRenderer.setLegendTextSize(28);
        defaultRenderer.setZoomButtonsVisible(false);
        return defaultRenderer;
    }

}

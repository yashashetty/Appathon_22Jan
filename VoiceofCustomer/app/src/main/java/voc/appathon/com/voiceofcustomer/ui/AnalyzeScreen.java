package voc.appathon.com.voiceofcustomer.ui;

import android.graphics.Color;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.CardView;
import android.support.v7.widget.Toolbar;
import android.view.View;
import android.view.Window;
import android.widget.LinearLayout;

import org.achartengine.ChartFactory;
import org.achartengine.model.CategorySeries;
import org.achartengine.renderer.DefaultRenderer;
import org.achartengine.renderer.SimpleSeriesRenderer;

import voc.appathon.com.voiceofcustomer.R;
import voc.appathon.com.voiceofcustomer.utils.GraphHelper;

public class AnalyzeScreen extends BaseAcitivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        requestWindowFeature(Window.FEATURE_NO_TITLE);
        setContentView(R.layout.activity_analyze_screen);
        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        showChart();
        setSupportActionBar(toolbar);
        toolbar.setTitle(R.string.analyze);
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        getSupportActionBar().setDisplayShowHomeEnabled(true);
        getSupportActionBar().setHomeButtonEnabled(true);
    }

    private void showChart() {

        GraphHelper gHelper = new GraphHelper();
        String[] ansOptionsArray ={ "Best", "Good",
                "Good", "Bad", "Better" };

        double[] responseArray= new double[]{20.0,20.0,30.0,10.0,20.0};
        String  question ="How is process improvement";


        LinearLayout chartContainer = (LinearLayout) findViewById(R.id.chart);
        // remove any views before u paint the chart
        chartContainer.removeAllViews();
        // drawing pie chart
        View mChart;

        // Instantiating CategorySeries to plot Pie Chart
        CategorySeries distributionSeries = new CategorySeries("   "+question);
        for (int i = 0; i < responseArray.length; i++) {
            // Adding a slice with its values and name to the Pie Chart
            distributionSeries.add(ansOptionsArray[i], responseArray[i]);
        }

        mChart = ChartFactory.getPieChartView(getBaseContext(),
                distributionSeries, gHelper.openPieChart( ansOptionsArray,responseArray,question, this));
        // adding the view to the linearlayout
        chartContainer.addView(mChart);

    }

}

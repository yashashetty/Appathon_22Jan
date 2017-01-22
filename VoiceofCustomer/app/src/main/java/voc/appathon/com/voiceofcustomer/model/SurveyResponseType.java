package voc.appathon.com.voiceofcustomer.model;

/**
 * Created by yshetty on 1/16/17.
 */
public class SurveyResponseType {
    public static final int TEXT_TYPE=0;
    public static final int IMAGE_TYPE=1;
    public static final int AUDIO_TYPE=2;

    public static int type;
    public int data;
    public String text;

    public SurveyResponseType(int type, String text, int data)
    {
        this.type=type;
        this.data=data;
        this.text=text;
    }
}

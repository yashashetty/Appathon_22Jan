package voc.appathon.com.voiceofcustomer.adapters;

/**
 * Created by tanu.rawal on 1/13/2017.
 */

import android.content.Context;
import android.media.MediaPlayer;
import android.support.design.widget.FloatingActionButton;
import android.support.v7.widget.CardView;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import java.util.ArrayList;

import voc.appathon.com.voiceofcustomer.R;
import voc.appathon.com.voiceofcustomer.model.SurveyResponseType;

/**
 * Created by anupamchugh on 09/02/16.
 */
public class MultiViewAdapter extends RecyclerView.Adapter {

    private ArrayList<SurveyResponseType> dataSet;
    Context mContext;
    int total_types;
    MediaPlayer mPlayer;
    private boolean fabStateVolume = false;

    public static class TextTypeViewHolder extends RecyclerView.ViewHolder {

        TextView txtType;
        CardView cardView;

        public TextTypeViewHolder(View itemView) {
            super(itemView);

            this.txtType = (TextView) itemView.findViewById(R.id.type);
            this.cardView = (CardView) itemView.findViewById(R.id.card_view);
        }
    }

    public static class ImageTypeViewHolder extends RecyclerView.ViewHolder {

        TextView txtType;
        ImageView image;

        public ImageTypeViewHolder(View itemView) {
            super(itemView);

            this.txtType = (TextView) itemView.findViewById(R.id.type);
            this.image = (ImageView) itemView.findViewById(R.id.background);
        }
    }

    public static class AudioTypeViewHolder extends RecyclerView.ViewHolder {

        TextView txtType;
        FloatingActionButton fab;

        public AudioTypeViewHolder(View itemView) {
            super(itemView);

            this.txtType = (TextView) itemView.findViewById(R.id.type);
           // this.fab = (FloatingActionButton) itemView.findViewById(R.id.fab);
        }
    }

    public MultiViewAdapter(ArrayList<SurveyResponseType> data, Context context) {
        this.dataSet = data;
        this.mContext = context;
        total_types = dataSet.size();
    }

    @Override
    public RecyclerView.ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {

        View view;
        switch (viewType) {
            case SurveyResponseType.TEXT_TYPE:

                view = LayoutInflater.from(mContext).inflate(R.layout.survey_text_only, parent, false);
                return new TextTypeViewHolder(view);
            case SurveyResponseType.IMAGE_TYPE:
                view = LayoutInflater.from(mContext).inflate(R.layout.survey_with_image, parent, false);
                return new ImageTypeViewHolder(view);
            case SurveyResponseType.AUDIO_TYPE:
                view = LayoutInflater.from(parent.getContext()).inflate(R.layout.survey_text_only, parent, false);
                return new AudioTypeViewHolder(view);
        }
        return null;
    }

    @Override
    public int getItemViewType(int position) {

        switch (dataSet.get(position).type) {
            case 0:
                return SurveyResponseType.TEXT_TYPE;
            case 1:
                return SurveyResponseType.IMAGE_TYPE;
            case 2:
                return SurveyResponseType.AUDIO_TYPE;
            default:
                return -1;
        }
    }

    @Override
    public void onBindViewHolder(final RecyclerView.ViewHolder holder, final int listPosition) {

        SurveyResponseType object = dataSet.get(listPosition);
        if (object != null) {
            switch (object.type) {
                case SurveyResponseType.TEXT_TYPE:
                    ((TextTypeViewHolder) holder).txtType.setText(object.text);


                    break;
                case SurveyResponseType.IMAGE_TYPE:
                    ((ImageTypeViewHolder) holder).txtType.setText(object.text);
                    ((ImageTypeViewHolder) holder).image.setImageResource(object.data);
                    break;
                case SurveyResponseType.AUDIO_TYPE:

                    ((AudioTypeViewHolder) holder).txtType.setText(object.text);

                    ((AudioTypeViewHolder) holder).fab.setOnClickListener(new View.OnClickListener() {
                        @Override
                        public void onClick(View view) {

                            if (fabStateVolume) {
                                if (mPlayer.isPlaying()) {
                                    mPlayer.stop();

                                }
                                ((AudioTypeViewHolder) holder).fab.setImageResource(R.drawable.ic_launcher);
                                fabStateVolume = false;

                            } else {
                               /* mPlayer = MediaPlayer.create(mContext, R.raw.sound);
                                mPlayer.setLooping(true);
                                mPlayer.start();
                                ((AudioTypeViewHolder) holder).fab.setImageResource(R.drawable.mute);
                                fabStateVolume = true;*/

                            }
                        }
                    });
                    break;
            }
        }
    }

    @Override
    public int getItemCount() {
        return dataSet.size();
    }
}

package com.example.fragment;

import android.content.Context;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import android.graphics.Rect;
import android.media.AudioManager;
import android.media.MediaPlayer;
import android.media.audiofx.BassBoost;
import android.media.audiofx.Equalizer;
import android.media.audiofx.PresetReverb;
import android.media.audiofx.Visualizer;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.text.Layout;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.MotionEvent;
import android.view.View;
import android.view.ViewGroup;
import android.widget.LinearLayout;

import com.example.myapplication.R;


/**
 * Created by mym_0314 on 2016/5/22.
 */
public class Fragment_mine extends Fragment {
    private MediaPlayer mMediaPlayer;
    //定义系统示波器
    private Visualizer mVisualizer;
    //定义系统的均衡器
    private Equalizer mEqualizer;
    //自定义系统重低音控制器
    private BassBoost mBoasboost;
    //定义系统的预设音场控制
    private PresetReverb mPresetReverb;

    private LinearLayout mLinear;

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        getActivity().setVolumeControlStream(AudioManager.STREAM_MUSIC);
        View view = inflater.inflate(
                R.layout.fragment_mine, container, false);
        mLinear = (LinearLayout) view.findViewById(R.id.fragment_mine);
        mMediaPlayer = MediaPlayer.create(getActivity(), R.raw.showmethemeaningofbeinglonely);
        setupVisualizer();
        mMediaPlayer.start();
        return view;
    }

    @Override
    public void onPause() {
        super.onPause();
        mMediaPlayer.pause();
    }

    private void setupVisualizer() {
        final MyVisualizerView mMyvisualizerView = new MyVisualizerView(getActivity());
        mMyvisualizerView.setLayoutParams(new ViewGroup.LayoutParams(
                ViewGroup.LayoutParams.MATCH_PARENT,
                (int) (120f * getActivity().getResources().getDisplayMetrics().density)));
        mLinear.addView(mMyvisualizerView);
        Log.e("Error", mMediaPlayer.getAudioSessionId() + "");
        mVisualizer = new Visualizer(mMediaPlayer.getAudioSessionId());
        mVisualizer.setCaptureSize(Visualizer.getCaptureSizeRange()[1]);
        mVisualizer.setDataCaptureListener(new Visualizer.OnDataCaptureListener() {
            @Override
            public void onWaveFormDataCapture(Visualizer visualizer, byte[] waveform, int samplingRate) {
                mMyvisualizerView.updateVisualizer(waveform);
            }

            @Override
            public void onFftDataCapture(Visualizer visualizer, byte[] fft, int samplingRate) {

            }
        }, Visualizer.getMaxCaptureRate() / 2, true, false);
        mVisualizer.setEnabled(true);
    }

    private static class MyVisualizerView extends View {
        private byte[] bytes;
        private float[] points;
        private Paint paint = new Paint();
        private Rect rect = new Rect();
        private byte type = 0;

        public MyVisualizerView(Context context) {
            super(context);
            bytes = null;
            paint.setStrokeMiter(1f);
            paint.setAntiAlias(true);
            paint.setColor(Color.GREEN);
            paint.setStyle(Paint.Style.FILL);
        }

        public void updateVisualizer(byte[] ftt) {
            bytes = ftt;
            invalidate();
        }

        @Override
        public boolean onTouchEvent(MotionEvent event) {
            if (event.getAction() != MotionEvent.ACTION_DOWN)
                return false;
            type++;
            if (type >= 3) type = 0;
            return true;
        }

        @Override
        protected void onDraw(Canvas canvas) {
            super.onDraw(canvas);
            if (bytes == null) return;
            canvas.drawColor(Color.WHITE);
            rect.set(0, 0, getWidth(), getHeight());
            switch (type) {
                case 0:
                    for (int i = 0; i < bytes.length - 1; i++) {
                        float left = getWidth() * i / (bytes.length - 1);
                        float top = rect.height() - (byte) (bytes[i + 1] + 128) * rect.height() / 128;
                        float right = left + 1;
                        float bottom = rect.height();
                        canvas.drawRect(left, top, right, bottom, paint);
                    }
                    break;
                case 1:
                    for (int i = 0; i < bytes.length - 1; i++) {
                        float left = rect.width() * i / (bytes.length - 1);
                        float top = rect.height() - (byte) (bytes[i + 1] + 128) * rect.height() / 128;
                        float right = left + 6;
                        float bottom = rect.height();
                        canvas.drawRect(left, top, right, bottom, paint);
                    }
                    break;
                case 2:
                    if (points == null || points.length < bytes.length * 4)
                        points = new float[bytes.length * 4];
                    for (int i = 0; i < bytes.length - 1; i++) {
                        points[i * 4] = rect.width() * i / (bytes.length - 1);
                        points[i * 4 + 1] = (rect.height() / 2) + ((byte) (bytes[i] + 128)) * 128 / (rect.height() / 2);
                        points[i * 4 + 2] = rect.width() * (i + 1) / (bytes.length - 1);
                        points[i * 4 + 3] = (rect.height() / 2) + ((byte) (bytes[i + 1] + 128)) * 128 / (rect.height() / 2);
                        canvas.drawLines(points, paint);

                    }
                    break;
            }

        }
    }

}

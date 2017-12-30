package ru.androidtools.embossmaskfilter;

import android.app.Activity;
import android.graphics.EmbossMaskFilter;
import android.os.Bundle;
import android.view.View;
import android.widget.SeekBar;
import android.widget.TextView;

/**
 * An example full-screen activity that shows and hides the system UI (i.e.
 * status bar and navigation/system bar) with user interaction.
 */
public class FullscreenActivity extends Activity implements SeekBar.OnSeekBarChangeListener {

  private TextView tv_x, textView, tv_y, tv_z, tv_ambient, tv_specular, tv_blur;
  private SeekBar x, y, z, ambient, specular, blur;

  @Override protected void onCreate(Bundle savedInstanceState) {
    super.onCreate(savedInstanceState);

    setContentView(R.layout.activity_fullscreen);

    textView = findViewById(R.id.image);
    tv_x = findViewById(R.id.tv_x);
    tv_y = findViewById(R.id.tv_y);
    tv_z = findViewById(R.id.tv_z);
    tv_ambient = findViewById(R.id.tv_ambient);
    tv_specular = findViewById(R.id.tv_specular);
    tv_blur = findViewById(R.id.tv_blur);

    x = findViewById(R.id.x);
    x.setOnSeekBarChangeListener(this);

    y = findViewById(R.id.y);
    y.setOnSeekBarChangeListener(this);

    z = findViewById(R.id.z);
    z.setOnSeekBarChangeListener(this);

    ambient = findViewById(R.id.ambient);
    ambient.setOnSeekBarChangeListener(this);

    specular = findViewById(R.id.specular);
    specular.setOnSeekBarChangeListener(this);
    blur = findViewById(R.id.blur);
    blur.setOnSeekBarChangeListener(this);
  }

  private void applyFilter(TextView textView, float[] direction, float ambient, float specular,
      float blurRadius) {
    textView.setLayerType(View.LAYER_TYPE_SOFTWARE, null);
    EmbossMaskFilter filter = new EmbossMaskFilter(direction, ambient, specular, blurRadius);
    textView.getPaint().setMaskFilter(filter);
  }

  @Override public void onProgressChanged(SeekBar seekBar, int progress, boolean fromUser) {

    switch (seekBar.getId()) {
      case R.id.x:
        tv_x.setText(String.valueOf(getProgress(progress)) + "|");
        break;
      case R.id.y:
        tv_y.setText(String.valueOf(getProgress(progress)) + "|");
        break;
      case R.id.z:
        tv_z.setText(String.valueOf(getProgress(progress)) + "|");
        break;
      case R.id.ambient:
        tv_ambient.setText(String.valueOf(getAmbient(progress)) + "|");
        break;
      case R.id.specular:
        tv_specular.setText(String.valueOf((float) progress) + "|");
        break;
      case R.id.blur:
        tv_blur.setText(String.valueOf(getProgress(progress)));
        break;
    }
    //getProgress( int i);
    applyFilter(textView, new float[] {
            getProgress(x.getProgress()), getProgress(y.getProgress()), getProgress(z.getProgress())
        }, getAmbient(ambient.getProgress()), (float) specular.getProgress(),
        getProgress(blur.getProgress()));

    ;
  }

  private float getProgress(int i) {
    return ((float) i - 10) / 10;
  }

  private float getAmbient(int i) {
    return ((float) i) / 10;
  }

  @Override public void onStartTrackingTouch(SeekBar seekBar) {

  }

  @Override public void onStopTrackingTouch(SeekBar seekBar) {

  }
}

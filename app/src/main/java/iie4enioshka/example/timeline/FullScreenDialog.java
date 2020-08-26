package iie4enioshka.example.timeline;

import android.os.Bundle;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.fragment.app.DialogFragment;

import static android.view.View.SYSTEM_UI_FLAG_LAYOUT_STABLE;

public class FullScreenDialog extends DialogFragment{
    public static final String TAG = "FullScreenDialog";
    public View decorView;
    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);



       // setStyle(DialogFragment.STYLE_NORMAL, R.style.FullScreenDialogStyle);
    }

    @Override
    public void onStart() {
        super.onStart();

        //Dialog dialog = getDialog();

        decorView = getDialog().getWindow().getDecorView();
        decorView.setOnSystemUiVisibilityChangeListener
                (new View.OnSystemUiVisibilityChangeListener() {
                    @Override
                    public void onSystemUiVisibilityChange(int visibility) {
                        // Note that system bars will only be "visible" if none of the
                        // LOW_PROFILE, HIDE_NAVIGATION, or FULLSCREEN flags are set.
                        if ((visibility & View.SYSTEM_UI_FLAG_FULLSCREEN) == 0) {


                            int uiOptions = View.SYSTEM_UI_FLAG_HIDE_NAVIGATION
                                    | SYSTEM_UI_FLAG_LAYOUT_STABLE
                                    | View.SYSTEM_UI_FLAG_FULLSCREEN
                                    | View.SYSTEM_UI_FLAG_LAYOUT_HIDE_NAVIGATION
                                    | View.SYSTEM_UI_FLAG_LAYOUT_FULLSCREEN
                                    | View.SYSTEM_UI_FLAG_IMMERSIVE_STICKY;
                            decorView.setSystemUiVisibility(uiOptions);

                            Log.d(TAG, "navigation");



                            // TODO: The system bars are visible. Make any desired
                            // adjustments to your UI, such as showing the action bar or
                            // other navigational controls.
                        } else {
                            // TODO: The system bars are NOT visible. Make any desired
                            // adjustments to your UI, such as hiding the action bar or
                            // other navigational controls.
                            Log.d(TAG, "hide navigation");
                        }
                    }
                });


        //View decorView = getDialog().getWindow().getDecorView();

//        if (dialog != null) {
//            int width = ViewGroup.LayoutParams.WRAP_CONTENT;
//            int height = ViewGroup.LayoutParams.WRAP_CONTENT;
//            dialog.getWindow().setLayout(width, height);
//        }
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup parent, Bundle state) {
        super.onCreateView(inflater, parent, state);

        View view = getActivity().getLayoutInflater().inflate(R.layout.dialog_layout, parent, false);
        return view;
    }
}

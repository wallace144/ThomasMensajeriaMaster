package co.udistrital.android.thomasmensageria.validate_route;


import android.content.Intent;
import android.content.pm.ActivityInfo;
import android.graphics.Color;
import android.os.Bundle;
import android.os.Handler;
import android.support.v4.app.Fragment;
import android.util.Log;
import android.view.Gravity;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import com.google.zxing.Result;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;
import butterknife.Unbinder;
import co.udistrital.android.thomasmensageria.R;
import me.dm7.barcodescanner.zxing.ZXingScannerView;

/**
 * A simple {@link Fragment} subclass.
 */
public class ValidateRouteFragment extends Fragment /*implements ZXingScannerView.ResultHandler */{

    private static final String BS_PACKAGE = "com.google.zxing.client.android";

    @BindView(R.id.tvCodeText)
    EditText tvCodeText;
    @BindView(R.id.ivCodigo)
    ImageView ivCodigo;
    Unbinder unbinder;
    private ZXingScannerView scannerView;

    public ValidateRouteFragment() {
        // Required empty public constructor
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {

        // Inflate the layout for this fragment
        getActivity().setRequestedOrientation(ActivityInfo.SCREEN_ORIENTATION_PORTRAIT);
        View view = inflater.inflate(R.layout.fragment_validate_route, container, false);

        unbinder = ButterKnife.bind(this, view);

        return view;
    }

    /*@Override
    public void handleResult(Result rawResult) {
        this.tvCodeText.setText(rawResult.getText().toString());

        Toast.makeText(getActivity(), "Contents = " + rawResult.getText() +
                ", Format = " + rawResult.getBarcodeFormat().toString(), Toast.LENGTH_SHORT).show();
        // Note:
        // * Wait 2 seconds to resume the preview.
        // * On older devices continuously stopping and resuming camera preview can result in freezing the app.
        // * I don't know why this is the case but I don't have the time to figure out.

        this.tvCodeText.setText(rawResult.getText().toString());
        try {
            Log.e("Resultado:  ", rawResult.getText());
        }catch (Exception e){
            Log.e("Resultado:  ", e.getMessage().toString() );
        }
        //scannerView.resumeCameraPreview(this);


    }*/

    @OnClick(R.id.ivCodigo)
    public void handleScan(View view) {
        try {

            Intent intent = new Intent(BS_PACKAGE + ".SCAN");
            intent.addFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP);

            intent.addFlags(Intent.FLAG_ACTIVITY_CLEAR_WHEN_TASK_RESET);
            intent.putExtra("PROMPT_MESSAGE",
                    " Lee Cualquier Codigo BAR. Gracias a Dios!!");

            intent.putExtra("SCAN_WIDTH", 1780);

            intent.putExtra("SCAN_HEIGHT", 1070);

            startActivityForResult(intent, 0);
        }catch (Exception e) {
            Toast toast = Toast.makeText(getActivity().getApplicationContext(),
                    "error: " + e.getMessage(), Toast.LENGTH_SHORT);
            toast.setGravity(Gravity.CENTER_VERTICAL, 0, 0);
            TextView v = (TextView) toast.getView().findViewById(
                    android.R.id.message);
            v.setTextColor(Color.RED);
            toast.show();
        }

       /* scannerView = new ZXingScannerView(getActivity());
        getActivity().setContentView(scannerView);
        scannerView.setResultHandler(this);
        scannerView.startCamera();*/

    }

    public void onActivityResult(int requestCode, int resultCode, Intent intent) {
        if (requestCode == 0 /* || requestCode == REQUEST_CODE */) {
            if (resultCode == getActivity().RESULT_OK) {
                String contents = intent.getStringExtra("SCAN_RESULT");
                this.tvCodeText.setText(contents);
            } else if (resultCode == getActivity().RESULT_CANCELED) {
                // Handle cancel
            }
        }
    }

    @Override
    public void onPause() {
        super.onPause();

        //scannerView.stopCamera();


    }



    @Override
    public void onDestroyView() {
        super.onDestroyView();
        unbinder.unbind();


    }
}

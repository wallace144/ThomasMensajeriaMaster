package co.udistrital.android.thomasmensageria.services_route;


import android.graphics.Bitmap;
import android.os.Bundle;
import android.support.design.widget.FloatingActionButton;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.Toast;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;
import butterknife.Unbinder;
import co.udistrital.android.thomasmensageria.R;
import co.udistrital.android.thomasmensageria.help.CaptureSignatureView;

/**
 * A simple {@link Fragment} subclass.
 */
public class ServicesRouteFragment extends Fragment {


    @BindView(R.id.btnlimpiar)
    Button btnlimpiar;
    @BindView(R.id.btnguardar)
    Button btnguardar;
    @BindView(R.id.ivfirma)
    ImageView ivfirma;
    @BindView(R.id.linear)
    LinearLayout linear;
    @BindView(R.id.fabPhoto)
    FloatingActionButton fabPhoto;
    @BindView(R.id.fabFirma)
    FloatingActionButton fabFirma;
    @BindView(R.id.fabMail)
    FloatingActionButton fabMail;
    @BindView(R.id.fabCode)
    FloatingActionButton fabCode;
    Unbinder unbinder;

    public View view;
    private CaptureSignatureView mSig;

    public ServicesRouteFragment() {
        // Required empty public constructor
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment




        view = inflater.inflate(R.layout.fragment_services_route, container, false);
        unbinder = ButterKnife.bind(this, view);

        mSig = new CaptureSignatureView(this, null);
        linear.addView(mSig, LinearLayout.LayoutParams.MATCH_PARENT, LinearLayout.LayoutParams.MATCH_PARENT);


        return view;
    }

    @Override
    public void onDestroyView() {
        super.onDestroyView();
        unbinder.unbind();
    }


    @OnClick(R.id.btnguardar)
    public void guardar() {
        byte[] signature = mSig.getBytes();
        Bitmap signature1 = mSig.getBitmap();
        linear.setVisibility(View.GONE);
        ivfirma.setVisibility(view.VISIBLE);
        ivfirma.setImageBitmap(signature1);
    }

    @OnClick(R.id.btnlimpiar)
    public void limpiar() {
        Toast.makeText(this.getContext(), "Limpiar", Toast.LENGTH_LONG).show();
        ivfirma.setVisibility(view.GONE);
        linear.setVisibility(View.VISIBLE);
        mSig.ClearCanvas();

    }
}

package co.udistrital.android.thomasmensageria.login;

import android.os.AsyncTask;
import android.util.Log;
import android.widget.Toast;

import org.ksoap2.SoapFault;
import org.ksoap2.serialization.SoapObject;
import org.ksoap2.serialization.SoapSerializationEnvelope;
import org.ksoap2.transport.HttpTransportSE;

/**
 * Created by wisuarez on 06/02/2018.
 */

public class PruebaRepositoryBorrar extends AsyncTask<String, Object, String>{

    public static  final String NAMESPACE = "http://service.dataxmldistribution.argos.cls.fr/";
    public static final String URL = "http://ws-argos.cls.fr/argosDws/services/DixService";
    public static final String SOAP_ACTION = "getXml";
    public static final String METHOD_NAME ="xmlRequest";

    SoapSerializationEnvelope envelope;
    public int timeOut = 30000;
    private String response;

    private String user;
    private String pass;

    public PruebaRepositoryBorrar(String user, String pass) {
        this.user = user;
        this.pass = pass;
    }

    @Override
    protected void onPreExecute() {
        super.onPreExecute();
    }

    @Override
    protected String doInBackground(String... strings) {

        SoapObject request = new SoapObject(NAMESPACE, METHOD_NAME);


        envelope= new SoapSerializationEnvelope(SoapSerializationEnvelope.VER11);
        envelope.dotNet = true;

        envelope.setOutputSoapObject(request);

        HttpTransportSE transportSE = new HttpTransportSE(URL,timeOut);

        try {
            transportSE.call(SOAP_ACTION, envelope);
            System.out.println("Respuesta ws: " + envelope.getResponse().toString());
            response =(String) envelope.getResponse();


        }catch (Exception e){
            e.printStackTrace();
            Log.e("Error", e.toString());
        }
        return response;
    }

    @Override
    protected void onPostExecute(String s) {
        super.onPostExecute(s);
        try {
            if (s != null){

                    System.out.println("Respuesta: " +  envelope.getResponse().toString());
                    System.out.println("Respuesta1: " +  s.toString());

            }else{
                System.out.println("Respuesta ws: " + envelope.getResponse().toString());
                //System.out.println("Respuesta2: "+response);//
                //System.out.println("Respuesta3: " +  s.toString());
            }
        }catch (SoapFault soapFault) {
            soapFault.printStackTrace();
        }



    }
}

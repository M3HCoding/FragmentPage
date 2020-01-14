package dev.zmq.fragmentpage;

import android.app.Activity;
import android.app.Fragment;
import android.app.FragmentManager;
import android.app.FragmentTransaction;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ListView;

import java.util.ArrayList;

public class FragmentOne extends Fragment
{
    private View view;
    private ListView listView_One;
    private EditText editText_One;
    private Button button_One;
    private ArrayList<String> data1;
    private ArrayAdapter<String> adapter;
    FragmentTwo fragmentTwo;
    FragmentManager fragmentManager;
    FragmentTransaction fragmentTransaction;

        @Override
        public void onAttach(Activity activity)
        {
            super.onAttach( activity );
            fragmentManager=getFragmentManager();
            fragmentTransaction=fragmentManager.beginTransaction();
        }

    @Override
    public View onCreateView(LayoutInflater inflater,ViewGroup container, Bundle savedInstanceState)
    {
       view=inflater.inflate(R.layout.fragment_one,container,false);
       listView_One=(ListView)view.findViewById( R.id.listView_one);
       editText_One=(EditText)view.findViewById( R.id.editText_one );
       button_One=(Button)view.findViewById( R.id.btn_one );

       button_One.setOnClickListener( new View.OnClickListener()
       {
           @Override
           public void onClick(View v)
           {
               String sendOne=editText_One.getText().toString();
               Bundle bundle_One=new Bundle(  );
               bundle_One.putString( "two", sendOne);
               fragmentTwo=new FragmentTwo();
               fragmentTwo.setArguments( bundle_One );
               fragmentManager.beginTransaction();
               fragmentTransaction.add( R.id.f2,fragmentTwo);
               fragmentTransaction.addToBackStack( null ).commit();

           }
       } );

       if (getArguments()!=null)
       {
           String recievedTwoData=getArguments().getString( "one" );
           adapter=new ArrayAdapter<String>( getActivity(),R.layout.fragment_two,data1);
           data1=new ArrayList<>(  );
           data1.add(recievedTwoData  );
           adapter.notifyDataSetChanged();

       }

       return  view;
    }
}

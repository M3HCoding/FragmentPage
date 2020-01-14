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
import java.util.List;

public class FragmentTwo extends Fragment
{
    private View view;
    private ListView listView_Two;
    private EditText editText_Two;
    private Button button_Two;
    private ArrayAdapter<String> adapter;
    private ArrayList<String> data2;
    FragmentOne fragmentOne;

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
        view=inflater.inflate( R.layout.fragment_two,container,false );
        listView_Two=(ListView)view.findViewById( R.id.listView_two );
        editText_Two=(EditText)view.findViewById( R.id.editText_two );
        button_Two=(Button)view.findViewById( R.id.btn_two );

        button_Two.setOnClickListener( new View.OnClickListener()
        {
            @Override
            public void onClick(View v)
            {
                String sendOne=editText_Two.getText().toString();
                Bundle bundle_One=new Bundle(  );
                bundle_One.putString( "one", sendOne);
                fragmentOne=new FragmentOne();
                fragmentOne.setArguments( bundle_One );
                fragmentManager.beginTransaction();
                fragmentTransaction.add( R.id.f1,fragmentOne );
                fragmentTransaction.addToBackStack( null ).commit();

            }
        } );

        if (getArguments()!=null)
        {
            String recievedOneData=getArguments().getString( "two" );
            data2=new ArrayList<>();
            adapter=new ArrayAdapter<String>(getActivity(),R.layout.textview_two,data2);
            data2.add( recievedOneData );
            adapter.notifyDataSetChanged();


        }
        return view;


    }
}

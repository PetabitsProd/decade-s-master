package fr.agopiantexier.decadesmaster;

import android.content.Context;
import android.content.Intent;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.TextView;

import java.util.List;

public class PlaylistAdapter extends BaseAdapter {
    private String TAG = "PlaylistAdapter";
    private Context context;
    private List<String> list;


    public PlaylistAdapter(List<String> list, Context context){
        this.context = context;
        this.list = list;
    }

    public Context getContext() {
        return context;
    }

    public void setContext(Context context) {
        this.context = context;
    }

    @Override
    public int getCount() {
        return this.list.size();
    }

    @Override
    public Object getItem(int position) {
        return this.list.get(position);
    }

    @Override
    public long getItemId(int position) {
        return position;
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        LayoutInflater inflater =
                context.getSystemService(LayoutInflater.class);
        View v = inflater.inflate(R.layout.playlist_element, parent, false);

        TextView txt = v.findViewById(R.id.playlistName);
        txt.setText(list.get(position));
        v.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(context, GameActivity.class);
                intent.putExtra("room", list.get(position));
                context.startActivity(intent);

            }
        });

        return v;
    }
}

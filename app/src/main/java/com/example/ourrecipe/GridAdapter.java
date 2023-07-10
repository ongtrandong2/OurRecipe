package com.example.ourrecipe;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ImageView;
import android.widget.TextView;

public class GridAdapter extends BaseAdapter {
    Context context;
    int images[];
    String names[];
    LayoutInflater inflater;

    public GridAdapter(Context applicationContext, int[] images, String[] names) {
        this.context = applicationContext;
        this.images = images;
        this.names = names;
        inflater = (LayoutInflater.from(applicationContext));
    }

    @Override
    public int getCount() {
        return images.length;
    }

    @Override
    public Object getItem(int i) {
        return null;
    }

    @Override
    public long getItemId(int i) {
        return 0;
    }

    @Override
    public View getView(int i, View view, ViewGroup viewGroup) {
        view = inflater.inflate(R.layout.grid_item_user, null); // inflate the layout
        ImageView image = (ImageView) view.findViewById(R.id.gridItemImage); // get the reference of ImageView
        TextView name = (TextView) view.findViewById(R.id.gridItemName);
        image.setImageResource(images[i]); // set logo images
        name.setText(names[i]);
        return view;
    }
}

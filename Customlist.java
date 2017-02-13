package com.docto.subham;

/**
 * Created by Subham Lenka on 26-10-2016.
 */

import android.app.Activity;
import android.graphics.Typeface;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.ImageView;
import android.widget.TextView;

/**
 * Created by Subham Lenka on 29-03-2016.
 */
public class Customlist extends ArrayAdapter<String> {
    private final Activity context;
    private final String[] web;
    private final Integer[] imageId;
    public Customlist(Activity context,
                      String[] web, Integer[] imageId) {
        super(context, R.layout.placelist, web);
        this.context = context;
        this.web = web;
        this.imageId = imageId;

    }
    @Override
    public View getView(int position, View view, ViewGroup parent) {
        LayoutInflater inflater = context.getLayoutInflater();
        View rowView= inflater.inflate(R.layout.placelist, null, true);

        TextView txtTitle = (TextView) rowView.findViewById(R.id.tt);

        ImageView imageView = (ImageView) rowView.findViewById(R.id.ig);
        txtTitle.setText(web[position]);

        imageView.setImageResource(imageId[position]);
        return rowView;
    }

}

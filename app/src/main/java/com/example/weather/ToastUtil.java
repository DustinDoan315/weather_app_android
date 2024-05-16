package com.example.weather;

import android.content.Context;
import android.util.Log;
import android.view.Gravity;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.TextView;
import android.widget.Toast;

public class ToastUtil  {

    public static void showCustomToast(Context context,String message) {

        LayoutInflater inflater = LayoutInflater.from(context);
        View layout = inflater.inflate(R.layout.custom_toast, null);

        TextView text = layout.findViewById(R.id.custom_toast_text);
        text.setText(message);

        Log.d("LOG", "showCustomToast: "+ message);

        // ImageView image = layout.findViewById(R.id.image);
        // image.setImageResource(R.drawable.ic_toast_icon); // Uncomment if you want to set an image

        Toast toast = new Toast(context.getApplicationContext());
        toast.setGravity(Gravity.CENTER_VERTICAL, 0, 0);
        toast.setDuration(Toast.LENGTH_LONG);
        toast.setView(layout);
        toast.show();
    }
}

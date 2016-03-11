package up.ua.edu.universityprograms;

import android.content.Context;
import android.content.Intent;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by graysonwebster on 3/4/16.
 */
public final class LargeAdapter extends RecyclerView.Adapter<LargeAdapter.ViewHolder> {
    private static final int SIZE = 1000;
    private final List<String> items;


    public static LargeAdapter newInstance(Context context) {
        List<String> items = new ArrayList<>();
        String format = context.getString(R.string.item_string);
        for (int i = 0; i < SIZE; i++) {
            items.add(String.format(format, i + 1));
        }
        return new LargeAdapter(items);
    }

    private LargeAdapter(List<String> items) {
        this.items = items;
    }

    @Override
    public ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(android.R.layout.simple_list_item_1, parent, false);
        return ViewHolder.newInstance(view);
    }

    @Override
    public void onBindViewHolder(ViewHolder holder, int position) {
        String text = items.get(position);
        holder.setItem(text + " " + position);
    }

    @Override
    public int getItemCount() {
        return items.size();
    }

    public static class ViewHolder extends RecyclerView.ViewHolder
            implements View.OnClickListener {
        final private String TAG = "edu.ua.up";
        private String mItem;
        private TextView mTextView;

        public static ViewHolder newInstance(View itemView) {
            TextView textView = (TextView) itemView.findViewById(android.R.id.text1);
            return new ViewHolder(textView);
        }

        public ViewHolder(View view) {
            super(view);
            view.setOnClickListener(this);
            mTextView = (TextView) view;
        }

        public void setItem(String item) {
            mItem = item;
            mTextView.setText(item);
        }

        @Override
        public void onClick(View view) {
            Log.d(TAG, "onClick " + getLayoutPosition() + " " + mItem);
            Intent intent = new Intent(view.getContext(), EventActivity.class);
            intent.putExtra(Intent.EXTRA_TEXT, mItem);
            view.getContext().startActivity(intent);
        }
    }

}

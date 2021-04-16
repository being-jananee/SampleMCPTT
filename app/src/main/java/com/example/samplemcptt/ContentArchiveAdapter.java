package com.example.samplemcptt;

import android.content.Context;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageButton;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import java.io.File;
import java.io.IOException;
import java.util.List;

public class ContentArchiveAdapter extends RecyclerView.Adapter {
    private final String LOG_TAG = ContentArchiveAdapter.class.getSimpleName();
    private List<ContentArchiveTemplate> dataset;
    Context mContext;
    int total_types;
    private OnContentListener mOnContentListener;
    public interface OnContentListener {
        void onContentClick(String fileclicked) throws IOException;
    }
    public static class FileTypeViewHolder extends RecyclerView.ViewHolder implements View.OnClickListener{

        TextView filename;
        TextView filedesc;
        TextView lastmodified;
        ImageButton fileicon;
        OnContentListener onContentListener;

        public FileTypeViewHolder(View itemView,OnContentListener onContentListener){
            super(itemView);
            this.filename = (TextView) itemView.findViewById(R.id.textViewFileName);
            this.filedesc =(TextView) itemView.findViewById(R.id.textViewFileDesc);
            this.lastmodified = (TextView) itemView.findViewById(R.id.textViewLastModified);
            this.fileicon = (ImageButton)itemView.findViewById(R.id.imageButtonFileIcon);
            this.onContentListener = onContentListener;
            itemView.setOnClickListener(this);
        }
        @Override
        public void onClick(View v) {
            try {
                onContentListener.onContentClick(this.filename.getText().toString());
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
    }

    public ContentArchiveAdapter(List<ContentArchiveTemplate> data,Context context,OnContentListener onContentListener){
        this.dataset = data;
        this.mContext = context;
        total_types = data.size();
       this.mOnContentListener = onContentListener;
        Log.d(LOG_TAG, "ContentArchiveAdapter: "+total_types);
    }
    @NonNull
    @Override
    public RecyclerView.ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view;
        view = LayoutInflater.from(parent.getContext()).inflate(R.layout.content_archive_item, parent, false);
        return new ContentArchiveAdapter.FileTypeViewHolder(view,mOnContentListener);

    }

    @Override
    public void onBindViewHolder(@NonNull RecyclerView.ViewHolder holder, int position) {
    ContentArchiveTemplate obj = dataset.get(position);
        ((ContentArchiveAdapter.FileTypeViewHolder)holder).filename.setText(obj.filename);
        ((ContentArchiveAdapter.FileTypeViewHolder)holder).fileicon.setImageResource(obj.fileicon);
        ((ContentArchiveAdapter.FileTypeViewHolder)holder).filedesc.setText(obj.filedescription);
        ((ContentArchiveAdapter.FileTypeViewHolder)holder).lastmodified.setText(obj.lastmodifiedat);
    }

    @Override
    public int getItemCount() {
        return this.dataset.size();
    }
}

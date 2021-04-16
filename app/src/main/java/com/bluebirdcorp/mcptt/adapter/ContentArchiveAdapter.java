package com.bluebirdcorp.mcptt.adapter;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageButton;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.bluebirdcorp.mcptt.R;
import com.bluebirdcorp.mcptt.entity.ContentArchiveTemplate;

import java.io.IOException;
import java.util.List;

public class ContentArchiveAdapter extends RecyclerView.Adapter {

    private List<ContentArchiveTemplate> contentArchiveTemplates;
    Context mContext;
    int total_types;
    private OnContentListener mOnContentListener;

    public interface OnContentListener {
        void onContentClick(String fileName) throws IOException;
    }

    public static class FileTypeViewHolder extends RecyclerView.ViewHolder implements View.OnClickListener{
        TextView filename;
        TextView fileDescription;
        TextView lastModified;
        ImageButton fileIcon;
        OnContentListener onContentListener;

        public FileTypeViewHolder(View itemView,OnContentListener onContentListener){
            super(itemView);
            this.filename = (TextView) itemView.findViewById(R.id.textViewFileName);
            this.fileDescription =(TextView) itemView.findViewById(R.id.textViewFileDesc);
            this.lastModified = (TextView) itemView.findViewById(R.id.textViewLastModified);
            this.fileIcon = (ImageButton)itemView.findViewById(R.id.imageButtonFileIcon);
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
        this.contentArchiveTemplates = data;
        this.mContext = context;
        total_types = data.size();
       this.mOnContentListener = onContentListener;
    }

    @NonNull
    @Override
    public RecyclerView.ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view;
        view = LayoutInflater.from(parent.getContext()).inflate(R.layout.content_archive_item, parent, false);
        return new FileTypeViewHolder(view,mOnContentListener);
    }

    @Override
    public void onBindViewHolder(@NonNull RecyclerView.ViewHolder holder, int position) {
    ContentArchiveTemplate obj = contentArchiveTemplates.get(position);
        ((FileTypeViewHolder)holder).filename.setText(obj.fileName);
        ((FileTypeViewHolder)holder).fileIcon.setImageResource(obj.fileIcon);
        ((FileTypeViewHolder)holder).fileDescription.setText(obj.fileDescription);
        ((FileTypeViewHolder)holder).lastModified.setText(obj.lastModified);
    }

    @Override
    public int getItemCount() {
        return this.contentArchiveTemplates.size();
    }
}

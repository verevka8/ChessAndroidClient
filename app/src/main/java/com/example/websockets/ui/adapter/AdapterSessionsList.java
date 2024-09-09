package com.example.websockets.ui.adapter;


import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.example.websockets.R;
import com.example.websockets.networkClient.httpClient.entity.response.SessionInfo;

import java.util.List;


public class AdapterSessionsList extends RecyclerView.Adapter<AdapterSessionsList.ViewHolderSession> {

    private final List<SessionInfo> sessions;
    private final String SESSION_ID_PREFIX = "Session id: ";

    public AdapterSessionsList(List<SessionInfo> sessions){
        this.sessions = sessions;
    }

    @NonNull
    @Override
    public ViewHolderSession onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.item_open_session, parent, false);
        return new ViewHolderSession(view);
    }

    @Override
    public void onBindViewHolder(@NonNull ViewHolderSession holder, int position) {
        ((TextView) holder.itemView.findViewById(R.id.SessionIdTextView)).setText(SESSION_ID_PREFIX + sessions.get(position).getSessionId());
    }

    @Override
    public int getItemCount() {
        return sessions.size();
    }

    public static class ViewHolderSession extends RecyclerView.ViewHolder {

        public ViewHolderSession(View itemView) {
            super(itemView);
        }
    }
}
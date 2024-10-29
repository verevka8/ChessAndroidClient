package com.example.chessgame.ui.adapter;


import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;


import com.example.chessgame.R;
import com.example.chessgame.network.httpClient.entity.response.SessionInfo;

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
        holder.itemView.findViewById(R.id.JoinSessionButton).setOnClickListener(view -> {

        });
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
package com.example.pushfirebase;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import java.util.List;


public class UserAdapter extends RecyclerView.Adapter<UserAdapter.UserViewHolder>{
    private List<User> mListUser;
    private IClickListener mIclick;
    public interface IClickListener{
        void onClickUpdateItem(User user);
    }

    public UserAdapter(List<User> mListUser) {
        this.mListUser = mListUser;
    }

    @NonNull
    @Override
    public UserViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.item_user,parent,false);
        return new UserViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull UserViewHolder holder, int position) {
            User user = mListUser.get(position);
            if (user == null){
                return;
            }
            holder.tvid.setText("Ma Thuoc: " + user.getId());
            holder.tvname.setText("Ten Thuoc:  " + user.getName());
    }

    @Override
    public int getItemCount() {
        if(mListUser != null){
            return mListUser.size();
        }
        return 0;
    }

    public class UserViewHolder extends RecyclerView.ViewHolder{
        private TextView tvid,tvname;
        public UserViewHolder(@NonNull View itemView) {
            super(itemView);
            tvid = itemView.findViewById(R.id.tv_id);
            tvname= itemView.findViewById(R.id.tv_name);
        }
    }
}

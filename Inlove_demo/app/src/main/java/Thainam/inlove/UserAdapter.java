package Thainam.inlove;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.ramotion.foldingcell.FoldingCell;

import java.util.List;

public class UserAdapter extends  RecyclerView.Adapter<UserAdapter.UserViewHolder>{
    private List<User> mListUser;

    public void setData(List<User>list){
        this.mListUser=list;
        notifyDataSetChanged();
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
        if(user == null){
            return;
        }
        holder.tvUsername.setText(user.getUsername());
        holder.tvAddress.setText(user.getAddress());
//        holder.foldingCell.setOnClickListener(new View.OnClickListener() {
//            @Override
//            public void onClick(View v) {
//                holder.foldingCell.toggle(false);
//            }
//        });
        holder.tvUsername.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Toast.makeText(v.getContext(),user.getUsername(),Toast.LENGTH_SHORT).show();
            }
        });
        holder.tvAddress.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Toast.makeText(v.getContext(),user.getAddress(),Toast.LENGTH_SHORT).show();
            }
        });

    }

    @Override
    public int getItemCount() {
        if(mListUser != null){
            return mListUser.size();
        }
        return 0;
    }

    public class UserViewHolder extends RecyclerView.ViewHolder{
        public TextView tvUsername;
        public TextView tvAddress;
//        private FoldingCell foldingCell;


        public UserViewHolder(@NonNull View itemView) {
            super(itemView);
//            foldingCell = itemView.findViewById(R.id.folding_cell);
            tvUsername = itemView.findViewById(R.id.tv_username);
            tvAddress = itemView.findViewById(R.id.tv_address);


        }
    }
}

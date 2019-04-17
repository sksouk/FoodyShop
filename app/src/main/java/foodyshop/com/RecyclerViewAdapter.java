package foodyshop.com;


import android.content.Context;
import android.support.annotation.NonNull;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageButton;
import android.widget.ImageView;
import android.widget.TextView;

import java.util.List;

public class RecyclerViewAdapter extends RecyclerView.Adapter<RecyclerViewAdapter.MyViewHolderOrder> {
    private Context mContext_order ;
    private List<recyclerview_class> mData_order ;

    public RecyclerViewAdapter(Context mContext_order, List<recyclerview_class> mData_order) {
        this.mContext_order = mContext_order;
        this.mData_order = mData_order;
    }

    @NonNull
    @Override
    public RecyclerViewAdapter.MyViewHolderOrder onCreateViewHolder(@NonNull ViewGroup viewGroup, int i) {
        View view;
        LayoutInflater mInflater = LayoutInflater.from(mContext_order);
        view = mInflater.inflate(R.layout.cardview,viewGroup,false);
        return new RecyclerViewAdapter.MyViewHolderOrder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull RecyclerViewAdapter.MyViewHolderOrder holder, final int position) {
        holder.tv_title.setText(mData_order.get(position).getTxttt());
        holder.tv_ms1.setText(mData_order.get(position).getTxtms1());
        holder.tv_ms2.setText(mData_order.get(position).getTxtms2());
        holder.tv_ms3.setText(mData_order.get(position).getTxtms3());

    }

    @Override
    public int getItemCount() {
        return mData_order.size();
    }

    public static class MyViewHolderOrder extends RecyclerView.ViewHolder {

        TextView tv_title;
        TextView tv_ms1;
        TextView tv_ms2;
        TextView tv_ms3;

        public MyViewHolderOrder(View itemView) {
            super(itemView);

            tv_title = (TextView) itemView.findViewById(R.id.txttt);
            tv_ms1 = (TextView) itemView.findViewById(R.id.txtms1);
            tv_ms2 = (TextView) itemView.findViewById(R.id.txtms2);
            tv_ms3 = (TextView) itemView.findViewById(R.id.txtms3);
        }
    }
}

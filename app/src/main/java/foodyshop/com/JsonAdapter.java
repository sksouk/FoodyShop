package foodyshop.com;
import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import java.util.List;

/**
 * Created by ankit on 27/10/17.
 */

public class JsonAdapter extends RecyclerView.Adapter<JsonAdapter.ViewHolder> {

    private Context context;
    private List<getjson> list;

    public JsonAdapter(Context context, List<getjson> list) {
        this.context = context;
        this.list = list;
    }

    @Override
    public ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View v = LayoutInflater.from(context).inflate(R.layout.cardview, parent, false);
        return new ViewHolder(v);
    }

    @Override
    public void onBindViewHolder(ViewHolder holder, int position) {
        getjson data = list.get(position);

        holder.textTitle.setText(data.getName());
        holder.textRating.setText(String.valueOf(data.getId()));
        holder.textYear.setText(String.valueOf(data.getPrice()));
        holder.txtms3.setText(data.getPhong());

    }

    @Override
    public int getItemCount() {
        return list.size();
    }

    public class ViewHolder extends RecyclerView.ViewHolder {
        public TextView textTitle, textRating, textYear, txtms3;

        public ViewHolder(View itemView) {
            super(itemView);

            textTitle = itemView.findViewById(R.id.txttt);
            textRating = itemView.findViewById(R.id.txtms1);
            textYear = itemView.findViewById(R.id.txtms2);
            txtms3 = itemView.findViewById(R.id.txtms3);
        }
    }

}
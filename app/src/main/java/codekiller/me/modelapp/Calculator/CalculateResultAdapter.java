package codekiller.me.modelapp.Calculator;

import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import java.util.List;
import java.util.Map;

import codekiller.me.modelapp.R;

/**
 * Created by Lollipop on 2018/3/17.
 */

public class CalculateResultAdapter extends RecyclerView.Adapter<CalculateResultAdapter.ViewHolder> {
    private List<Map<String, Double>> results;
    private Context context;

    public CalculateResultAdapter(List<Map<String, Double>> results, Context context){
        this.results = results;
        this.context = context;
    }

    @Override
    public ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        return new ViewHolder(LayoutInflater.from(context).inflate(R.layout.calculate_result_item, parent, false));
    }

    @Override
    public void onBindViewHolder(ViewHolder holder, int position) {
        Map<String, Double> map = results.get(position);
        holder.month.setText(String.valueOf(Math.round(map.get("qc"))));
        holder.principalInterest.setText(String.valueOf(map.get("chbx")));
        holder.interest.setText(String.valueOf(map.get("chlx")));
        holder.principal.setText(String.valueOf(map.get("chbj")));
        holder.lastPrincipal.setText(String.valueOf(map.get("sybj")));
    }

    @Override
    public int getItemCount() {
        return results.size();
    }

    public class ViewHolder extends RecyclerView.ViewHolder{
        private TextView month;
        private TextView principalInterest;
        private TextView interest;
        private TextView principal;
        private TextView lastPrincipal;

        public ViewHolder(View itemView) {
            super(itemView);
            month = itemView.findViewById(R.id.month);
            principalInterest = itemView.findViewById(R.id.principal_interest);
            interest = itemView.findViewById(R.id.interest);
            principal = itemView.findViewById(R.id.principal);
            lastPrincipal = itemView.findViewById(R.id.last_principal);
        }
    }
}

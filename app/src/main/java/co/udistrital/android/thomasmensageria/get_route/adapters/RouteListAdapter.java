package co.udistrital.android.thomasmensageria.get_route.adapters;

import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;
import co.udistrital.android.thomasmensageria.R;
import co.udistrital.android.thomasmensageria.entities.Route;

/**
 * Created by wisuarez on 06/03/2018.
 */

public class RouteListAdapter extends RecyclerView.Adapter<RouteListAdapter.ViewHolder> {

    private List<Route> routeList;
    private OnItemClickListener onItemClickListener;

    public RouteListAdapter(List<Route> routeList, OnItemClickListener onItemClickListener) {
        this.routeList = routeList;
        this.onItemClickListener = onItemClickListener;
    }

    @Override
    public ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.row_route, parent, false);
        return new ViewHolder(view);
    }

    @Override
    public void onBindViewHolder(ViewHolder holder, int position) {
        Route route = routeList.get(position);
        holder.setClickListener(route, onItemClickListener);

        holder.txtGuide.setText(""+route.getId_guia());
        holder.txtClient.setText(""+route.getIdproducto());
        holder.txtAddress.setText(route.getDireccion());
        holder.txtDate.setText(route.getFecha_envio());
    }

    @Override
    public int getItemCount() {
        return routeList.size();
    }

    public void add(Route route) {
        if (!routeList.contains(route)){
            routeList.add(route);
            notifyDataSetChanged();
        }
    }

    public void update(Route route) {
        if (routeList.contains(route)){
            int index =routeList.indexOf(route);
            routeList.set(index, route);
            notifyDataSetChanged();
        }

    }

    public void remove(Route route) {
        if (!routeList.contains(route)){
            routeList.remove(route);
            notifyDataSetChanged();
        }
    }

    static class ViewHolder extends RecyclerView.ViewHolder {

        @BindView(R.id.txtGuide)
        TextView txtGuide;
        @BindView(R.id.txtClient)
        TextView txtClient;
        @BindView(R.id.txtAddress)
        TextView txtAddress;
        @BindView(R.id.txtDate)
        TextView txtDate;
        private View view;

        public ViewHolder(View itemView) {
            super(itemView);
            ButterKnife.bind(this, itemView);
            this.view = itemView;
        }

        private void setClickListener(final Route route, final OnItemClickListener listener){
            view.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    listener.onItemClick(route);
                }
            });
            view.setOnLongClickListener(new View.OnLongClickListener() {
                @Override
                public boolean onLongClick(View view) {
                    listener.onItemLongClick(route);
                    return false;
                }
            });
        }
    }
}

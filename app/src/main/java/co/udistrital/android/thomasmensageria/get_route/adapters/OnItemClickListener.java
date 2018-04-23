package co.udistrital.android.thomasmensageria.get_route.adapters;

import co.udistrital.android.thomasmensageria.entities.Route;

/**
 * Created by wisuarez on 06/03/2018.
 */

public interface OnItemClickListener {
    void onItemClick(Route route);
    void onItemLongClick(Route route);
}

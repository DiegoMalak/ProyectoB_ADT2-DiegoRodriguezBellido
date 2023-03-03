package com.dam.proyectob_adt2_diegorodriguezbellido.rvUtil;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.recyclerview.widget.RecyclerView;

import com.dam.proyectob_adt2_diegorodriguezbellido.R;
import com.dam.proyectob_adt2_diegorodriguezbellido.entity.Terremoto;

import java.util.ArrayList;
import java.util.List;

// Le pongo que extienda RecyclerView.Adapter porque es un adaptador para un RecyclerView
// y le paso como parámetro la clase TerremotoVH, que es la clase que hemos creado para
// mostrar los datos de los terremotos.
public class TerremotoAdapter extends RecyclerView.Adapter<TerremotoAdapter.TerremotoVH> {

    // Creamos un ArrayList de Terremotos.
    private ArrayList<Terremoto> datosTerremoto;

    // Creamos un constructor para pasarle los datos.
    public TerremotoAdapter(ArrayList<Terremoto> datosTerremoto) {
        this.datosTerremoto = datosTerremoto;
    }

    // Creamos una clase interna que extienda de RecyclerView.ViewHolder, que es la clase
    // que nos va a permitir mostrar los datos en el RecyclerView.
    @NonNull
    @Override
    public TerremotoVH onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        // Creamos la vista para poder mostrarla en el RecyclerView.
        // Le pasamos el layout que queremos mostrar, que es el que hemos creado para pasar
        // los datos de los terremotos.
        View v = LayoutInflater.from(parent.getContext())
                .inflate(R.layout.mostrar_terremoto_layout, parent, false);
        // Devolvemos un objeto de la clase TerremotoVH, que es la clase que hemos creado
        // para mostrar los datos de los terremotos.
        return new TerremotoVH(v);
    }

    // Método que se ejecuta cuando se crea el RecyclerView, que es cuando se crea la vista.
    // Aquí es donde se hace el trabajo de volcar los datos a mostrar en el RecyclerView.
    @Override
    public void onBindViewHolder(@NonNull TerremotoVH holder, int position) {
        // Hacemos el binding de los datos, que es volcar la información en el RecyclerView.
        // Aquí es donde se hace el trabajo de volcar los datos a mostrar en el RecyclerView.
        holder.bind(datosTerremoto.get(position));
    }

    // Método que devuelve el tamaño del ArrayList de Terremotos, para saber cuántos
    // elementos tiene y poder mostrarlos en el RecyclerView.
    @Override
    public int getItemCount() {
        // Devolvemos el tamaño del ArrayList de Terremotos para
        // contar cuántos elementos tiene.
        return datosTerremoto.size();
    }

    // Creamos una clase interna que extienda de RecyclerView.ViewHolder, que es la clase
    // que nos va a permitir mostrar los datos en el RecyclerView. Esta clase la creamos
    // para poder mostrar los datos de los terremotos.
    public static class TerremotoVH extends RecyclerView.ViewHolder {
        // TextView tvAdapterInfo;
        TextView tvNomTerre;
        TextView tvMagnitudTerre;
        TextView tvFechaHoraTerre;
        TextView tvNombreLugarTerre;
        TextView tvCoorderenadasTerre;
        TextView tvMuertosTerre;

        // Creamos un constructor para poder inicializar los TextView.
        public TerremotoVH(@Nullable View itemView) {
            super(itemView);
            // tvAdapterInfo = itemView.findViewById(R.id.tvMostrarInfo);
            tvNomTerre = itemView.findViewById(R.id.tvSeeNombreTerremoto);
            tvMagnitudTerre = itemView.findViewById(R.id.tvSeeMagnitudTerremoto);
            tvFechaHoraTerre = itemView.findViewById(R.id.tvSeeFechaHoraTerremoto);
            tvNombreLugarTerre = itemView.findViewById(R.id.tvSeeNombreLugarTerremoto);
            tvCoorderenadasTerre = itemView.findViewById(R.id.tvSeeCoordenadasTerremoto);
            tvMuertosTerre = itemView.findViewById(R.id.tvSeeMuertosTerremoto);
        }

        // Método para hacer el binding de los datos, que es volcar la información en el RecyclerView.
        public void bind(Terremoto terremoto) {
            // tvAdapterInfo.setText(terremoto.toString());
            tvNomTerre.setText(terremoto.getNombre());
            tvMagnitudTerre.setText(String.valueOf(terremoto.getMagnitud()));
            tvFechaHoraTerre.setText(terremoto.getFechaHora());
            tvNombreLugarTerre.setText(terremoto.getLugar());
            tvCoorderenadasTerre.setText(terremoto.getCoordEpicentro());
            tvMuertosTerre.setText(String.valueOf(terremoto.getMuertos()));
        }
    }

    // Método para actualizar los datos del RecyclerView.
    public void setDatosTerremoto(ArrayList<Terremoto> datosTerremoto) {
        // Le pasamos los datos del ArrayList de Terremotos.
        this.datosTerremoto = datosTerremoto;
        // Notificamos que los datos han cambiado, con este método del sistema y
        // se actualiza el RecyclerView.
        notifyDataSetChanged();
    }

    // Creamos un setDatos pero con List ya que las Querys de Room devuelven List.
    public void setDatosTerremotoRoom(List<Terremoto> datosTerremotoList) {
        // Le pasamos los datos del ArrayList de Terremotos.
        this.datosTerremoto = new ArrayList<>(datosTerremotoList);
        // Notificamos que los datos han cambiado.
        notifyDataSetChanged();
    }
}
// Una vez creado el adaptador, tenemos que crear el layout que vamos a usar para mostrar
// los datos de los terremotos en el RecyclerView. Así que nos vamos al main y lo configuramos.
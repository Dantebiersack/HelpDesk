package org.utl.helpdesk;
import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;
import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;
import org.utl.helpdesk.model.Ticket;
import java.util.List;
public class AdapterTicket extends RecyclerView.Adapter<AdapterTicket.ViewHolder> {
    //se agregan despues junto con el constructor
    Context context;
    List<Ticket> ticketList;
    public AdapterTicket(Context context, List<Ticket> items){
        this.context=context;
        this.ticketList = items;
    }
    //adapterTicket View Holder se hace después de que se implementa el método ViewHolder de a continuación
//--------------------tres metodos se autogeneran ____________
    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(context).inflate(R.layout.item_view_ticket,parent,false);
        return new ViewHolder(view);
    }
    @Override
    public void onBindViewHolder(@NonNull ViewHolder holder, int position) {
        Ticket ticket=ticketList.get(position);
        holder.txtIdTicket.setText(String.valueOf(ticket.getIdTicket()));
        holder.txtDispositivo.setText(ticket.getDispositivo());
        holder.txtEstatus.setText(String.valueOf(ticket.getEstatus()));
        holder.txtCategoria.setText(String.valueOf(ticket.getCategoria().getCategoria()));
        holder.txtFecha.setText(String.valueOf(ticket.getFecha()));
    }
    @Override
    public int getItemCount() {
        return ticketList.size();
    }
    // Hasta aqui se generararón automatico
    public static class ViewHolder extends RecyclerView.ViewHolder{
        TextView txtIdTicket;
        TextView txtDispositivo;
        TextView txtEstatus;
        TextView txtCategoria;
        TextView txtFecha;
        public ViewHolder(@NonNull View itemView) {
            super(itemView);
            txtIdTicket=itemView.findViewById(R.id.txtIdTicket);
            txtDispositivo=itemView.findViewById(R.id.txtDispositivo);
            txtEstatus=itemView.findViewById(R.id.txtEstatus);
            txtCategoria=itemView.findViewById(R.id.txtCategoria);
            txtFecha=itemView.findViewById(R.id.txtFecha);
        }
    }
}
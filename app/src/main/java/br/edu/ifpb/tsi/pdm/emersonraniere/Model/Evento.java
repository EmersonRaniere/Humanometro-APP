package br.edu.ifpb.tsi.pdm.emersonraniere.Model;

import java.util.Calendar;

/**
 * Created by emersonraniere on 15/01/16.
 */
public class Evento implements Comparable<Evento>{
    private int id;
    private Calendar data;
    private String acao;

    public Evento(){

    }
    public Evento(int id, Calendar data, String acao){
        this.id = id;
        this.data = data;
        this.acao = acao;
    }
    public Evento getEvento () {
        return this;
    }
    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public Calendar getData() {
        return data;
    }

    public void setData(Calendar data) {
        this.data = data;
    }

    public void setData(long data){
        this.data = Calendar.getInstance();
        this.data.setTimeInMillis(data);
    }

    public String getDataString() {
        return String.format("%d/%d/%d", data.get(Calendar.DAY_OF_MONTH),
                                        data.get(Calendar.MONTH) +1,
                                        data.get(Calendar.YEAR));
    }
    public String getAcao() {
        return acao;
    }

    public void setAcao(String acao) {
        this.acao = acao;
    }

    @Override
    public String toString() {
        StringBuilder sb = new StringBuilder();
        sb.append("Data: " + getDataString());
        sb.append("\n");
        sb.append("Ação: " + getAcao());

        return sb.toString();
    }

    public String toStringActionSend() {
        StringBuilder sb = new StringBuilder();
        sb.append("Eu me sinto muito feliz porque na data: "  + getDataString());
        sb.append(" foi realizada a seguinte ação: " + getAcao());

        return sb.toString();
    }

    @Override
    public int compareTo(Evento another) {
        if (this.getId() < another.getId()){
            return 1;
        }else if (this.getId() == another.getId()){
            return 0;
        }else{
            return -1;
        }
    }

}

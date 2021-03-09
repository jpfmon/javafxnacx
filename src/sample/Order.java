package sample;

public class Order {

    private String referencia;
    private String telefono;
    private String importe;
    private String destinatario;
    private String tipoReem;
    private String prodList1;
    private String prodList2;
    private String prodList3;
    private String prodList4;
    private String prodList5;

    private String direccion;
    private String cp;
    private String poblacion;
    private String provincia;
    private String observaciones1;
    private String observaciones2;
    private String observaciones3;
    private String observaciones4;



    public Order(String referencia, String telefono, String importe, String destinatario, String direccion, String cp, String poblacion, String provincia,String tipoReem,
                 String observaciones1,String observaciones2,String observaciones3,String observaciones4,String prodList1, String prodList2, String prodList3, String prodList4, String prodList5) {
        this.referencia = referencia;
        this.telefono = telefono;
        this.importe = importe;
        this.destinatario = destinatario;

        this.direccion = direccion;
        this.cp = cp;
        this.poblacion = poblacion;
        this.provincia = provincia;
        this.observaciones1 = observaciones1;
        this.observaciones2 = observaciones2;
        this.observaciones3 = observaciones3;
        this.observaciones4 = observaciones4;

        this.tipoReem = tipoReem;
        this.prodList1 = prodList1;
        this.prodList2 = prodList2;
        this.prodList3 = prodList3;
        this.prodList4 = prodList4;
        this.prodList5 = prodList5;
    }

    public String getObservaciones4() {
        return observaciones4;
    }

    public void setObservaciones4(String observaciones4) {
        this.observaciones4 = observaciones4;
    }

    public String getObservaciones3() {
        return observaciones3;
    }

    public void setObservaciones3(String observaciones3) {
        this.observaciones3 = observaciones3;
    }

    public String getObservaciones2() {
        return observaciones2;
    }

    public void setObservaciones2(String observaciones2) {
        this.observaciones2 = observaciones2;
    }

    public String getObservaciones1() {
        return observaciones1;
    }

    public void setObservaciones1(String observaciones1) {
        this.observaciones1 = observaciones1;
    }

    public String getProvincia() {
        return provincia;
    }

    public void setProvincia(String provincia) {
        this.provincia = provincia;
    }

    public String getPoblacion() {
        return poblacion;
    }

    public void setPoblacion(String poblacion) {
        this.poblacion = poblacion;
    }

    public String getCp() {
        return cp;
    }

    public void setCp(String cp) {
        this.cp = cp;
    }

    public String getDireccion() {
        return direccion;
    }

    public void setDireccion(String direccion) {
        this.direccion = direccion;
    }

    public String getReferencia() {
        return referencia;
    }

    public void setReferencia(String referencia) {
        this.referencia = referencia;
    }

    public String getTelefono() {
        return telefono;
    }

    public void setTelefono(String telefono) {
        this.telefono = telefono;
    }

    public String getImporte() {
        return importe;
    }

    public String getDestinatario() {
        return destinatario;
    }

    public void setDestinatario(String destinatario) {
        this.destinatario = destinatario;
    }

    public String getTipoReem() {
        return tipoReem;
    }

    public void setTipoReem(String tipoReem) {
        this.tipoReem = tipoReem;
    }

    public void setImporte(String importe) {
        this.importe = importe;
    }

    public String getProdList1() {
        return prodList1;
    }

    public void setProdList1(String prodList1) {
        this.prodList1 = prodList1;
    }

    public String getProdList2() {
        return prodList2;
    }

    public void setProdList2(String prodList2) {
        this.prodList2 = prodList2;
    }

    public String getProdList3() {
        return prodList3;
    }

    public void setProdList3(String prodList3) {
        this.prodList3 = prodList3;
    }

    public String getProdList4() {
        return prodList4;
    }

    public void setProdList4(String prodList4) {
        this.prodList4 = prodList4;
    }

    public String getProdList5() {
        return prodList5;
    }

    public void setProdList5(String prodList5) {
        this.prodList5 = prodList5;
    }

    @Override
    public String toString() {
        return "Order{" +
                "referencia='" + referencia + '\'' +
                ", telefono='" + telefono + '\'' +
                '}';
    }
}

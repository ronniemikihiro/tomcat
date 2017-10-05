package br.com.tomcat.entity;

import br.com.tomcat.util.DateUtil;
import br.com.tomcat.util.ListUtil;
import br.com.tomcat.util.LocalDateConverter;

import javax.persistence.*;
import javax.persistence.Entity;
import java.time.LocalDate;
import java.util.Date;
import java.util.List;

/**
 * Created by ronnie-msl on 29/09/17.
 * Tabela BD: tb_pedido
 */
@Entity
@Table(name = "tb_pedido")
public class Pedido implements br.com.tomcat.entity.Entity<Long> {

    public static final String TABLE_NAME = "tb_pedido";

    @Id
    @Column(name = "id", unique = true, nullable = false)
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @OneToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "id_atendente", nullable = false)
    private Usuario atendente;

    @OneToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "id_cliente", nullable = false)
    private Cliente cliente;

    @OneToMany(mappedBy = "pedido", fetch = FetchType.LAZY, cascade = CascadeType.ALL, orphanRemoval = true)
    private List<ItemPedidoCliente> listItemPedidoCliente;

    @Override
    public Long getId() {
        return id;
    }

    @Override
    public void setId(Long id) {
        this.id = id;
    }

    public Usuario getAtendente() {
        return atendente;
    }

    public void setAtendente(Usuario atendente) {
        this.atendente = atendente;
    }

    public Cliente getCliente() {
        return cliente;
    }

    public void setCliente(Cliente cliente) {
        this.cliente = cliente;
    }

    public List<ItemPedidoCliente> getListItemPedidoCliente() {
        return listItemPedidoCliente;
    }

    public void setListItemPedidoCliente(List<ItemPedidoCliente> listItemPedidoCliente) {
        this.listItemPedidoCliente = listItemPedidoCliente;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        Pedido pedido = (Pedido) o;

        return id != null ? id.equals(pedido.id) : pedido.id == null;
    }

    @Override
    public int hashCode() {
        return id != null ? id.hashCode() : 0;
    }
}

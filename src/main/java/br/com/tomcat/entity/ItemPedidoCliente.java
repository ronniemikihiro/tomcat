package br.com.tomcat.entity;

import br.com.tomcat.enums.EnumTipoAtendimento;
import br.com.tomcat.util.DateUtil;
import br.com.tomcat.util.LocalDateTimeConverter;

import javax.persistence.*;
import javax.persistence.Entity;
import java.time.LocalDateTime;

/**
 * Created by ronnie-msl on 29/09/17.
 * Tabela BD: tb_item_pedido_cliente
 */
@Entity
@Table(name = "tb_item_pedido_cliente")
public class ItemPedidoCliente implements br.com.tomcat.entity.Entity<Long> {

    public static final String TABLE_NAME = "tb_item_pedido_cliente";

    @Id
    @Column(name = "id", unique = true, nullable = false)
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @OneToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "id_pedido", nullable = false)
    private Pedido pedido;

    @Column(name = "dataHora", nullable = false)
    @Convert(converter = LocalDateTimeConverter.class)
    private LocalDateTime dataHora;

    @Column(name = "quantidade", nullable = false)
    private Long quantidade;

    @OneToOne(fetch = FetchType.EAGER)
    @JoinColumn(name = "id_item_pedido", nullable = false)
    private ItemPedido itemPedido;

    @Column(name = "tipo_atendimento", nullable = false, length = 1)
    @Enumerated(EnumType.STRING)
    private EnumTipoAtendimento enumTipoAtendimento;

    @Override
    public Long getId() {
        return id;
    }

    @Override
    public void setId(Long id) {
        this.id = id;
    }

    public Pedido getPedido() {
        return pedido;
    }

    public void setPedido(Pedido pedido) {
        this.pedido = pedido;
    }

    public LocalDateTime getDataHora() {
        return dataHora;
    }

    public void setDataHora(LocalDateTime dataHora) {
        this.dataHora = dataHora;
    }

    public Long getQuantidade() {
        return quantidade;
    }

    public void setQuantidade(Long quantidade) {
        this.quantidade = quantidade;
    }

    public ItemPedido getItemPedido() {
        return itemPedido;
    }

    public void setItemPedido(ItemPedido itemPedido) {
        this.itemPedido = itemPedido;
    }

    public EnumTipoAtendimento getEnumTipoAtendimento() {
        return enumTipoAtendimento;
    }

    public void setEnumTipoAtendimento(EnumTipoAtendimento enumTipoAtendimento) {
        this.enumTipoAtendimento = enumTipoAtendimento;
    }

    public String getDataHoraFormatPT() {
        return DateUtil.dateHourFormatPT(dataHora);
    }

    public Double getTotalPreco() {
        return quantidade * itemPedido.getPreco();
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        ItemPedidoCliente that = (ItemPedidoCliente) o;

        return id != null ? id.equals(that.id) : that.id == null;
    }

    @Override
    public int hashCode() {
        return id != null ? id.hashCode() : 0;
    }
}
